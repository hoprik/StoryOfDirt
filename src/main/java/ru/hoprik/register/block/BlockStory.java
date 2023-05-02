package ru.hoprik.register.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.Items.ItemStory;

import java.util.function.Supplier;

public class BlockStory {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HoprikStory.MODID);


    public static RegistryObject<Block> stone = RegisterBlock("stone", ()-> new AnimationBlock(AbstractBlock.Properties.create(Material.GOURD).harvestLevel(6).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(12F), 0,3));


    private static <T extends Block> RegistryObject<T> RegisterBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        RegisterBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void RegisterBlockItem(String name, Supplier<T> block){
        ItemStory.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void RegisterBlock(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
