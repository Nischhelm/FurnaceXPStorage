package furnacexpstorage.mixin.modfurnaces.ironfurnaces;

import com.llamalad7.mixinextras.sugar.Local;
import com.xenomustache.ironfurnaces.tileentity.*;
import furnacexpstorage.util.SmeltItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {
        TileEntityDiamondFurnace.class,
        TileEntityGlassFurnace.class,
        TileEntityGoldFurnace.class,
        TileEntityIronFurnace.class,
        TileEntityObsidianFurnace.class,
        TileEntityShulkerFurnace.class
})
public abstract class TileEntityMATERIALFurnaceMixin {

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

