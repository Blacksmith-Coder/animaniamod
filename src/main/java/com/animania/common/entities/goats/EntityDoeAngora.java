package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoeAngora extends EntityDoeBase
{

	public EntityDoeAngora(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ANGORA;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}

}
