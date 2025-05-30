package com.spacetechmod.block.entity.machine;

import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WarpDriveBlockEntity extends BlockEntity {
    public WarpDriveBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WARP_DRIVE.get(), pos, blockState);
    }

    public int direction = 0;
    //0 = forward, 1 = right, 2 = backward, 3 = left

    //SHIP SIZE IS INCLUSIVE OF THE CORE. Ex. 5 means 4 out from the core.
    public int shipSizeY = 0;
    public int shipSizeX = 0;
    public int shipSizeZ = 0;

    BlockPos oldCorePos = worldPosition;
    BlockPos newCorePos;
    BlockPos NEW_POS = new BlockPos(0, 0, 0); //for when the block is placed

    BlockPos scanOriginPos;

    BlockPos scratchPos;
    BlockPos newPos;

    BlockState scratchState;
    BlockEntity scratchEntity;

    int deltaX;
    int deltaY;
    int deltaZ;

    public void setSize(int x, int y, int z) {
        shipSizeX = x;
        shipSizeY = y;
        shipSizeZ = z;
    }

    public void setParameters(int x, int y, int z, int newDirection) {
        NEW_POS = new BlockPos(x, y, z);
        direction = newDirection;
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

    public void warp(Level level) {
        newCorePos = NEW_POS;
        scanOriginPos = new BlockPos(oldCorePos.getX() - shipSizeX, oldCorePos.getY() - shipSizeY, oldCorePos.getZ() - shipSizeZ);
        for(int y = 0; y < 2*shipSizeY; ++y) {
            for(int x = 0; x < 2*shipSizeX; ++x) {
                for(int z = 0; z < 2*shipSizeZ; ++z) {
                    scratchPos = new BlockPos(scanOriginPos.getX() + x, scanOriginPos.getY() + y, scanOriginPos.getZ() + z);
                    scratchState = level.getBlockState(scratchPos);
                    scratchEntity = level.getBlockEntity(scratchPos);
                    if(!ModLists.WARP_DRIVE_EXCLUSION_LIST.contains(scratchState.getBlock())) {
                        newPos = newCorePos.offset(findBlockDeltaFromCore(scratchPos, oldCorePos));
                        level.setBlock(newPos, scratchState.rotate(level, newPos, getBlockRotation()), 2);
                        if(scratchEntity instanceof ChestBlockEntity) {
                            ChestBlockEntity.swapContents(((ChestBlockEntity) scratchEntity), ((ChestBlockEntity) level.getBlockEntity(newPos)));
                        }
                        level.setBlock(scratchPos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }
        level.setBlock(newCorePos, ModBlocks.WARP_DRIVE.get().defaultBlockState(), 2);
        level.setBlock(oldCorePos, Blocks.AIR.defaultBlockState(), 2);
    }

    //TODO: saving and loading, crash fix for negative numbers, teleporting players,
    // figuring out why WarpDriveBlock #UseWithoutItem won't fire off, and transferring coords to new ship core
}
