package furnacexpstorage.compat.modcompat;

import mod.beethoven92.betterendforge.common.block.EndStoneSmelter;
import net.minecraft.block.Block;

public class BetterEndCompat implements IFurnaceModCompat {
    public boolean isModFurnace(Block block){
        return block instanceof EndStoneSmelter;
    }
}
