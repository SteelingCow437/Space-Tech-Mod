package com.spacetechmod.util;

import com.spacetechmod.block.ModBlocks;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.Arrays;

public class ModMultiBlockStructures {
    
    /* So here's how I'm doing this:
    A multiblock structure will check for a list of "MultiBlockParts",
    which is a block associated with an x, y, and z coordinate relative to
    that of the core block.
    Multiblocks should be symmetrical across the X and Z axes to prevent the
    need for duplicates or specific instructions on what plane to build them in!

    An example of how to use this!
    public static final ArrayList<MultiBlockPart> EXAMPLE = new ArrayList<MultiBlockPart>(
            Arrays.asList(
                    new MultiBlockPart(ModBlocks.STARGATE_FRAME.get(), 1, 0, -1),
                    new MultiBlockPart(ModBlocks.STARGATE_FRAME.get(), 1, 0, 0),
                    new MultiBlockPart(ModBlocks.STARGATE_FRAME.get(), 1, 0, 1),
                    new MultiBlockPart(ModBlocks.STARGATE_FRAME.get(), 0, 0, -1),
                    new MultiBlockPart(ModBlocks.STARGATE_FRAME.get(), 0, 0, 1),
                    new MultiBlockPart(ModBlocks.STARGATE_FRAME.get(), -1, 0, -1),
                    new MultiBlockPart(ModBlocks.STARGATE_FRAME.get(), -1, 0, -0),
                    new MultiBlockPart(ModBlocks.STARGATE_FRAME.get(), -1, 0, 1)
    ));


    Use this method to test if a structure is valid!

    public boolean isStructureValid(ArrayList<MultiBlockPart> structure, Level level, BlockPos originPos) {
        for(int i = 0; i < structure.size(); ++i) {
            if(level.getBlockState(new BlockPos(originPos.getX() + structure.get(i).relativeX, originPos.getY() + structure.get(i).relativeY, originPos.getZ() + structure.get(i).relativeZ)).getBlock() != structure.get(i).block) {
                return false;
            }
        }
        return true;
    }

   With all of that being said, declare multiblocks below this line! :)
    */

    public static final ArrayList<MultiBlockPart> ORBITAL_TNT_CANNON = new ArrayList<MultiBlockPart>(
            Arrays.asList(
                    new MultiBlockPart(Blocks.DISPENSER, 0, 2, 0),
                    new MultiBlockPart(ModBlocks.TNT_COMPRESSOR.get(), 0, 1, 0),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, -1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -1, -1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, -1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, -1, 0),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 0, -1, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, -1, 0),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, -1, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -1, 1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, -1, 1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, -2, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -2, -1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, -2, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, -2, 0),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 0, -2, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, -2, 0),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, -2, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -2, 1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, -2, 1)
    ));

    public static final ArrayList<MultiBlockPart> ORBITAL_FLAME_CANNON = new ArrayList<MultiBlockPart>(
            Arrays.asList(
                    new MultiBlockPart(Blocks.DISPENSER, 0, 2, 0),
                    new MultiBlockPart(Blocks.CRYING_OBSIDIAN, 0, 1, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -1, -1, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 0, -1, -1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 1, -1, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, -1, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 0, -1, 0),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, -1, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -1, -1, 1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 0, -1, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 1, -1, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -1, -2, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 0, -2, -1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 1, -2, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, -2, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 0, -2, 0),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, -2, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -1, -2, 1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 0, -2, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 1, -2, 1)

    ));



    
}