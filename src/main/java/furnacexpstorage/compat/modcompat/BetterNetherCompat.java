package furnacexpstorage.compat.modcompat;

import net.minecraft.block.Block;
import paulevs.betternether.blocks.BlockCincinnasiteForge;

public class BetterNetherCompat implements IFurnaceModCompat {
    public boolean isModFurnace(Block block){
        return block instanceof BlockCincinnasiteForge;
    }
}
