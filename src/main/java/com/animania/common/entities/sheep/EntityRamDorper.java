package com.animania.common.entities.sheep;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityRamDorper extends EntityRamBase
{

	public EntityRamDorper(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.DORPER;
		this.dropRaw = ItemHandler.rawMutton;
		this.dropCooked = ItemHandler.cookedMutton;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 15987699;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 1776411;
	}
	
}
