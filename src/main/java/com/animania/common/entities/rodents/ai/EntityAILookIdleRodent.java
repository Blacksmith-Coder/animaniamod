package com.animania.common.entities.rodents.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;

public class EntityAILookIdleRodent extends EntityAIBase
{
    /** The entity that is looking idle. */
    private final EntityAnimal idleEntity;
    /** X offset to look at */
    private double             lookX;
    /** Z offset to look at */
    private double             lookZ;
    /**
     * A decrementing tick that stops the entity from being idle once it reaches
     * 0.
     */
    private int                idleTime;

    public EntityAILookIdleRodent(EntityAnimal entitylivingIn) {
        this.idleEntity = entitylivingIn;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        return this.idleEntity.getRNG().nextFloat() < 0.02F && !this.idleEntity.isRiding();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        return this.idleTime >= 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        double d0 = Math.PI * 2D * this.idleEntity.getRNG().nextDouble();
        this.lookX = Math.cos(d0);
        this.lookZ = Math.sin(d0);
        this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        --this.idleTime;
        this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + this.idleEntity.getEyeHeight(),
                this.idleEntity.posZ + this.lookZ, this.idleEntity.getHorizontalFaceSpeed(), this.idleEntity.getVerticalFaceSpeed());
    }
}