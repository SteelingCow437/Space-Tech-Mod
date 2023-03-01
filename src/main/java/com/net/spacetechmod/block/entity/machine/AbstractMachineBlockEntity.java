package com.net.spacetechmod.block.entity.machine;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractMachineBlockEntity extends BlockEntity {
    public int networkId;
    public int maxPower;
    public int powerStored;
    Random random = new Random();
    public AbstractMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    ArrayList<String> machineList = new ArrayList<String>();

    public ArrayList<String> getMachineList() {
        return machineList;
    }

    public void setMachineList(ArrayList<String> machineList) {
        this.machineList = machineList;
        machineList.add("stirling_engine");
    }

    public void setId() {
        networkId = random.nextInt(0, 1023);
    }

    public int getId() {
        return networkId;
    }

}
