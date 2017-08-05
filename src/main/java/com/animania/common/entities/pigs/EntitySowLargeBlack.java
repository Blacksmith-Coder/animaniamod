package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntitySowLargeBlack extends EntitySowBase
{

	public EntitySowLargeBlack(World world)
	{
		super(world);
		this.pigType = PigType.LARGE_BLACK;
		this.dropRaw = ItemHandler.rawLargeBlackPork;
		this.dropCooked = ItemHandler.cookedLargeBlackRoast;
	}

}