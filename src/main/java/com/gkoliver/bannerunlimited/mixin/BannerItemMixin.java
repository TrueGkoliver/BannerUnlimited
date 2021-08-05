package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;
@Mixin(BannerItem.class)
public class BannerItemMixin extends WallOrFloorItem {

    public BannerItemMixin(Block floorBlock, Block wallBlockIn, Properties propertiesIn) {
        super(floorBlock, wallBlockIn, propertiesIn);
    }
    //@ModifyConstant(method="appendHoverTextFromTileEntityTag", constant = @Constant(intValue = 6))
    /*public int getAmountOfBannerPatternsAllowed() {
        return BannerUnlimited.AMOUNT_ALLOWED;
    }*/



    @Inject(method="Lnet/minecraft/item/BannerItem;addInformation(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Ljava/util/List;Lnet/minecraft/client/util/ITooltipFlag;)V", at=@At("HEAD"), cancellable = true)
    public void addInformationI(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn, CallbackInfo ci) {
        appendHoverTextFromTileEntityTagMixin(stack, tooltip);
        ci.cancel();
    }
    //This function only exists to
    @OnlyIn(Dist.CLIENT)
    private static void appendHoverTextFromTileEntityTagMixin(ItemStack stack, List<ITextComponent> p_185054_1_) {
        CompoundNBT compoundnbt = stack.getChildTag("BlockEntityTag");
        if (compoundnbt != null && compoundnbt.contains("Patterns")) {
            ListNBT listnbt = compoundnbt.getList("Patterns", 10);

            for(int i = 0; i < listnbt.size() && i < BannerUnlimited.getAmountAllowed(); ++i) {
                CompoundNBT compoundnbt1 = listnbt.getCompound(i);
                DyeColor dyecolor = DyeColor.byId(compoundnbt1.getInt("Color"));
                BannerPattern bannerpattern = BannerPattern.byHash(compoundnbt1.getString("Pattern"));
                if (bannerpattern != null) {
                    p_185054_1_.add((new TranslationTextComponent("block.minecraft.banner." + bannerpattern.getFileName() + '.' + dyecolor.getTranslationKey())).func_240699_a_(TextFormatting.GRAY));
                }
            }

        }
    }
}
