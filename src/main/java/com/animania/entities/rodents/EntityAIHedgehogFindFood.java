package com.animania.entities.rodents;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import com.animania.Animania;
import com.animania.tileentities.TileEntityNest;
import com.animania.tileentities.TileEntityNest;

public class EntityAIHedgehogFindFood extends EntityAIBase 
{
	private final EntityCreature temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private boolean isRunning;
	private int delayTemptCounter;

	public EntityAIHedgehogFindFood(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		delayTemptCounter++;
		if (delayTemptCounter > 20) {

			if (this.temptedEntity instanceof EntityHedgehog) {
				EntityHedgehog entity = (EntityHedgehog) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHedgehogAlbino) {
				EntityHedgehogAlbino entity = (EntityHedgehogAlbino)temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			
			}

			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();
			
			if (poschk == Animania.blockNest) {
				TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(currentpos);
				
				if (te.getNestType() == 0) {
					return false;
				}
				
				if (te !=null && (te.getNestType() == 1 || te.getNestType() == 4 || te.getNestType() == 7 || te.getNestType() == 10 || te.getNestType() == 13)) {
					te.setType(0);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 0);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);

					if (temptedEntity instanceof EntityHedgehog) {
						EntityHedgehog ech = (EntityHedgehog)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityHedgehogAlbino) {
						EntityHedgehogAlbino ech = (EntityHedgehogAlbino)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
						ech.setWatered(true);
					}

					return false;

				} else if (te !=null && te.getNestType() >= 2 && te.getNestType() <= 15) {
					te.setType(te.getNestType()-1);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), te.getNestType()-1);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
					if (temptedEntity instanceof EntityHedgehog) {
						EntityHedgehog ech = (EntityHedgehog)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHedgehogAlbino) {
						EntityHedgehogAlbino ech = (EntityHedgehogAlbino)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					
					}
					return false;
				}
			} 

			if (poschk == Blocks.CARROTS || poschk == Blocks.BEETROOTS || poschk == Blocks.POTATOES) {

				if (temptedEntity instanceof EntityHedgehog) {
					EntityHedgehog ech = (EntityHedgehog)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityHedgehogAlbino) {
					EntityHedgehogAlbino ech = (EntityHedgehogAlbino)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} 

				if (temptedEntity.world.getGameRules().getBoolean("mobGriefing"))
				{
					temptedEntity.world.destroyBlock(currentpos, false);
				}


				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean foodFound = false;
			Random rand = new Random();

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == Animania.blockNest) {
							TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);

							if (te !=null && (te.getNestType() >= 1 && te.getNestType() <= 15)) {
								foodFound = true;
								if (rand.nextInt(50) == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								} else {
									return true;
								}
							}
						}

						if (blockchk == Blocks.CARROTS || blockchk == Blocks.BEETROOTS || blockchk == Blocks.POTATOES) {

							foodFound = true;
							if (rand.nextInt(20) == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							} else {
								return true;
							}
						}

					}

				}
			}

			if (!foodFound) {
				return false;
			}
		}

			return false;
		
	}



	public boolean continueExecuting()
	{

		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{	
		this.isRunning = true;
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.isRunning = false;

	}


	public void updateTask()
	{

		double x = this.temptedEntity.posX;
		double y = this.temptedEntity.posY;
		double z = this.temptedEntity.posZ;

		boolean foodFound = false;
		int loc = 24;
		int newloc = 24;
		BlockPos pos = new BlockPos(x, y, z);
		BlockPos foodPos = new BlockPos(x, y, z);

		for (int i = -16; i < 16; i++) {
			for (int j = -3; j < 3; j++) {
				for (int k = -16; k < 16; k++) {

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();


					if (blockchk == Animania.blockNest) {


						TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);

						if (te !=null && (te.getNestType() >= 1 && te.getNestType() <= 15)) {

							foodFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (temptedEntity.posX < foodPos.getX()) {
									BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
									i = i + 1;
								} 

								if (temptedEntity.posZ < foodPos.getZ()) {
									BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
									k = k + 1;
								}

								foodPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					} else if (blockchk == Blocks.CARROTS || blockchk == Blocks.BEETROOTS || blockchk == Blocks.POTATOES) {
						foodFound = true;
						newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

						if (newloc < loc) {

							loc = newloc;

							if (temptedEntity.posX < foodPos.getX()) {
								BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
								Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
								if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.BEETROOTS || foodBlockchk == Blocks.POTATOES) {
									i = i + 1;
								}
							} 

							if (temptedEntity.posZ < foodPos.getZ()) {
								BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
								Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
								if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.BEETROOTS || foodBlockchk == Blocks.POTATOES) {
									k = k + 1;
								} 
							}

							foodPos = new BlockPos(x + i, y + j, z + k);

						}
					}
				}
			}
		}

		if (foodFound) {

			Block foodBlockchk = temptedEntity.world.getBlockState(foodPos).getBlock();

			if (foodBlockchk == Animania.blockNest) {
				TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(foodPos);

				if (te.getNestType() > 0 && te.getNestType() <= 15) {
					if(this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed) == false) {
						this.resetTask();
					} else {
						this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed);
					}

				}

			} else if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.BEETROOTS || foodBlockchk == Blocks.POTATOES) {
				if(this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed) == false) {
					this.resetTask();
				} else {
					this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed);
				}
			}
		}

	}


	public boolean isRunning()
	{
		return this.isRunning;
	}
}