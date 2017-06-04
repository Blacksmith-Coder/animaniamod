package com.animania.common.entities.peacocks;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.amphibians.EntityAmphibian;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.peacocks.ai.EntityAIFindFood;
import com.animania.common.entities.peacocks.ai.EntityAIFindWater;
import com.animania.common.entities.peacocks.ai.EntityAIPanicPeacocks;
import com.animania.common.entities.peacocks.ai.EntityAISwimmingPeacocks;
import com.animania.common.entities.peacocks.ai.EntityAIWanderPeacock;
import com.animania.common.entities.peacocks.ai.EntityAIWatchClosestFromSide;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityPeachickBlue extends EntityAnimal
{
    private static final DataParameter<Boolean> FED                   = EntityDataManager.<Boolean> createKey(EntityPeachickBlue.class,
            DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> WATERED               = EntityDataManager.<Boolean> createKey(EntityPeachickBlue.class,
            DataSerializers.BOOLEAN);
    private static final DataParameter<Float>   AGE                   = EntityDataManager.<Float> createKey(EntityPeachickBlue.class,
            DataSerializers.FLOAT);
    private static final Set<Item>              TEMPTATION_ITEMS      = Sets
            .newHashSet(new Item[] { Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS });
    private static List                         ColorList;
    private ResourceLocation                    resourceLocation      = new ResourceLocation("animania:textures/entity/peacocks/peachick_blue.png");
    private ResourceLocation                    resourceLocationBlink = new ResourceLocation(
            "animania:textures/entity/peacocks/peachick_blue_blink.png");
    public float                                wingRotation;
    public float                                destPos;
    public float                                oFlapSpeed;
    public float                                oFlap;
    public float                                wingRotDelta          = 1.0F;
    private int                                 fedTimer;
    private int                                 wateredTimer;
    private int                                 ageTimer;
    private int                                 happyTimer;
    public int                                  blinkTimer;
    private int                                 damageTimer;

    public EntityPeachickBlue(World world) {
        super(world);
        this.setSize(0.3F, 0.6F);
        this.tasks.addTask(0, new EntityAISwimmingPeacocks(this));
        this.tasks.addTask(1, new EntityAIFindWater(this, 1.0D));
        this.tasks.addTask(2, new EntityAIFindFood(this, 1.0D));
        this.tasks.addTask(3, new EntityAIPanicPeacocks(this, 1.4D));
        this.tasks.addTask(4, new EntityAITempt(this, 1.0D, Items.WHEAT_SEEDS, false));
        this.tasks.addTask(5, new EntityAIWanderPeacock(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(9, new EntityAILeapAtTarget(this, 0.2F));
		this.tasks.addTask(10, new EntityAIAttackMelee(this, 1.0D, true));
		this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityFrogs.class, false));
		this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityToad.class, false));
        this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer * 2 + this.rand.nextInt(100);
        this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer * 2 + this.rand.nextInt(100);
        this.blinkTimer = 80 + this.rand.nextInt(80);
        this.enablePersistence();
        this.ageTimer = 0;
        String texture = null;
        this.happyTimer = 60;

    }

    @Override
    public boolean isChild() {
        return true;
    }

    @Override
    protected void consumeItemFromStack(EntityPlayer player, ItemStack stack) {
        this.setFed(true);
        player.addStat(AnimaniaAchievements.IndiaBlue, 1);
        if (player.hasAchievement(AnimaniaAchievements.IndiaBlue) && player.hasAchievement(AnimaniaAchievements.White))
            player.addStat(AnimaniaAchievements.Peacocks, 1);

        if (!player.capabilities.isCreativeMode)
            stack.setCount(stack.getCount() - 1);
    }

    @Override
    public void setInLove(EntityPlayer player) {
        this.world.setEntityState(this, (byte) 18);
    }

    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        EntityPlayer entityplayer = player;

        if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
            this.setWatered(true);
            this.setInLove(player);
            return true;
        }
        else
            return super.processInteract(player, hand);
    }
    
    @Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
		entityIn.attackEntityFrom(DamageSource.GENERIC, 1.0F);

		if (flag)
		{
			this.applyEnchantments(this, entityIn);
		}
		
		if (entityIn instanceof EntityAmphibian) {
			this.setFed(true);
		}

		//Custom Knockback		
		if (entityIn instanceof EntityPlayer) {
			((EntityLivingBase) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);
		}


		return flag;
	}

    public ResourceLocation getResourceLocation() {
        return this.resourceLocation;
    }

    public ResourceLocation getResourceLocationBlink() {
        return this.resourceLocationBlink;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(EntityPeachickBlue.FED, Boolean.valueOf(true));
        this.dataManager.register(EntityPeachickBlue.WATERED, Boolean.valueOf(true));
        this.dataManager.register(EntityPeachickBlue.AGE, Float.valueOf(0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Fed", this.getFed());
        nbttagcompound.setBoolean("Watered", this.getWatered());
        nbttagcompound.setFloat("Age", this.getEntityAge());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.setFed(nbttagcompound.getBoolean("Fed"));
        this.setWatered(nbttagcompound.getBoolean("Watered"));
        this.setEntityAge(nbttagcompound.getFloat("Age"));

    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float) (this.destPos + (this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

        if (!this.onGround && this.wingRotDelta < 1.0F)
            this.wingRotDelta = 1.0F;

        this.wingRotDelta = (float) (this.wingRotDelta * 0.9D);

        if (!this.onGround && this.motionY < 0.0D)
            this.motionY *= 0.6D;

        this.wingRotation += this.wingRotDelta * 2.0F;

        if (this.blinkTimer > -1) {
            this.blinkTimer--;
            if (this.blinkTimer == 0)
                this.blinkTimer = 100 + this.rand.nextInt(100);
        }

        if (this.fedTimer > -1) {
            this.fedTimer--;

            if (this.fedTimer == 0)
                this.setFed(false);
        }

        if (this.wateredTimer > -1) {
            this.wateredTimer--;

            if (this.wateredTimer == 0)
                this.setWatered(false);
        }

        boolean fed = this.getFed();
        boolean watered = this.getWatered();

        if (this.isEntityInsideOpaqueBlock()) {
			this.jumpHelper.setJumping();
		}
        
        if (!fed && !watered) {
            this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
            if (AnimaniaConfig.gameRules.animalsStarve) {
                if (this.damageTimer >= AnimaniaConfig.careAndFeeding.starvationTimer) {
                    this.attackEntityFrom(DamageSource.STARVE, 4f);
                    this.damageTimer = 0;
                }
                this.damageTimer++;
            }

        }
        else if (!fed || !watered)
            this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));

        this.ageTimer++;
        if (this.ageTimer >= AnimaniaConfig.careAndFeeding.childGrowthTick)
            if (fed && watered) {
                this.ageTimer = 0;
                float age = this.getEntityAge();
                age = age + .01F;
                this.setEntityAge(age);

                if (age >= .4 && !this.world.isRemote) {

                    this.setDead();

                    if (this.rand.nextInt(2) < 1) {
                        EntityPeafowlBlue entityFowl = new EntityPeafowlBlue(this.world);
                        entityFowl.setPosition(this.posX, this.posY + .5, this.posZ);
                        String name = this.getCustomNameTag();
                        if (name != "")
                            entityFowl.setCustomNameTag(name);
                        this.world.spawnEntity(entityFowl);
                        this.playSound(ModSoundEvents.peacock1, 0.50F, 1.1F);
                    }
                    else {
                        EntityPeacockBlue entityPeacock = new EntityPeacockBlue(this.world);
                        entityPeacock.setPosition(this.posX, this.posY + .5, this.posZ);
                        String name = this.getCustomNameTag();
                        if (name != "")
                            entityPeacock.setCustomNameTag(name);
                        this.world.spawnEntity(entityPeacock);
                        this.playSound(ModSoundEvents.peacock8, 0.50F, 1.1F);
                    }

                }
            }

        if (this.happyTimer > -1) {
            this.happyTimer--;
            if (this.happyTimer == 0) {
                this.happyTimer = 60;

                if (!this.getFed() && !this.getWatered() && AnimaniaConfig.gameRules.showUnhappyParticles) {
                    double d = this.rand.nextGaussian() * 0.001D;
                    double d1 = this.rand.nextGaussian() * 0.001D;
                    double d2 = this.rand.nextGaussian() * 0.001D;
                    this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.rand.nextFloat() * this.width - this.width,
                            this.posY + 1.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d,
                            d1, d2);
                }
            }
        }

    }

    public boolean getFed() {
        return this.dataManager.get(EntityPeachickBlue.FED).booleanValue();
    }

    public void setFed(boolean fed) {
        if (fed) {
            this.dataManager.set(EntityPeachickBlue.FED, Boolean.valueOf(true));
            this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
            this.setHealth(this.getHealth() + 1.0F);
        }
        else
            this.dataManager.set(EntityPeachickBlue.FED, Boolean.valueOf(false));
    }

    public boolean getWatered() {
        return this.dataManager.get(EntityPeachickBlue.WATERED).booleanValue();
    }

    public void setWatered(boolean watered) {
        if (watered) {
            this.dataManager.set(EntityPeachickBlue.WATERED, Boolean.valueOf(true));
            this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
        }
        else
            this.dataManager.set(EntityPeachickBlue.WATERED, Boolean.valueOf(false));
    }

    public float getEntityAge() {
        return this.dataManager.get(EntityPeachickBlue.AGE).floatValue();
    }

    public void setEntityAge(float age) {
        this.dataManager.set(EntityPeachickBlue.AGE, Float.valueOf(age));
    }

    protected void fall(float p_70069_1_) {}

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected SoundEvent getAmbientSound() {

        int happy = 0;
        int num = 0;

        if (this.getWatered())
            happy++;
        if (this.getFed())
            happy++;

        if (happy == 2)
            num = 20;
        else if (happy == 1)
            num = 40;
        else
            num = 100;

        Random rand = new Random();
        int chooser = rand.nextInt(num);

        if (chooser == 0)
            return ModSoundEvents.peacock1;
        else if (chooser == 1)
            return ModSoundEvents.peacock2;
        else if (chooser == 2)
            return ModSoundEvents.peacock3;
        else if (chooser == 3)
            return ModSoundEvents.peacock4;
        else if (chooser == 4)
            return ModSoundEvents.peacock5;
        else if (chooser == 5)
            return null;
        else if (chooser == 6)
            return ModSoundEvents.peacock7;
        else if (chooser == 7)
            return ModSoundEvents.peacock8;
        else if (chooser == 8)
            return ModSoundEvents.peacock9;
        else if (chooser == 9)
            return ModSoundEvents.peacock10;
        else
            return null;

    }

    @Override
    protected SoundEvent getHurtSound() {
        Random rand = new Random();
        int chooser = rand.nextInt(2);

        if (chooser == 0)
            return ModSoundEvents.peacockHurt1;
        else
            return ModSoundEvents.peacockHurt2;
    }

    @Override
    protected SoundEvent getDeathSound() {
        Random rand = new Random();
        int chooser = rand.nextInt(2);

        if (chooser == 0)
            return ModSoundEvents.peacockHurt1;
        else
            return ModSoundEvents.peacockHurt2;
    }

    @Override
    public void playLivingSound() {
        SoundEvent soundevent = this.getAmbientSound();

        if (soundevent != null)
            this.playSound(soundevent, this.getSoundVolume() - .9F, this.getSoundPitch() + .4F - this.getEntityAge() * 2);
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.01F, 1.4F);
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed
     * it (wheat, carrots or seeds depending on the animal type)
     */
    @Override
    public boolean isBreedingItem(@Nullable ItemStack stack) {
        return stack != ItemStack.EMPTY && EntityPeachickBlue.TEMPTATION_ITEMS.contains(stack.getItem());
    }

    /*
     * public static void func_189789_b(DataFixer p_189789_0_) {
     * EntityLiving.func_189752_a(p_189789_0_, "Peachick Blue"); }
     */

    /**
     * Get the experience points the entity currently has.
     */

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void updatePassenger(Entity passenger) {
        super.updatePassenger(passenger);
        float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
        float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
        float f2 = 0.1F;
        float f3 = 0.0F;
        passenger.setPosition(this.posX + 0.1F * f, this.posY + this.height * 0.5F + passenger.getYOffset() + 0.0D, this.posZ - 0.1F * f1);

        if (passenger instanceof EntityLivingBase)
            ((EntityLivingBase) passenger).renderYawOffset = this.renderYawOffset;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
}
