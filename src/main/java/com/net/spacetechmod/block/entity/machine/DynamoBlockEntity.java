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

    public boolean isActive = true; //not used yet
    public boolean hasRefreshed = false;
    public double outputWattage = 0;
    private static int tick = 0;

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.putDouble("outputWattage", outputWattage);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        outputWattage = nbt.getDouble("outputWattage");
        super.load(nbt);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DynamoBlockEntity entity) {
        if (!entity.hasRefreshed && tick >= 40) {
            entity.hasRefreshed = true;
            setChanged(level, pos, state);
        } else if (tick < 50) {
            tick++;
        }
    }

    public String getOutput() {
        if (outputWattage < 1000) {
            return outputWattage + " W";
        }
        else if(outputWattage > 1000 && outputWattage < 1000000) {
            return (outputWattage / 1000) + " KW";
        }
        else if(outputWattage > 1000000) {
            return (outputWattage / 1000000) + " MW";
        }
        return "Bruh";
    }
}
