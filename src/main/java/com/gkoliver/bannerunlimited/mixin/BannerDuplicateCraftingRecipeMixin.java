package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.BannerDuplicateRecipe;
import net.minecraft.world.item.crafting.CustomRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BannerDuplicateRecipe.class)
public abstract class BannerDuplicateCraftingRecipeMixin extends CustomRecipe {
    public BannerDuplicateCraftingRecipeMixin(ResourceLocation idIn) {
        super(idIn);
    }

    @ModifyConstant(method="Lnet/minecraft/world/item/crafting/BannerDuplicateRecipe;matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z", constant = @Constant(intValue = 6))
    private int modifyBannerPatternAmount(int orig) {
        return BannerUnlimited.getAmountAllowed();
    }
    @ModifyConstant(method="Lnet/minecraft/world/item/crafting/BannerDuplicateRecipe;assemble(Lnet/minecraft/world/inventory/CraftingContainer;)Lnet/minecraft/world/item/ItemStack;", constant = @Constant(intValue = 6))
    private int modifyBannerPatternAmount2(int orig) {
        return BannerUnlimited.getAmountAllowed();
    }
    public abstract boolean canFit(int width, int height);
}
