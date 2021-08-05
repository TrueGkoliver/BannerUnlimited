package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.BannerItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.BannerDuplicateRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BannerDuplicateRecipe.class)
public abstract class BannerDuplicateCraftingRecipeMixin extends SpecialRecipe  {
    public BannerDuplicateCraftingRecipeMixin(ResourceLocation idIn) {
        super(idIn);
    }

    @ModifyConstant(method="Lnet/minecraft/item/crafting/BannerDuplicateRecipe;matches(Lnet/minecraft/inventory/CraftingInventory;Lnet/minecraft/world/World;)Z", constant = @Constant(intValue = 6))
    private int modifyBannerPatternAmount(int orig) {
        return BannerUnlimited.getAmountAllowed();
    }
    @ModifyConstant(method="Lnet/minecraft/item/crafting/BannerDuplicateRecipe;getCraftingResult(Lnet/minecraft/inventory/CraftingInventory;)Lnet/minecraft/item/ItemStack;", constant = @Constant(intValue = 6))
    private int modifyBannerPatternAmount2(int orig) {
        return BannerUnlimited.getAmountAllowed();
    }
    public abstract boolean canFit(int width, int height);
    public abstract IRecipeSerializer<?> getSerializer();
}
