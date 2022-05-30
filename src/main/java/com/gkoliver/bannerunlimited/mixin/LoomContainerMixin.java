package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import javax.annotation.Nullable;

@Mixin(LoomMenu.class)
public abstract class LoomContainerMixin extends AbstractContainerMenu {

    @Shadow @Final
    private Slot bannerSlot; //Slot banner...
    @Shadow @Final
    private Slot dyeSlot; //Slot dye
    @Shadow @Final
    private Slot patternSlot; //Slot pattern
    @Shadow @Final
    private Slot resultSlot; //Slot output?
    @Shadow
    private long lastSoundTime;
    protected LoomContainerMixin(int p_39859_, Inventory p_39860_, final ContainerLevelAccess p_39861_) {
        super(MenuType.LOOM, p_39859_);
    }
    @ModifyConstant(method="Lnet/minecraft/world/inventory/LoomMenu;slotsChanged(Lnet/minecraft/world/Container;)V", constant = @Constant(intValue = 6))
    public int modifyCraftMatrixAmount(int old) {
        return BannerUnlimited.getAmountAllowed();
    }
}
