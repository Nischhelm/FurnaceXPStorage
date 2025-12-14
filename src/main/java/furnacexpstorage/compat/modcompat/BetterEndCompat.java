package furnacexpstorage.compat.modcompat;

import mod.beethoven92.betterendforge.common.block.EndStoneSmelter;
import mod.beethoven92.betterendforge.common.tileentity.EndStoneSmelterTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class BetterEndCompat implements IFurnaceModCompat {
	@Override
    public boolean isModFurnace(Block block){
        return block instanceof EndStoneSmelter;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof EndStoneSmelterTileEntity;
	}
}
