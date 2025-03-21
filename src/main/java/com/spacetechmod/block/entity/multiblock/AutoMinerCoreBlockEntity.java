package com.spacetechmod.block.entity.multiblock;

import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.util.ModMultiBlockStructures;
import com.spacetechmod.util.MultiBlockPart;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;

public class AutoMinerCoreBlockEntity extends BlockEntity {

    public AutoMinerCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.AUTO_MINER.get(), pos, blockState);
    }

    private Block targetMaterial;
    private AABB BOUNDS;
    private ServerLevel serverLevel;

    private void setServerLevel(Player player) {
        try {
            serverLevel = player.getServer().getLevel(player.level().dimension());
        } catch (Exception e) {
            level.playSound(null, worldPosition, SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 2.0f, 1.0f);
        }
    }


    public boolean isStructureValid(ArrayList<MultiBlockPart> structure, ServerLevel level, BlockPos originPos) {
        for(int i = 0; i < structure.size(); ++i) {
            if(level.getBlockState(new BlockPos(originPos.getX() + structure.get(i).relativeX, originPos.getY() + structure.get(i).relativeY, originPos.getZ() + structure.get(i).relativeZ)).getBlock() != structure.get(i).block) {
                return false;
            }
        }
        return true;
    }

    private void makeBounds() {
        if(BOUNDS == null) {
            BOUNDS = new AABB(worldPosition.getX() - 30, -60, worldPosition.getZ() - 30, worldPosition.getX() + 30, 180, worldPosition.getZ() + 30);
        }
    }

    private void removeBounds() {
        BOUNDS = null;
    }

    public void switchMaterial(ItemStack stack) {
        if(stack.getItem() instanceof BlockItem) {
            targetMaterial = Block.byItem(stack.getItem());
        }
    }

    public void onRemove() {
        removeBounds();
    }

    public void setup(Player player) {
        setServerLevel(player);
        if(isStructureValid(ModMultiBlockStructures.AUTO_MINER, serverLevel, worldPosition)) {
            makeBounds();
        }
        makeBounds();

    }

    private void mine(Level level) {
        if(isStructureValid(ModMultiBlockStructures.AUTO_MINER, serverLevel, worldPosition) && BOUNDS != null) {

        }
    }






}