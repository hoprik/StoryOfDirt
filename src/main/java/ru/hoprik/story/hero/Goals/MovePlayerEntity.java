package ru.hoprik.story.hero.Goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;
import java.util.EnumSet;

public class MovePlayerEntity extends Goal {
    private final MobEntity creature;
    private final double speed;
    private Vector3d vector3d;
    private boolean Run = true;

    public MovePlayerEntity(MobEntity creature,Vector3d vector3d, double speedIn) {
        this.creature = creature;
        this.speed = speedIn;
        this.vector3d = vector3d;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        if (Run){
            this.creature.getNavigator().tryMoveToXYZ(vector3d.x, vector3d.y, vector3d.z, this.speed);
        }

    }

    public void stop(){
        Run = false;
    }

    public void move(Vector3d vector3d){
        Run = true;
        this.vector3d = vector3d;
    }


    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */

    @Override
    public boolean shouldExecute() {
        return true;
    }


    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        if (Run) {
            return !this.creature.getNavigator().noPath();
        }
        else {
            return this.creature.getNavigator().noPath();
        }
    }

}
