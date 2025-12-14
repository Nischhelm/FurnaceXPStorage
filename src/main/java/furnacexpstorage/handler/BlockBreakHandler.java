package furnacexpstorage.handler;

import furnacexpstorage.FurnaceXPStorage;
import furnacexpstorage.compat.CompatHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = FurnaceXPStorage.MODID)
public class BlockBreakHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        World world = event.getWorld();
        if (world.isRemote) return;

        BlockPos pos = event.getPos();
        TileEntity tile = world.getTileEntity(pos);
        if(tile == null) return; //early return if no tile entity

        if (!CompatHandler.isFurnaceTile(tile)) return;

        NBTTagCompound nbt = tile.getTileData();
        if(!nbt.hasKey(FurnaceXPStorage.NBTKEY)) return;

        int outXP = getRandXP(nbt.getFloat(FurnaceXPStorage.NBTKEY));
        nbt.setFloat(FurnaceXPStorage.NBTKEY, 0); //just for safety, tile should get deleted

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
