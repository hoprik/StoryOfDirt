package ru.hoprik.story.hero;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IWorld;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import ru.hoprik.story.hero.Goals.MovePlayerEntity;

import java.util.Set;

public class Hero {
    public MobEntity ET;
    static boolean isFirstMove = true;
    static String name = null;

    public Hero(MobEntity entityType, BlockPos pos){
        entityType.setPosition(pos.getX(),pos.getY(),pos.getZ());
        entityType.world.addEntity(entityType);
        ET = entityType;
    }



    public void stopMoveEntity(){
        MobEntity entity = ET;
        Set<PrioritizedGoal> goals = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, entity.goalSelector, "goals");
        for(PrioritizedGoal goal: goals){
            if (goal.getGoal() instanceof MovePlayerEntity){
                goal.getGoal().resetTask();
                entity.targetSelector.removeGoal(goal.getGoal());
            }
        }
    }

    public void stopMoveEntityv2(){
        MobEntity entity = ET;
        Set<PrioritizedGoal> goals = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, entity.goalSelector, "goals");
        for(PrioritizedGoal goal: goals){
            if (goal.getGoal() instanceof MovePlayerEntity){
                ((MovePlayerEntity) goal.getGoal()).stop();
            }
        }
    }

    public void moveEntity(Vector3d vector3d, int speed){
        MobEntity entity = ET;
        //entity.moveForced(vector3d);
        if (isFirstMove) {
            entity.goalSelector.addGoal(0, new MovePlayerEntity(entity, vector3d, speed));
            isFirstMove = false;
        }
        else {
            Set<PrioritizedGoal> goals = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, entity.goalSelector, "goals");
            for(PrioritizedGoal goal: goals){
                if (goal.getGoal() instanceof MovePlayerEntity){
                    ((MovePlayerEntity) goal.getGoal()).move(vector3d);
                }
            }
        }
    }

    public void rotate(float yaw){
        Entity entity = ET;
        entity.rotationYaw = yaw;
    }

    public void PlayerSay(String text, PlayerEntity player){
        player.sendMessage(new StringTextComponent(text), player.getUniqueID());
    }

    public void AllPlayerSay(String text, PlayerEntity player){
        if (name == null){
            name = "Noname";
        }
        for (PlayerEntity player1: player.world.getPlayers()) {
            player1.sendMessage(new StringTextComponent(TextFormatting.GOLD+"["+name+"] "+TextFormatting.RESET+text), player1.getUniqueID());
        }
    }

    public void setName(String name) {
        Hero.name = name;
    }

    public String getName() {
        return name;
    }


}

