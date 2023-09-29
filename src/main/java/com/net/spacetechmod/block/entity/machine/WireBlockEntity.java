package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;

public class WireBlockEntity extends BlockEntity {
    public WireBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WIRE.get(), pos, state);
    }

    private ArrayList<BlockPos> adjacents = new ArrayList<BlockPos>(
            Arrays.asList(worldPosition.above(), worldPosition.below(), worldPosition.north(),
                    worldPosition.south(), worldPosition.east(), worldPosition.west()));

    public int energyPerTick = 0;

    public int getEnergyPerTick() {
        return energyPerTick;
    }

    public void setEnergyPerTick(int newEnergy) {
        energyPerTick = newEnergy;
    }

    public void addEnergyPerTick(int newEnergy) {
        energyPerTick += newEnergy;
    }

    public void resetEnergyPerTick() {
        energyPerTick = 0;
    }

    public void updateNeighbors(Level level) {
        BlockEntity entity;
        for(BlockPos pos : adjacents) {
            if(level.getBlockEntity(pos) != null) {
                if(level.getBlockEntity(pos).getType() == ModBlockEntities.WIRE.get()) {
                    entity = level.getBlockEntity(pos);
                    ((WireBlockEntity) entity).addEnergyPerTick(this.energyPerTick);
                }
            }
        }
    }
}
