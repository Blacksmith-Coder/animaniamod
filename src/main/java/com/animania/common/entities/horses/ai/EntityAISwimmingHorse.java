package com.animania.common.entities.horses.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;

public class EntityAISwimmingHorse extends EntityAIBase
{
	private final EntityLiving theEntity;

	public EntityAISwimmingHorse(EntityLiving entitylivingIn)
	{
		this.theEntity = entitylivingIn;
		this.setMutexBits(4);
		((PathNavigateGround)entitylivingIn.getNavigator()).setCanSwim(true);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		return this.theEntity.isInWater() || this.theEntity.isInLava();
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		if (this.theEntity.getRNG().nextFloat() < 0.9F)
		{
		
			BlockPos poschk = new BlockPos(this.theEntity.posX + this.theEntity.motionX/1.5, this.theEntity.posY+.1F, this.theEntity.posZ + this.theEntity.motionZ/1.5);

			Block blockchk = this.theEntity.world.getBlockState(poschk).getBlock();

			if (this.theEntity.isPushedByWater()) {
				this.theEntity.move(MoverType.SELF, this.theEntity.motionX, this.theEntity.motionY + .3, this.theEntity.motionZ);	
			} else if (blockchk != Blocks.WATER) {
				this.theEntity.move(MoverType.SELF, this.theEntity.motionX, this.theEntity.motionY + .3, this.theEntity.motionZ);	
				this.theEntity.getJumpHelper().setJumping();
			} else {
				this.theEntity.move(MoverType.SELF, this.theEntity.motionX, this.theEntity.motionX + .3F, this.theEntity.motionZ);	
			}
		}
	}
}
