package com.spacetechmod.block.entity.dungeon;

import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.block.custom.dungeon.StarGatePortalBlock;
import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.util.ModMultiBlockStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StarGateCoreBlockEntity extends BlockEntity {

        public StarGateCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STARGATE_CORE.get(), pos, state);
    }

    private static int timer = 0;
    public boolean ACTIVE = false;
    public int destinationX = 0;
    public int destinationY = 0;
    public int destinationZ = 0;

    public void setDestination(int x, int y, int z) {
        destinationX = x;
        destinationY = y;
        destinationZ = z;
    }


    private int getDirection() { //0 is X, 1 is Z, 2 means no block is there
            if(level.getBlockState(worldPosition.east()).getBlock() == ModBlocks.STARGATE_FRAME.get()
                || level.getBlockState(worldPosition.west()).getBlock() == ModBlocks.STARGATE_FRAME.get()) {
                return 0;
            }
            else if(level.getBlockState(worldPosition.north()).getBlock() == ModBlocks.STARGATE_FRAME.get()
                || level.getBlockState(worldPosition.south()).getBlock() == ModBlocks.STARGATE_FRAME.get()) {
                return 1;
            }
            else {
                return 2;
            }
    }

    public void breakPortalOnDirection() {
        switch(getDirection()) {
            case 0 -> breakPortalOnX();
            case 1 -> breakPortalOnZ();
        }
    }

    public void makePortalOnDirection() {
        switch(getDirection()) {
            case 0 -> makePortalOnX();
            case 1 -> makePortalOnZ();
        }
    }

    public boolean checkForCompletion(Player player) {
        switch(getDirection()) {
            case 0 -> {
                return checkXCompletion();
            }
            case 1 -> {
                return checkZCompletion();
            }
            case 2 -> {
                player.sendSystemMessage(Component.literal("Portal Incomplete!"));
                return false;
            }
        }
        return false;
    }

    private boolean tickCheckForCompletion() {
        switch(getDirection()) {
            case 0 -> {
                return checkXCompletion();
            }
            case 1 -> {
                return checkZCompletion();
            }
            default -> {
                return false;
            }
        }
    }

    private boolean checkXCompletion() {
            boolean toReturn = true;
            BlockPos origin = worldPosition;
            BlockPos pos;
            for(int i = 0; i < 36; i += 2) {
                pos = new BlockPos(origin.getX() + ModMultiBlockStructures.STARGATE_FRAME_POSITIONS.get(i), origin.getY() + ModMultiBlockStructures.STARGATE_FRAME_POSITIONS.get(i + 1), origin.getZ());
                if(level.getBlockState(pos).getBlock() != ModBlocks.STARGATE_FRAME.get()) {
                    toReturn = false;
                }
            }
            return toReturn;
    }

    private boolean checkZCompletion() {
        boolean toReturn = true;
        BlockPos origin = worldPosition;
        BlockPos pos;
        for(int i = 0; i < 36; i += 2) {
            pos = new BlockPos(origin.getX(), origin.getY() + ModMultiBlockStructures.STARGATE_FRAME_POSITIONS.get(i + 1), origin.getZ() + ModMultiBlockStructures.STARGATE_FRAME_POSITIONS.get(i));
            if(level.getBlockState(pos).getBlock() != ModBlocks.STARGATE_FRAME.get()) {
                toReturn = false;
            }
        }
        return toReturn;
    }


    //portal making and breaking
    private void makePortalOnX() {
        BlockPos origin = new BlockPos(worldPosition.getX() - 4, worldPosition.getY() + 10, worldPosition.getZ());
        BlockPos setAt;
        Block block;
        for(int i = 0; i < 9; ++i) {
            for(int e = 0; e < 9; ++e) {
                setAt = new BlockPos(origin.getX() + i, origin.getY() - e, origin.getZ());
                level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
                block = level.getBlockState(setAt).getBlock();
                ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
            }
        }
        //sides
        origin = new BlockPos(worldPosition.getX() - 5, worldPosition.getY() + 8, worldPosition.getZ());
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(origin.getX(), origin.getY() - i, worldPosition.getZ());
            level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
            block = level.getBlockState(setAt).getBlock();
            ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
        }
        origin = new BlockPos(worldPosition.getX() + 5, worldPosition.getY() + 8, worldPosition.getZ());
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(origin.getX(), origin.getY() - i, worldPosition.getZ());
            level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
            block = level.getBlockState(setAt).getBlock();
            ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
        }
        //top and bottom
        origin = new BlockPos(worldPosition.getX() - 2, worldPosition.getY() + 1, worldPosition.getZ());
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(origin.getX() + i, origin.getY(), worldPosition.getZ());
            level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
            block = level.getBlockState(setAt).getBlock();
            ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
        }
        origin = new BlockPos(worldPosition.getX() - 2, worldPosition.getY() + 11, worldPosition.getZ());
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(origin.getX() + i, origin.getY(), worldPosition.getZ());
            level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
            block = level.getBlockState(setAt).getBlock();
            ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
        }
        ACTIVE = true;
    }

    private void makePortalOnZ() {
        BlockPos origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 10, worldPosition.getZ() - 4);
        BlockPos setAt;
        Block block;
        for(int i = 0; i < 9; ++i) {
            for(int e = 0; e < 9; ++e) {
                setAt = new BlockPos(origin.getX(), origin.getY() - e, origin.getZ() + i);
                level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
                block = level.getBlockState(setAt).getBlock();
                ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
            }
        }
        //sides
        origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 8, worldPosition.getZ() - 5);
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(worldPosition.getX(), origin.getY() - i, origin.getZ());
            level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
            block = level.getBlockState(setAt).getBlock();
            ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
        }
        origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 8, worldPosition.getZ() + 5);
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(worldPosition.getX(), origin.getY() - i, origin.getZ());
            level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
            block = level.getBlockState(setAt).getBlock();
            ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
        }
        //top and bottom
        origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ() - 2);
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(worldPosition.getX(), origin.getY(), origin.getZ() + i);
            level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
            block = level.getBlockState(setAt).getBlock();
            ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
        }
        origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 11, worldPosition.getZ() - 2);
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(worldPosition.getX(), origin.getY(), origin.getZ() + i);
            level.setBlockAndUpdate(setAt, ModBlocks.STARGATE_PORTAL.get().defaultBlockState());
            block = level.getBlockState(setAt).getBlock();
            ((StarGatePortalBlock) block).setDestination(destinationX, destinationY, destinationZ);
        }
        ACTIVE = true;
    }

    private void breakPortalOnX() {
        BlockPos origin = new BlockPos(worldPosition.getX() - 4, worldPosition.getY() + 10, worldPosition.getZ());
        BlockPos setAt;
        for(int i = 0; i < 9; ++i) {
            for(int e = 0; e < 9; ++e) {
                setAt = new BlockPos(origin.getX() + i, origin.getY() - e, origin.getZ());
                level.removeBlock(setAt, false);
            }
        }
        //sides
        origin = new BlockPos(worldPosition.getX() - 5, worldPosition.getY() + 8, worldPosition.getZ());
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(origin.getX(), origin.getY() - i, worldPosition.getZ());
            level.removeBlock(setAt, false);
        }
        origin = new BlockPos(worldPosition.getX() + 5, worldPosition.getY() + 8, worldPosition.getZ());
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(origin.getX(), origin.getY() - i, worldPosition.getZ());
            level.removeBlock(setAt, false);
        }
        //top and bottom
        origin = new BlockPos(worldPosition.getX() - 2, worldPosition.getY() + 1, worldPosition.getZ());
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(origin.getX() + i, origin.getY(), worldPosition.getZ());
            level.removeBlock(setAt, false);
        }
        origin = new BlockPos(worldPosition.getX() - 2, worldPosition.getY() + 11, worldPosition.getZ());
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(origin.getX() + i, origin.getY(), worldPosition.getZ());
            level.removeBlock(setAt, false);
        }
        ACTIVE = false;
    }

    private void breakPortalOnZ() {
        BlockPos origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 10, worldPosition.getZ() + 4);
        BlockPos setAt;
        for(int i = 0; i < 9; ++i) {
            for(int e = 0; e < 9; ++e) {
                setAt = new BlockPos(origin.getX(), origin.getY() - e, origin.getZ() - i);
                level.removeBlock(setAt, false);
            }
        }
        //sides
        origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 8, worldPosition.getZ() + 5);
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(worldPosition.getX(), origin.getY() - i, origin.getZ());
            level.removeBlock(setAt, false);
        }
        origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 8, worldPosition.getZ() - 5);
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(worldPosition.getX(), origin.getY() - i, origin.getZ());
            level.removeBlock(setAt, false);
        }
        //top and bottom
        origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ() + 2);
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(worldPosition.getX(), origin.getY(), origin.getZ() - i);
            level.removeBlock(setAt, false);
        }
        origin = new BlockPos(worldPosition.getX(), worldPosition.getY() + 11, worldPosition.getZ() + 2);
        for(int i = 0; i < 5; ++i) {
            setAt = new BlockPos(worldPosition.getX(), origin.getY(), origin.getZ() - i);
            level.removeBlock(setAt, false);
        }
        ACTIVE = false;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, StarGateCoreBlockEntity entity) {
        ++timer;
        if(timer >= 20) {
            if(!entity.tickCheckForCompletion() && entity.ACTIVE) {
                entity.ACTIVE = false;
                entity.breakPortalOnDirection();
            }
        }
    }
}