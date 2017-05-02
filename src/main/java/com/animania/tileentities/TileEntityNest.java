package com.animania.tileentities;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;

public class TileEntityNest extends TileEntity implements ITickable
{
	private int nestType;
	private int nestRotation;
	private GameProfile playerProfile;
	private int dragonAnimatedTicks;
	private boolean dragonAnimated;
	private static PlayerProfileCache profileCache;
	private static MinecraftSessionService sessionService;

	public static void setProfileCache(PlayerProfileCache profileCacheIn)
	{
		profileCache = profileCacheIn;
	}

	public static void setSessionService(MinecraftSessionService sessionServiceIn)
	{
		sessionService = sessionServiceIn;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setByte("nestType", (byte)(this.nestType & 255));
		return compound;
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.nestType = compound.getByte("nestType");
	}
	

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
		this.world.notifyBlockUpdate(pos, this.blockType.getDefaultState(),this.blockType.getDefaultState(), 1);
		
	}
	
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }
	
	public void update()
	{

	}

	@SideOnly(Side.CLIENT)
	public float getAnimationProgress(float p_184295_1_)
	{
		return this.dragonAnimated ? (float)this.dragonAnimatedTicks + p_184295_1_ : (float)this.dragonAnimatedTicks;
	}

	@Nullable
	public GameProfile getPlayerProfile()
	{
		return this.playerProfile;
	}

	public void setType(int type)
	{
		this.nestType = type;
		this.playerProfile = null;
	}


	public static GameProfile updateGameprofile(GameProfile input)
	{
		if (input != null && !StringUtils.isNullOrEmpty(input.getName()))
		{
			if (input.isComplete() && input.getProperties().containsKey("textures"))
			{
				return input;
			}
			else if (profileCache != null && sessionService != null)
			{
				GameProfile gameprofile = profileCache.getGameProfileForUsername(input.getName());

				if (gameprofile == null)
				{
					return input;
				}
				else
				{
					Property property = (Property)Iterables.getFirst(gameprofile.getProperties().get("textures"), null);

					if (property == null)
					{
						gameprofile = sessionService.fillProfileProperties(gameprofile, true);
					}

					return gameprofile;
				}
			}
			else
			{
				return input;
			}
		}
		else
		{
			return input;
		}
	}

	public int getNestType()
	{
		return this.nestType;
	}

	

}