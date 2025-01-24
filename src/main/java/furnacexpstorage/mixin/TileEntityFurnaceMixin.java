package furnacexpstorage.mixin;

import furnacexpstorage.ConfigHandler;
import furnacexpstorage.FurnaceXPStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntityFurnace.class)
public abstract class TileEntityFurnaceMixin {

    @Shadow protected abstract boolean canSmelt();
    @Shadow public abstract ItemStack getStackInSlot(int index);

    @Inject(
            method = "smeltItem",
            at = @At("HEAD")
    )
    private void furnaceXpStorage_slotFurnaceOutput_storeSmeltXPinTileNBT(CallbackInfo ci) {
        if (canSmelt()) {
            ItemStack cookedItem = FurnaceRecipes.instance().getSmeltingResult(getStackInSlot(0));
            float xp = FurnaceRecipes.instance().getSmeltingExperience(cookedItem);
            if(ConfigHandler.recreateVanillaLimit)
                xp = Math.min(xp, 1.0F);
            xp *= cookedItem.getCount();

            NBTTagCompound nbt = ((TileEntityFurnace) (Object) this).getTileData();
            nbt.setFloat(FurnaceXPStorage.NBTKEY, nbt.getFloat(FurnaceXPStorage.NBTKEY) + xp);
        }
    }
}

