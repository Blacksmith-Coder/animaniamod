package com.animania.common.items;

import com.animania.common.blocks.IMetaBlockName;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SubtypesItemBlock extends ItemBlock
{

	public SubtypesItemBlock(Block block)
	{
		super(block);
		this.setRegistryName(block.getRegistryName());
		GameRegistry.register(this);
		this.hasSubtypes = true;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return block.getUnlocalizedName() + "_" + ((IMetaBlockName)block).getSpecialName(stack);
	}

}
