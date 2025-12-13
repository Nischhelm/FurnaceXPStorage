package furnacexpstorage.mixin.modfurnaces.nuclearcraft;

import com.llamalad7.mixinextras.sugar.Local;
import furnacexpstorage.util.SmeltItemHelper;
import nc.tile.processor.TileNuclearFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileNuclearFurnace.class)
public abstract class TileNuclearFurnaceMixin {
    /**
     * When smelting items, store the xp in TileEntity NBT
     */
    @Inject(
            method = "smeltItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V")
    )
    private void furnaceXpStorage_tileEntityFurnace_storeSmeltXPinTileNBT(
            CallbackInfo ci,
            @Local(name = "itemstack") ItemStack inputStack,
            @Local(name = "itemstack1") ItemStack cookedStack
    ) {
        SmeltItemHelper.storeXpFromSmelting(inputStack, cookedStack, ((TileEntity) (Object) this).getTileData());
    }
}
