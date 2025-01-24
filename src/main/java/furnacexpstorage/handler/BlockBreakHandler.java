package furnacexpstorage.handler;

import furnacexpstorage.FurnaceXPStorage;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BlockBreakHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        World world = event.getWorld();
        if (world.isRemote) return;
        BlockPos pos = event.getPos();
        if (!(event.getState().getBlock() instanceof BlockFurnace)) return;
        if (!(world.getTileEntity(pos) instanceof TileEntityFurnace)) return;
        TileEntityFurnace furnace = (TileEntityFurnace) world.getTileEntity(pos);
        if (furnace == null) return;
        NBTTagCompound nbt = furnace.getTileData();
        if(!nbt.hasKey(FurnaceXPStorage.NBTKEY)) return;

        int outXP = getRandXP(nbt.getFloat(FurnaceXPStorage.NBTKEY));
        nbt.setFloat(FurnaceXPStorage.NBTKEY, 0);

        // Drop XP
        if (outXP < 0) return;
        while (outXP > 0) {
            int orbXP = EntityXPOrb.getXPSplit(outXP);
            outXP -= orbXP;
            world.spawnEntity(new EntityXPOrb(world, pos.getX(), pos.getY(), pos.getZ(), orbXP));
        }
    }

    public static int getRandXP(float storedXP){
        int outXP = MathHelper.floor(storedXP);
        if (FurnaceXPStorage.RAND.nextFloat() < storedXP - outXP)
            outXP++;
        return outXP;
    }
}
