package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoeFainting extends EntityDoeBase
{

	public EntityDoeFainting(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.FAINTING;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}

}
