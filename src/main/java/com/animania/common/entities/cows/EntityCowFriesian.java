package com.animania.common.entities.cows;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.cows.ai.EntityAICowEatGrass;
import com.animania.common.entities.cows.ai.EntityAIFindFood;
import com.animania.common.entities.cows.ai.EntityAIFindWater;
import com.animania.common.entities.cows.ai.EntityAIMateCows;
import com.animania.common.entities.cows.ai.EntityAIPanicCows;
import com.animania.common.entities.cows.ai.EntityAISwimmingCows;
import com.animania.common.entities.cows.ai.EntityAIWanderCow;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCowFriesian extends EntityAnimal
{
	private static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityCowFriesian.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityCowFriesian.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityCowFriesian.class, DataSerializers.BOOLEAN);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] { Items.WHEAT });
	private int happyTimer;
	private int gestationTimer;
	public int blinkTimer;
	private ItemStack milkFriesian;
	
	public EntityCowFriesian(World world)
	{
		super(world);
		this.setSize(1.4F, 1.8F);
		this.stepHeight = 1.1F;
		this.entityAIEatGrass = new EntityAICowEatGrass(this);
		this.tasks.addTask(1, new EntityAIFindFood(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWanderCow(this, 1.0D));
		this.tasks.addTask(3, new EntityAIFindWater(this, 1.1D));
		this.tasks.addTask(4, new EntityAIPanicCows(this, 2.0D));
		this.tasks.addTask(5, new EntityAIMateCows(this, 1.0D));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.YELLOW_FLOWER), false));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.RED_FLOWER), false));
		this.tasks.addTask(7, this.entityAIEatGrass);
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(10, new EntityAILookIdle(this));
		this.tasks.addTask(11, new EntityAISwimmingCows(this));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		this.gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + rand.nextInt(200);
		this.happyTimer = 60;
		this.blinkTimer = 100 + rand.nextInt(100);
		this.enablePersistence();
		this.milkFriesian = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian);

	}

	public static void registerFixesCow(DataFixer fixer)
	{
		EntityLiving.registerFixesMob(fixer, EntityCowFriesian.class);
	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{

		if (this.world.isRemote)
		{
			return null;
		}

		int cowCount = 0;
		int esize = this.world.loadedEntityList.size();
		for (int k = 0; k <= esize - 1; k++)
		{
			Entity entity = this.world.loadedEntityList.get(k);
			if (entity.getName().contains("Holstein") || entity.getName().contains("Friesian") || entity.getName().contains("Angus") || entity.getName().contains("Longhorn") || entity.getName().contains("Hereford"))
			{
				EntityAnimal ea = (EntityAnimal) entity;
				if (ea.hasCustomName() || ea.isInLove())
				{
					// cowCount = cowCount - 1;
				} else
				{
					cowCount = cowCount + 1;
				}
			}
		}

		if (cowCount <= AnimaniaConfig.spawn.spawnLimitCows)
		{

			int chooser = rand.nextInt(5);

			if (chooser == 0)
			{
				EntityBullFriesian entityCow = new EntityBullFriesian(this.world);
				entityCow.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityCow);
				entityCow.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityCow.getUniqueID());
			} else if (chooser == 1)
			{
				EntityCalfFriesian entityCow = new EntityCalfFriesian(this.world);
				entityCow.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityCow);
				entityCow.setParentUniqueId(this.entityUniqueID);
			} else if (chooser > 2)
			{
				EntityBullFriesian entityCow = new EntityBullFriesian(this.world);
				entityCow.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityCow);
				entityCow.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityCow.getUniqueID());
				EntityCalfFriesian entityCalf = new EntityCalfFriesian(this.world);
				entityCalf.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityCalf);
				entityCalf.setParentUniqueId(this.entityUniqueID);
			}

			this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

			if (this.rand.nextFloat() < 0.05F)
			{
				this.setLeftHanded(true);
			} else
			{
				this.setLeftHanded(false);
			}

		}

		return livingdata;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	public int eatTimer;
	private int fedTimer;
	private int wateredTimer;
	public EntityAICowEatGrass entityAIEatGrass;
	private int damageTimer;

	@Override
	protected void entityInit()
	{

		super.entityInit();
		this.dataManager.register(MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.21000000298023224D);
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.entityAIEatGrass.startExecuting();
		eatTimer = 80;
		player.addStat(AnimaniaAchievements.Friesian, 1);
		if (player.hasAchievement(AnimaniaAchievements.Angus) && player.hasAchievement(AnimaniaAchievements.Friesian) && player.hasAchievement(AnimaniaAchievements.Hereford) && player.hasAchievement(AnimaniaAchievements.Holstein) && player.hasAchievement(AnimaniaAchievements.Longhorn))
		{
			player.addStat(AnimaniaAchievements.Cows, 1);
		}
		if (!player.capabilities.isCreativeMode)
		{
			stack.setCount(stack.getCount() - 1);
		}
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		if (this.getMateUniqueId() != null)
		{
			if (this.getMateUniqueId() != null)
			{
				compound.setString("MateUUID", this.getMateUniqueId().toString());
			}
		}

		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());

	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		String s;

		if (compound.hasKey("MateUUID", 8))
		{
			s = compound.getString("MateUUID");
		} else
		{
			String s1 = compound.getString("Mate");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
		{
			this.setMateUniqueId(UUID.fromString(s));
		}
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));

	}

	@Nullable
	public UUID getMateUniqueId()
	{
		return (UUID) ((Optional) this.dataManager.get(MATE_UNIQUE_ID)).orNull();
	}

	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public boolean getFed()
	{
		return this.dataManager.get(FED).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		} else
		{
			this.dataManager.set(FED, Boolean.valueOf(false));
		}
	}

	public boolean getWatered()
	{
		return this.dataManager.get(WATERED).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		} else
		{
			this.dataManager.set(WATERED, Boolean.valueOf(false));
		}
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 0;

		if (this.getWatered())
		{
			happy++;
		}
		if (this.getFed())
		{
			happy++;
		}

		if (happy == 2)
		{
			num = 16;
		} else if (happy == 1)
		{
			num = 32;
		} else
		{
			num = 60;
		}

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0)
		{
			return ModSoundEvents.moo1;
		} else if (chooser == 1)
		{
			return ModSoundEvents.moo3;
		} else if (chooser == 2)
		{
			return ModSoundEvents.moo4;
		} else if (chooser == 3)
		{
			return ModSoundEvents.moo4;
		} else if (chooser == 4)
		{
			return ModSoundEvents.moo5;
		} else if (chooser == 5)
		{
			return ModSoundEvents.moo6;
		} else if (chooser == 6)
		{
			return ModSoundEvents.moo7;
		} else if (chooser == 7)
		{
			return ModSoundEvents.moo8;
		} else
		{
			return null;
		}

	}

	@Override
	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0)
		{
			return ModSoundEvents.cowHurt1;
		} else
		{
			return ModSoundEvents.cowHurt2;
		}
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0)
		{
			return ModSoundEvents.cowDeath1;
		} else
		{
			return ModSoundEvents.cowDeath2;
		}
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.10F, 1.0F);
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected Item getDropItem()
	{
		return Items.LEATHER;
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 100 + rand.nextInt(100);

				//Check for Mate
				if (this.getMateUniqueId() != null) {
					String mate = this.getMateUniqueId().toString();
					boolean mateReset = true;

					int esize = this.world.loadedEntityList.size();
					for (int k = 0; k <= esize - 1; k++)
					{
						Entity entity = this.world.loadedEntityList.get(k);
						if (entity != null) {
							UUID id = entity.getPersistentID();
							if (id.toString().equals(this.getMateUniqueId().toString()) && !entity.isDead) {
								mateReset = false;
								break;
							}
						}
					}

					if (mateReset) {
						this.setMateUniqueId(null);
					}

				}
			}
		}

		if (this.world.isRemote)
		{
			this.eatTimer = Math.max(0, this.eatTimer - 1);
		}

		if (this.fedTimer > -1)
		{
			this.fedTimer--;

			if (fedTimer == 0)
			{
				this.setFed(false);
			}
		}

		if (this.wateredTimer > -1)
		{
			this.wateredTimer--;

			if (wateredTimer == 0)
			{
				this.setWatered(false);
			}
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (!fed && !watered)
		{
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if (AnimaniaConfig.gameRules.animalsStarve)
			{
				if (this.damageTimer >= AnimaniaConfig.careAndFeeding.starvationTimer)
				{
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		} else if (!fed || !watered)
		{
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));
		}

		if (this.happyTimer > -1)
		{
			this.happyTimer--;
			if (happyTimer == 0)
			{
				happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + rand.nextFloat() * width) - width, posY + 1.5D + rand.nextFloat() * height, (posZ + rand.nextFloat() * width) - width, d, d1, d2);
				}
			}
		}

		if (this.gestationTimer > -1 && this.getMateUniqueId() != null)
		{
			this.gestationTimer--;
			if (gestationTimer == 0)
			{

				gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + rand.nextInt(2000);

				String MateID = this.getMateUniqueId().toString();

				int esize = this.world.loadedEntityList.size();
				for (int k = 0; k <= esize - 1; k++)
				{
					Entity entity = this.world.loadedEntityList.get(k);

					double xt = entity.posX;
					double yt = entity.posY;
					double zt = entity.posZ;
					int x1 = MathHelper.floor(this.posX);
					int y1 = MathHelper.floor(this.posY);
					int z1 = MathHelper.floor(this.posZ);
					double x2 = xt - x1;
					double y2 = yt - y1;
					double z2 = zt - z1;

					if (entity != null && this.getFed() && this.getWatered() && entity.getPersistentID().toString().equals(MateID) && x2 <= 10 && y2 <= 10 && z2 <= 10)
					{

						this.setInLove(null);

						if (!this.world.isRemote)
						{

							BabyEntitySpawnEvent event = null;

							if (entity instanceof EntityBullFriesian)
							{
								EntityCalfFriesian entityCalf = new EntityCalfFriesian(this.world);
								entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
								this.world.spawnEntity(entityCalf);
								entityCalf.setParentUniqueId(this.getPersistentID());
								this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
								event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);

							} else if (entity instanceof EntityBullAngus)
							{
								if (rand.nextInt(2) == 0)
								{
									EntityCalfFriesian entityCalf = new EntityCalfFriesian(this.world);
									entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
									this.world.spawnEntity(entityCalf);
									entityCalf.setParentUniqueId(this.getPersistentID());
									this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
									event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);

								} else
								{
									EntityCalfAngus entityCalf = new EntityCalfAngus(this.world);
									entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
									this.world.spawnEntity(entityCalf);
									entityCalf.setParentUniqueId(this.getPersistentID());
									this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
									event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);

								}
							} else if (entity instanceof EntityBullHereford)
							{
								if (rand.nextInt(2) == 0)
								{
									EntityCalfFriesian entityCalf = new EntityCalfFriesian(this.world);
									entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
									this.world.spawnEntity(entityCalf);
									entityCalf.setParentUniqueId(this.getPersistentID());
									this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
									event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);

								} else
								{
									EntityCalfHereford entityCalf = new EntityCalfHereford(this.world);
									entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
									this.world.spawnEntity(entityCalf);
									entityCalf.setParentUniqueId(this.getPersistentID());
									this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
									event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);

								}
							} else if (entity instanceof EntityBullHolstein)
							{
								if (rand.nextInt(2) == 0)
								{
									EntityCalfFriesian entityCalf = new EntityCalfFriesian(this.world);
									entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
									this.world.spawnEntity(entityCalf);
									entityCalf.setParentUniqueId(this.getPersistentID());
									this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
									event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);

								} else
								{
									EntityCalfHolstein entityCalf = new EntityCalfHolstein(this.world);
									entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
									this.world.spawnEntity(entityCalf);
									entityCalf.setParentUniqueId(this.getPersistentID());
									this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
									event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);

								}
							} else if (entity instanceof EntityBullLonghorn)
							{
								if (rand.nextInt(2) == 0)
								{
									EntityCalfFriesian entityCalf = new EntityCalfFriesian(this.world);
									entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
									this.world.spawnEntity(entityCalf);
									entityCalf.setParentUniqueId(this.getPersistentID());
									this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
									event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);

								} else
								{
									EntityCalfLonghorn entityCalf = new EntityCalfLonghorn(this.world);
									entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
									this.world.spawnEntity(entityCalf);
									entityCalf.setParentUniqueId(this.getPersistentID());
									this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
									event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);

								}
							}

							MinecraftForge.EVENT_BUS.post(event);

						}
					}
				}
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{

		int happyDrops = 0;

		if (this.getWatered())
		{
			happyDrops++;
		}
		if (this.getFed())
		{
			happyDrops++;
		}

		int j;
		int k;

		j = happyDrops + lootlevel;

		for (k = 0; k < j; ++k)
		{
			if (this.isBurning())
			{
				this.dropItem(Items.COOKED_BEEF, 1);
				this.dropItem(Items.LEATHER, 1);
			} else
			{
				this.dropItem(Items.BEEF, 1);
				this.dropItem(Items.LEATHER, 1);
			}
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;
		if (this.getFed() && this.getWatered() && stack != ItemStack.EMPTY && stack.getItem() == Items.BUCKET)
		{
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 0.8F);
			stack.shrink(1);
			if (stack.getCount() == 0)
			{
				player.setHeldItem(hand, milkFriesian);
			} else if (!player.inventory.addItemStackToInventory(milkFriesian))
			{
				player.dropItem(milkFriesian, false);
			}

			this.setWatered(false);

			return true;
		} else if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET)
		{
			{
				if (stack.getCount() == 1 && !player.capabilities.isCreativeMode)
				{
					player.setHeldItem(hand, new ItemStack(Items.BUCKET));
				} else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
				{
					player.dropItem(new ItemStack(Items.BUCKET), false);
				}

				eatTimer = 40;
				this.entityAIEatGrass.startExecuting();
				this.setWatered(true);
				this.setInLove(player);
				return true;
			}
		} else
		{
			return super.processInteract(player, hand);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
		{
			this.eatTimer = 160;
		} else
		{
			super.handleStatusUpdate(id);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : (this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F : (this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F : -(this.eatTimer - 160 - p_70894_1_) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 156)
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 64.0F;
			return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
		} else
		{
			return this.eatTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && this.isCowBreedingItem(stack.getItem());
	}

	private boolean isCowBreedingItem(Item itemIn)
	{
		return itemIn == Items.WHEAT || itemIn == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemIn == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}

	@Override
	public EntityCowFriesian createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}
}