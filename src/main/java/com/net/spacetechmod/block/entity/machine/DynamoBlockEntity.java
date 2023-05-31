package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DynamoBlockEntity extends BlockEntity {
    public DynamoBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DYNAMO.get(), pos, state);
    }

    public int outputVoltage = 0;
    public int outputAmperage = 0;
    public int outputWattage = 0;

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.putInt("outputVoltage", outputVoltage);
        nbt.putInt("outputAmperage", outputAmperage);
        nbt.putInt("outputWattage", outputWattage);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        outputVoltage = nbt.getInt("outputVoltage");
        outputAmperage = nbt.getInt("outputAmperage");
        outputWattage = nbt.getInt("outputWattage");
        super.load(nbt);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DynamoBlockEntity entity) {

    }
}
