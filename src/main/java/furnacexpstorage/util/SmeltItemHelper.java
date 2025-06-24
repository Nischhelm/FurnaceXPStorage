package furnacexpstorage.util;

import furnacexpstorage.ConfigHandler;
import furnacexpstorage.FurnaceXPStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SmeltItemHelper {
    //input item, input metadata, experience
    private static final Map<Item, Map<Integer, Float>> experienceListByInput = new HashMap<>();

    public static void addFurnaceRecipe(ItemStack input, float experience) {
        if (!experienceListByInput.containsKey(input.getItem()))
            experienceListByInput.put(input.getItem(), new HashMap<>());

        if (ConfigHandler.recreateVanillaLimit)
            experience = Math.min(experience, 1.0F);

        if (experienceListByInput.get(input.getItem()).containsKey(input.getMetadata()))
            FurnaceXPStorage.LOGGER.warn("FurnaceXPStorage: Registering smelting recipe that was already registered with {} xp: input item {} metadata {} new xp {}", experienceListByInput.get(input.getItem()).get(input.getMetadata()), input.getItem().getRegistryName().toString(), input.getMetadata(), experience);

        experienceListByInput.get(input.getItem()).put(input.getMetadata(), experience);
    }

    public static float getSmeltingExperience(ItemStack input, ItemStack output) {
        float ret = output.getItem().getSmeltingExperience(output);
        if (ret != -1) return ret;

        Map<Integer, Float> tmp = experienceListByInput.getOrDefault(input.getItem(), Collections.emptyMap());
        return tmp.getOrDefault(input.getMetadata(), tmp.getOrDefault(32767, 0.0F));
    }

    public static void clear() {
        experienceListByInput.clear();
    }

    public static void removeRecipeFor(ItemStack input) {
        experienceListByInput.get(input.getItem()).remove(input.getMetadata());
        if (experienceListByInput.get(input.getItem()).isEmpty())
            experienceListByInput.remove(input.getItem());
    }

    public static void storeXpFromSmelting(ItemStack inputStack, ItemStack cookedStack, NBTTagCompound tileData){
        float xp = getSmeltingExperience(inputStack, cookedStack);
        xp *= cookedStack.getCount();

        tileData.setFloat(FurnaceXPStorage.NBTKEY, tileData.getFloat(FurnaceXPStorage.NBTKEY) + xp);
    }
}
