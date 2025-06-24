package furnacexpstorage.mixin.vanilla;

import furnacexpstorage.util.SmeltItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FurnaceRecipes.class)
public abstract class FurnaceRecipesMixin {

    /**
     * Store recipe experiences by input instead of output (in a different map to not break anything)
     */
    @Inject(
            method = "addSmeltingRecipe",
            at = @At(value = "TAIL")
    )
    private void furnaceXpStorage_furnaceRecipes_storeRecipes(ItemStack input, ItemStack output, float experience, CallbackInfo ci) {
        SmeltItemHelper.addFurnaceRecipe(input, experience);
    }
}

