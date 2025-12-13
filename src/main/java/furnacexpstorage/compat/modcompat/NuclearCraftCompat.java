package furnacexpstorage.compat.modcompat;

import nc.block.tile.processor.BlockNuclearFurnace;
import net.minecraft.block.Block;

public class NuclearCraftCompat implements IFurnaceModCompat {
    public boolean isModFurnace(Block block){
        return block instanceof BlockNuclearFurnace;
    }
}
