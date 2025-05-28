package com.spacetechmod.block.entity.machine;

import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class WarpDriveBlockEntity extends BlockEntity {
    public WarpDriveBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WARP_DRIVE.get(), pos, blockState);
    }

    public int direction = 0;
    //0 = forward, 1 = right, 2 = left, 3 = backward

    int shipSizeY = 5;
    int shipSizeX = 5;
    int shipSizeZ = 5;

    BlockPos oldCorePos = worldPosition;
    BlockPos newCorePos;
    BlockPos NEW_POS = new BlockPos(15, -40, 20); //temporary

    BlockPos scanOriginPos;
    BlockPos buildOriginPos;

    BlockPos scratchPos;
    BlockPos newPos;

    BlockState scratchState;

    int deltaX;
    int deltaY;
    int deltaZ;


    public void changeDirection() {
        if(direction >= 3) {
            direction = 0;
        }
        else {
            ++direction;
        }
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
                    if(!ModLists.WARP_DRIVE_EXCLUSION_LIST.contains(scratchState.getBlock())) {
                        newPos = newCorePos.offset(findBlockDeltaFromCore(scratchPos, oldCorePos));
                        level.setBlock(newPos, scratchState, 2);
                    }
                }
            }
        }
        level.setBlock(newCorePos, ModBlocks.WARP_DRIVE.get().defaultBlockState(), 2);
        level.setBlock(oldCorePos, Blocks.AIR.defaultBlockState(), 2);
        //level.setBlock(scratchPos, Blocks.AIR.defaultBlockState(), 2);
        //newPos = scratchPos.rotate(spin);
        //findPosDeltas(oldCorePos, scratchPos, direction);
        //newPos = new BlockPos(newCorePos.getX() + deltaX, newCorePos.getY() + deltaY, newCorePos.getZ() + deltaZ);
    }


}
