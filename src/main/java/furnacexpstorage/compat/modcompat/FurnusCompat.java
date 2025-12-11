package furnacexpstorage.compat.modcompat;

import mrriegel.furnus.init.ModBlocks;
import net.minecraft.block.Block;

public class FurnusCompat implements IFurnaceModCompat{
    @Override
    public boolean isModFurnace(Block block) {
        return block == ModBlocks.furnus.getItemBlock().getBlock() || block == ModBlocks.pulvus.getItemBlock().getBlock();
    }
}
