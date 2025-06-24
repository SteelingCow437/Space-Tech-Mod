package com.spacetechmod.block.entity.machine;

import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;

public class WarpDriveBlockEntity extends BlockEntity {
    public WarpDriveBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WARP_DRIVE.get(), pos, blockState);
    }

    public int direction = 0;
    //0 = forward, 1 = right, 2 = left, 3 = backward

    int shipSizeY;
    int shipSizeX;
    int shipSizeZ;
    //SHIP SIZE IS INCLUSIVE OF CORE

    final BlockPos oldCorePos = worldPosition;
    BlockPos NEW_POS;

    BlockPos scanOriginPos;

    BlockPos scratchPos;
    BlockPos newPos;

    BlockState scratchState;
    BlockEntity scratchEntity;

    int deltaX;
    int deltaY;
    int deltaZ;

    public void setInitialSize(int x, int y, int z, @Nullable Player player, boolean announce) {
        shipSizeX = x;
        shipSizeY = y;
        shipSizeZ = z;
        if(announce) {
            player.sendSystemMessage(Component.literal("Ship dimensions set to: " + x + ", " + y + ", " + z));
        }
        setChanged();
    }

    public void rotateSize(int ix, int iy, int iz, int dir) {
        switch(dir) {
            case 1, 3 -> setInitialSize(iz, iy, ix, null, false);
            default -> setInitialSize(ix, iy, iz, null, false);
        }
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
    }

    public void changeDirection() {
        if(direction >= 3) {
            direction = 0;
        }
        else {
            ++direction;
        }
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
        deltaZ = block.getZ() - oldCorePos.getZ();
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

    private boolean getShipSize() {
        return (shipSizeX > 0 && shipSizeY > 0 && shipSizeZ > 0);
    }

    public void warp(Level level) {
        if(NEW_POS != null && NEW_POS != oldCorePos && getShipSize()) {
            AABB bounds = new AABB(oldCorePos.getX() - shipSizeX, oldCorePos.getY() - shipSizeY, oldCorePos.getZ() - shipSizeZ, oldCorePos.getX() + shipSizeX, oldCorePos.getY() + shipSizeY, oldCorePos.getZ() + shipSizeZ);
            scanOriginPos = new BlockPos(oldCorePos.getX() - shipSizeX, oldCorePos.getY() - shipSizeY, oldCorePos.getZ() - shipSizeZ);
            for (int y = 0; y < 2 * shipSizeY + 1; ++y) {
                for (int x = 0; x < 2 * shipSizeX + 1; ++x) {
                    for (int z = 0; z < 2 * shipSizeZ + 1; ++z) {
                        scratchPos = new BlockPos(scanOriginPos.getX() + x, scanOriginPos.getY() + y, scanOriginPos.getZ() + z);
                        scratchState = level.getBlockState(scratchPos);
                        scratchEntity = level.getBlockEntity(scratchPos);
                        if (!ModLists.WARP_DRIVE_EXCLUSION_LIST.contains(scratchState.getBlock())) {
                            newPos = NEW_POS.offset(findBlockDeltaFromCore(scratchPos, oldCorePos));
                            level.setBlock(newPos, scratchState.rotate(level, newPos, getBlockRotation()), 2);
                            if (scratchEntity instanceof ChestBlockEntity && level.getBlockEntity(newPos) instanceof ChestBlockEntity) {
                                ChestBlockEntity.swapContents(((ChestBlockEntity) scratchEntity), ((ChestBlockEntity) level.getBlockEntity(newPos)));
                            }
                            if(scratchEntity instanceof WarpDriveBlockEntity && level.getBlockEntity(newPos) instanceof WarpDriveBlockEntity) {
                                ((WarpDriveBlockEntity) level.getBlockEntity(newPos)).rotateSize(shipSizeX, shipSizeY, shipSizeZ, direction);
                            }
                            if(scratchEntity instanceof DispenserBlockEntity && level.getBlockEntity(newPos) instanceof DispenserBlockEntity) {
                                ItemStack stack;
                                for(int i = 0; i < ((DispenserBlockEntity) level.getBlockEntity(newPos)).getContainerSize(); ++i) {
                                    stack = ((DispenserBlockEntity) scratchEntity).getItem(i);
                                    ((DispenserBlockEntity) level.getBlockEntity(newPos)).setItem(i, stack);
                                    ((DispenserBlockEntity) scratchEntity).setItem(i, ItemStack.EMPTY);
                                }
                            }
                        }
                    }
                }
            }
            for (int y = 0; y < 2 * shipSizeY + 1; ++y) {
                for (int x = 0; x < 2 * shipSizeX + 1; ++x) {
                    for (int z = 0; z < 2 * shipSizeZ + 1; ++z) {
                        scratchPos = new BlockPos(scanOriginPos.getX() + x, scanOriginPos.getY() + y, scanOriginPos.getZ() + z);
                        level.setBlock(scratchPos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
            Vec3i newPlayerPos;
            for(Player player : level.getEntitiesOfClass(Player.class, bounds)) {
                newPlayerPos = findBlockDeltaFromCore(player.getOnPos(), oldCorePos);
                player.teleportTo(NEW_POS.offset(newPlayerPos).getX(), NEW_POS.offset(newPlayerPos).getY() + 1, NEW_POS.offset(newPlayerPos).getZ());
            }
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