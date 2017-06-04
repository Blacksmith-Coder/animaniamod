package com.animania.common.entities.pigs.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;

public class EntityAISwimmingPigs extends EntityAIBase
{
    private final EntityLiving theEntity;

    public EntityAISwimmingPigs(EntityLiving entitylivingIn) {
        this.theEntity = entitylivingIn;
        this.setMutexBits(4);
        ((PathNavigateGround) entitylivingIn.getNavigator()).setCanSwim(true);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        return this.theEntity.isInWater() || this.theEntity.isInLava();
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        if (this.theEntity.getRNG().nextFloat() < 0.9F) {

            BlockPos poschk = new BlockPos(this.theEntity.posX + this.theEntity.motionX / 1.5, this.theEntity.posY + .1F,
                    this.theEntity.posZ + this.theEntity.motionZ / 1.5);

            Block blockchk = this.theEntity.world.getBlockState(poschk).getBlock();
            if (this.theEntity.isPushedByWater()) {
				this.theEntity.getJumpHelper().setJumping();
				this.theEntity.setPositionAndUpdate(this.theEntity.posX + this.theEntity.motionX/2, this.theEntity.posY+.5F, this.theEntity.posZ + this.theEntity.motionZ/2);
			} else if (blockchk != Blocks.WATER) {
				this.theEntity.getJumpHelper().setJumping();
				this.theEntity.setPositionAndUpdate(this.theEntity.posX + this.theEntity.motionX / 2, this.theEntity.posY + .5F, this.theEntity.posZ + this.theEntity.motionZ / 2);
			} else {
				this.theEntity.setPositionAndUpdate(this.theEntity.posX + this.theEntity.motionX / 2, this.theEntity.posY + .6F, this.theEntity.posZ + this.theEntity.motionZ / 2);
			}
        }

    }
}
