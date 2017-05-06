package com.animania.common.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.animania.Animania;

public class ItemCookedHerefordRoast extends ItemFood {
	private final String name = "cooked_hereford_roast";

	public ItemCookedHerefordRoast() {
		super (12, 12F, true); 
		this.setAlwaysEdible();
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
		if (Animania.customMobDrops) {
			this.setCreativeTab(null);
		} else {
			this.setCreativeTab(Animania.TabAnimaniaResources);
		}
	}


	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.EAT;
	}

	protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer) {
		if (!worldObj.isRemote && Animania.foodsGiveBonusEffects)
		{
			entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 4, 1, false, false));
		}
	}

	public String getName()
	{
		return name;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		if (Animania.foodsGiveBonusEffects) 
			list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.instanthealth"));
			list.add(TextFormatting.BOLD.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));
		
	}


}
