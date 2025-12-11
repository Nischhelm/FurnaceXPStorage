package furnacexpstorage.compat.modcompat;

import cubex2.mods.morefurnaces.blocks.BlockMoreFurnaces;
import net.minecraft.block.Block;

public class MoreFurnacesCompat implements IFurnaceModCompat{
    @Override
    public boolean isModFurnace(Block block) {
        return block instanceof BlockMoreFurnaces;
    }
}
