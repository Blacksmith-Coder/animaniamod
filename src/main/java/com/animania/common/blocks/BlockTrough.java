package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.tileentities.TileEntityInvisiblock;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.ItemHandlerHelper;

public class BlockTrough extends BlockContainer
{
	private String name = "block_trough";
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 3);
	protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.25D, 2.0D, 0.3D, 0.75D);
	protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(-1.0D, 0.0D, 0.25D, 1.0D, 0.3D, 0.75D);
	protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.25D, 0.0D, -1.0D, 0.75D, 0.3D, 1.0D);
	protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 0.3D, 2.0D);

	public BlockTrough()
	{
		super(Material.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.MODID, name));
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.MODID + "_" + name);
		setCreativeTab(Animania.TabAnimaniaResources);
	}

	@Override
	public String getLocalizedName()
	{
		return I18n.translateToLocal("tile.animania_block_trough.name");
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);

		if (entityIn != null && entityIn instanceof EntityItem)
		{

			EntityItem entityitem = (EntityItem) entityIn;
			ItemStack stack = entityitem.getEntityItem();

			// SOLIDS
			if (!stack.isEmpty() && stack.getItem() == Items.WHEAT)
			{
				if ((!te.itemHandler.getStackInSlot(0).isEmpty() && te.itemHandler.getStackInSlot(0).getCount() < 3) || te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
				{
					te.itemHandler.insertItem(0, new ItemStack(Items.WHEAT), false);
					stack.shrink(1);

				}

			}
			// LIQUIDS
			else if (hasFluid(stack, FluidRegistry.WATER) && te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
			{
				te.fluidHandler.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 0.6F, 0.8F);
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				entityitem.setEntityItemStack(newStack);

			} else if (hasFluid(stack, BlockHandler.fluidSlop) && te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
			{
				te.fluidHandler.fill(new FluidStack(BlockHandler.fluidSlop, 1000), true);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_SLIME_PLACE, SoundCategory.BLOCKS, 0.6F, 0.8F);

				IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				entityitem.setEntityItemStack(newStack);

			}

		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{

		switch (state.getValue(FACING))
		{
		case NORTH:
		default:
			return NORTH_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case WEST:
			return WEST_AABB;
		case EAST:
			return EAST_AABB;
		}
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{

		TileEntityTrough teChk = (TileEntityTrough) worldIn.getTileEntity(pos);

		if (teChk == null || teChk.getTroughType() == 0.0F)
		{

			EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3).getOpposite();
			state = state.withProperty(FACING, enumfacing);
			BlockPos blockpos = pos.north();
			boolean flag = this.blockState == worldIn.getBlockState(blockpos);

			if (!flag)
			{
				worldIn.setBlockState(pos, state, 3);
			}

			// System.out.println(placer.getHorizontalFacing().toString());

			if (placer.getHorizontalFacing().toString() == "south")
			{
				BlockPos invisipos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
				worldIn.setBlockState(invisipos, BlockHandler.blockInvisiblock.getDefaultState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(0);
			} else if (placer.getHorizontalFacing().toString() == "north")
			{
				BlockPos invisipos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
				worldIn.setBlockState(invisipos, BlockHandler.blockInvisiblock.getDefaultState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(1);
			} else if (placer.getHorizontalFacing().toString() == "east")
			{
				BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
				worldIn.setBlockState(invisipos, BlockHandler.blockInvisiblock.getDefaultState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(2);
			} else if (placer.getHorizontalFacing().toString() == "west")
			{
				BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
				worldIn.setBlockState(invisipos, BlockHandler.blockInvisiblock.getDefaultState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(3);
			}
		}

	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		EntityPlayer entityplayer = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);

		String dir = "";

		if (entityplayer != null)
		{
			dir = entityplayer.getHorizontalFacing().toString().trim();
		}

		BlockPos blockpos = pos.west();
		BlockPos blockpos1 = pos.east();
		BlockPos blockpos2 = pos.north();
		BlockPos blockpos3 = pos.south();

		if (dir.equals("north") && !worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos))
		{
			return false;
		}

		if (dir.equals("east") && !worldIn.getBlockState(blockpos2).getBlock().isReplaceable(worldIn, blockpos2))
		{
			return false;
		}

		if (dir.equals("south") && !worldIn.getBlockState(blockpos1).getBlock().isReplaceable(worldIn, blockpos1))
		{
			return false;
		}

		if (dir.equals("west") && !worldIn.getBlockState(blockpos3).getBlock().isReplaceable(worldIn, blockpos3))
		{
			return false;
		}

		return true;

	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing
	 * the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTrough();
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(BlockHandler.blockTrough, 1);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{

		String dir = state.getValue(FACING).toString();

		if (dir == "south")
		{
			BlockPos invisipos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
			worldIn.setBlockToAir(invisipos);
		} else if (dir == "north")
		{
			BlockPos invisipos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
			worldIn.setBlockToAir(invisipos);
		} else if (dir == "east")
		{
			BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
			worldIn.setBlockToAir(invisipos);
		} else if (dir == "west")
		{
			BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
			worldIn.setBlockToAir(invisipos);
		}

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.itemHandler.getStackInSlot(0));
		Random rand = new Random();

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(BlockHandler.blockTrough);
	}

	public boolean canDispenserPlace(World worldIn, BlockPos pos, ItemStack stack)
	{
		return stack.getMetadata() == 1 && pos.getY() >= 2 && worldIn.getDifficulty() != EnumDifficulty.PEACEFUL;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | state.getValue(FACING).getIndex();
		return i;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = playerIn.getHeldItem(hand);
		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);

		if (worldIn.isRemote)
		{
			return true;

		}
		// SOLIDS
		else if (!heldItem.isEmpty() && heldItem.getItem() == Items.WHEAT)
		{
			if ((!te.itemHandler.getStackInSlot(0).isEmpty() && te.itemHandler.getStackInSlot(0).getCount() < 3) || te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
			{
				te.itemHandler.insertItem(0, new ItemStack(Items.WHEAT), false);
				if (!playerIn.isCreative())
					heldItem.shrink(1);
				return true;
			}

		}
		// LIQUIDS
		else if (hasFluid(heldItem, FluidRegistry.WATER) && te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
		{
			te.fluidHandler.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 0.6F, 0.8F);
			if (!playerIn.isCreative())
			{
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(heldItem);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				playerIn.setHeldItem(hand, newStack);
			}
			return true;

		} else if (hasFluid(heldItem, BlockHandler.fluidSlop) && te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
		{
			te.fluidHandler.fill(new FluidStack(BlockHandler.fluidSlop, 1000), true);
			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_SLIME_PLACE, SoundCategory.PLAYERS, 0.6F, 0.8F);
			if (!playerIn.isCreative())
			{
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(heldItem);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				playerIn.setHeldItem(hand, newStack);
			}
			return true;

		}
		// EMPTY SOLIDS
		else if (heldItem.isEmpty() && !te.itemHandler.getStackInSlot(0).isEmpty())
		{
			ItemStack extract = te.itemHandler.extractItem(0, 1, false);
			playerIn.inventory.addItemStackToInventory(extract);
			return true;
		}
		// EMPTY LIQUIDS
		else if (!heldItem.isEmpty() && FluidUtil.getFluidHandler(heldItem) != null && FluidUtil.getFluidContained(heldItem) == null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().amount >= 1000)
		{
			FluidStack fluidStack = te.fluidHandler.drain(1000, true);
			if (!playerIn.isCreative())
			{
				IFluidHandlerItem handler;
				if (heldItem.getCount() > 1)
				{
					ItemStack heldItem1 = heldItem.copy();
					heldItem1.setCount(1);
					handler = FluidUtil.getFluidHandler(heldItem1);

				} else
				{
					handler = FluidUtil.getFluidHandler(heldItem);
				}

				handler.fill(fluidStack, true);
				ItemStack newstack = handler.getContainer();

				if (heldItem.getCount() > 1)
				{
					heldItem.shrink(1);
					playerIn.inventory.addItemStackToInventory(newstack);
				} else
					playerIn.setHeldItem(hand, newstack);

			}
			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
			return true;
		}
		return false;

	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	{

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
		if (!te.itemHandler.getStackInSlot(0).isEmpty())
			return ItemHandlerHelper.calcRedstoneFromInventory(te.itemHandler);

		if (te.fluidHandler.getFluid() != null)
		{
			int fluid = te.fluidHandler.getFluidAmount();
			return fluid / 66;
		}

		return 0;
	}

	public String getName()
	{
		return name;
	}

	private boolean hasFluid(ItemStack stack, Fluid fluid)
	{
		return FluidUtil.getFluidContained(stack) != null && FluidUtil.getFluidContained(stack).amount >= 1000 && FluidUtil.getFluidContained(stack).getFluid() == fluid;
	}

}