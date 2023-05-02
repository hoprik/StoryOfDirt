package ru.hoprik.register.entity.entity.Render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import ru.hoprik.HoprikStory;
import ru.hoprik.register.entity.entity.Entity.MardugaEntity;
import ru.hoprik.register.entity.entity.Entity.PlayerStory;
import ru.hoprik.register.entity.entity.Model.MardugaModel;
import ru.hoprik.register.entity.entity.Model.PlayerStoryModel;

public class MardugaRender extends MobRenderer<MardugaEntity, MardugaModel<MardugaEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(HoprikStory.MODID, "textures/entity/marduga.png");

    public MardugaRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MardugaModel<>(), 1F);
    }


    @Override
    public ResourceLocation getEntityTexture(MardugaEntity entity) {
        return TEXTURE;
    }
}
