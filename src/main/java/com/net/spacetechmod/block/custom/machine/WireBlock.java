package com.net.spacetechmod.block.custom.machine;

import com.net.spacetechmod.block.entity.machine.DynamoBlockEntity;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.item.custom.magic.LightningStaffItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class WireBlock extends Block {
    public WireBlock(Properties properties) {
        super(properties);
    }
    public double wattage = 0;
    public double wattageAddedByDynamo = 0;
    @Nullable
    BlockPos entityPos;
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
        wattage = 0;
        wattageAddedByDynamo = 0;
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);
        Block block = level.getBlockState(neighbor).getBlock();
        BlockEntity entity = level.getBlockEntity(neighbor);
        if(block instanceof WireBlock) {
            this.wattage = ((WireBlock) block).wattage;
        }
        else if(entity instanceof DynamoBlockEntity) {
            this.wattage += ((DynamoBlockEntity) entity).outputWattage;
            this.wattageAddedByDynamo = ((DynamoBlockEntity) entity).outputWattage;
            if(((DynamoBlockEntity) entity).outputWattage == 0) {
                this.wattage = 0;
                this.wattageAddedByDynamo = 0;
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            if(player.isHolding(Items.STICK)) {
                player.sendSystemMessage(Component.literal("Total Wattage: " + getOutput()));
            }
            else if(player.getMainHandItem().getItem() == ModItems.LIGHTNING_STAFF.get()) {
                ((LightningStaffItem) player.getMainHandItem().getItem()).charge = 0;
                setWattage(120);
            }
        }
        return InteractionResult.SUCCESS;
    }
    public String getOutput() {
        if (wattage < 1000) {
            return wattage + " W";
        }
        else if(wattage > 1000 && wattage < 1000000) {
            return (wattage / 1000) + " KW";
        }
        else if(wattage > 1000000) {
            return (wattage / 1000000) + " MW";
        }
        return "Bruh";
    }
    public void setWattage(int newWattage) {
        wattage = newWattage;
    }
}
