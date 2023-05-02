package ru.hoprik.story.scripts;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.Items.ItemStory;
import ru.hoprik.register.entity.EntityStory;
import ru.hoprik.register.entity.entity.Entity.MardugaEntity;
import ru.hoprik.story.hero.Executer;
import ru.hoprik.story.hero.Hero;
import ru.hoprik.story.hero.StoryFunction;
@Mod.EventBusSubscriber(modid = HoprikStory.MODID)
public class Script_six {
    static boolean[] isCrafting = {false, false, false, false};
    static int itemCreate = 0;
    static PlayerEntity playerTrigger;
    static ItemEntity itemEntity;

    @SubscribeEvent
    public static void Craft(PlayerEvent.ItemCraftedEvent event) {
        HoprikStory.LOGGER.info(itemCreate);
        if (!isCrafting[0] || !isCrafting[1] || !isCrafting[2] || !isCrafting[3]) {
            if (event.getCrafting().getItem() == ItemStory.GOD_HELMET.get()) {
                if (!isCrafting[0]) {
                    itemCreate++;
                } else {
                    event.setCanceled(true);
                }
            }
            if (event.getCrafting().getItem() == ItemStory.GOD_CHESTPLATE.get()) {
                if (!isCrafting[1]) {
                    itemCreate++;
                } else {
                    event.setCanceled(true);
                }
            }
            if (event.getCrafting().getItem() == ItemStory.GOD_LEGGINGS.get()) {
                if (!isCrafting[2]) {
                    itemCreate++;
                } else {
                    event.setCanceled(true);
                }
            }
            if (event.getCrafting().getItem() == ItemStory.GOD_BOOTS.get()) {
                if (!isCrafting[3]) {
                    itemCreate++;
                } else {
                    event.setCanceled(true);
                }
            }
        }
        if (isCrafting[0] || isCrafting[1] || isCrafting[2] || isCrafting[3]) {
            playerTrigger = event.getPlayer();
            Executer executer = new Executer();
            executer.addS(() -> {HoprikStory.LOGGER.info("");}, 5);
            executer.addS(() -> {
                StoryFunction.ItemSay(playerTrigger);
                StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Поздравляю ты получил свою долгажданую броню");
                
            }, 2);
            executer.addS(() -> {
                StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Спасибо, Спасибо что меня спас меня");
                
            }, 2);
            executer.addS(() -> {
                StoryFunction.ItemDisbleSay(playerTrigger);
                for (PlayerEntity entity : playerTrigger.world.getPlayers()) {
                    for (int slot = 0; slot <= entity.inventory.getSizeInventory(); slot++) {
                        if (entity.inventory.getStackInSlot(slot).getItem() == ItemStory.stoneHero.get()) {
                            itemEntity = entity.dropItem(entity.inventory.getStackInSlot(slot), false);
                            entity.inventory.setInventorySlotContents(slot, new ItemStack(Items.AIR.getItem()));
                        }
                    }
                }
                
            }, 2);
            executer.addS(() -> {
                StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Хахахахаахах ты заметил, как так обычная земля говорит?");
                
            }, 2);
            executer.addS(() -> {
                BlockPos pos = itemEntity.getPosition();
                itemEntity.getEntity().onKillCommand();
                Hero hero = new Hero(new MardugaEntity(EntityStory.marduga_entity.get(), playerTrigger.world), pos);
                StoryFunction.SayPlayerAll(playerTrigger, "Земля", "Я на самом деле Мардуга");
                
            }, 2);
            executer.addS(() -> {
                StoryFunction.SayPlayerAll(playerTrigger, "Мардуга", "И я захвочу мир, хахахаха");
                
            }, 2);
            executer.addS(() -> {
                StoryFunction.SayPlayerAll(playerTrigger, "Мардуга", "Хахахаха");
                
            }, 1);
            executer.addS(() -> {
                StoryFunction.SayPlayerAll(playerTrigger, "Мардуга", "Ты нечего не сделаешь!");
                
            }, 1);
            executer.addS(() -> {
                StoryFunction.SayPlayerAll(playerTrigger, "Мардуга", "Хахахаха");
                
            }, 1);
            executer.addS(() -> {
                StoryFunction.SayPlayerAll(playerTrigger, "Мардуга", "Хахахаха");
                
            }, 1);
            executer.addS(() -> {
                for (PlayerEntity player1 : playerTrigger.world.getPlayers()) {
                    player1.sendMessage(new StringTextComponent(TextFormatting.YELLOW + "Hopryansky0059 подключился к игре"), player1.getUniqueID());
                }
                
            }, 2);
            executer.addS(() -> {
                StoryFunction.ShowWaitScreenAll(playerTrigger, "Конец первой главы");
                
            }, 1);
            executer.Exec();
        }
    }
}