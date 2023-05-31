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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class WireBlock extends Block {
    public WireBlock(Properties properties) {
        super(properties);
    }
    public int voltage = 0;
    public int amperage = 0;
    public int wattage = 0;
    public int machineDesiredAmperage = 0;
    private int voltageAddedByDynamo = 0;
    private int amperageAddedByDynamo = 0;


    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);
        Block block = level.getBlockState(neighbor).getBlock();
        BlockEntity entity = level.getBlockEntity(neighbor);
        if(block instanceof WireBlock) {
            this.amperage = ((WireBlock) block).amperage;
            this.voltage = ((WireBlock) block).voltage;
            this.machineDesiredAmperage = ((WireBlock) block).machineDesiredAmperage;
        }
        else if(block == Blocks.AIR) {
            this.voltage -= voltageAddedByDynamo;
            this.amperage -= amperageAddedByDynamo;
        }
        else if(entity instanceof DynamoBlockEntity) {
            this.amperage += ((DynamoBlockEntity) entity).outputAmperage;
            this.voltage += ((DynamoBlockEntity) entity).outputVoltage;
            voltageAddedByDynamo = ((DynamoBlockEntity) entity).outputVoltage;
            amperageAddedByDynamo = ((DynamoBlockEntity) entity).outputAmperage;
            this.wattage = this.voltage * this.amperage;
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            if(player.isHolding(Items.STICK)) {
                player.sendSystemMessage(Component.literal("Voltage: " + voltage + " " + "Amperage: " + amperage + " " + "Total wattage: " + wattage));
            }
            else if(player.getMainHandItem().getItem() == ModItems.LIGHTNING_STAFF.get()) {
                ((LightningStaffItem) player.getMainHandItem().getItem()).charge = 0;
                setVoltage(120);
                setAmperage(10 + amperage);
            }
        }
        return InteractionResult.SUCCESS;
    }
    public void setVoltage(int newVoltage) {voltage = newVoltage;}
    public void setAmperage(int newAmperage) {amperage = newAmperage;}
}
