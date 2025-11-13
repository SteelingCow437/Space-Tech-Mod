package com.spacetechmod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;

public class TestMultiBlockPart {

    private Block testB;
    private ArrayList<Integer> tRelativeX;
    private ArrayList<Integer> tRelativeY;
    private ArrayList<Integer> tRelativeZ;

    public TestMultiBlockPart(Block b, ArrayList<Integer> rX, ArrayList<Integer> rY, ArrayList<Integer> rZ) {
        testB = b;
        tRelativeX = rX;
        tRelativeY = rY;
        tRelativeZ = rZ;
    }

    public Block getBlock() {
        return testB;
    }
    public ArrayList<Integer> getRelativeX() {
        return tRelativeX;
    }
    public ArrayList<Integer> getRelativeY() {
        return tRelativeY;
    }
    public ArrayList<Integer> getRelativeZ() {
        return tRelativeZ;
    }
    //todo: structure validity check
}
