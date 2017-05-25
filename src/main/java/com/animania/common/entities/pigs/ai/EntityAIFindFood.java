package com.animania.common.entities.pigs.ai;

import java.util.Random;

import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletHampshire;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class EntityAIFindFood extends EntityAIBase
{
    private final EntityCreature temptedEntity;
    private final double         speed;
    private double               targetX;
    private double               targetY;
    private double               targetZ;
    private double               pitch;
    private double               yaw;
    private EntityPlayer         temptingPlayer;
    private boolean              isRunning;
    private int                  delayTemptCounter;

    public EntityAIFindFood(EntityCreature temptedEntityIn, double speedIn) {
        this.temptedEntity = temptedEntityIn;
        this.speed = speedIn;
        this.setMutexBits(3);
        this.delayTemptCounter = 0;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        this.delayTemptCounter++;
        if (this.delayTemptCounter > 20) {

            if (this.temptedEntity instanceof EntitySowYorkshire) {
                EntitySowYorkshire sow = (EntitySowYorkshire) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowDuroc) {
                EntitySowDuroc sow = (EntitySowDuroc) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowHampshire) {
                EntitySowHampshire sow = (EntitySowHampshire) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowYorkshire) {
                EntitySowYorkshire sow = (EntitySowYorkshire) this.temptedEntity;
                sow.entityAIEatGrass.startExecuting();
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowLargeBlack) {
                EntitySowLargeBlack sow = (EntitySowLargeBlack) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowLargeWhite) {
                EntitySowLargeWhite sow = (EntitySowLargeWhite) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowOldSpot) {
                EntitySowOldSpot sow = (EntitySowOldSpot) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogOldSpot) {
                EntityHogOldSpot sow = (EntityHogOldSpot) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogDuroc) {
                EntityHogDuroc sow = (EntityHogDuroc) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogHampshire) {
                EntityHogHampshire sow = (EntityHogHampshire) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogYorkshire) {
                EntityHogYorkshire sow = (EntityHogYorkshire) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogLargeBlack) {
                EntityHogLargeBlack sow = (EntityHogLargeBlack) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogLargeWhite) {
                EntityHogLargeWhite sow = (EntityHogLargeWhite) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                EntityPigletOldSpot sow = (EntityPigletOldSpot) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletDuroc) {
                EntityPigletDuroc sow = (EntityPigletDuroc) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletHampshire) {
                EntityPigletHampshire sow = (EntityPigletHampshire) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletYorkshire) {
                EntityPigletYorkshire sow = (EntityPigletYorkshire) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletLargeBlack) {
                EntityPigletLargeBlack sow = (EntityPigletLargeBlack) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletLargeWhite) {
                EntityPigletLargeWhite sow = (EntityPigletLargeWhite) this.temptedEntity;
                if (sow.getFed())
                    return false;
            }

            BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
            BlockPos trypos1 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ);
            BlockPos trypos2 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ);
            BlockPos trypos3 = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
            BlockPos trypos4 = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
            BlockPos trypos5 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
            BlockPos trypos6 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
            BlockPos trypos7 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
            BlockPos trypos8 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
            Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
            Block poschk1 = this.temptedEntity.world.getBlockState(trypos1).getBlock();
            Block poschk2 = this.temptedEntity.world.getBlockState(trypos2).getBlock();
            Block poschk3 = this.temptedEntity.world.getBlockState(trypos3).getBlock();
            Block poschk4 = this.temptedEntity.world.getBlockState(trypos4).getBlock();
            Block poschk5 = this.temptedEntity.world.getBlockState(trypos5).getBlock();
            Block poschk6 = this.temptedEntity.world.getBlockState(trypos6).getBlock();
            Block poschk7 = this.temptedEntity.world.getBlockState(trypos7).getBlock();
            Block poschk8 = this.temptedEntity.world.getBlockState(trypos8).getBlock();

            if (poschk == BlockHandler.blockTrough) {
                // do nothing
            }
            else if (poschk1 == BlockHandler.blockTrough)
                currentpos = trypos1;
            else if (poschk2 == BlockHandler.blockTrough)
                currentpos = trypos2;
            else if (poschk3 == BlockHandler.blockTrough)
                currentpos = trypos3;
            else if (poschk4 == BlockHandler.blockTrough)
                currentpos = trypos4;
            else if (poschk5 == BlockHandler.blockTrough)
                currentpos = trypos5;
            else if (poschk6 == BlockHandler.blockTrough)
                currentpos = trypos6;
            else if (poschk7 == BlockHandler.blockTrough)
                currentpos = trypos7;
            else if (poschk8 == BlockHandler.blockTrough)
                currentpos = trypos8;

            if (poschk1 == BlockHandler.blockTrough || poschk2 == BlockHandler.blockTrough || poschk3 == BlockHandler.blockTrough
                    || poschk4 == BlockHandler.blockTrough || poschk5 == BlockHandler.blockTrough || poschk6 == BlockHandler.blockTrough
                    || poschk7 == BlockHandler.blockTrough || poschk8 == BlockHandler.blockTrough) {
                TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(currentpos);
                if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == BlockHandler.fluidSlop) {
                    te.fluidHandler.drain(335, true);

                    if (this.temptedEntity instanceof EntitySowDuroc) {
                        EntitySowDuroc ech = (EntitySowDuroc) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowHampshire) {
                        EntitySowHampshire ech = (EntitySowHampshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowLargeBlack) {
                        EntitySowLargeBlack ech = (EntitySowLargeBlack) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowLargeWhite) {
                        EntitySowLargeWhite ech = (EntitySowLargeWhite) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowOldSpot) {
                        EntitySowOldSpot ech = (EntitySowOldSpot) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowYorkshire) {
                        EntitySowYorkshire ech = (EntitySowYorkshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogDuroc) {
                        EntityHogDuroc ech = (EntityHogDuroc) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogHampshire) {
                        EntityHogHampshire ech = (EntityHogHampshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogLargeBlack) {
                        EntityHogLargeBlack ech = (EntityHogLargeBlack) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogLargeWhite) {
                        EntityHogLargeWhite ech = (EntityHogLargeWhite) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogOldSpot) {
                        EntityHogOldSpot ech = (EntityHogOldSpot) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogYorkshire) {
                        EntityHogYorkshire ech = (EntityHogYorkshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletDuroc) {
                        EntityPigletDuroc ech = (EntityPigletDuroc) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletHampshire) {
                        EntityPigletHampshire ech = (EntityPigletHampshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletLargeBlack) {
                        EntityPigletLargeBlack ech = (EntityPigletLargeBlack) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletLargeWhite) {
                        EntityPigletLargeWhite ech = (EntityPigletLargeWhite) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                        EntityPigletOldSpot ech = (EntityPigletOldSpot) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                        EntityPigletYorkshire ech = (EntityPigletYorkshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setSlopFed(true);
                        ech.setWatered(true);
                    }

                    return false;

                }
            }

            if (poschk == Blocks.CARROTS || poschk == Blocks.BEETROOTS || poschk == Blocks.POTATOES || poschk == Blocks.WHEAT) {

                if (this.temptedEntity instanceof EntitySowDuroc) {
                    EntitySowDuroc ech = (EntitySowDuroc) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntitySowHampshire) {
                    EntitySowHampshire ech = (EntitySowHampshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntitySowLargeBlack) {
                    EntitySowLargeBlack ech = (EntitySowLargeBlack) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntitySowLargeWhite) {
                    EntitySowLargeWhite ech = (EntitySowLargeWhite) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntitySowOldSpot) {
                    EntitySowOldSpot ech = (EntitySowOldSpot) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntitySowYorkshire) {
                    EntitySowYorkshire ech = (EntitySowYorkshire) this.temptedEntity;
                    ech.setFed(true);
                    ech.entityAIEatGrass.startExecuting();
                }
                else if (this.temptedEntity instanceof EntityHogDuroc) {
                    EntityHogDuroc ech = (EntityHogDuroc) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityHogHampshire) {
                    EntityHogHampshire ech = (EntityHogHampshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityHogLargeBlack) {
                    EntityHogLargeBlack ech = (EntityHogLargeBlack) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityHogLargeWhite) {
                    EntityHogLargeWhite ech = (EntityHogLargeWhite) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityHogOldSpot) {
                    EntityHogOldSpot ech = (EntityHogOldSpot) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityHogYorkshire) {
                    EntityHogYorkshire ech = (EntityHogYorkshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletDuroc) {
                    EntityPigletDuroc ech = (EntityPigletDuroc) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletHampshire) {
                    EntityPigletHampshire ech = (EntityPigletHampshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletLargeBlack) {
                    EntityPigletLargeBlack ech = (EntityPigletLargeBlack) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletLargeWhite) {
                    EntityPigletLargeWhite ech = (EntityPigletLargeWhite) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                    EntityPigletOldSpot ech = (EntityPigletOldSpot) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletYorkshire) {
                    EntityPigletYorkshire ech = (EntityPigletYorkshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }

                if (this.temptedEntity.world.getGameRules().getBoolean("mobGriefing"))
                    this.temptedEntity.world.destroyBlock(currentpos, false);

                return false;
            }

            double x = this.temptedEntity.posX;
            double y = this.temptedEntity.posY;
            double z = this.temptedEntity.posZ;

            boolean foodFound = false;
            Random rand = new Random();

            BlockPos pos = new BlockPos(x, y, z);

            for (int i = -16; i < 16; i++)
                for (int j = -3; j < 3; j++)
                    for (int k = -16; k < 16; k++) {

                        pos = new BlockPos(x + i, y + j, z + k);

                        Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

                        if (blockchk == BlockHandler.blockTrough) {
                            TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);

                            if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == BlockHandler.fluidSlop) {
                                foodFound = true;
                                if (rand.nextInt(50) == 0) {
                                    this.delayTemptCounter = 0;
                                    this.resetTask();
                                    return false;
                                }
                                else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
                                        && this.temptedEntity.motionZ == 0) {
                                    this.delayTemptCounter = 0;
                                    this.resetTask();
                                    return false;
                                }
                                else
                                    return true;
                            }
                        }

                        if (blockchk == Blocks.CARROTS || blockchk == Blocks.BEETROOTS || blockchk == Blocks.POTATOES || blockchk == Blocks.WHEAT) {

                            foodFound = true;
                            if (rand.nextInt(20) == 0) {
                                this.delayTemptCounter = 0;
                                this.resetTask();
                                return false;
                            }
                            else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
                                    && this.temptedEntity.motionZ == 0) {
                                this.delayTemptCounter = 0;
                                this.resetTask();
                                return false;
                            }
                            else
                                return true;
                        }

                    }

            if (!foodFound)
                return false;
        }

        return false;

    }

    @Override
    public boolean continueExecuting() {

        return this.shouldExecute();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.isRunning = true;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPathEntity();
        this.isRunning = false;

    }

    @Override
    public void updateTask() {

        double x = this.temptedEntity.posX;
        double y = this.temptedEntity.posY;
        double z = this.temptedEntity.posZ;

        boolean foodFound = false;
        int loc = 24;
        int newloc = 24;
        BlockPos pos = new BlockPos(x, y, z);
        BlockPos foodPos = new BlockPos(x, y, z);

        for (int i = -16; i < 16; i++)
            for (int j = -3; j < 3; j++)
                for (int k = -16; k < 16; k++) {

                    pos = new BlockPos(x + i, y + j, z + k);
                    Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

                    if (blockchk == BlockHandler.blockTrough) {

                        TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);

                        if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == BlockHandler.fluidSlop) {

                            foodFound = true;
                            newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

                            if (newloc < loc) {

                                loc = newloc;

                                if (this.temptedEntity.posX < foodPos.getX()) {
                                    BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
                                    Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPoschk).getBlock();
                                    i = i + 1;
                                }

                                if (this.temptedEntity.posZ < foodPos.getZ()) {
                                    BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                    Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPoschk).getBlock();
                                    k = k + 1;
                                }

                                foodPos = new BlockPos(x + i, y + j, z + k);

                            }
                        }
                    }
                    else if (blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT || blockchk == Blocks.BEETROOTS || blockchk == Blocks.POTATOES) {
                        foodFound = true;
                        newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

                        if (newloc < loc) {

                            loc = newloc;

                            if (this.temptedEntity.posX < foodPos.getX()) {
                                BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
                                Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPoschk).getBlock();
                                if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.WHEAT || foodBlockchk == Blocks.BEETROOTS
                                        || foodBlockchk == Blocks.POTATOES)
                                    i = i + 1;
                            }

                            if (this.temptedEntity.posZ < foodPos.getZ()) {
                                BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPoschk).getBlock();
                                if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.WHEAT || foodBlockchk == Blocks.BEETROOTS
                                        || foodBlockchk == Blocks.POTATOES)
                                    k = k + 1;
                            }

                            foodPos = new BlockPos(x + i, y + j, z + k);

                        }
                    }
                }

        if (foodFound) {

            Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPos).getBlock();

            if (foodBlockchk == BlockHandler.blockTrough) {
                TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(foodPos);

                if (te.getTroughType() == 7 || te.getTroughType() == 8 || te.getTroughType() == 9)
                    if (this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed) == false)
                        this.resetTask();
                    else
                        this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed);

            }
            else if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.WHEAT || foodBlockchk == Blocks.BEETROOTS
                    || foodBlockchk == Blocks.POTATOES)
                if (this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed) == false)
                    this.resetTask();
                else
                    this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed);
        }

    }

    public boolean isRunning() {
        return this.isRunning;
    }
}