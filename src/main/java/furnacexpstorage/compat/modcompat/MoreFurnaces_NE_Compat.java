package furnacexpstorage.compat.modcompat;

import dan.morefurnaces.blocks.BlockMoreFurnaces;
import net.minecraft.block.Block;

public class MoreFurnaces_NE_Compat implements IFurnaceModCompat{
    @Override
    public boolean isModFurnace(Block block) {
        return block instanceof BlockMoreFurnaces;
    }
}
