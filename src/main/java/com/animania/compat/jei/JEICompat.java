package com.animania.compat.jei;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
//import mezz.jei.ingredients.IngredientBlacklist;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

@JEIPlugin
public class JEICompat extends BlankModPlugin
{

    private ItemStack slopBucket   = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
    private ItemStack milkHolstein = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkHolstein);
    private ItemStack milkFriesian = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian);
    private ItemStack milkJersey = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkJersey);
    private ItemStack milkGoat = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkGoat);
    private ItemStack milkSheep = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkSheep);

    @Override
    public void register(@Nonnull IModRegistry registry) {
        registry.addDescription(new ItemStack(BlockHandler.blockTrough), "text.jei.trough");
        registry.addDescription(new ItemStack(BlockHandler.blockMud), "text.jei.mud");
        registry.addDescription(new ItemStack(Items.WHEAT_SEEDS), "text.jei.seeds");
        registry.addDescription(new ItemStack(ItemHandler.truffle), "text.jei.truffle");
        registry.addDescription(new ItemStack(BlockHandler.blockNest), "text.jei.nest");
        registry.addDescription(this.slopBucket, "text.jei.slop", "text.jei.slop.craft");
        registry.addDescription(this.milkHolstein, "text.jei.milkholstein");
        registry.addDescription(this.milkFriesian, "text.jei.milkfriesian");
        registry.addDescription(this.milkFriesian, "text.jei.milkjersey");
        registry.addDescription(this.milkGoat, "text.jei.milkgoat");
        registry.addDescription(this.milkSheep, "text.jei.milksheep");
        registry.addDescription(new ItemStack(ItemHandler.salt), "text.jei.salt");

        /*
        IngredientBlacklist blacklist = new IngredientBlacklist(registry.getIngredientRegistry());
        blacklist.addIngredientToBlacklist(new ItemStack(ItemHandler.dolly));
        */
    }

}
