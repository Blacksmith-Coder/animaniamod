package com.animania.entities.rodents;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.AnimaniaAchievements;
import com.animania.ModSoundEvents;
import com.animania.entities.chickens.EntityChickLeghorn;
import com.animania.entities.chickens.EntityChickOrpington;
import com.animania.entities.chickens.EntityChickPlymouthRock;
import com.animania.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.entities.chickens.EntityChickWyandotte;
import com.google.common.collect.Sets;

public class EntityFerretGrey extends EntityTameable
{
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityFerretGrey.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityFerretGrey.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityFerretGrey.class, DataSerializers.BOOLEAN);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Items.MUTTON, Items.EGG, Animania.brownEgg, Items.CHICKEN, Animania.rawWyandotteChicken, Animania.rawRhodeIslandRedChicken, Animania.rawRhodeIslandRedChicken, Animania.rawOrpingtonChicken});
	private int fedTimer;
	private int wateredTimer;
	private int happyTimer;
	private int tamedTimer;
	public int blinkTimer;

	public EntityFerretGrey(World worldIn)
	{
		super(worldIn);
		this.setSize(.75F, .40F);
		this.stepHeight = 1.1F;
		this.fedTimer = Animania.feedTimer + rand.nextInt(100);
		this.wateredTimer = Animania.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 70 + rand.nextInt(70);

	}

	protected void initEntityAI()
	{
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(0, new EntityAISwimmingRodents(this));
		this.tasks.addTask(1, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(2, this.aiSit);
		this.entityAIEatGrass = new EntityAIRodentEat(this);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.2F));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(3, new EntityAIFerretFindFood(this, 1.0D));
		this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(2, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(3, new EntityAIRodentEat(this));
		this.tasks.addTask(5, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(6, this.entityAIEatGrass);
		this.tasks.addTask(7, new EntityAIWanderRodent(this, 1.2D));
		this.tasks.addTask(8, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityChickLeghorn.class, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityChickOrpington.class, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityChickPlymouthRock.class, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityChickRhodeIslandRed.class, false));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityChickWyandotte.class, false));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}
	
	public int getVerticalFaceSpeed()
	{
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.setOwnerId(player.getUniqueID());
		this.setIsTamed(true);
		//this.setTamed(true);
		this.setSitting(false);
		player.addStat(AnimaniaAchievements.GreyFerret, 1);
		this.entityAIEatGrass.startExecuting();
		if (player.hasAchievement(AnimaniaAchievements.WhiteFerret) && player.hasAchievement(AnimaniaAchievements.GreyFerret)) {
			player.addStat(AnimaniaAchievements.Ferrets, 1);
		}
		if (!player.capabilities.isCreativeMode)
		{
			if (stack != ItemStack.EMPTY) {
				stack.setCount(stack.getCount()-1); 
			}
		}
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte)18);
	}

	public int eatTimer;
	public EntityAIRodentEat entityAIEatGrass;
	private int damageTimer;

	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		int happyDrops = 0;

		if (this.getWatered()) {
			happyDrops++;
		} 
		if (this.getFed()) {
			happyDrops++;
		} 

		Item dropItem;
		if (Animania.customMobDrops) {
			String drop = Animania.ferretDrop;
			dropItem = getItem(drop);
		} else {
			dropItem = null;
		}

		if (happyDrops == 2) {
			this.dropItem(dropItem, 1 + lootlevel);
		} else if (happyDrops == 1) {
			this.dropItem(dropItem, 1 + lootlevel);
		}

	}
	
	private Item getItem(String moditem) {
		Item bob = Item.getByNameOrId(moditem);
		return bob;
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;
		
		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
			this.setWatered(true);
			this.setInLove(player);
			return true;
		} else if (stack != ItemStack.EMPTY && stack.getItem() == Items.NAME_TAG) {
			if (!stack.hasDisplayName())
			{
				return false;
			}
			else 
			{
				EntityLiving entityliving = (EntityLiving)this;
				entityliving.setCustomNameTag(stack.getDisplayName());
				entityliving.enablePersistence();
				stack.setCount(stack.getCount()-1);
				this.setIsTamed(true);
				this.setTamed(true);
				this.setOwnerId(player.getUniqueID());
				return true;
			}	

		} else if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack) && !this.isSitting() && !player.isSneaking()) {
			this.aiSit.setSitting(true);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase)null);
			return true;
		} else if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack) && this.isSitting() && !player.isSneaking()) {
			this.aiSit.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase)null);
			return true;
		} else if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack) && player.isSneaking()) {
			this.aiSit.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase)null);
			this.setOwnerId(null);
			this.setIsTamed(false);
			this.setTamed(false);
			return true;
		} else {
			return super.processInteract(player, hand);
		}
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

		//Custom Knockback		
		if (entityIn instanceof EntityPlayer) {
			((EntityLivingBase) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);
		}


		return flag;
	}
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
		this.dataManager.register(TAMED, Boolean.valueOf(false));

	}

	/*
	public static void func_189792_b(DataFixer p_189792_0_)
	{
		EntityLiving.func_189752_a(p_189792_0_, "Grey Ferret");
	}
	*/

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("IsTamed", this.getIsTamed());

	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setIsTamed(compound.getBoolean("IsTamed"));

	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player)
    {
        return true;
    }

	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 0;

		if (this.getWatered()) {
			happy++;
		} 
		if (this.getFed()) {
			happy++;
		} 

		if (happy == 2) {
			num = 10;
		} else if (happy == 1) {
			num = 20;
		} else {
			num = 40;
		}

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0) {
			return ModSoundEvents.ferretLiving1;
		} else if (chooser == 1){
			return ModSoundEvents.ferretLiving2;
		} else if (chooser == 2){
			return ModSoundEvents.ferretLiving3;
		} else if (chooser == 3){
			return ModSoundEvents.ferretLiving4;
		} else if (chooser == 4){
			return ModSoundEvents.ferretLiving5;
		} else if (chooser == 5){
			return ModSoundEvents.ferretLiving6;
		} else {
			return null;
		}

	}


	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0) {
			return ModSoundEvents.ferretHurt1;
		} else if (chooser == 1) {
			return ModSoundEvents.ferretHurt1;
		} else {
			return null;
		}
	}

	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.ferretHurt1;	
	}


	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch());
		}
	}

	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.5F);
	}




	@Override
	public void onLivingUpdate()
	{
		if (this.world.isRemote)
		{
			this.eatTimer = Math.max(0, this.eatTimer - 1);
		}
		
		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 100 + rand.nextInt(100);
			}
		}

		if (this.fedTimer > -1) {
			this.fedTimer--;

			if (fedTimer == 0) {
				this.setFed(false);
			}
		}

		if (this.wateredTimer > -1) {
			this.wateredTimer--;

			if (wateredTimer == 0) {
				this.setWatered(false);
			}
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (!fed && !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if(Animania.animalsStarve)
			{
				if(this.damageTimer >= Animania.starvationTimer)
				{
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		}
		else if (!fed || !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));
		}

		if (this.happyTimer > -1) {
			this.happyTimer--;
			if (happyTimer == 0) {
				happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && Animania.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + (double)(rand.nextFloat() * width)) - (double)width, posY + 1.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width)) - (double)width, d, d1, d2);
				}
			}
		}

		if (this.tamedTimer > -1) {
			this.tamedTimer--;
			if (tamedTimer == 0) {
				tamedTimer = 120;

				if (this.getIsTamed() && Animania.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.02D;
					double d1 = rand.nextGaussian() * 0.02D;
					double d2 = rand.nextGaussian() * 0.02D;
					world.spawnParticle(EnumParticleTypes.HEART, (posX + (double)(rand.nextFloat() * width)) - (double)width, posY + 1D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width)) - (double)width, d, d1, d2);
				}
			}
		}


		super.onLivingUpdate();
	}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
		{
			this.eatTimer = 80;
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}

	public boolean getFed()
	{
		return ((Boolean)this.dataManager.get(FED)).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(FED, Boolean.valueOf(true));
			this.fedTimer = Animania.feedTimer + rand.nextInt(100);
			this.setHealth(this.getHealth()+1.0F);
		}
		else
		{
			this.dataManager.set(FED, Boolean.valueOf(false));
		}
	}

	public boolean getWatered()
	{
		return ((Boolean)this.dataManager.get(WATERED)).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(WATERED, Boolean.valueOf(true));
			this.wateredTimer = Animania.waterTimer + rand.nextInt(100);
		}
		else
		{
			this.dataManager.set(WATERED, Boolean.valueOf(false));
		}
	}

	public boolean getIsTamed()
	{
		return ((Boolean)this.dataManager.get(TAMED)).booleanValue();
	}

	public void setIsTamed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(TAMED, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(TAMED, Boolean.valueOf(false));
		}
	}


	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : (this.eatTimer >= 4 && this.eatTimer <= 176 ? 1.0F : (this.eatTimer < 4 ? ((float)this.eatTimer - p_70894_1_) / 4.0F : -((float)(this.eatTimer - 80) - p_70894_1_) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 176)
		{
			float f = ((float)(this.eatTimer - 4) - p_70890_1_) / 24.0F;
			return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 150F) * MathHelper.sin(f * 28.7F); 
		}
		else
		{
			return this.eatTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F; 
		}
	}

	public EntityFerretGrey createChild(EntityAgeable ageable)
	{
		return null;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && TEMPTATION_ITEMS.contains(stack.getItem());
	}
}