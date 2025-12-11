package furnacexpstorage.mixin.modfurnaces.furnus;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import furnacexpstorage.FurnaceXPStorage;
import furnacexpstorage.util.SmeltItemHelper;
import mrriegel.furnus.tile.TileDevice;
import mrriegel.furnus.tile.TileFurnus;
import mrriegel.furnus.tile.TilePulvus;
import mrriegel.furnus.util.CrushHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TileDevice.class)
public abstract class TileDeviceMixin {
    /**
     * When smelting items, store the xp in TileEntity NBT
     */
    @WrapOperation(
            method = "processItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V")
    )
    private void furnaceXpStorage_tileEntityFurnace_storeSmeltXPinTileNBT(
            ItemStack inputStack, int amount,
            Operation<Void> original,
            @Local(name = "itemstack") ItemStack cookedStack
    ) {
        TileEntity thisTile = ((TileEntity) (Object) this);
        NBTTagCompound tileData = thisTile.getTileData();
        if(thisTile instanceof TileFurnus) SmeltItemHelper.storeXpFromSmelting(inputStack, cookedStack, tileData);
        else if(thisTile instanceof TilePulvus){
            float xp = CrushHandler.instance().getExperience(cookedStack); // xp by output for crusher
            xp *= cookedStack.getCount();

            tileData.setFloat(FurnaceXPStorage.NBTKEY, tileData.getFloat(FurnaceXPStorage.NBTKEY) + xp);
        }
        original.call(inputStack, amount);
    }
}
