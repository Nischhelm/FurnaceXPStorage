package furnacexpstorage.compat.modcompat;

import com.progwml6.natura.nether.block.furnace.BlockNetherrackFurnace;
import com.progwml6.natura.nether.block.furnace.tile.TileEntityNetherrackFurnace;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class NaturaCompat implements IFurnaceModCompat {
	@Override
    public boolean isModFurnace(Block block){
        return block instanceof BlockNetherrackFurnace;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof TileEntityNetherrackFurnace;
	}
}
