package furnacexpstorage;

import furnacexpstorage.handler.BlockBreakHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Random;

@Mod(modid = FurnaceXPStorage.MODID, version = FurnaceXPStorage.VERSION, name = FurnaceXPStorage.NAME, dependencies = "required-after:fermiumbooter")
public class FurnaceXPStorage {
    public static final String MODID = "furnacexpstorage";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "FurnaceXPStorage";
    public static final Random RAND = new Random();
    public static final String NBTKEY = "StoredXP";

	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(BlockBreakHandler.class);
    }
}