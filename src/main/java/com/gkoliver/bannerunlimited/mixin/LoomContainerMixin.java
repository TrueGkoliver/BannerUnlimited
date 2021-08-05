package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.LoomContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.IntReferenceHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import javax.annotation.Nullable;

@Mixin(LoomContainer.class)
public abstract class LoomContainerMixin extends Container {

    @Shadow @Final
    private Slot slotBanner;
    @Shadow @Final
    private Slot slotDye;
    @Shadow @Final
    private Slot slotPattern;
    @Shadow @Final
    private Slot output;
    @Shadow
    private long field_226622_j_;
    @Shadow @Final
    private IntReferenceHolder field_217034_d;
    @Shadow
    public void createOutputStack() {}
    protected LoomContainerMixin(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }
    @ModifyConstant(method="Lnet/minecraft/inventory/container/LoomContainer;onCraftMatrixChanged(Lnet/minecraft/inventory/IInventory;)V", constant = @Constant(intValue = 6))
    public int modifyCraftMatrixAmount(int old) {
        return BannerUnlimited.getAmountAllowed();
    }
    @Shadow
    public abstract boolean canInteractWith(PlayerEntity playerIn);
}
