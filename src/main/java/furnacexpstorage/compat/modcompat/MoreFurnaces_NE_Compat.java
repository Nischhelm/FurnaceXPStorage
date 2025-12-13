package furnacexpstorage.compat.modcompat;

import dan.morefurnaces.blocks.BlockMoreFurnaces;
import dan.morefurnaces.tileentity.TileEntityIronFurnace;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class MoreFurnaces_NE_Compat implements IFurnaceModCompat{
    @Override
    public boolean isModFurnace(Block block) {
        return block instanceof BlockMoreFurnaces;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		//All tileEntities from this mod extend TileEntityIronFurnace
		return tile instanceof TileEntityIronFurnace;
	}
}
