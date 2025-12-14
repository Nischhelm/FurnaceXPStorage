package furnacexpstorage.compat.modcompat;

import com.xenomustache.ironfurnaces.blocks.*;
import com.xenomustache.ironfurnaces.tileentity.*;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class IronFurnacesCompat implements IFurnaceModCompat {
	@Override
    public boolean isModFurnace(Block block){
        if(block instanceof BlockDiamondFurnace) return true;
        if(block instanceof BlockGlassFurnace) return true;
        if(block instanceof BlockGoldFurnace) return true;
        if(block instanceof BlockIronFurnace) return true;
        if(block instanceof BlockObsidianFurnace) return true;
        if(block instanceof BlockShulkerFurnace) return true;
        return false;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		if(tile instanceof TileEntityDiamondFurnace) return true;
		if(tile instanceof TileEntityGlassFurnace) return true;
		if(tile instanceof TileEntityGoldFurnace) return true;
		if(tile instanceof TileEntityIronFurnace) return true;
		if(tile instanceof TileEntityObsidianFurnace) return true;
		if(tile instanceof TileEntityShulkerFurnace) return true;
		return false;
	}
}
