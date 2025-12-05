package com.spacetechmod.block.entity.machine;

import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.util.ModLists;
import com.spacetechmod.util.ShipPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
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
import java.util.ArrayList;
import java.util.List;

public class WarpDriveBlockEntity extends BlockEntity {
    public WarpDriveBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WARP_DRIVE.get(), pos, blockState);
    }

    public int direction = 0;
    //0 = forward, 1 = right, 2 = backward, 3 = left

    private int shipSizeY;
    private int shipSizeX;
    private int shipSizeZ;
    //SHIP SIZE IS INCLUSIVE OF CORE. It's more like the ship radius

    //scanning stuff
    private final BlockPos oldCorePos = worldPosition;
    private BlockPos NEW_POS;

    private BlockPos scanOriginPos;

    private BlockPos scratchPos;
    private BlockPos newPos;

    private BlockState scratchState;
    private BlockEntity scratchEntity;

    private AABB bounds;


    //placing stuff
    private boolean warping = false;
    private ShipPart sp;
    private Vec3i newPlayerPos;
    private MobEffectInstance resist = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 999999, 9, false, false, false);
    private MobEffectInstance invis = new MobEffectInstance(MobEffects.INVISIBILITY, 999999, 9, false, false, false);

    private ArrayList<ShipPart> SHIP_PARTS = new ArrayList<ShipPart>(
            List.of());

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

    public void setDestination(BlockPos d, Player player) {
        if(d.getX() == 0 || d.getZ() == 0) {
            player.sendSystemMessage(Component.literal("ERROR: X or Z coordinate cannot equal Zero!"));
        }
        else {
            NEW_POS = d;
            player.sendSystemMessage(Component.literal("Destination set to: " + d.getX() + ", " + d.getY() + ", " + d.getZ()));
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
        boolean zero = (shipSizeX > 0 && shipSizeY > 0 && shipSizeZ > 0);
        boolean one = shipSizeX < 50 && shipSizeY < 50 && shipSizeZ < 50;
        return zero && one;
    }

    public void warp(Level level) {
        if(NEW_POS != null && NEW_POS != oldCorePos && getShipSize()) {
            bounds = new AABB(oldCorePos.getX() - shipSizeX, oldCorePos.getY() - shipSizeY, oldCorePos.getZ() - shipSizeZ, oldCorePos.getX() + shipSizeX, oldCorePos.getY() + shipSizeY, oldCorePos.getZ() + shipSizeZ);
            scanOriginPos = new BlockPos(oldCorePos.getX() - shipSizeX, oldCorePos.getY() - shipSizeY, oldCorePos.getZ() - shipSizeZ);
            for(Player player : level.getEntitiesOfClass(Player.class, bounds)) {
                player.setNoGravity(true);
                player.addEffect(resist);
                player.addEffect(invis);
            }
            for (int y = 0; y < 2 * shipSizeY + 1; ++y) {
                for (int x = 0; x < 2 * shipSizeX + 1; ++x) {
                    for (int z = 0; z < 2 * shipSizeZ + 1; ++z) {
                        scratchPos = new BlockPos(scanOriginPos.getX() + x, scanOriginPos.getY() + y, scanOriginPos.getZ() + z);
                        scratchState = level.getBlockState(scratchPos);
                        scratchEntity = level.getBlockEntity(scratchPos);

                        if(scratchEntity instanceof ChestBlockEntity) {
                            newPos = NEW_POS.offset(findBlockDeltaFromCore(scratchPos, oldCorePos));
                            level.setBlock(newPos, scratchState.rotate(level, newPos, getBlockRotation()), 2);
                            ChestBlockEntity.swapContents(((ChestBlockEntity) scratchEntity), ((ChestBlockEntity) level.getBlockEntity(newPos)));
                            level.setBlock(scratchPos, Blocks.AIR.defaultBlockState(), 2);
                        }
                        if(scratchEntity instanceof DispenserBlockEntity) {
                            newPos = NEW_POS.offset(findBlockDeltaFromCore(scratchPos, oldCorePos));
                            level.setBlock(newPos, scratchState.rotate(level, newPos, getBlockRotation()), 2);
                            ItemStack stack;
                            for(int i = 0; i < ((DispenserBlockEntity) level.getBlockEntity(newPos)).getContainerSize(); ++i) {
                                stack = ((DispenserBlockEntity) scratchEntity).getItem(i);
                                ((DispenserBlockEntity) level.getBlockEntity(newPos)).setItem(i, stack);
                                ((DispenserBlockEntity) scratchEntity).setItem(i, ItemStack.EMPTY);
                            }
                            level.setBlock(scratchPos, Blocks.AIR.defaultBlockState(), 2);
                        }
                        if(scratchEntity instanceof WarpDriveBlockEntity) {
                            newPos = NEW_POS.offset(findBlockDeltaFromCore(scratchPos, oldCorePos));
                            level.setBlock(newPos, scratchState.rotate(level, newPos, getBlockRotation()), 2);
                            ((WarpDriveBlockEntity) level.getBlockEntity(newPos)).rotateSize(shipSizeX, shipSizeY, shipSizeZ, direction);
                        }

                        if (!ModLists.WARP_DRIVE_EXCLUSION_LIST.contains(scratchState.getBlock())) {
                            newPos = NEW_POS.offset(findBlockDeltaFromCore(scratchPos, oldCorePos));
                            SHIP_PARTS.add(new ShipPart(scratchState.rotate(level, newPos, getBlockRotation()), newPos, scratchPos));
                        }
                    }
                }
            }
            warping = true;
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

    public static void tick(Level level, BlockPos pos, BlockState state, WarpDriveBlockEntity entity) {
        if(entity.warping && !entity.SHIP_PARTS.isEmpty()) {
            for(int i = 0; i < 10; ++i) {
                if(!entity.SHIP_PARTS.isEmpty()) {
                    entity.sp = entity.SHIP_PARTS.getFirst();
                    level.setBlock(entity.sp.getPos(), entity.sp.getBlock(), 2);
                    entity.SHIP_PARTS.removeFirst();
                    level.setBlock(entity.sp.getOldPos(), Blocks.AIR.defaultBlockState(), 2);
                    if(ModLists.WARP_DRIVE_EXCLUSION_LIST.contains(entity.sp.getBlock().getBlock()) && entity.sp.getBlock().getBlock() != Blocks.AIR) {
                        for(ItemEntity item : level.getEntitiesOfClass(ItemEntity.class, entity.bounds)) {
                            item.kill();
                        }
                    }
                }
            }
        }
        if(entity.warping && entity.SHIP_PARTS.isEmpty()) {
            for(Player player : level.getEntitiesOfClass(Player.class, entity.bounds)) {
                    entity.newPlayerPos = entity.findBlockDeltaFromCore(player.getOnPos(), entity.oldCorePos);
                    player.teleportTo(entity.NEW_POS.offset(entity.newPlayerPos).getX(), entity.NEW_POS.offset(entity.newPlayerPos).getY() + 1, entity.NEW_POS.offset(entity.newPlayerPos).getZ());
                    player.setNoGravity(false);
                    player.removeEffect(entity.resist.getEffect());
                    player.removeEffect(entity.invis.getEffect());
            }
            entity.warping = false;
            level.setBlock(entity.oldCorePos, Blocks.AIR.defaultBlockState(), 2);
        }
    }
}