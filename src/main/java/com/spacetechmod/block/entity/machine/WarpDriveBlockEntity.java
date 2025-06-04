package com.spacetechmod.block.entity.machine;

import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class WarpDriveBlockEntity extends BlockEntity {
    public WarpDriveBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WARP_DRIVE.get(), pos, blockState);
    }

    public int direction = 0;
    //0 = forward, 1 = right, 2 = left, 3 = backward

    int shipSizeY = 0;
    int shipSizeX = 0;
    int shipSizeZ = 0;
    //SHIP SIZE IS INCLUSIVE OF CORE

    BlockPos oldCorePos = worldPosition;
    BlockPos newCorePos;
    BlockPos NEW_POS;

    BlockPos scanOriginPos;

    BlockPos scratchPos;
    BlockPos newPos;

    BlockState scratchState;
    BlockEntity scratchEntity;

    int deltaX;
    int deltaY;
    int deltaZ;

    public void setInitialSize(int x, int y, int z, Player player) {
        shipSizeX = x;
        shipSizeY = y;
        shipSizeZ = z;
        player.sendSystemMessage(Component.literal("Ship dimensions set to: " + x + ", " + y + ", " + z));
        setChanged();
    }

    private void setSizeAfterWarp(int x, int y, int z, int dir) {
        switch(dir) {
            case 1 -> {
                shipSizeX = -z;
                shipSizeZ = x;

            }
            case 2 -> {
                shipSizeX = -x;
                shipSizeZ = -z;

            }
            case 3 -> {
                shipSizeX = z;
                shipSizeZ = -x;

            }
        }
        shipSizeY = y;
        setChanged();
    }

    public void setDestination(int x, int y, int z, Player player) {
        if(x == 0 || z == 0) {
            player.sendSystemMessage(Component.literal("ERROR: X or Z coordinate cannot equal Zero!"));
        }
        else {
            NEW_POS = new BlockPos(x, y, z);
            player.sendSystemMessage(Component.literal("Destination set to: " + x + ", " + y + ", " + z));
        }
        setChanged();
    }

    public void changeDirection() {
        if(direction >= 3) {
            direction = 0;
        }
        else {
            ++direction;
        }
        setChanged();
    }

    public String getDirectionName() {
        switch(direction) {
            case 0 -> {
                return "Forward";
            }
            case 1 -> {
                return "Right";
            }
            case 2 -> {
                return "Backward";
            }
            case 3 -> {
                return "Left";
            }
        }
        return "How'd you manage this?";
    }

    private Vec3i findBlockDeltaFromCore(BlockPos block, BlockPos oldCorePos) {
        deltaX = block.getX() - oldCorePos.getX();
        deltaY = block.getY() - oldCorePos.getY();
        deltaZ = block.getZ() - oldCorePos.getX();
        switch(direction) {
            case 1 -> {
                return new Vec3i(-deltaZ, deltaY, deltaX);
            }
            case 2 -> {
                return new Vec3i(-deltaX, deltaY, -deltaZ);
            }
            case 3 -> {
                return new Vec3i(deltaZ, deltaY, -deltaX);
            }
        }
        return new Vec3i(deltaX, deltaY, deltaZ);
    }

    private Rotation getBlockRotation() {
        switch(direction) {
            case 0 -> {
                return Rotation.NONE;
            }
            case 1 -> {
                return Rotation.CLOCKWISE_90;
            }
            case 2 -> {
                return Rotation.CLOCKWISE_180;
            }
            case 3 -> {
                return Rotation.COUNTERCLOCKWISE_90;
            }
        }
        return Rotation.NONE;
    }

    public void warp(Level level) {
        if(NEW_POS != null && NEW_POS != oldCorePos) {
            AABB bounds = new AABB(oldCorePos.getX() - shipSizeX, oldCorePos.getY() - shipSizeY, oldCorePos.getZ() - shipSizeZ, oldCorePos.getX() + shipSizeX, oldCorePos.getY() + shipSizeY, oldCorePos.getZ() + shipSizeZ);
            BlockEntity newEntity;
            newCorePos = NEW_POS;
            scanOriginPos = new BlockPos(oldCorePos.getX() - shipSizeX, oldCorePos.getY() - shipSizeY, oldCorePos.getZ() - shipSizeZ);
            for (int y = 0; y < 2 * shipSizeY; ++y) {
                for (int x = 0; x < 2 * shipSizeX; ++x) {
                    for (int z = 0; z < 2 * shipSizeZ; ++z) {
                        scratchPos = new BlockPos(scanOriginPos.getX() + x, scanOriginPos.getY() + y, scanOriginPos.getZ() + z);
                        scratchState = level.getBlockState(scratchPos);
                        scratchEntity = level.getBlockEntity(scratchPos);
                        if (!ModLists.WARP_DRIVE_EXCLUSION_LIST.contains(scratchState.getBlock())) {
                            newPos = newCorePos.offset(findBlockDeltaFromCore(scratchPos, oldCorePos));
                            level.setBlock(newPos, scratchState.rotate(level, newPos, getBlockRotation()), 2);
                            if (scratchEntity instanceof ChestBlockEntity && level.getBlockEntity(newPos) instanceof ChestBlockEntity) {
                                ChestBlockEntity.swapContents(((ChestBlockEntity) scratchEntity), ((ChestBlockEntity) level.getBlockEntity(newPos)));
                            }
                        }
                    }
                }
            }
            for (int y = 0; y < 2 * shipSizeY; ++y) {
                for (int x = 0; x < 2 * shipSizeX; ++x) {
                    for (int z = 0; z < 2 * shipSizeZ; ++z) {
                        scratchPos = new BlockPos(scanOriginPos.getX() + x, scanOriginPos.getY() + y, scanOriginPos.getZ() + z);
                        level.setBlock(scratchPos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
            for(Player player : level.getEntitiesOfClass(Player.class, bounds)) {
                player.teleportTo(newCorePos.getX(), newCorePos.getY(), newCorePos.getZ() - 1);
            }
            level.setBlock(newCorePos, ModBlocks.WARP_DRIVE.get().defaultBlockState(), 2);
            newEntity = level.getBlockEntity(newCorePos);
            ((WarpDriveBlockEntity) newEntity).setSizeAfterWarp(shipSizeX, shipSizeY, shipSizeZ, direction);
            level.setBlock(oldCorePos, Blocks.AIR.defaultBlockState(), 2);

            setChanged();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("size_x", shipSizeX);
        tag.putInt("size_y", shipSizeY);
        tag.putInt("size_z", shipSizeZ);
        tag.putInt("direction", direction);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        shipSizeX = tag.getInt("size_x");
        shipSizeY = tag.getInt("size_y");
        shipSizeZ = tag.getInt("size_z");
        direction = tag.getInt("direction");
    }
}
