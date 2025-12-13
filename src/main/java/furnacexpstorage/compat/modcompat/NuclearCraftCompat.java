package furnacexpstorage.compat.modcompat;

import nc.block.tile.processor.BlockNuclearFurnace;
import nc.tile.processor.TileNuclearFurnace;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class NuclearCraftCompat implements IFurnaceModCompat {
	@Override
    public boolean isModFurnace(Block block){
        return block instanceof BlockNuclearFurnace;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof TileNuclearFurnace;
	}
}
