package furnacexpstorage.compat.modcompat;

import cubex2.mods.morefurnaces.blocks.BlockMoreFurnaces;
import cubex2.mods.morefurnaces.tileentity.TileEntityIronFurnace;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class MoreFurnacesCompat implements IFurnaceModCompat{
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
