package com.spacetechmod.util;

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

    private boolean isStructureValid(ArrayList<TestMultiBlockPart> tStructure) {
        for(int i = 0; i < tStructure.size(); ++i) {




        }
        return false;
    }

    //TODO: This. Just save it for the next update.

}
