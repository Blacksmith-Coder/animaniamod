package com.animania.common.entities.cows;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.ModSoundEvents;
import com.animania.common.entities.cows.ai.EntityAIAttackMeleeBulls;
import com.animania.common.entities.cows.ai.EntityAIFollowMateCows;
import com.animania.common.entities.cows.ai.EntityAIMateCows;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBullBase extends EntityAnimaniaCow
{

	protected static final DataParameter<Boolean> FIGHTING = EntityDataManager.<Boolean>createKey(EntityBullAngus.class, DataSerializers.BOOLEAN);
	
	public EntityBullBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.6F, 1.8F);
		this.stepHeight = 1.1F;
		this.tasks.addTask(0, new EntityAIAttackMeleeBulls(this, 1.8D, false));
		this.tasks.addTask(1, new EntityAIFollowMateCows(this, 1.1D));
		this.tasks.addTask(6, new EntityAIMateCows(this, 1.0D));
		this.milkable = false;
		this.mateable = true;
	}
	
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityBullBase.FIGHTING, Boolean.valueOf(false));
	}
	
	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}
	
	
	
	public boolean getFighting()
	{
		return this.dataManager.get(EntityBullBase.FIGHTING).booleanValue();
	}

	public void setFighting(boolean fighting)
	{
		if (fighting)
			this.dataManager.set(EntityBullBase.FIGHTING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityBullBase.FIGHTING, Boolean.valueOf(false));
	}
	
	@Override
	public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
	{
		super.setAttackTarget(entitylivingbaseIn);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
			return false;
		else
			return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSourceHandler.bullDamage, 5.0F);
	
		if (flag)
			this.applyEnchantments(this, entityIn);

		// Custom Knockback
		if (entityIn instanceof EntityPlayer)
			((EntityLivingBase) entityIn).knockBack(this, 1, (this.posX - entityIn.posX)/2, (this.posZ - entityIn.posZ)/2);

		return flag;
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
			num = 18;
		else if (happy == 1)
			num = 36;
		else
			num = 60;

		Random rand = new Random();
		int chooser = rand.nextInt(num);
		if (chooser == 0)
			return ModSoundEvents.bullMoo1;
		else if (chooser == 1)
			return ModSoundEvents.bullMoo2;
		else if (chooser == 2)
			return ModSoundEvents.bullMoo3;
		else if (chooser == 3)
			return ModSoundEvents.bullMoo4;
		else if (chooser == 4)
			return ModSoundEvents.bullMoo5;
		else if (chooser == 5)
			return ModSoundEvents.bullMoo6;
		else if (chooser == 6)
			return ModSoundEvents.bullMoo7;
		else if (chooser == 7)
			return ModSoundEvents.bullMoo8;
		else if (chooser == 8)
			return ModSoundEvents.moo4;
		else if (chooser == 9)
			return ModSoundEvents.moo8;
		else if (chooser == 10)
			return ModSoundEvents.moo4;
		else
			return ModSoundEvents.moo8;
	}
	
	@Override
	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.angryBull1;
		else if (chooser == 1)
			return ModSoundEvents.angryBull2;
		else
			return ModSoundEvents.angryBull3;
	}
	
	@Override
	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.cowDeath1;
		else
			return ModSoundEvents.cowDeath2;
	}
	

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{

		if (!this.getFighting())
			return this.eatTimer <= 0 ? 0.0F : this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F : this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F : -(this.eatTimer - 160 - p_70894_1_) / 4.0F;
		else
			return 0.0F;

	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 156 && !this.getFighting())
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 64.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 100F * MathHelper.sin(f * 28.7F);
		} else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}
	
	@Override
	public EntityBullBase createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}
	
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		
		compound.setBoolean("Fighting", this.getFighting());

	}
	
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		
		this.setFighting(compound.getBoolean("Fighting"));

	}


}
