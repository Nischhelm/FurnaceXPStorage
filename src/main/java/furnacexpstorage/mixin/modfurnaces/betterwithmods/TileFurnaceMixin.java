package furnacexpstorage.mixin.modfurnaces.betterwithmods;

import betterwithmods.common.blocks.tile.TileFurnace;
import com.llamalad7.mixinextras.sugar.Local;
import furnacexpstorage.util.SmeltItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileFurnace.class)
public abstract class TileFurnaceMixin {

    /**
     * When smelting items, store the xp in TileEntity NBT
     */
    @Inject(
            method = "smeltItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V")
    )
    private void furnaceXpStorage_tileEntityFurnace_storeSmeltXPinTileNBT(
            CallbackInfo ci,
            @Local(name = "input") ItemStack inputStack,
            @Local(name = "recipeOutput") ItemStack cookedStack
    ) {
        SmeltItemHelper.storeXpFromSmelting(inputStack, cookedStack, ((TileEntity) (Object) this).getTileData());
    }
}

