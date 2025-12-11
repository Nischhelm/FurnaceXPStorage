package furnacexpstorage.mixin.modfurnaces.nethercraft;

import com.legacy.nethercraft.tileentity.TileEntityNetherrackFurnace;
import com.llamalad7.mixinextras.sugar.Local;
import furnacexpstorage.util.SmeltItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntityNetherrackFurnace.class)
public abstract class TileEntityNetherrackFurnaceMixin {

    /**
     * When smelting items, store the xp in TileEntity NBT
     */
    @Inject(
            method = "smeltItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V")
    )
    private void furnaceXpStorage_tileEntityNetherrackFurnace_storeSmeltXPinTileNBT(
            CallbackInfo ci,
            @Local(name = "itemstack", ordinal = 0) ItemStack inputStack,
            @Local(name = "itemstack1", ordinal = 1) ItemStack cookedStack
    ) {
        SmeltItemHelper.storeXpFromSmelting(inputStack, cookedStack, ((TileEntity) (Object) this).getTileData());
    }
}

