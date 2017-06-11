package com.animania.common.entities.cows;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class EntityCowFriesian extends EntityCowBase
{

	public EntityCowFriesian(World world)
	{
		super(world);
		this.cowType = CowType.FRIESIAN;
		this.milk = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian);
	}

}