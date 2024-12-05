package com.spacetechmod.util;

import net.minecraft.world.level.block.Block;

public class MultiBlockPart {

    public Block block;
    public int relativeX;
    public int relativeY;
    public int relativeZ;

    public MultiBlockPart(Block b, int x, int y, int z) {
        block = b;
        relativeX = x;
        relativeY = y;
        relativeZ = z;
    }
    //Literally just a block and coordinates relative to the core block
}
