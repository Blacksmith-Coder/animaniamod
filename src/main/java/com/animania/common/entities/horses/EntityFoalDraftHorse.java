package com.animania.common.entities.horses;

import net.minecraft.world.World;

public class EntityFoalDraftHorse extends EntityFoalBase
{

	public EntityFoalDraftHorse(World world)
	{
		super(world);
		this.horseType = HorseType.DRAFT;
	}

}

