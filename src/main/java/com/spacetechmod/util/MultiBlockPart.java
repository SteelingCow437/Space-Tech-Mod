package com.spacetechmod.util;

import net.minecraft.world.level.block.Block;

public class MultiBlockPart {

    public Block block;
    public int relativeX;
    public int relativeY;
    public int relativeZ;

    //new system
    private Block nB;
    private int[] rX;
    private int[] rY;
    private int[] rZ;

    public MultiBlockPart(Block b, int x, int y, int z) {
        block = b;
        relativeX = x;
        relativeY = y;
        relativeZ = z;
    }
    //Literally just a block and coordinates relative to the core block

    public MultiBlockPart(Block b, int[] x, int[] y, int[] z) {
        nB = b;
        rX = x;
        rY = y;
        rZ = z;
    }

    /*so, here's how this works:

    Each block will have an array of ints attached to it, reducing
    the number of multiblockparts I need to add to a structure, hopefully
    lessening the lag. Combine this with an optimized way for checking what
    direction the structure is facing, and you have yourself an optimized
    multiblock!

    The nth entry on each int[] will be the coords for one blockpos.
    nX[1], nY[1], nZ[1] will be the same as new BlockPos(nX[1], nY[1], nZ[1])

     */

    public Block getBlock() {
        return nB;
    }
    public int[] getRX() {
        return rX;
    }
    public int[] getRY() {
        return rY;
    }
    public int[] getRZ() {
        return rZ;
    }

}
