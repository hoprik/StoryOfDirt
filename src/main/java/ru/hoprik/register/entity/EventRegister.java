package ru.hoprik.register.entity;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.entity.entity.Entity.MardugaEntity;
import ru.hoprik.register.entity.entity.Entity.PlayerStory;

@Mod.EventBusSubscriber(modid = HoprikStory.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventRegister {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityStory.keeper_entity.get(), PlayerStory.registerAttributes().create());
        event.put(EntityStory.marduga_entity.get(), MardugaEntity.registerAttributes().create());
    }
}
