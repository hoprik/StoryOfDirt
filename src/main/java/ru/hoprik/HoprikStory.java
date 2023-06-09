package ru.hoprik;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import ru.hoprik.register.Items.ItemStory;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.hoprik.register.block.BlockStory;
import ru.hoprik.register.entity.EntityStory;
import ru.hoprik.register.entity.entity.Entity.MardugaEntity;
import ru.hoprik.register.entity.entity.Render.MardugaRender;
import ru.hoprik.register.entity.entity.Render.PlayerStoryRender;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HoprikStory.MODID)
public class HoprikStory {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "hoprikstrory";

    public HoprikStory() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        ItemStory.RegisterItems(eventBus);
        BlockStory.RegisterBlock(eventBus);
        EntityStory.RegisterEntity(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        RenderingRegistry.registerEntityRenderingHandler(EntityStory.marduga_entity.get(), MardugaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityStory.keeper_entity.get(), PlayerStoryRender::new);
    }
}
