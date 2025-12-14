package furnacexpstorage.compat.modcompat;

import mrriegel.furnus.init.ModBlocks;
import mrriegel.furnus.tile.TileFurnus;
import mrriegel.furnus.tile.TilePulvus;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class FurnusCompat implements IFurnaceModCompat{
    @Override
    public boolean isModFurnace(Block block) {
        return block == ModBlocks.furnus.getItemBlock().getBlock() || block == ModBlocks.pulvus.getItemBlock().getBlock();
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof TileFurnus || tile instanceof TilePulvus;
	}
}
