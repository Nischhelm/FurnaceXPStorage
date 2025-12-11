package furnacexpstorage;

import fermiumbooter.FermiumRegistryAPI;
import furnacexpstorage.compat.CompatHandler;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class FurnaceXPStoragePlugin implements IFMLLoadingPlugin {

	public FurnaceXPStoragePlugin() {
		MixinBootstrap.init();
		FermiumRegistryAPI.enqueueMixin(false, "mixins.furnacexpstorage.json");

		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.nethercraft.json", () -> CompatHandler.isModLoaded("nethercraft"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.betternether.json", () -> CompatHandler.isModLoaded("betternether"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.fastfurnace.json", () -> CompatHandler.isModLoaded("fastfurnace"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.ironfurnaces.json", () -> CompatHandler.isModLoaded("ironfurnaces"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.betterwithmods.json", () -> CompatHandler.isModLoaded("betterwithmods"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.morefurnaces.json", () -> CompatHandler.isModLoaded("morefurnaces") && CompatHandler.isModOfName("morefurnaces", "More Furnaces"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.morefurnacesnomifactory.json", () -> CompatHandler.isModLoaded("morefurnaces") && CompatHandler.isModOfName("morefurnaces", "More Furnaces: Nomifactory Edition"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.betterfurnacesreforged.json", () -> CompatHandler.isModLoaded("betterfurnacesreforged"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.furnaceoverhaul.json", () -> CompatHandler.isModLoaded("furnaceoverhaul"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.furnus.json", () -> CompatHandler.isModLoaded("furnus"));
		//FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.betterend.json", () -> CompatHandler.isModLoaded("betterendforge"));

		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.crafttweaker.json", () -> CompatHandler.isModLoaded("crafttweaker"));

		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.jei.json", () -> CompatHandler.isModLoaded("jei") && !CompatHandler.isJeiAboveRelevantHeiVersion());
		FermiumRegistryAPI.enqueueMixin(true, "mixins.furnacexpstorage.hei.json", () -> CompatHandler.isModLoaded("jei") && CompatHandler.isJeiAboveRelevantHeiVersion());
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