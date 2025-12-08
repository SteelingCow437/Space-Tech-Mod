package com.spacetechmod.util;

import net.minecraft.world.level.block.Block;

public class MultiBlockPart {

    private Block block;
    private int relativeX;
    private int relativeY;
    private int relativeZ;

    public MultiBlockPart(Block b, int x, int y, int z) {
        block = b;
        relativeX = x;
        relativeY = y;
        relativeZ = z;
    }
    //Literally just a block and coordinates relative to the core block
    
    public Block getBlock() {
        return block;
    }
    public int getRX() {
        return relativeX;
    }
    public int getRY() {
        return relativeY;
    }
    public int getRZ() {
        return relativeZ;
    }
    
}
