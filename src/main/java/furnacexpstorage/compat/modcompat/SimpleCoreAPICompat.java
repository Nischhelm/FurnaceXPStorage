package furnacexpstorage.compat.modcompat;

import alexndr.api.content.blocks.SimpleFurnace;
import alexndr.api.content.tiles.TileEntitySimpleFurnace;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class SimpleCoreAPICompat implements IFurnaceModCompat {
	@Override
    public boolean isModFurnace(Block block){
        return block instanceof SimpleFurnace;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof TileEntitySimpleFurnace;
	}
}
