package furnacexpstorage;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = FurnaceXPStorage.MODID)
public class ConfigHandler {
    @Config.Comment("Minecraft limits xp per smelted item to 1. Set to true to keep this behavior.")
    @Config.Name("Vanilla behavior: 1 xp per output item")
    public static boolean recreateVanillaLimit = true;

	@Config.Comment("Configs for liquid xp to be extracted from furnaces")
	@Config.Name("Fluid XP options")
	public static XPConfig xpConfig = new XPConfig();

	public static class XPConfig{
		@Config.Comment("The fluid xp to be drained. Leave empty to disable")
		@Config.Name("Fluid Name")
		@Config.RequiresMcRestart
		public String fluidName = "";

		@Config.Comment("The conversion factor from xp to liquid xp")
		@Config.Name("Conversion Factor")
		@Config.RangeInt(min = 0)
		public float conversionFactor = 0;
	}

	@Mod.EventBusSubscriber(modid = FurnaceXPStorage.MODID)
	public static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(FurnaceXPStorage.MODID)) {
				ConfigManager.sync(FurnaceXPStorage.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
