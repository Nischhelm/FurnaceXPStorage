package furnacexpstorage.compat;

import furnacexpstorage.compat.modcompat.*;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompatHandler {
    private static final Map<String, IFurnaceModCompat> modHandler = new HashMap<>();
    public static IFurnaceModCompat getHandler(String modid, boolean original){

        return modHandler.computeIfAbsent(modid, s -> {
           switch (modid){
               case "betternether" : return new BetterNetherCompat();
               case "betterendforge" : return new BetterEndCompat();
               case "ironfurnaces" : return new IronFurnacesCompat();
               case "morefurnaces" :
                   if(original) return new MoreFurnacesCompat();
                   else return new MoreFurnaces_NE_Compat();
               case "betterfurnacesreforged" : return new BetterFurnacesReforgedCompat();
               case "furnaceoverhaul" : return new FurnaceOverhaulCompat();
               case "furnus" : return new FurnusCompat();
               case "mysticalagriculture" : return new MysticalAgricultureCompat();
               case "nuclearcraft" : return new NuclearCraftCompat();
               default: return null;
           }
        });
    }

    private static final Map<String, Boolean> loadedMap = new HashMap<>();
    public static boolean isModLoaded(String modid){
        return loadedMap.computeIfAbsent(modid, Loader::isModLoaded);
    }

    public static boolean isModOfName(String modid, String intendedName){
        ModContainer container = Loader.instance().getIndexedModList().get(modid);
        if(container == null) return false;
        return intendedName.equals(container.getName());
    }

    public static boolean isModAboveVersion(String modid, int... compare){
        ModContainer container = Loader.instance().getIndexedModList().get(modid);
        if(container == null) return false;
        try {
            List<Integer> actual = Arrays.stream(container.getVersion().split("\\.")).map(Integer::parseInt).collect(Collectors.toList());

            for(int i = 0; i < actual.size(); i++){
                if(i >= compare.length) return true; //assumes 1.2.3 > 1.2
                if(actual.get(i) > compare[i]) return true;
            }
            return false;

        } catch (Exception ignored){
            return false;
        }
    }

    private static Boolean isRelevantHeiVersion = null;
    public static boolean isJeiAboveRelevantHeiVersion(){
        if(isRelevantHeiVersion == null) isRelevantHeiVersion = isModOfName("jei", "Had Enough Items") && isModAboveVersion("jei", 4, 24, 1);
        return isRelevantHeiVersion;
    }
}
