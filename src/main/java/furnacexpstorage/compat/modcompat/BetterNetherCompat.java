package furnacexpstorage.compat.modcompat;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import paulevs.betternether.blocks.BlockCincinnasiteForge;
import paulevs.betternether.tileentities.TileEntityForge;

public class BetterNetherCompat implements IFurnaceModCompat {
	@Override
    public boolean isModFurnace(Block block){
        return block instanceof BlockCincinnasiteForge;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof TileEntityForge;
	}
}
