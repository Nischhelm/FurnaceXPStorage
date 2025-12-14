package furnacexpstorage.mixin.modfurnaces.simplecore;

import alexndr.api.content.tiles.TileEntitySimpleFurnace;
import com.llamalad7.mixinextras.sugar.Local;
import furnacexpstorage.util.SmeltItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntitySimpleFurnace.class)
public abstract class TileEntitySimpleFurnaceMixin {

    /**
     * When smelting items, store the xp in TileEntity NBT
     */
    @Inject(
            method = "smeltItem",
            at = @At(value = "INVOKE", target = "Lalexndr/api/content/tiles/TileEntitySimpleFurnace;decrStackSize(II)Lnet/minecraft/item/ItemStack;")
    )
    private void furnaceXpStorage_tileEntityNetherrackFurnace_storeSmeltXPinTileNBT(
            CallbackInfo ci,
            @Local(name = "instack") ItemStack inputStack,
            @Local(name = "result_stack") ItemStack cookedStack
    ) {
        SmeltItemHelper.storeXpFromSmelting(inputStack, cookedStack, ((TileEntity) (Object) this).getTileData());
    }
}

