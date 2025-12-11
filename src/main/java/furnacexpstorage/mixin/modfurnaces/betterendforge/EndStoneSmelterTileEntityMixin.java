package furnacexpstorage.mixin.modfurnaces.betterendforge;

import mod.beethoven92.betterendforge.common.tileentity.EndStoneSmelterTileEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EndStoneSmelterTileEntity.class)
public abstract class EndStoneSmelterTileEntityMixin {

//    /**
//     * When smelting items, store the xp in TileEntity NBT
//     */
//    @Inject(
//            method = "craftRecipe",
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V")
//    )
//    private void furnaceXpStorage_tileEntityForge_storeSmeltXPinTileNBT(
//            CallbackInfo ci,
//            @Local(name = "itemstack") ItemStack inputStack,
//            @Local(name = "result") ItemStack cookedStack
//    ) {
//        SmeltItemHelper.storeXpFromSmelting(inputStack, cookedStack, ((TileEntity) (Object) this).getTileData());
//    }
}

