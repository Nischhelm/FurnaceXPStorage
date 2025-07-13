package furnacexpstorage;

import fermiumbooter.FermiumRegistryAPI;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class FurnaceXPStoragePlugin implements IFMLLoadingPlugin {

	public FurnaceXPStoragePlugin() {
		MixinBootstrap.init();
		FermiumRegistryAPI.enqueueMixin(false, "mixins.furnacexpstorage.json");
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.nethercraft.json", () -> Loader.isModLoaded("nethercraft"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.betternether.json", () -> Loader.isModLoaded("betternether"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.crafttweaker.json", () -> Loader.isModLoaded("crafttweaker"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.jei.json", () -> Loader.isModLoaded("jei") && !CompatHandler.isJeiAboveRelevantHeiVersion());
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.hei.json", () -> Loader.isModLoaded("jei") && CompatHandler.isJeiAboveRelevantHeiVersion());
	}

	@Override
	public String[] getASMTransformerClass()
	{
		return new String[0];
	}
	
	@Override
	public String getModContainerClass()
	{
		return null;
	}
	
	@Override
	public String getSetupClass()
	{
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data) { }
	
	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}