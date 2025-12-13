package furnacexpstorage;

import net.minecraftforge.common.config.Config;

@Config(modid = FurnaceXPStorage.MODID)
public class ConfigHandler {
    @Config.Comment("Minecraft limits xp per smelted item to 1. Set to true to keep this behavior.")
    @Config.Name("Vanilla behavior: 1 xp per output item")
    public static boolean recreateVanillaLimit = true;

	@Config.Comment("Configs for liquid xp to be extracted from the furnace")
	public static XPConfig xpConfig = new XPConfig();
	public static class XPConfig{
		@Config.Comment("The fluid xp to be drained. Leave empty to disable")
		public String fluid = "";

		@Config.Comment("The conversion factor from xp to liquid xp")
		public float conversionFactor = 0.0f;
	}
}
