package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screen.LoomScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.LoomContainer;
import net.minecraft.item.BannerItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(LoomScreen.class)
public abstract class LoomScreenMixin extends ContainerScreen<LoomContainer> {
    @Nullable @Shadow
    private List<Pair<BannerPattern, DyeColor>> field_230155_n_;
    @Shadow
    private ItemStack field_214119_q;
    @Shadow
    private ItemStack field_214120_r;
    @Shadow
    private ItemStack field_214121_s;
    @Shadow
    private boolean field_214123_u;
    @Shadow
    private boolean field_214124_v;
    @Shadow
    private boolean field_214125_w;

    public LoomScreenMixin(LoomContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }
    @ModifyConstant(method="Lnet/minecraft/client/gui/screen/LoomScreen;func_214111_b()V", constant = @Constant(intValue = 6))
    private int modifyBannerPatternAmount(int orig) {
        return BannerUnlimited.getAmountAllowed();
    }
}
