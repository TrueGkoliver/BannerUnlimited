package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
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
public class BannerItemMixin extends StandingAndWallBlockItem {

    public BannerItemMixin(Block floorBlock, Block wallBlockIn, Item.Properties propertiesIn) {
        super(floorBlock, wallBlockIn, propertiesIn);
    }
    //@ModifyConstant(method="appendHoverTextFromTileEntityTag", constant = @Constant(intValue = 6))
    /*public int getAmountOfBannerPatternsAllowed() {
        return BannerUnlimited.AMOUNT_ALLOWED;
    }*/



    @Inject(method="Lnet/minecraft/world/item/BannerItem;appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;)V", at=@At("HEAD"), cancellable = true)
    public void addInformationI(ItemStack p_40538_, @Nullable Level p_40539_, List<Component> p_40540_, TooltipFlag p_40541_, CallbackInfo ci) {
        appendHoverTextFromTileEntityTagMixin(p_40538_, p_40540_);
        ci.cancel();
    }
    //This function only exists to override the public static one. It's not great.
    @OnlyIn(Dist.CLIENT)
    private static void appendHoverTextFromTileEntityTagMixin(ItemStack stack, List<Component> p_40544_) {
        CompoundTag compoundnbt = BlockItem.getBlockEntityData(stack);
        if (compoundnbt != null && compoundnbt.contains("Patterns")) {
            ListTag listnbt = compoundnbt.getList("Patterns", 10);

            for(int i = 0; i < listnbt.size() && i < BannerUnlimited.getAmountAllowed(); ++i) {
                CompoundTag compoundnbt1 = listnbt.getCompound(i);
                DyeColor dyecolor = DyeColor.byId(compoundnbt1.getInt("Color"));
                BannerPattern bannerpattern = BannerPattern.byHash(compoundnbt1.getString("Pattern"));
                if (bannerpattern != null) {
                    p_40544_.add((new TranslatableComponent("block.minecraft.banner." + bannerpattern.getFilename() + '.' + dyecolor.getName())).withStyle(ChatFormatting.GRAY));
                }
            }

        }
    }
}
