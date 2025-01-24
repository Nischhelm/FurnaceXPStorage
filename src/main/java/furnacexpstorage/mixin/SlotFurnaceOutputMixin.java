package furnacexpstorage.mixin;

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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SlotFurnaceOutput.class)
public abstract class SlotFurnaceOutputMixin extends Slot {
    @Shadow private int removeCount;

    public SlotFurnaceOutputMixin(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Redirect(
            method = "onCrafting(Lnet/minecraft/item/ItemStack;)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/inventory/SlotFurnaceOutput;removeCount:I", ordinal = 1)
    )
    public int furnaceXpStorage_slotFurnaceOutput_removeCount(SlotFurnaceOutput instance) {
        NBTTagCompound nbt = ((TileEntityFurnace) this.inventory).getTileData();
        if(!nbt.hasKey(FurnaceXPStorage.NBTKEY)) return this.removeCount;   //Default behavior

        float storedXP = nbt.getFloat(FurnaceXPStorage.NBTKEY);
        nbt.setFloat(FurnaceXPStorage.NBTKEY, 0F);

        return BlockBreakHandler.getRandXP(storedXP);
    }

    @Redirect(
            method = "onCrafting(Lnet/minecraft/item/ItemStack;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/crafting/FurnaceRecipes;getSmeltingExperience(Lnet/minecraft/item/ItemStack;)F")
    )
    private float furnaceXpStorage_slotFurnaceOutput_getSmeltingExperience(FurnaceRecipes instance, ItemStack stack){
        NBTTagCompound nbt = ((TileEntityFurnace) this.inventory).getTileData();
        if(!nbt.hasKey(FurnaceXPStorage.NBTKEY))
            return instance.getSmeltingExperience(stack);   //Default behavior
        else
            return 1.0F;
    }
}


