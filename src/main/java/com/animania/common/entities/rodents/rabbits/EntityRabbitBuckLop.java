package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityRabbitBuckLop extends EntityRabbitBuckBase
{

	public EntityRabbitBuckLop(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.LOP;
		this.dropRaw = Items.RABBIT;
		this.dropCooked = Items.COOKED_RABBIT;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16513763;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 12883817;
	}
}