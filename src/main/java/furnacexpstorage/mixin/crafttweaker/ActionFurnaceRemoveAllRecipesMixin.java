package furnacexpstorage.mixin.crafttweaker;

import crafttweaker.mc1120.actions.ActionFurnaceRemoveAllRecipes;
import furnacexpstorage.handler.SmeltingExperiencesByInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ActionFurnaceRemoveAllRecipes.class)
public abstract class ActionFurnaceRemoveAllRecipesMixin {
    @Inject(
            method = "apply",
            at = @At("HEAD"),
            remap = false
    )
    private void furnaceXpStorage_actionFurnaceRemoveAllRecipes_apply(CallbackInfo ci){
        SmeltingExperiencesByInput.clear();
    }
}
