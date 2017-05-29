package com.animania.common.entities.horses.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class EntityHorseEatGrass extends EntityAIBase
{
	private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	/** The entity owner of this AITask */
	private final EntityLiving grassEaterEntity;
	/** The world the grass eater entity is eating from */
	private final World entityWorld;
	/** Number of ticks since the entity started to eat grass */
	int eatingGrassTimer;

	public EntityHorseEatGrass(EntityLiving grassEaterEntityIn)
	{
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.worldObj;
		this.setMutexBits(7);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{


		if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
		{
			return false;
		}
		else
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
			return IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)) ? true : (this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS);
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		this.eatingGrassTimer = 100;
		this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
		this.grassEaterEntity.getNavigator().clearPathEntity();
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.eatingGrassTimer = 0;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{

		return this.eatingGrassTimer > 0;
	}

	/**
	 * Number of ticks since the entity started to eat grass
	 */
	public int getEatingGrassTimer()
	{
		return this.eatingGrassTimer;
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.eatingGrassTimer == 4)
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

			if (IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)))
			{

				this.entityWorld.destroyBlock(blockpos, false);


				if (grassEaterEntity instanceof EntityStallionDraftHorse) {
					EntityStallionDraftHorse ech = (EntityStallionDraftHorse)grassEaterEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} 
				this.grassEaterEntity.eatGrassBonus();
			}

			else

			{
				BlockPos blockpos1 = blockpos.down();

				if (this.entityWorld.getBlockState(blockpos1).getBlock() == Blocks.GRASS)
				{

					this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(Blocks.GRASS));
					this.entityWorld.setBlockState(blockpos1, Blocks.DIRT.getDefaultState(), 2);


					if (grassEaterEntity instanceof EntityStallionDraftHorse) {
						EntityStallionDraftHorse ech = (EntityStallionDraftHorse)grassEaterEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} 

					this.grassEaterEntity.eatGrassBonus();
				}
			}
		}
	}
}