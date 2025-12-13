package furnacexpstorage.handler;

import furnacexpstorage.ConfigHandler;
import furnacexpstorage.FurnaceXPStorage;
import furnacexpstorage.compat.CompatHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AttachCapabilitiesHandler {
	//Only listens if allowed
	@SubscribeEvent
	public static void onAttackCapabilities(AttachCapabilitiesEvent<TileEntity> event){
		FurnaceXPStorage.LOGGER.info("AttachCapabilitiesEvent is fired for {}", event.getObject().getClass().getSimpleName());
		if(isFurnaceTile(event.getObject())){
			event.addCapability(new ResourceLocation(FurnaceXPStorage.MODID, "xp"), new ICapabilityProvider() {
				@Override
				public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing enumFacing) {
					return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
				}

				@Nullable
				@Override
				@SuppressWarnings("unchecked")
				public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing) {
					if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
						return (T) new FurnaceXPCapability(event.getObject());
					}
					return null;
				}
			});
		}
	}

	public static class FurnaceXPCapability implements IFluidHandler {
		public static Fluid xpFluid = FluidRegistry.getFluid(ConfigHandler.xpConfig.fluid);

		public TileEntity tile;

		public FurnaceXPCapability(TileEntity tile){
			this.tile = tile;
		}
		@Override
		public IFluidTankProperties[] getTankProperties() {
			return new FluidTankProperties[]{new FluidTankProperties(
				new FluidStack(xpFluid, (int)(ConfigHandler.xpConfig.conversionFactor*tile.getTileData().getFloat(FurnaceXPStorage.NBTKEY))),
				Integer.MAX_VALUE,
				false,
				true
			)};
		}

		@Override
		public int fill(FluidStack fluidStack, boolean b) {
			return 0;
		}

		@Nullable
		@Override
		public FluidStack drain(FluidStack fluidStack, boolean doDrain) {
			if(fluidStack.getFluid() == xpFluid){
				return drain(fluidStack.amount, doDrain);
			}
			return null;
		}

		@Nullable
		@Override
		public FluidStack drain(int requested, boolean doDrain) {
			float stored = tile.getTileData().getFloat(FurnaceXPStorage.NBTKEY);
			//
			int drained = Math.min(requested, (int)(ConfigHandler.xpConfig.conversionFactor*stored));
			if(drained == 0){
				return null;
			}
			if(doDrain){
				tile.getTileData().setFloat(FurnaceXPStorage.NBTKEY, stored - drained/ConfigHandler.xpConfig.conversionFactor);
				tile.markDirty();
			}
			return new FluidStack(xpFluid, drained);
		}
	}

	private static boolean isFurnaceTile(TileEntity tile){
		if (tile instanceof TileEntityFurnace) return true; //includes betterendforge EndFurnace, betterwithmods, fastfurnace, nethercraft

		if (CompatHandler.isModLoaded("betterendforge") && CompatHandler.getHandler("betterendforge", true).isModFurnace(tile)) return true;
		if (CompatHandler.isModLoaded("betterfurnacesreforged") && CompatHandler.getHandler("betterfurnacesreforged", true).isModFurnace(tile)) return true;
		if (CompatHandler.isModLoaded("betternether") && CompatHandler.getHandler("betternether", true).isModFurnace(tile)) return true;
		if (CompatHandler.isModLoaded("furnaceoverhaul") && CompatHandler.getHandler("furnaceoverhaul", true).isModFurnace(tile)) return true;
		if (CompatHandler.isModLoaded("furnus") && CompatHandler.getHandler("furnus", true).isModFurnace(tile)) return true;
		if (CompatHandler.isModLoaded("ironfurnaces") && CompatHandler.getHandler("ironfurnaces", true).isModFurnace(tile)) return true;
		if (CompatHandler.isModLoaded("morefurnaces") && CompatHandler.getHandler("morefurnaces", false).isModFurnace(tile)) return true;
		if (CompatHandler.isModLoaded("morefurnaces") && CompatHandler.getHandler("morefurnaces", true).isModFurnace(tile)) return true;
		if (CompatHandler.isModLoaded("mysticalagriculture") && CompatHandler.getHandler("mysticalagriculture", true).isModFurnace(tile)) return true;
		if (CompatHandler.isModLoaded("nuclearcraft") && CompatHandler.getHandler("nuclearcraft", true).isModFurnace(tile)) return true;
		return false;
	}
}
