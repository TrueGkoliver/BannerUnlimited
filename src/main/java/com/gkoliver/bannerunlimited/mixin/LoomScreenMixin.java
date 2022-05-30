package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.LoomScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(LoomScreen.class)
public abstract class LoomScreenMixin extends AbstractContainerScreen<LoomMenu> {
    @Nullable @Shadow
    private List<Pair<BannerPattern, DyeColor>> resultBannerPatterns;
    @Shadow
    private ItemStack bannerStack;
    @Shadow
    private ItemStack dyeStack;
    @Shadow
    private ItemStack patternStack;
    @Shadow
    private boolean displayPatterns;
    @Shadow
    private boolean displaySpecialPattern;
    @Shadow
    private boolean hasMaxPatterns;

    public LoomScreenMixin(LoomMenu a, Inventory b, Component c) {
        super(a,b,c);
    }
    @ModifyConstant(method="Lnet/minecraft/client/gui/screens/inventory/LoomScreen;containerChanged()V", constant = @Constant(intValue = 6))
    private int modifyBannerPatternAmount(int orig) {
        return BannerUnlimited.getAmountAllowed();
    }
}
