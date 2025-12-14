package furnacexpstorage.compat;

import furnacexpstorage.compat.modcompat.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompatHandler {
    public static final Map<String, IFurnaceModCompat> modHandler = new HashMap<>();
    public static void addModHandler(String modid, Supplier<IFurnaceModCompat> handlerSupplier){
        if(isModLoaded(modid)) modHandler.put(modid, handlerSupplier.get());
    }
    public static void addModHandler(String modid, String name, Supplier<IFurnaceModCompat> handlerSupplier){
        if(isModLoaded(modid) && isModOfName(modid, name)) modHandler.put(modid, handlerSupplier.get());
    }
    public static void preInit(){
        addModHandler("betternether", BetterNetherCompat::new);
        addModHandler("betterendforge", BetterEndCompat::new);
        addModHandler("ironfurnaces", IronFurnacesCompat::new);
        addModHandler("morefurnaces", "More Furnaces", MoreFurnacesCompat::new);
        addModHandler("morefurnaces", "More Furnaces: Nomifactory Edition", MoreFurnaces_NE_Compat::new);
        addModHandler("betterfurnacesreforged", BetterFurnacesReforgedCompat::new);
        addModHandler("furnaceoverhaul", FurnaceOverhaulCompat::new);
        addModHandler("furnus", FurnusCompat::new);
        addModHandler("mysticalagriculture", MysticalAgricultureCompat::new);
        addModHandler("nuclearcraft", NuclearCraftCompat::new);
        addModHandler("divinerpg", DivineRPGCompat::new);
        addModHandler("simplecore", SimpleCoreAPICompat::new);
        addModHandler("natura", NaturaCompat::new);
    }

    //unused
    public static boolean isFurnaceBlock(Block block){
        if (block instanceof BlockFurnace) return true; //includes betterendforge EndFurnace, betterwithmods, fastfurnace, nethercraft
        for(Map.Entry<String, IFurnaceModCompat> entry : modHandler.entrySet())
            if(entry.getValue().isModFurnace(block)) return true;
        return false;
    }

    public static boolean isFurnaceTile(TileEntity tile){
        if (tile instanceof TileEntityFurnace) return true; //includes betterendforge EndFurnace, betterwithmods, fastfurnace, nethercraft
        for(Map.Entry<String, IFurnaceModCompat> entry : modHandler.entrySet())
            if(entry.getValue().isModFurnace(tile)) return true;
        return false;
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
