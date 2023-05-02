package ru.hoprik.register.block;

import javafx.beans.property.StringProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AnimationBlock extends Block {

    public static IntegerProperty ANIMATE = IntegerProperty.create("animation", 0,3);

    public AnimationBlock(Properties properties, int min, int max) {
        super(properties);
    }

    public void setAnimate(BlockState blockState, World level, BlockPos pos, int animation){
        level.setBlockState(pos, blockState.with(ANIMATE, animation));
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ANIMATE);
        super.fillStateContainer(builder);
    }
}
