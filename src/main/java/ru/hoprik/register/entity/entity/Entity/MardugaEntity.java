package ru.hoprik.register.entity.entity.Entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MardugaEntity extends CreatureEntity {
    private Boolean animate = false;

    public MardugaEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        super.registerGoals();
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2F);
    }

}
