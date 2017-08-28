package com.animania.common.entities.sheep;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.ModSoundEvents;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityEweBase extends EntityAnimaniaSheep
{

	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityEweBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected int gestationTimer;

	public EntityEweBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.1F, 1.0F);
		this.stepHeight = 1.1F;
		this.gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200);
	}


	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();
		//	this.tasks.addTask(8, new EntityAIMateSheeps(this, 1.0D));

	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{

		if (this.world.isRemote)
			return null;

		int sheepCount = 0;
		List entities = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaSheep.class, 128, this.world, this);
		sheepCount = entities.size();

		if (sheepCount <= AnimaniaConfig.spawn.spawnLimitSheep)
		{

			int chooser = this.rand.nextInt(5);

			if (chooser == 0)
			{
				EntityRamBase entitySheep = this.sheepType.getMale(world);
				entitySheep.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entitySheep);
				entitySheep.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entitySheep.getUniqueID());
			}
			else if (chooser == 1)
			{
				EntityLambBase entityKid = this.sheepType.getChild(world);
				entityKid.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityKid);
				entityKid.setParentUniqueId(this.entityUniqueID);
			}
			else if (chooser > 2)
			{
				EntityRamBase entityBuck = this.sheepType.getMale(world);
				entityBuck.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityBuck);
				entityBuck.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityBuck.getUniqueID());
				EntityLambBase entityKid = this.sheepType.getChild(world);
				entityKid.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityKid);
				entityKid.setParentUniqueId(this.entityUniqueID);
			}

			this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

			if (this.rand.nextFloat() < 0.05F)
				this.setLeftHanded(true);
			else
				this.setLeftHanded(false);
		}
		return livingdata;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityEweBase.MATE_UNIQUE_ID, Optional.<UUID>absent());

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		if (this.getMateUniqueId() != null)
			compound.setString("MateUUID", this.getMateUniqueId().toString());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		String s;

		if (compound.hasKey("MateUUID", 8))
			s = compound.getString("MateUUID");
		else
		{
			String s1 = compound.getString("Mate");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

	}

	@Nullable
	public UUID getMateUniqueId()
	{
		return (UUID) ((Optional) this.dataManager.get(EntityEweBase.MATE_UNIQUE_ID)).orNull();
	}

	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityEweBase.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 0;

		if (this.getWatered())
			happy++;
		if (this.getFed())
			happy++;

		if (happy == 2)
			num = 10;
		else if (happy == 1)
			num = 20;
		else
			num = 40;

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.pig1;
		else if (chooser == 1)
			return ModSoundEvents.pig2;
		else if (chooser == 2)
			return ModSoundEvents.pig4;
		else if (chooser == 3)
			return ModSoundEvents.pig5;
		else if (chooser == 4)
			return ModSoundEvents.pig6;
		else if (chooser == 5)
			return ModSoundEvents.pig7;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.pigHurt1;
		else if (chooser == 1)
			return ModSoundEvents.pigHurt2;
		else
			return ModSoundEvents.pig3;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.pigHurt1;
		else if (chooser == 1)
			return ModSoundEvents.pigHurt2;
		else
			return ModSoundEvents.pig3;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 200 + this.rand.nextInt(200);

				// Check for Mate
				if (this.getMateUniqueId() != null)
				{
					String mate = this.getMateUniqueId().toString();
					boolean mateReset = true;

					List<EntityLivingBase> entities = AnimaniaHelper.getEntitiesInRange(EntityRamBase.class, 64, world, this);
					for (int k = 0; k <= entities.size() - 1; k++)
					{
						Entity entity = entities.get(k);
						if (entity != null)
						{
							UUID id = entity.getPersistentID();
							if (id.toString().equals(this.getMateUniqueId().toString()) && !entity.isDead)
							{
								mateReset = false;
								break;
							}
						}
					}

					if (mateReset)
						this.setMateUniqueId(null);

				}
			}
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (this.gestationTimer > -1 && this.getMateUniqueId() != null)
		{
			this.gestationTimer--;
			if (this.gestationTimer == 0)
			{

				this.gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(2000);

				UUID MateID = this.getMateUniqueId();
				List entities = AnimaniaHelper.getEntitiesInRange(EntityRamBase.class, 16, this.world, this);
				int esize = entities.size();
				for (int k = 0; k <= esize - 1; k++) {

					EntityRamBase entity = (EntityRamBase)entities.get(k);
					if (entity !=null && this.getFed() && this.getWatered() && entity.getPersistentID().equals(MateID)) {

						this.setInLove(null);

						if (!this.world.isRemote)
						{

							SheepType maleType = ((EntityRamBase) entity).sheepType;
							SheepType babyType = SheepType.breed(maleType, this.sheepType);

							EntityLambBase kid = babyType.getChild(world);
							kid.setPosition(this.posX, this.posY + .2, this.posZ);
							this.world.spawnEntity(kid);
							kid.setParentUniqueId(this.getPersistentID());
							this.playSound(ModSoundEvents.piglet1, 0.50F, 1.1F); //TODO Sheep Noises
							//BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, kid);
							//MinecraftForge.EVENT_BUS.post(event);
						}
					}
				}
			}
		}

		super.onLivingUpdate();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 80;
		else
			super.handleStatusUpdate(id);
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : this.eatTimer >= 4 && this.eatTimer <= 76 ? 1.0F : this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F : -(this.eatTimer - 80 - p_70894_1_) / 4.0F;
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 76)
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 24.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 150F * MathHelper.sin(f * 28.7F);
		}
		else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (EntityAnimaniaSheep.TEMPTATION_ITEMS.contains(stack.getItem()));
	}
}
