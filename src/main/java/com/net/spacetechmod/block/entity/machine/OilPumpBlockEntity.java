package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.block.entity.fluid.BasicFluidBarrelBlockEntity;
import com.net.spacetechmod.fluid.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OilPumpBlockEntity extends BlockEntity {
    public OilPumpBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OIL_PUMP.get(), pos, state);
    }
    private int x = worldPosition.getX();
    private int y = worldPosition.getY();
    private int z = worldPosition.getZ();

    public boolean active = false;

    public static final int powerDrawPerTick = 25;

    private BlockEntity wire;

    private BasicFluidBarrelBlockEntity connectedBarrel;

    private final ArrayList<BlockPos> ADJACENT_POS_LIST = new ArrayList<BlockPos>(
            Arrays.asList(worldPosition.north(), worldPosition.south(), worldPosition.east(),
                    worldPosition.west(), worldPosition.above(), worldPosition.below()));

    private static int timer = 0;
    private AABB boundingBox;
    private static Random random = new Random();

    private static int index;
    private static BlockPos removePos;

    private int overflowCapacity = 0;

    public void makeBoundingBox() {
        if(y >= 0) {
            boundingBox = new AABB(x - 10, y, z - 10, x + 10, y - 60, z + 10);
        }
    }

    private ArrayList<BlockPos> getBlocksInAABB(Level level, AABB aabb, List<Block> blockTypes) {
        ArrayList<BlockPos> blockPositions = new ArrayList<>();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        for (int x = (int) aabb.minX; x <= aabb.maxX; ++x) {
            for (int y = (int) aabb.minY; y <= aabb.maxY; ++y) {
                for (int z = (int) aabb.minZ; z <= aabb.maxZ; ++z) {
                    mutablePos.set(x, y, z);
                    BlockState blockState = level.getBlockState(mutablePos);
                    Block block = blockState.getBlock();
                    if (blockTypes.contains(block)) {
                        blockPositions.add(mutablePos.immutable());
                    }
                }
            }
        }
        return blockPositions;
    }

    public void drawPower() {
        if(powerSourceConnected(level) && checkPower()) {
            ((WireBlockEntity) wire).addEnergyPerTick(-powerDrawPerTick);
            ((WireBlockEntity) wire).updateNeighbors(level);
        }
    }

    public boolean checkPower() {
        if (wire != null && wire instanceof WireBlockEntity) {
            if (active) {
                return ((WireBlockEntity) wire).energyPerTick > -1;
            } else {
                return ((WireBlockEntity) wire).energyPerTick >= powerDrawPerTick;
            }
        }
        return false;
    }
    public void disconnectPower() {
        if(powerSourceConnected(level)) {
            ((WireBlockEntity) wire).addEnergyPerTick(powerDrawPerTick);
        }
    }

    private ArrayList getOilDeposits(Level level) {
        if(boundingBox != null) {
            ArrayList<Block> desiredBlocks = new ArrayList<Block>(List.of(ModBlocks.OIL_DEPOSIT.get()));
            return getBlocksInAABB(level, boundingBox, desiredBlocks);
        }
        else {
            makeBoundingBox();
        }
        return null;
    }

    public boolean barrelConnected(Level level) {
        for (BlockPos pos : ADJACENT_POS_LIST) {
            if (level.getBlockEntity(pos) instanceof BasicFluidBarrelBlockEntity) {
                connectedBarrel = (BasicFluidBarrelBlockEntity) level.getBlockEntity(pos);
                return true;
            }
        }
        connectedBarrel = null;
        return false;
    }

    public boolean powerSourceConnected(Level level) {
        for(BlockPos pos : ADJACENT_POS_LIST) {
            if(level.getBlockEntity(pos) instanceof WireBlockEntity) {
                wire = level.getBlockEntity(pos);
                return wire != null && ((WireBlockEntity) wire).energyPerTick >= powerDrawPerTick;
            }
        }
        return false;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, OilPumpBlockEntity entity) {
        if(timer >= 100 && entity.boundingBox != null && entity.active && entity.checkPower()) {
            if(entity.barrelConnected(level) && entity.getOilDeposits(level) != null) {
                if(entity.connectedBarrel.fluidType == null) {entity.connectedBarrel.fluidType = ModFluids.CRUDE_OIL.get();}
                while(!entity.connectedBarrel.isFull() && entity.overflowCapacity > 0) {
                    entity.overflowCapacity--;
                    entity.connectedBarrel.amount++;
                }
                index = random.nextInt(entity.getOilDeposits(level).size());
                removePos = (BlockPos) entity.getOilDeposits(level).get(index);
                if(removePos != null) {
                    level.removeBlock(removePos, false);
                    if(!entity.connectedBarrel.isFull()) {
                        entity.connectedBarrel.fillBarrelFromMachine(ModFluids.CRUDE_OIL.get(), 2);
                        while(entity.connectedBarrel.amount > entity.connectedBarrel.capacity) {
                            entity.connectedBarrel.amount--;
                            entity.overflowCapacity++;
                        }
                    }
                    else {
                        entity.overflowCapacity += 2;
                    }
                }
                else {
                    entity.disconnectPower();
                }
            }
        }
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.putInt("overflow", overflowCapacity);
        nbt.putBoolean("active", active);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        overflowCapacity = nbt.getInt("overflow");
        active = nbt.getBoolean("active");
        getOilDeposits(level);
        super.load(nbt);
    }

    public void debug(Player player) {
        player.sendSystemMessage(Component.literal(
                "Is drawing power: " + active + "\n"
                + "Overflow: " + overflowCapacity + "\n"
                + "Amount of detected oil blocks: " + getOilDeposits(level)));
    }
}