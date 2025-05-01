package furnacexpstorage.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import furnacexpstorage.FurnaceXPStorage;
import furnacexpstorage.handler.BlockBreakHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SlotFurnaceOutput.class)
public abstract class SlotFurnaceOutputMixin extends Slot {
    public SlotFurnaceOutputMixin(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    /**
     * For the amount of smelted items,
     * return a random integer between floor(xpStored) and ceil(xpStored)
     * that on average yields the exact xp stored
     */
    @WrapOperation(
            method = "onCrafting(Lnet/minecraft/item/ItemStack;)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/inventory/SlotFurnaceOutput;removeCount:I", ordinal = 1)
    )
    public int furnaceXpStorage_slotFurnaceOutput_removeCount(SlotFurnaceOutput instance, Operation<Integer> original) {
        NBTTagCompound nbt = ((TileEntityFurnace) instance.inventory).getTileData();
        if(!nbt.hasKey(FurnaceXPStorage.NBTKEY)) return original.call(instance);   //Default behavior

        float storedXP = nbt.getFloat(FurnaceXPStorage.NBTKEY);
        nbt.setFloat(FurnaceXPStorage.NBTKEY, 0F);

        return BlockBreakHandler.getRandXP(storedXP);
    }

    /**
     * use 1x  multiplier for the xp taken out
     */
    @WrapOperation(
            method = "onCrafting(Lnet/minecraft/item/ItemStack;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/crafting/FurnaceRecipes;getSmeltingExperience(Lnet/minecraft/item/ItemStack;)F")
    )
    private float furnaceXpStorage_slotFurnaceOutput_getSmeltingExperience(FurnaceRecipes instance, ItemStack stack, Operation<Float> original){
        NBTTagCompound nbt = ((TileEntityFurnace) this.inventory).getTileData();
        if(!nbt.hasKey(FurnaceXPStorage.NBTKEY))
            return original.call(instance, stack);   //Default behavior
        else
            return 1.0F;
    }
}


