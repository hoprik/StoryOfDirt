package ru.hoprik.register.Items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import ru.hoprik.HoprikStory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemStory {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HoprikStory.MODID);


    public static RegistryObject<Item> hammer = ITEMS.register("hammer", ()-> new PickaxeItem(TierItem.HAMMER, 1, 5, new Item.Properties()));
    public static RegistryObject<Item> stoneHero = ITEMS.register("stonehero", ()-> new Item(new Item.Properties()));
    public static RegistryObject<Item> stoneHeroSay = ITEMS.register("stonesay", ()-> new Item(new Item.Properties()));
    public static RegistryObject<Item> half_god_igot = ITEMS.register("half_god_igot", ()-> new Item(new Item.Properties()));
    public static RegistryObject<Item> god_igot = ITEMS.register("god_igot", ()-> new Item(new Item.Properties()));
    public static RegistryObject<Item> key = ITEMS.register("key", ()-> new Item(new Item.Properties()));
    public static RegistryObject<Item> GOD_HELMET = ITEMS.register("god_helmet", ()-> new ArmorItem(ArmorTier.GOD, EquipmentSlotType.HEAD, (new Item.Properties()).group(ItemGroup.COMBAT)));
    public static RegistryObject<Item> GOD_CHESTPLATE = ITEMS.register("god_chestplate", ()-> new ArmorItem(ArmorTier.GOD, EquipmentSlotType.CHEST, (new Item.Properties()).group(ItemGroup.COMBAT)));
    public static RegistryObject<Item> GOD_LEGGINGS = ITEMS.register("god_leggings", ()-> new ArmorItem(ArmorTier.GOD, EquipmentSlotType.LEGS, (new Item.Properties()).group(ItemGroup.COMBAT)));
    public static RegistryObject<Item> GOD_BOOTS = ITEMS.register("god_boots", ()-> new ArmorItem(ArmorTier.GOD, EquipmentSlotType.FEET, (new Item.Properties()).group(ItemGroup.COMBAT)));


    public static void RegisterItems(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
