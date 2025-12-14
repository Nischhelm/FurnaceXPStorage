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

        if (!isFurnaceBlock(event.getState().getBlock())) return;

        NBTTagCompound nbt = tile.getTileData();
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

    private static boolean isFurnaceBlock(Block block){
        if (block instanceof BlockFurnace) return true; //includes betterendforge EndFurnace, betterwithmods, fastfurnace, nethercraft

        //mod --> block (here) / tile (smeltItem mixin) / slot (2 mixins)
        //------------------------------
        // betterEnd EndStoneSmelter --> EndStoneSmelter / EndStoneSmelterTileEntity / SmelterOutputSlot
        // betterfurnacesreforged --> BlockSmelting / TileEntitySmeltingBase / SlotFurnaceOutput
        // betternether --> BlockCincinnasiteForge / vanilla(TileEntityForge::smeltItem) / vanilla
        // betterwithmods --> vanilla / vanilla(TileFurnace::smeltItem) / vanilla
        // fastfurnace --> vanilla / vanilla(TileFastFurnace::smeltItem) / vanilla
        // furnaceoverhaul --> BlockIronFurnace / TileEntityIronFurnace / SlotFurnaceOutput
        // furnus --> BlockDevice / TileDevice(processItem) / SlotOutput
        // ironfurnaces --> BlockMATERIALFurnace / TileEntityLockable / vanilla
        // morefurnaces x2 --> BlockMoreFurnaces / TileEntityIronFurnace / SlotOutput
        // nethercraft --> vanilla / vanilla(TileEntityNetherrackFurnace:smeltItem) / vanilla

        if (CompatHandler.isModLoaded("betterendforge") && CompatHandler.getHandler("betterendforge", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("betterfurnacesreforged") && CompatHandler.getHandler("betterfurnacesreforged", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("betternether") && CompatHandler.getHandler("betternether", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("furnaceoverhaul") && CompatHandler.getHandler("furnaceoverhaul", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("furnus") && CompatHandler.getHandler("furnus", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("ironfurnaces") && CompatHandler.getHandler("ironfurnaces", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("morefurnaces") && CompatHandler.getHandler("morefurnaces", false).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("morefurnaces") && CompatHandler.getHandler("morefurnaces", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("mysticalagriculture") && CompatHandler.getHandler("mysticalagriculture", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("nuclearcraft") && CompatHandler.getHandler("nuclearcraft", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("divinerpg") && CompatHandler.getHandler("divinerpg", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("simplecore") && CompatHandler.getHandler("simplecore", true).isModFurnace(block)) return true;
        if (CompatHandler.isModLoaded("natura") && CompatHandler.getHandler("natura", true).isModFurnace(block)) return true;
        return false;
    }
}
