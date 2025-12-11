package furnacexpstorage.compat.modcompat;

import net.minecraft.block.Block;
import wily.betterfurnaces.blocks.BlockSmelting;

public class BetterFurnacesReforgedCompat implements IFurnaceModCompat {
    public boolean isModFurnace(Block block){
        return block instanceof BlockSmelting;
    }
}
