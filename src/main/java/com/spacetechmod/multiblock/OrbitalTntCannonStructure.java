package com.spacetechmod.multiblock;

import com.spacetechmod.util.MultiBlockPart;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;

public class OrbitalTntCannonStructure {

    private ArrayList<MultiBlockPart> parts;

    public OrbitalTntCannonStructure(ArrayList<MultiBlockPart> p) {
        parts = p;
    }



    public boolean isStructureValid(Level level) {
        BlockPos checkPos;
        Block checkBlock;
        int[] x;
        int[] y;
        int[] z;
        for(MultiBlockPart p : parts) {
            checkBlock = p.getBlock();
            x = p.getRX();
            y = p.getRY();
            z = p.getRZ();
            for(int a : p.getRX()) {
                checkPos = new BlockPos(x[a], y[a], z[a]);
                if(level.getBlockState(checkPos).getBlock() != checkBlock) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }


}
