package furnacexpstorage.compat.modcompat;

import com.blakebr0.mysticalagriculture.blocks.furnace.BlockEssenceFurnace;
import net.minecraft.block.Block;

public class MysticalAgricultureCompat implements IFurnaceModCompat {
    public boolean isModFurnace(Block block){
        return block instanceof BlockEssenceFurnace;
    }
}
