package furnacexpstorage.mixin.hei;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import furnacexpstorage.util.SmeltItemHelper;
import mezz.jei.plugins.vanilla.furnace.SmeltingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(SmeltingRecipe.class)
public abstract class SmeltingRecipeMixin {
    @SuppressWarnings("target")
    @Shadow(remap = false) @Final private List<ItemStack> input;

    @WrapOperation(
            method = "drawInfo",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/crafting/FurnaceRecipes;getSmeltingExperience(Lnet/minecraft/item/ItemStack;)F")
    )
    private float furnaceXpStorage_heiSmeltingRecipe_drawXPInfo(FurnaceRecipes instance, ItemStack stack, Operation<Float> original){
        //input can be bigger than size 1, if there is multiple metadata items that all smelt to the same output, registered via the wildcard metadata 32767
        // examples: sand and red sand -> glass, or all log types to charcoal
        //since they have to all be registered to the same xp value (otherwise it would be different smelting recipes), i just choose the first entry to get the xp
        if(input == null || input.isEmpty()) return original.call(instance,stack);
        return SmeltItemHelper.getSmeltingExperience(input.get(0),stack);
    }
}
