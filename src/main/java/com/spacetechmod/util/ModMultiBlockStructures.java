package com.spacetechmod.util;

import com.spacetechmod.block.ModBlocks;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.Arrays;

public class ModMultiBlockStructures {
    
    /* So here's how I'm doing this:
    A multiblock structure will check for a list of "MultiBlockParts",
    which is a block associated with an x, y, and z coordinate relative to
    the position of the core block.

    This file will be either very large or have a shitload of lines, but that's ok! Some files of this game's
    code have over 2,000 lines! This mod should be < 2MB anyway! Hopefully the multiblock checks don't eat too
    much RAM or CPU.

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


    Use these methods to test if a structure is valid!

    public boolean isStructureValid(ArrayList<MultiBlockPart> structure, ServerLevel level, BlockPos originPos) {
        boolean v0 = true;
        boolean v1 = true;
        boolean v2 = true;
        boolean v3 = true;
        if (level.getBlockState(originPos.offset(multiBlockPart.relativeX, multiBlockPart.relativeY, multiBlockPart.relativeZ)).getBlock() != multiBlockPart.block) {
                v0 = false;
            }
            if (level.getBlockState(originPos.offset(-multiBlockPart.relativeZ, multiBlockPart.relativeY, multiBlockPart.relativeX)).getBlock() != multiBlockPart.block) {
                v1 = false;
            }
            if (level.getBlockState(originPos.offset(-multiBlockPart.relativeX, multiBlockPart.relativeY, -multiBlockPart.relativeZ)).getBlock() != multiBlockPart.block) {
                v2 = false;
            }
            if (level.getBlockState(originPos.offset(multiBlockPart.relativeZ, multiBlockPart.relativeY, -multiBlockPart.relativeX)).getBlock() != multiBlockPart.block) {
                v3 = false;
            }

        }

        //For debugging purposes only!
        //level.players().getFirst().sendSystemMessage(Component.literal("Test 0 Success!"));
        //level.players().getFirst().sendSystemMessage(Component.literal("Test 1 Success!"));
        //level.players().getFirst().sendSystemMessage(Component.literal("Test 2 Success!"));
        //level.players().getFirst().sendSystemMessage(Component.literal("Test 3 Success!"));

        return v0 || v1 || v2 || v3;
}

   With all of that being said, declare multiblocks below this line! :)
    */

    //Stargate frame position constants
    public static final ArrayList<Integer> STARGATE_FRAME_POSITIONS = new ArrayList<>(
            Arrays.asList(
                    -2, 12, -1, 12, 0, 12, 1, 12, 2, 12, -4, 11, -3, 11, 3, 11,
                    4, 11, -5, 10, 5, 10, -5, 9, 5, 9, -6, 8, 6, 8, -6, 7, 6, 7,
                    -6, 6, 6, 6, -6, 5, 6, 5, -6, 4, 6, 4, -5, 3, 5, 3, -5, 2,
                    5, 2, -4, 1, 4, 1, -3, 1, 3, 1, -2, 0, 2, 0, 1, 0, -1, 0)
    ); //good Lord that was horrible to make. NO TOUCHY!

    //Radar *TEMPORARY*
    public static final ArrayList<MultiBlockPart> RESOURCE_RADAR = new ArrayList<>(
            Arrays.asList(
                    //this one is symmetrical!
                    //layer 0
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, 2, -1, -2),
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, 2, -1, 2),
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, 0, -1, 0),
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, -2, -1, -2),
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, -2, -1, 2),

                    new MultiBlockPart(Blocks.PURPUR_BLOCK, 1, -1, -1),
                    new MultiBlockPart(Blocks.PURPUR_BLOCK, 1, -1, 0),
                    new MultiBlockPart(Blocks.PURPUR_BLOCK, 1, -1, 1),
                    new MultiBlockPart(Blocks.PURPUR_BLOCK, 0, -1, -1),
                    new MultiBlockPart(Blocks.PURPUR_BLOCK, 0, -1, 1),
                    new MultiBlockPart(Blocks.PURPUR_BLOCK, -1, -1, -1),
                    new MultiBlockPart(Blocks.PURPUR_BLOCK, -1, -1, 0),
                    new MultiBlockPart(Blocks.PURPUR_BLOCK, -1, -1, 1),

                    //layer 1
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, 1, 0, 1),
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, 1, 0, -1),
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, -1, 0, 1),
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, -1, 0, -1),
                    //core is in the middle of this layer!

                    //layer 2
                    new MultiBlockPart(Blocks.END_STONE_BRICKS, 0, 1, 0),

                    //layer 3
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 2, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 2, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 2, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 2, 1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 2, -1),

                    //layer 4
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 3, -1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 3, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 3, 1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 3, -1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 3, 1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 3, -1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 3, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 3, 1),
                    new MultiBlockPart(Blocks.AMETHYST_BLOCK, 0, 3, 0),

                    //layer 5
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 4, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 4, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 4, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 4, 1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 4, -1)
    ));

    //cannons
    public static final ArrayList<MultiBlockPart> ORBITAL_TNT_CANNON = new ArrayList<MultiBlockPart>(
            Arrays.asList(
                    //layer 0
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, -1, -3),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -1, -3),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, -1, -3),
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, -1, -2),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, -1, -2),
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, -1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, -1, -1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 1, -1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, -1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, -1, 0),

                    //layer 1
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 1, 0, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 0, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, 0, -3),
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, 0, -2),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, 0, -2),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, 0, -1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, 0, -3),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, 0, -3),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, 0, -1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, 0, -1),
                    //core is in this layer!

                    //layer 2
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 1, 1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, 1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 1, 0),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, 1, -3),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, 1, -2),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, 1, -1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 0, 1, -3),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 0, 1, -1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, 1, -3),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, 1, -2),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, 1, -1),

                    //layer 3
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 1, 2, -1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, 2, -1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 2, -1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, 2, -3),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 0, 2, -3),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, 2, -3),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, 2, -2),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, 2, -2),

                    //layer 4
                    new MultiBlockPart(Blocks.IRON_BLOCK, 0, 3, -3),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, 3, -2),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, 3, -2),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 0, 3, -1)
    ));

    public static final ArrayList<MultiBlockPart> ORBITAL_FLAME_CANNON = new ArrayList<MultiBlockPart>(
            Arrays.asList(
                    //layer 0
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, -1, -3),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -1, -3),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, -1, -3),
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, -1, -2),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, -1, -2),
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, -1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, -1, -1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 1, -1, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 0, -1, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -1, -1, 0),

                    //layer 1
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 1, 0, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -1, 0, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, 0, -3),
                    new MultiBlockPart(Blocks.OBSIDIAN, 1, 0, -2),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, 0, -2),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, 0, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, 0, -3),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, 0, -3),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, 0, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, 0, -1),
                    //core is in this layer!

                    //layer 2
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 1, 1, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 0, 1, 0),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -1, 1, 0),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, 1, -3),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, 1, -2),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, 1, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 0, 1, -3),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 0, 1, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, 1, -3),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, 1, -2),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, 1, -1),

                    //layer 3
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 1, 2, -1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, 0, 2, -1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -1, 2, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, 2, -3),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 0, 2, -3),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, 2, -3),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, 2, -2),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, 2, -2),

                    //layer 4
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 0, 3, -3),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 1, 3, -2),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -1, 3, -2),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, 0, 3, -1)
    ));
}