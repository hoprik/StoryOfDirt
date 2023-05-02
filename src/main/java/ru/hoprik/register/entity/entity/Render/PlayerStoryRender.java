package ru.hoprik.register.entity.entity.Render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.entity.entity.Entity.PlayerStory;
import ru.hoprik.register.entity.entity.Model.PlayerStoryModel;

public class PlayerStoryRender extends MobRenderer<PlayerStory, PlayerStoryModel<PlayerStory>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(HoprikStory.MODID, "textures/entity/keeper.png");

    public PlayerStoryRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PlayerStoryModel<>(), 1F);
    }


    @Override
    public ResourceLocation getEntityTexture(ru.hoprik.register.entity.entity.Entity.PlayerStory entity) {
        return TEXTURE;
    }
}
