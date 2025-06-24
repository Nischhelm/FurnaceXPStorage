package furnacexpstorage.mixin.crafttweaker;

import crafttweaker.mc1120.actions.ActionFurnaceRemoveRecipe;
import furnacexpstorage.util.SmeltItemHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ActionFurnaceRemoveRecipe.class)
public abstract class ActionFurnaceRemoveRecipeMixin {
    @Shadow(remap = false) Map<ItemStack, ItemStack> smeltingMap;

    @Inject(
            method = "apply",
            at = @At(value = "TAIL"),
            remap = false
    )
    private void furnaceXpStorage_actionFurnaceRemoveRecipe_apply(CallbackInfo ci){
        this.smeltingMap.forEach((k,v) -> SmeltItemHelper.removeRecipeFor(k));
    }
}
