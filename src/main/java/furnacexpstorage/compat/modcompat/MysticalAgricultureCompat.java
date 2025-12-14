package furnacexpstorage.compat.modcompat;

import com.blakebr0.mysticalagriculture.blocks.furnace.BlockEssenceFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class MysticalAgricultureCompat implements IFurnaceModCompat {
	@Override
    public boolean isModFurnace(Block block){
        return block instanceof BlockEssenceFurnace;
    }

	@Override
	public boolean isModFurnace(TileEntity tile) {
		return tile instanceof TileEssenceFurnace;
	}
}
