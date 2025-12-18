package com.astronautica.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ShipPart {

    private BlockState block;
    private BlockPos pos;
    private BlockPos oldPos;


    public ShipPart(BlockState b, BlockPos p, BlockPos oP) {
        block = b;
        pos = p;
        oldPos = oP;
    }

    public BlockState getBlock() {
        return block;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockPos getOldPos() {
        return oldPos;
    }

    /*
    Like a MultiBlockPart, but the cords of the block are absolute.
    Used by the Warp Drive.
     */

    
}
