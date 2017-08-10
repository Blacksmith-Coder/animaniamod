package com.animania.common.items;

import java.util.Iterator;
import java.util.List;

import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

public class ItemDolly extends AnimaniaItem
{

	public static final String TILE_DATA_KEY = "tileData";

	public ItemDolly()
	{
		super("dolly");
		this.setMaxStackSize(1);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		if (hasTileData(stack))
		{
			return getItemStack(stack).getDisplayName();
		}

		return "";
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		Block block = world.getBlockState(pos).getBlock();
		ItemStack stack = player.getHeldItem(hand);
		if (hasTileData(stack))
		{
			Vec3d vec = player.getLookVec();
			EnumFacing facing2 = EnumFacing.getFacingFromVector((float) vec.xCoord, 0f, (float) vec.zCoord);
			BlockPos pos2 = pos;
			Block containedblock = getBlock(stack);
			int meta = getMeta(stack);
			if (!world.getBlockState(pos2).getBlock().isReplaceable(world, pos2))
			{
				pos2 = pos.offset(facing);
			}

			if (world.getBlockState(pos2).getBlock().isReplaceable(world, pos2))
			{
				boolean canPlace = containedblock.canPlaceBlockAt(world, pos2);

				if (canPlace)
				{
					if (player.canPlayerEdit(pos, facing, stack) && world.mayPlace(containedblock, pos2, false, facing, (Entity) null))
					{
						boolean hasDirection = false;
						boolean hasAllDirection = false;
						
						Iterator<IProperty<?>> iterator = containedblock.getDefaultState().getPropertyKeys().iterator();
						while (iterator.hasNext())
						{
							IProperty<?> prop = iterator.next();
							Object[] allowedValues = prop.getAllowedValues().toArray();
							
							if (prop instanceof PropertyDirection && this.equal(allowedValues, EnumFacing.HORIZONTALS))
								hasDirection = true;
							
							if(prop instanceof PropertyDirection && this.equal(allowedValues, EnumFacing.VALUES))
							{
								hasAllDirection = true;
								facing2 = EnumFacing.getFacingFromVector((float) vec.xCoord, (float) vec.yCoord, (float) vec.zCoord);
							}

						}

						if(hasAllDirection)
							world.setBlockState(pos2, containedblock.getStateFromMeta(meta).withProperty(BlockDirectional.FACING, facing2.getOpposite()));
						else if (hasDirection)
							world.setBlockState(pos2, containedblock.getStateFromMeta(meta).withProperty(BlockHorizontal.FACING, facing2.getOpposite()));
						else
							world.setBlockState(pos2, containedblock.getStateFromMeta(meta));

						TileEntity tile = world.getTileEntity(pos2);
						if (tile != null)
						{
							tile.readFromNBT(getTileData(stack));
							tile.setPos(pos2);
							AnimaniaHelper.sendTileEntityUpdate(tile);
						}
						clearTileData(stack);
						player.playSound(containedblock.getSoundType().getPlaceSound(), 1.0f, 0.5f);
						player.setHeldItem(hand, ItemStack.EMPTY);
						return EnumActionResult.SUCCESS;
					}

				}
			}

		}

		return EnumActionResult.FAIL;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (hasTileData(stack))
		{
			if (entity instanceof EntityLivingBase)
			{
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1, 1, false, false));
			}
		}
		else
		{
			stack = ItemStack.EMPTY;
		}
	}

	public static boolean hasTileData(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();
			return tag.hasKey(TILE_DATA_KEY) && tag.hasKey("block") && tag.hasKey("meta");
		}
		return false;
	}

	public static boolean storeTileData(TileEntity tile, IBlockState state, ItemStack stack)
	{
		if (tile == null)
			return false;

		if (stack.isEmpty())
			return false;

		NBTTagCompound chest = new NBTTagCompound();
		chest = tile.writeToNBT(chest);

		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		if (tag.hasKey(TILE_DATA_KEY))
			return false;

		tag.setTag(TILE_DATA_KEY, chest);

		tag.setString("block", state.getBlock().getRegistryName().toString());
		Item item = Item.getItemFromBlock(state.getBlock());
		tag.setInteger("meta", item.getHasSubtypes() ? state.getBlock().getMetaFromState(state) : 0);
		stack.setTagCompound(tag);
		return true;
	}

	public static void clearTileData(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();
			tag.removeTag(TILE_DATA_KEY);
			tag.removeTag("block");
			tag.removeTag("meta");

		}
	}

	public static NBTTagCompound getTileData(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();
			return tag.getCompoundTag(TILE_DATA_KEY);
		}
		return null;
	}

	public static Block getBlock(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();
			String name = tag.getString("block");
			return Block.getBlockFromName(name);
		}
		return Blocks.AIR;
	}

	public static int getMeta(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();
			int meta = tag.getInteger("meta");
			return meta;
		}
		return 0;
	}

	public static ItemStack getItemStack(ItemStack stack)
	{
		return new ItemStack(getBlock(stack), 1, getMeta(stack));
	}
	
	private boolean equal(Object[] a, Object[] b)
	{
		if (a.length != b.length)
			return false;
		
		List lA = Arrays.asList(a);
		List lB = Arrays.asList(b);

		return lA.containsAll(lB);
		
		
	}

}
