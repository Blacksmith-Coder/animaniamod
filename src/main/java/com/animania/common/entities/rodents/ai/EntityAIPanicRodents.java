package com.animania.common.entities.rodents.ai;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAIPanicRodents extends EntityAIBase
{
    private final EntityCreature theEntityCreature;
    protected double             speed;
    private double               randPosX;
    private double               randPosY;
    private double               randPosZ;
    private int                  duration;
    private boolean              hitFlag;

    public EntityAIPanicRodents(EntityCreature creature, double speedIn) {
        this.theEntityCreature = creature;
        this.speed = speedIn;
        this.setMutexBits(1);
        this.duration = 0;
        this.hitFlag = false;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        EntityPlayer checkPlayer = this.theEntityCreature.world.getClosestPlayer(this.theEntityCreature.posX, this.theEntityCreature.posY,
                this.theEntityCreature.posZ, 20, false);

        if (this.theEntityCreature.getCustomNameTag().equals("Sonic") || this.theEntityCreature.getCustomNameTag().equals("Sanic")) {
            Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 24, 4);
            if (vec3d != null) {
                this.randPosX = vec3d.xCoord;
                this.randPosY = vec3d.yCoord;
                this.randPosZ = vec3d.zCoord;
                return true;
            }
            else
                return false;
        }

        if (this.theEntityCreature.getAITarget() == null && !this.theEntityCreature.isBurning() && this.duration == 0) {
            this.hitFlag = false;
            return false;
        }
        else if (!this.theEntityCreature.isBurning()) {
            Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 20, 4);

            if (this.hitFlag == false) {
                this.hitFlag = true;
                this.duration = 40;
            }

            this.duration--;

            if (vec3d == null) {
                this.hitFlag = false;
                return false;
            }
            else {
                this.randPosX = vec3d.xCoord;
                this.randPosY = vec3d.yCoord;
                this.randPosZ = vec3d.zCoord;
                return true;
            }
        }
        else {
            BlockPos blockpos = this.getRandPos(this.theEntityCreature.world, this.theEntityCreature, 20, 4);

            if (blockpos == null) {
                this.hitFlag = false;
                return false;
            }
            else {
                this.randPosX = blockpos.getX();
                this.randPosY = blockpos.getY();
                this.randPosZ = blockpos.getZ();
                return true;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.theEntityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        return !this.theEntityCreature.getNavigator().noPath();
    }

    private BlockPos getRandPos(World worldIn, Entity entityIn, int horizontalRange, int verticalRange) {
        BlockPos blockpos = new BlockPos(entityIn);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        horizontalRange = horizontalRange + 10;
        verticalRange = verticalRange + 10;
        int i = blockpos.getX();
        int j = blockpos.getY();
        int k = blockpos.getZ();
        float f = horizontalRange * horizontalRange * verticalRange * 5;
        BlockPos blockpos1 = null;

        for (int l = i - horizontalRange; l <= i + horizontalRange; ++l)
            for (int i1 = j - verticalRange; i1 <= j + verticalRange; ++i1)
                for (int j1 = k - horizontalRange; j1 <= k + horizontalRange; ++j1) {
                    blockpos$mutableblockpos.setPos(l, i1, j1);
                    IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);
                    Block block = iblockstate.getBlock();

                    if (block == Blocks.WATER || block == Blocks.FLOWING_WATER) {
                        float f1 = (l - i) * (l - i) + (i1 - j) * (i1 - j) + (j1 - k) * (j1 - k);

                        if (f1 < f) {
                            f = f1;
                            blockpos1 = new BlockPos(blockpos$mutableblockpos);
                        }
                    }
                }

        return blockpos1;
    }
}