package furnacexpstorage.compat.modcompat;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import wily.betterfurnaces.blocks.BlockSmelting;
import wily.betterfurnaces.tile.TileEntitySmeltingBase;

public class BetterFurnacesReforgedCompat implements IFurnaceModCompat {
    public boolean isModFurnace(Block block){
        return block instanceof BlockSmelting;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof TileEntitySmeltingBase;
	}
}
