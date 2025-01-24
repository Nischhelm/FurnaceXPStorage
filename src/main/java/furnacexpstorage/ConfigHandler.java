package furnacexpstorage;

import net.minecraftforge.common.config.Config;

@Config(modid = FurnaceXPStorage.MODID)
public class ConfigHandler {
    @Config.Comment("Minecraft limits xp per smelted item to 1. Set to true to keep this behavior.")
    @Config.Name("Vanilla behavior: 1 xp per output item")
    public static boolean recreateVanillaLimit = true;
}
