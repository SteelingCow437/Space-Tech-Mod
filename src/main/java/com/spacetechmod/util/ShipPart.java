package com.spacetechmod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ShipPart {

    private BlockState block;
    private BlockPos pos;


    public ShipPart(BlockState b, BlockPos p) {
        block = b;
        pos = p;
    }

    public BlockState getBlock() {
        return block;
    }

    public BlockPos getPos() {
        return pos;
    }

    /*
    Like a MultiBlockPart, but the cords of the block are absolute.
    Used by the Warp Drive.
     */

    
}
