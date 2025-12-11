package furnacexpstorage.compat.modcompat;

import cazador.furnaceoverhaul.blocks.BlockIronFurnace;
import net.minecraft.block.Block;

public class FurnaceOverhaulCompat implements IFurnaceModCompat {
    public boolean isModFurnace(Block block){
        return block instanceof BlockIronFurnace;
    }
}
