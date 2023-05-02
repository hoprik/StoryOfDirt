package ru.hoprik.story.hero;

import jdk.nashorn.internal.ir.BlockStatement;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.Items.ItemStory;
import ru.hoprik.register.entity.EntityStory;
import ru.hoprik.register.entity.entity.Entity.PlayerStory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;

public class StoryFunction {

    public static void SayPlayer(PlayerEntity player, String nameHero, String text){
        player.sendMessage(new StringTextComponent(TextFormatting.GOLD+"["+nameHero+"] "+TextFormatting.RESET+text), player.getUniqueID());
    }

    public static void SayPlayerAll(PlayerEntity player, String nameHero, String text){
        for (PlayerEntity player1: player.world.getPlayers()){
            player1.sendMessage(new StringTextComponent(TextFormatting.GOLD+"["+nameHero+"] "+TextFormatting.RESET+text), player1.getUniqueID());
        }
    }

    public static void unlockRecipe(PlayerEntity player, String nameCraft) {
        Collection<IRecipe<?>> recipes = new ArrayList<>();
        if (!player.world.isRemote) {
            for (IRecipe<?> recipe : player.getServer().getRecipeManager().getRecipes()) {
                if (recipe.getId().toString().equals(HoprikStory.MODID + ":" + nameCraft)) {
                    recipes.add(recipe);
                }
            }
            player.unlockRecipes(recipes);
        }
    }

    public static void ShowWaitScreenAll(PlayerEntity player, String text){
        for (PlayerEntity human: player.world.getPlayers()){
            human.addPotionEffect(new EffectInstance(Effects.BLINDNESS,  100, 255, false,false));
            for(ServerPlayerEntity serverPlayerEntity: human.getServer().getPlayerList().getPlayers()){
                if (human.getName().toString().equals(serverPlayerEntity.getName().toString())) {
                    serverPlayerEntity.connection.sendPacket(new STitlePacket(STitlePacket.Type.TITLE, new StringTextComponent(text)));
                }
            }
        }
    }


    public static void TeleportEntityAll(PlayerEntity player, Vector3d vector3d){
        World world = player.world;
        for (double y = vector3d.y; y<=255; y++){
            if (world.getBlockState(new BlockPos(vector3d.x, y, vector3d.z)) == Blocks.AIR.getDefaultState()){
                for(ServerPlayerEntity serverPlayerEntity: player.getServer().getPlayerList().getPlayers()){
                    serverPlayerEntity.teleport(Objects.requireNonNull(player.world.getServer().getWorld(player.world.getDimensionKey())),vector3d.x, y, vector3d.z, player.rotationYaw, player.rotationPitch );
                }
                break;
            }
        }
    }

    public static void GenerateTempel(World world, int start_x, int start_y, int start_z){
        BlockState obsidian = Blocks.OBSIDIAN.getDefaultState();
        BlockState iron_bars = Blocks.IRON_BARS.getDefaultState();
        BlockState block_bricks = Blocks.STONE_BRICKS.getDefaultState();
        BlockState air = Blocks.AIR.getDefaultState();
        for (int x = 0; x<=16; x++){
            for (int z = 0; z<=7; z++){
                for (int y = 0; y<=4; y++){
                    world.setBlockState(new BlockPos(start_x+x,start_y+y,start_z+z), block_bricks);
                }
            }
        }
        for (int x = 0; x<=16; x++){
            for (int z = 0; z<=7; z++){
                world.setBlockState(new BlockPos(start_x+x,start_y,start_z+z), obsidian);
            }
        }
        for (int x = 1; x<=15; x++){
            for (int z = 1; z<=6; z++){
                for (int y = 1; y<=3; y++){
                    world.setBlockState(new BlockPos(start_x+x,start_y+y,start_z+z), air);
                }
            }
        }
        for (int x = 3; x<=13; x+=2){
            world.setBlockState(new BlockPos(start_x+x,start_y+2,start_z), iron_bars);
            world.setBlockState(new BlockPos(start_x+x,start_y+3,start_z), iron_bars);
        }
        for (int x = 3; x<=13; x+=2){
            world.setBlockState(new BlockPos(start_x+x,start_y+2,start_z+7), iron_bars);
            world.setBlockState(new BlockPos(start_x+x,start_y+3,start_z+7), iron_bars);
        }
        for (int z = 1; z<=6; z++){
            for (int y = 1; y<=3; y++){
                world.setBlockState(new BlockPos(start_x+16-4, start_y+y,start_z+z), block_bricks);
            }
        }
        for (int z = 1; z<=6; z++){
            for (int y = 1; y<=3; y++){
                world.setBlockState(new BlockPos(start_x+4, start_y+y,start_z+z), block_bricks);
            }
        }
        for (int z = 3; z<=4; z++){
            for (int y = 1; y<=2; y++){
                world.setBlockState(new BlockPos(start_x+4, start_y+y,start_z+z), air);
            }
        }
        for (int z = 3; z<=4; z++){
            for (int y = 1; y<=2; y++){
                world.setBlockState(new BlockPos(start_x+16-4, start_y+y,start_z+z), obsidian);
            }
        }
        world.setBlockState(new BlockPos(start_x+5, start_y+1, start_z+1), Blocks.LANTERN.getDefaultState());
        world.setBlockState(new BlockPos(start_x+5, start_y+1, start_z+7-1), Blocks.LANTERN.getDefaultState());
        world.setBlockState(new BlockPos(start_x+16-5, start_y+1, start_z+1), Blocks.LANTERN.getDefaultState());
        world.setBlockState(new BlockPos(start_x+16-5, start_y+1, start_z+7-1), Blocks.LANTERN.getDefaultState());

        world.setBlockState(new BlockPos(1296, 41, 2460), air);
        world.setBlockState(new BlockPos(1296, 41, 2461), air);
        world.setBlockState(new BlockPos(1295, 41, 2460), air);

        BlockState blockState = Blocks.CHEST.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.WEST).with(BlockStateProperties.CHEST_TYPE, ChestType.RIGHT);

        world.setBlockState(new BlockPos(1309, 38,2460), blockState);
        world.setBlockState(new BlockPos(1309, 38,2461), Blocks.CHEST.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.WEST).with(BlockStateProperties.CHEST_TYPE, ChestType.LEFT));

        BlockPos pos = new BlockPos(1309, 38,2460);

        ChestBlock chestBlock = (ChestBlock) Blocks.CHEST;
        TileEntity tileEntity = chestBlock.createTileEntity(chestBlock.getDefaultState(), world);

        if (tileEntity instanceof ChestTileEntity) {
            ChestTileEntity inventoryChest = (ChestTileEntity) blockState.createTileEntity(world);

            Item gold = Items.GOLD_INGOT.getItem();
            Item obsidianItem = Items.OBSIDIAN.getItem();
            Item diamond = Items.DIAMOND.getItem();
            Item coal = Items.COAL_BLOCK.getItem();
            Item iron = Items.IRON_BLOCK.getItem();

            inventoryChest.setInventorySlotContents(0, new ItemStack(gold, 64));
            inventoryChest.setInventorySlotContents(1, new ItemStack(gold, 64));
            inventoryChest.setInventorySlotContents(2, new ItemStack(gold, 64));
            inventoryChest.setInventorySlotContents(3, new ItemStack(gold, 32));

            inventoryChest.setInventorySlotContents(4, new ItemStack(obsidianItem, 64));
            inventoryChest.setInventorySlotContents(5, new ItemStack(obsidianItem, 64));
            inventoryChest.setInventorySlotContents(6, new ItemStack(obsidianItem, 64));
            inventoryChest.setInventorySlotContents(7, new ItemStack(obsidianItem, 32));

            inventoryChest.setInventorySlotContents(8, new ItemStack(diamond, 64));
            inventoryChest.setInventorySlotContents(9, new ItemStack(diamond, 64));
            inventoryChest.setInventorySlotContents(10, new ItemStack(diamond, 64));
            inventoryChest.setInventorySlotContents(11, new ItemStack(diamond, 32));

            inventoryChest.setInventorySlotContents(12, new ItemStack(coal, 64));
            inventoryChest.setInventorySlotContents(13, new ItemStack(coal, 64));
            inventoryChest.setInventorySlotContents(14, new ItemStack(coal, 64));
            inventoryChest.setInventorySlotContents(15, new ItemStack(coal, 32));

            inventoryChest.setInventorySlotContents(16, new ItemStack(iron, 64));
            inventoryChest.setInventorySlotContents(17, new ItemStack(iron, 64));
            inventoryChest.setInventorySlotContents(18, new ItemStack(iron, 64));
            inventoryChest.setInventorySlotContents(19, new ItemStack(iron, 32));

            world.setBlockState(pos, chestBlock.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.WEST).with(BlockStateProperties.CHEST_TYPE, ChestType.RIGHT));
            world.setTileEntity(pos, inventoryChest);

        }
        //Hero hero = new Hero(new PlayerStory(EntityStory.keeper_entity.get(), world));

    }

    public static void SpawnPointAll(PlayerEntity player, BlockPos pos){
        for (PlayerEntity entity: player.world.getPlayers()){
            entity.setBedPosition(pos);
        }
    }

    public static void ItemSay(PlayerEntity player){
        for (PlayerEntity entity: player.world.getPlayers()) {
            for (int slot = 0; slot <= entity.inventory.getSizeInventory(); slot++) {
                if (entity.inventory.getStackInSlot(slot).getItem() == ItemStory.stoneHero.get()) {
                    entity.replaceItemInInventory(slot, new ItemStack(ItemStory.stoneHeroSay.get()));
                }
            }
        }
    }
    public static void ItemDisbleSay(PlayerEntity player){
        for (PlayerEntity entity: player.world.getPlayers()) {
            for (int slot = 0; slot <= entity.inventory.getSizeInventory(); slot++) {
                if (entity.inventory.getStackInSlot(slot).getItem() == ItemStory.stoneHeroSay.get()) {
                    entity.replaceItemInInventory(slot, new ItemStack(ItemStory.stoneHero.get()));
                }
            }
        }
    }
}

