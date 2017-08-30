package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoeKiko extends EntityDoeBase
{

	public EntityDoeKiko(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KIKO;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}

}
