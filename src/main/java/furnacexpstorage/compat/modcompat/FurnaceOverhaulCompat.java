package furnacexpstorage.compat.modcompat;

import cazador.furnaceoverhaul.blocks.BlockIronFurnace;
import cazador.furnaceoverhaul.tile.TileEntityIronFurnace;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class FurnaceOverhaulCompat implements IFurnaceModCompat {
    public boolean isModFurnace(Block block){
        return block instanceof BlockIronFurnace;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof TileEntityIronFurnace;
	}
}
