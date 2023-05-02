package ru.hoprik.story.scripts;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.Items.ItemStory;

@Mod.EventBusSubscriber(modid = HoprikStory.MODID)
public class bugFixes {
    @SubscribeEvent
    public static void DropItem(ItemTossEvent event){
        if (event.getEntityItem().getItem().getItem() == ItemStory.stoneHero.get() || event.getEntityItem().getItem().getItem() == ItemStory.stoneHeroSay.get()){
            Item item = event.getEntityItem().getItem().getItem();
            event.getEntityItem().onKillCommand();
            event.getPlayer().inventory.setInventorySlotContents(event.getPlayer().inventory.currentItem, new ItemStack(item));
        }
    }
    @SubscribeEvent
    public static void playerKill(PlayerEvent.PlayerRespawnEvent event){
        if (Script_one.isPickup) {
            event.getPlayer().inventory.setInventorySlotContents(event.getPlayer().inventory.currentItem, ItemStory.stoneHero.get().getDefaultInstance());
        }
    }

}
