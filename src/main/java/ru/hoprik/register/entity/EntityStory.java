package ru.hoprik.register.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.entity.entity.Entity.MardugaEntity;
import ru.hoprik.register.entity.entity.Entity.PlayerStory;

public class EntityStory {
    public static DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, HoprikStory.MODID);

    public static RegistryObject<EntityType<PlayerStory>> keeper_entity = ENTITY.register("keeper_entity", ()-> EntityType.Builder.create(PlayerStory::new, EntityClassification.MISC).size(1, 2).build(new ResourceLocation(HoprikStory.MODID, "keeper_entity").toString()));
    public static RegistryObject<EntityType<MardugaEntity>> marduga_entity = ENTITY.register("marduga_entity", ()-> EntityType.Builder.create(MardugaEntity::new, EntityClassification.MISC).size(1, 2).build(new ResourceLocation(HoprikStory.MODID, "marduga_entity").toString()));

    public static void RegisterEntity(IEventBus eventBus){
        ENTITY.register(eventBus);
    }
}
