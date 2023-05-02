package ru.hoprik.story.scripts;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.Items.ItemStory;
import ru.hoprik.register.entity.EntityStory;
import ru.hoprik.register.entity.entity.Entity.PlayerStory;
import ru.hoprik.story.hero.Executer;
import ru.hoprik.story.hero.Hero;
import ru.hoprik.story.hero.StoryFunction;
import ru.hoprik.util.Random;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = HoprikStory.MODID)
public class Script_five {
    static boolean isMove = true;
    static boolean bossFight = false;
    static boolean isPossibleKill = false;
    static PlayerEntity playerTrigger;
    static Hero keeper;
    static int progress = 100;
    static BossBar bossBar;
    static int killCount = 4;
    static ChestTileEntity inventoryChest;
    static boolean isSpawn = false;
    static boolean isChangeTrigger = false;
    @SubscribeEvent
    public static void start(LivingEvent.LivingUpdateEvent event){
        if (event.getEntity() instanceof PlayerEntity) {
            playerTrigger = (PlayerEntity) event.getEntity();
            PlayerEntity player = playerTrigger;
            if (1299 <= player.getPosX() && player.getPosX() <= 1305 && 38 <= player.getPosY() && player.getPosY() <= 40 && 2458 <= player.getPosZ() && player.getPosZ() <= 2463 && isMove) {
                HoprikStory.LOGGER.info(playerTrigger);

                Executer executer = new Executer();
                executer.addS(() -> {
                    BlockState obsidian = Blocks.OBSIDIAN.getDefaultState();
                    if (!isSpawn) {
                        keeper = new Hero(new PlayerStory(EntityStory.keeper_entity.get(), player.world), new BlockPos(1302, 38, 2461.00F));
                        isSpawn = true;
                    }
                    StoryFunction.TeleportEntityAll(playerTrigger, new Vector3d(player.getPosX() + 1, player.getPosY(), player.getPosZ()));
                    for (int z = 3; z <= 4; z++) {
                        for (int y = 1; y <= 2; y++) {
                            player.world.setBlockState(new BlockPos(1294 + 4, 37 + y, 2457 + z), obsidian);
                        }
                    }
                }, 1);
                executer.addS(() -> StoryFunction.SayPlayerAll(playerTrigger, "Хранитель вечности", "Кто здесь?"), 1);
                executer.add(() -> StoryFunction.SayPlayerAll(playerTrigger, "Хранитель вечности", "Хахаххахаха наимный человек"), 2500);
                executer.add(() -> {
                    playerTrigger.getServer().setDifficultyForAllWorlds(Difficulty.NORMAL, true);
                    StoryFunction.SayPlayerAll(playerTrigger, "Хранитель вечности", "Я тебя уничтожу!");
                    StoryFunction.SpawnPointAll(playerTrigger, new BlockPos(playerTrigger.getPosY(), playerTrigger.getPosY(), playerTrigger.getPosZ()));
                    bossFight = true;
                    bossBar = new BossBar(TextFormatting.BOLD + "" + TextFormatting.GOLD + "-||" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + TextFormatting.DARK_RED + "Хранитель вечности" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + TextFormatting.GOLD + "||-", BossInfo.Color.RED, BossInfo.Overlay.PROGRESS);
                    bossBar.AddEntityAll(playerTrigger);
                    bossBar.ChangeProgress((float) progress / 100);
                }, 0);
                executer.addS(()-> MobsSpawn(), 2);
                executer.Exec();
                isMove = false;
            }

        }
    }
    public static void MobsSpawn(){
        HoprikStory.LOGGER.info("i am bern");
        final Executer[] executer = {new Executer()};
        executer[0].addS(() -> {HoprikStory.LOGGER.info(1); spawnMob(new BlockPos(1300, 38, 2459));},1);
        executer[0].addS(() -> {HoprikStory.LOGGER.info(2); spawnMob(new BlockPos(1304, 38, 2459));},1);
        executer[0].addS(() -> {HoprikStory.LOGGER.info(3); spawnMob(new BlockPos(1304, 38, 2462));},1);
        executer[0].addS(() -> {HoprikStory.LOGGER.info(4); spawnMob(new BlockPos(1300, 38, 2462));},1);
        executer[0].addS(()-> executer[0] = null, 5);
        executer[0].Exec();
    }

    @SubscribeEvent
    public static void damage(AttackEntityEvent event){
        if (event.getTarget() instanceof PlayerStory && !isPossibleKill) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void Kill(LivingDeathEvent event){
        if (bossFight && event.getSource().getTrueSource() instanceof PlayerEntity){
            if (event.getEntity() instanceof ZombieEntity || event.getEntity() instanceof SkeletonEntity || event.getEntity() instanceof SpiderEntity){
                if (progress == 0) {
                    bossFight = false;
                    isPossibleKill = true;
                }
                progress -= 5;
                bossBar.ChangeProgress((float) progress/100);
                killCount -= 1;
                if (killCount == 0){
                    MobsSpawn();
                    killCount = 4;
                }
                HoprikStory.LOGGER.info(killCount);
            }
        }
        if (event.getEntity() instanceof PlayerStory) {
            if (bossFight && event.getSource().getTrueSource() instanceof ArrowEntity || event.getSource().getTrueSource() instanceof SkeletonEntity || event.getSource().getTrueSource() instanceof ZombieEntity || event.getSource().getTrueSource() instanceof PlayerEntity) {
                event.setCanceled(true);
                ((PlayerStory) event.getEntity()).setHealth(20.0F);
            }
            if (!bossFight && event.getSource().getTrueSource() instanceof PlayerEntity){
                bossBar.RemoveEntityAll();
                Executer executer = new Executer();
                executer.addS(() -> {HoprikStory.LOGGER.info("");}, 3);
                executer.addS(() -> {StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Отлично хватай ключ"); },1);
                executer.Exec();
            }
        }
    }
    public static void spawnMob(BlockPos pos) {
        int random = Random.random(1, 4);
        HoprikStory.LOGGER.info(random);
        if (random == 1) {
            Hero hero = new Hero(new ZombieEntity(EntityType.ZOMBIE, playerTrigger.getEntityWorld()), pos);
        } else if (random == 2) {
            Hero hero = new Hero(new SkeletonEntity(EntityType.SKELETON, playerTrigger.getEntityWorld()), pos);
            int enchatBow = Random.random(0, 3);
            if (enchatBow == 0){
                hero.ET.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
            }
            else{
                ItemStack stack = new ItemStack(Items.BOW);
                stack.addEnchantment(Enchantments.POWER, enchatBow);
                hero.ET.setItemStackToSlot(EquipmentSlotType.MAINHAND, stack);
            }
        } else if (random == 3) {
            int head = Random.random(1, 2);
            int chest = Random.random(1, 2);
            int legs = Random.random(1, 2);
            int feet = Random.random(1, 2);
            Hero hero = new Hero(new ZombieEntity(EntityType.ZOMBIE, playerTrigger.getEntityWorld()), pos);
            ZombieEntity zombie = (ZombieEntity) hero.ET;
            //Голова
            if (head == 2) {
                int enchantments = Random.random(0, 2);
                if (enchantments == 0) {
                    zombie.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.IRON_HELMET));
                } else {
                    ItemStack stack = new ItemStack(Items.IRON_HELMET);
                    stack.addEnchantment(Enchantments.PROTECTION, enchantments);
                    zombie.setItemStackToSlot(EquipmentSlotType.HEAD, stack);
                }
            }
            //Грудь
            if (chest == 2) {
                int enchantments = Random.random(0, 2);
                if (enchantments == 0) {
                    zombie.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
                } else {
                    ItemStack stack = new ItemStack(Items.IRON_CHESTPLATE);
                    stack.addEnchantment(Enchantments.PROTECTION, enchantments);
                    zombie.setItemStackToSlot(EquipmentSlotType.CHEST, stack);
                }
            }
            //Ноги
            if (legs == 2) {
                int enchantments = Random.random(0, 2);
                if (enchantments == 0) {
                    zombie.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.IRON_LEGGINGS));
                } else {
                    ItemStack stack = new ItemStack(Items.IRON_LEGGINGS);
                    stack.addEnchantment(Enchantments.PROTECTION, enchantments);
                    zombie.setItemStackToSlot(EquipmentSlotType.LEGS, stack);
                }
            }
            //Ступни
            if (feet == 2) {
                int enchantments = Random.random(0, 2);
                if (enchantments == 0) {
                    zombie.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.IRON_BOOTS));
                } else {
                    ItemStack stack = new ItemStack(Items.IRON_BOOTS);
                    stack.addEnchantment(Enchantments.PROTECTION, enchantments);
                    zombie.setItemStackToSlot(EquipmentSlotType.FEET, stack);
                }
            }
        } else if (random == 4) {
            int head = Random.random(1, 2);
            int chest = Random.random(1, 2);
            int legs = Random.random(1, 2);
            int feet = Random.random(1, 2);
            int enchatBow = Random.random(0, 3);

            Hero hero = new Hero(new SkeletonEntity(EntityType.SKELETON, playerTrigger.getEntityWorld()), pos);
            SkeletonEntity zombie = (SkeletonEntity) hero.ET;
            //Голова
            if (head == 2) {
                int enchantments = Random.random(0, 2);
                if (enchantments == 0) {
                    zombie.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.IRON_HELMET));
                } else {
                    ItemStack stack = new ItemStack(Items.IRON_HELMET);
                    stack.addEnchantment(Enchantments.PROTECTION, enchantments);
                    zombie.setItemStackToSlot(EquipmentSlotType.HEAD, stack);
                }
            }
            //Грудь
            if (chest == 2) {
                int enchantments = Random.random(0, 2);
                if (enchantments == 0) {
                    zombie.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
                } else {
                    ItemStack stack = new ItemStack(Items.IRON_CHESTPLATE);
                    stack.addEnchantment(Enchantments.PROTECTION, enchantments);
                    zombie.setItemStackToSlot(EquipmentSlotType.CHEST, stack);
                }
            }
            //Ноги
            if (legs == 2) {
                int enchantments = Random.random(0, 2);
                if (enchantments == 0) {
                    zombie.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.IRON_LEGGINGS));
                } else {
                    ItemStack stack = new ItemStack(Items.IRON_LEGGINGS);
                    stack.addEnchantment(Enchantments.PROTECTION, enchantments);
                    zombie.setItemStackToSlot(EquipmentSlotType.LEGS, stack);
                }
            }

            //Ступни
            if (feet == 2) {
                int enchantments = Random.random(0, 2);
                if (enchantments == 0) {
                    zombie.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.IRON_BOOTS));
                } else {
                    ItemStack stack = new ItemStack(Items.IRON_BOOTS);
                    stack.addEnchantment(Enchantments.PROTECTION, enchantments);
                    zombie.setItemStackToSlot(EquipmentSlotType.FEET, stack);
                }
            }

            //Лук
            if (enchatBow == 0){
                zombie.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
            }
            else{
                ItemStack stack = new ItemStack(Items.BOW);
                stack.addEnchantment(Enchantments.POWER, enchatBow);
                zombie.setItemStackToSlot(EquipmentSlotType.MAINHAND, stack);
            }
        }
    }
    @SubscribeEvent
    public static void DisableDrop(LivingDropsEvent event){
        if ((event.getEntity() instanceof ZombieEntity || event.getEntity() instanceof SkeletonEntity) && bossFight){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void OpenDoorEvent(PlayerInteractEvent.RightClickItem event){
        if (event.getItemStack().getItem().equals(ItemStory.key.get())){
            for (int z = 3; z<=4; z++){
                for (int y = 1; y<=2; y++){
                    event.getWorld().setBlockState(new BlockPos(1294+16-4, 37+y,2457+z), Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    @SubscribeEvent
    public static void ChestOpen(PlayerInteractEvent.RightClickBlock event){
        if (event.getPos().equals(new BlockPos(1309, 38,2460)) || event.getPos().equals(new BlockPos(1309, 38,2461))){
            inventoryChest = (ChestTileEntity) event.getWorld().getTileEntity(new BlockPos(1309, 38,2460));
        }
    }

    @SubscribeEvent
    public static void ChestOpen(PlayerContainerEvent.Close event){
        boolean isFind = false;
        if (event.getContainer() instanceof ChestContainer) {
            try {
                for (int count = 0; count <= Objects.requireNonNull(inventoryChest).getSizeInventory() - 1; count++) {
                    if (!inventoryChest.getStackInSlot(count).getItem().equals(Items.AIR.getItem())) {
                        isFind = true;
                        break;
                    }
                }
                if (!isFind) {
                    StoryFunction.ShowWaitScreenAll(playerTrigger, "Cпустя 10 минут");
                    StoryFunction.TeleportEntityAll(playerTrigger, new Vector3d(event.getEntity().getPosX(), 42, event.getEntity().getPosZ()));
                    Executer executer = new Executer();
                    executer.addS(() -> {
                        HoprikStory.LOGGER.info("");
                    }, 5);
                    executer.addS(() -> {
                        StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Ух, сколько ресурсов");
                    }, 3);
                    executer.addS(() -> {
                        StoryFunction.SayPlayerAll(playerTrigger, "Земля", "На тебе рецепт брони");
                        StoryFunction.unlockRecipe(playerTrigger, "god_helmet");
                        StoryFunction.unlockRecipe(playerTrigger, "god_chestplate");
                        StoryFunction.unlockRecipe(playerTrigger, "god_leggings");
                        StoryFunction.unlockRecipe(playerTrigger, "god_boots");
                    }, 1);
                    executer.Exec();
                }
            } catch (Exception ignored) {

            }
        }

    }
}
class BossBar{
    static ServerBossInfo bossInfo;
    public BossBar(String name, BossInfo.Color color, BossInfo.Overlay overlay){
        bossInfo = (new ServerBossInfo(new StringTextComponent(name),color, overlay));
    }
    public void ChangeProgress(float value){
        bossInfo.setPercent(value);
    }
    public void AddEntityAll(PlayerEntity player){
        for (ServerPlayerEntity serverPlayerEntity: Objects.requireNonNull(player.getServer()).getPlayerList().getPlayers()) {
            bossInfo.addPlayer(serverPlayerEntity);
        }
    }
    public void RemoveEntityAll(){
        bossInfo.removeAllPlayers();
    }
}