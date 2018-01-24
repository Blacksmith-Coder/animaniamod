package com.animania.common.recipes;

import java.util.ArrayList;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class CheeseRecipe9 implements IRecipe
{

    private final ItemStack recipeOutput;
    public final ArrayList  recipeItems = new ArrayList();
    private int             bucketSlot;
    private int             moldSlot;
    private ItemStack       moldStack;
    private ItemStack       milk        = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket,
            BlockHandler.fluidMilkJersey);

    public CheeseRecipe9() {
        this.recipeOutput = new ItemStack(ItemHandler.cheeseWheelJersey);
        this.recipeItems.add(new ItemStack(ItemHandler.cheeseMold));
        this.recipeItems.add(this.milk);

    }

    @Override
    public boolean matches(InventoryCrafting inv, World world) {

        ItemStack mold = ItemStack.EMPTY;
        ItemStack milk = ItemStack.EMPTY;
        ItemStack extra = ItemStack.EMPTY;

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack current = inv.getStackInSlot(i);

            if (!current.isEmpty())
                if (ItemStack.areItemsEqualIgnoreDurability(current, new ItemStack(ItemHandler.cheeseMold)) && mold.isEmpty()) {
                    mold = current.copy();
                    this.moldSlot = i;
                    this.moldStack = current.copy();
                }
                else if (ItemStack.areItemStacksEqual(current, this.milk) && milk.isEmpty()) {
                    milk = current.copy();
                    this.bucketSlot = i;
                }
                else
                    extra = current.copy();

        }

        return !milk.isEmpty() && !mold.isEmpty() && extra.isEmpty();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {

        NonNullList<ItemStack> bob = NonNullList.<ItemStack> withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        bob.set(this.bucketSlot, new ItemStack(Items.BUCKET));
        this.moldStack.setItemDamage(this.moldStack.getItemDamage() + 1);

        if (this.moldStack.getItemDamage() >= this.moldStack.getMaxDamage())
            this.moldStack = ItemStack.EMPTY;

        bob.set(this.moldSlot, this.moldStack);

        return bob;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public int getRecipeSize() {
        return this.recipeItems.size();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}
