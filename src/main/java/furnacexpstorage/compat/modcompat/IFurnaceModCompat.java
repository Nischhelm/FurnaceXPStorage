package furnacexpstorage.compat.modcompat;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public interface IFurnaceModCompat {
    boolean isModFurnace(Block block);

	boolean isModFurnace(TileEntity tile);
}
