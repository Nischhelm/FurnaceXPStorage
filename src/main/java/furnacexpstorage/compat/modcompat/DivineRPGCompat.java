package furnacexpstorage.compat.modcompat;

import divinerpg.objects.blocks.BlockModFurnace;
import divinerpg.objects.blocks.tile.entity.TileEntityModFurnace;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class DivineRPGCompat implements IFurnaceModCompat {
	@Override
    public boolean isModFurnace(Block block){
        return block instanceof BlockModFurnace;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof TileEntityModFurnace;
	}
}
