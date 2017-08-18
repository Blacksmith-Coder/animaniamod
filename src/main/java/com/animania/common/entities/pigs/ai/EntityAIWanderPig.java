package com.animania.common.entities.pigs.ai;

import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIWanderPig extends EntityAIBase
{
    private final EntityCreature entity;
    private double               xPosition;
    private double               yPosition;
    private double               zPosition;
    private final double         speed;
    private int                  executionChance;
    private boolean              mustUpdate;

    public EntityAIWanderPig(EntityCreature creatureIn, double speedIn) {
        this(creatureIn, speedIn, 120);
    }

    public EntityAIWanderPig(EntityCreature creatureIn, double speedIn, int chance) {
        this.entity = creatureIn;
        this.speed = speedIn;
        this.executionChance = chance;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (!this.mustUpdate)
            if (this.entity.getRNG().nextInt(this.executionChance) != 0)
                return false;

        Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);

        if (vec3d == null)
            return false;
        else {
            this.xPosition = vec3d.xCoord;
            this.yPosition = vec3d.yCoord;
            this.zPosition = vec3d.zCoord;
            this.mustUpdate = false;
            return true;
        }
    }

    @Override
    public boolean continueExecuting() {
        return !this.entity.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {

        double x = this.entity.posX;
        double y = this.entity.posY - 1;
        double z = this.entity.posZ;

        BlockPos pos = new BlockPos(x, y, z);

        Block blockchk = this.entity.world.getBlockState(pos).getBlock();

        if (blockchk == BlockHandler.blockMud)
            this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed / 1.1);
        else
            this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }

    public void makeUpdate() {
        this.mustUpdate = true;
    }

    public void setExecutionChance(int newchance) {
        this.executionChance = newchance;
    }
}