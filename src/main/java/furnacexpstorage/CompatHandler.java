package furnacexpstorage;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

public class CompatHandler {

    private static Boolean isRelevantHeiVersion = null;

    public static boolean isJeiAboveRelevantHeiVersion(){
        if(isRelevantHeiVersion == null){
            ModContainer container = Loader.instance().getIndexedModList().get("jei");
            if(!container.getName().equals("Had Enough Items")) { isRelevantHeiVersion = false; return false; }

            String[] split = container.getVersion().split("\\.");
            try {
                int major = Integer.parseInt(split[0]);
                int minor = Integer.parseInt(split[1]);
                int patch = Integer.parseInt(split[2]);
                if(major < 4) { isRelevantHeiVersion = false; return false; }
                if(major == 4 && minor < 24) { isRelevantHeiVersion = false; return false; }
                if(major == 4 && minor == 24 && patch < 2) { isRelevantHeiVersion = false; return false; }
                return true;
            }
            catch(Exception ignored) { }
            return false;
        }
        return isRelevantHeiVersion;
    }
}
