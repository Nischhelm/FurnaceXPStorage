package furnacexpstorage.compat.modcompat;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public interface IFurnaceModCompat {
    //unused
    default boolean isModFurnace(Block block){ return false; };
	boolean isModFurnace(TileEntity tile);
}
