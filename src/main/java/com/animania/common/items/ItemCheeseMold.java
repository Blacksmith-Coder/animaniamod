package com.animania.common.items;

import java.util.List;

import com.animania.Animania;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCheeseMold extends ItemShears
{
    private String    name      = "cheese_mold";
    public int        numChants;
    private ItemStack emptyItem = null;

    public ItemCheeseMold() {
        this.setCreativeTab(Animania.TabAnimaniaResources);
        this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
        GameRegistry.register(this);
        this.setUnlocalizedName(Animania.MODID + "_" + this.name);
        this.setMaxDamage(100);

    }

    @Override
    public boolean hasContainerItem() {
        return true;
    }

    public void setEmptyItem(ItemStack ei) {
        this.emptyItem = ei;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        int dmg = stack.getItemDamage();

        if (dmg == 100)
            return new ItemStack(stack.getItem(), 0, 100);
        ItemStack tr = copyStack(stack, 1);
        tr.setItemDamage(dmg + 1);
        return tr;
    }

    public static ItemStack copyStack(ItemStack stack, int n) {
        return new ItemStack(stack.getItem(), n, stack.getItemDamage());
    }

    public void removeItem(EntityPlayer ep, ItemStack removeitem) {
        IInventory inv = ep.inventory;
        for (int i = 0; i < inv.getSizeInventory(); i++)
            if (inv.getStackInSlot(i) != ItemStack.EMPTY) {
                ItemStack j = inv.getStackInSlot(i);
                if (j.getItem() != null && j.getItem() == removeitem.getItem()) {
                    inv.setInventorySlotContents(i, null);
                    break;
                }
            }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
        list.add(TextFormatting.GOLD + I18n.translateToLocal("item.animania_cheese_mold.desc"));
    }

}
