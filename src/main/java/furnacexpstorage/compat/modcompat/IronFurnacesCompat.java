package furnacexpstorage.compat.modcompat;

import com.xenomustache.ironfurnaces.blocks.*;
import net.minecraft.block.Block;

public class IronFurnacesCompat implements IFurnaceModCompat {
    public boolean isModFurnace(Block block){
        if(block instanceof BlockDiamondFurnace) return true;
        if(block instanceof BlockGlassFurnace) return true;
        if(block instanceof BlockGoldFurnace) return true;
        if(block instanceof BlockIronFurnace) return true;
        if(block instanceof BlockObsidianFurnace) return true;
        if(block instanceof BlockShulkerFurnace) return true;
        return false;
    }
}
