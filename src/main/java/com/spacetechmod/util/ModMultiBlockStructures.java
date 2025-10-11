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
    Multiblocks should be symmetrical across the X and Z axes to prevent the
    need for duplicates or specific instructions on what plane to build them in!

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
        for (MultiBlockPart multiBlockPart : structure) {
            if (level.getBlockState(new BlockPos(originPos.getX() + multiBlockPart.relativeX, originPos.getY() + multiBlockPart.relativeY, originPos.getZ() + multiBlockPart.relativeZ)).getBlock() != multiBlockPart.block) {
                v0 = false;
            }
            if (level.getBlockState(new BlockPos(originPos.getX() - multiBlockPart.relativeZ, originPos.getY() + multiBlockPart.relativeY, originPos.getZ() + multiBlockPart.relativeX)).getBlock() != multiBlockPart.block) {
                v1 = false;
            }
            if (level.getBlockState(new BlockPos(originPos.getX() - multiBlockPart.relativeX, originPos.getY() + multiBlockPart.relativeY, originPos.getZ() - multiBlockPart.relativeZ)).getBlock() != multiBlockPart.block) {
                v2 = false;
            }
            if (level.getBlockState(new BlockPos(originPos.getX() + multiBlockPart.relativeZ, originPos.getY() + multiBlockPart.relativeY, originPos.getZ() - multiBlockPart.relativeX)).getBlock() != multiBlockPart.block) {
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
                    //layer 0
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, -1, 1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, -1, 0),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 1, -1, -1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 0, -1, 1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, 0, -1, -1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, -1, 1),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, -1, 0),
                    new MultiBlockPart(Blocks.IRON_BLOCK, -1, -1, -1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, -1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 2, -1, 2),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(),2, -1, -2),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -2, -1, 2),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -2, -1, -2),
                    //layer 1
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 1, 0, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 1, 0, -1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 0, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 0, -1),
                    //layer 2 & 3
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, 1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, 2, 0),
                    //layer 4 & 6
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 3, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 3, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 3, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 3, 1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 3, -1),

                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 5, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 5, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 5, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 5, 1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 5, -1),
                    //layer 5
                    new MultiBlockPart(Blocks.AMETHYST_BLOCK, 0, 4, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 4, 1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 4, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 1, 4, -1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0, 4, 1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, 0,4, -1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 4, 1),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 4, 0),
                    new MultiBlockPart(Blocks.QUARTZ_BLOCK, -1, 4, -1)
    ));

    //cannons
    public static final ArrayList<MultiBlockPart> ORBITAL_TNT_CANNON = new ArrayList<MultiBlockPart>(
            Arrays.asList(
                    //layer 0
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, -1 ,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, -1 ,1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, -1 ,0),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, -1 ,-1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, -1 ,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -4, -1 ,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -4, -1 ,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -3, -1 ,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -3, -1 ,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -2, -1 ,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -2, -1 ,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, -1 ,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, -1 ,1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, -1 ,0),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, -1 ,-1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, -1 ,-2),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -4, -1, 1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -4, -1, -1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -2, -1, 1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -2, -1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -4, -1, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, -1, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, -1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -2, -1, 0),
                    new MultiBlockPart(Blocks.DISPENSER, -3, -1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, -1, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, -1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, -1, -1),
                    //layer 1
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, 0,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, 0,1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, 0,0),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, 0,-1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, 0,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -4, 0,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -4, 0,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -3, 0,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -3, 0,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -2, 0,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -2, 0,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, 0,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, 0,1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, 0,0),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, 0,-1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -1, 0,-2),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -4, 0, 1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -4, 0, -1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -2, 0, 1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -2, 0, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -4, 0, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, 0, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, 0, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -2, 0, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, 0, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, 0, -1),
                    //layer 2
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, 1,1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, 1,0),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -5, 1,-1),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -4, 1,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -4, 1,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -3, 1,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -3, 1,-2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -2, 1,2),
                    new MultiBlockPart(Blocks.STONE_BRICKS, -2, 1,2),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -4, 1, 1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -4, 1, -1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -2, 1, 1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -2, 1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -4, 1, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, 1, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, 1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -2, 1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 1, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 1, -1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, 1, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, 1, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), 0, 1, -1),
                    //layer 3
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -4, 2, 1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -4, 2, -1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -2, 2, 1),
                    new MultiBlockPart(ModBlocks.TITANIUM_BLOCK.get(), -2, 2, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -4, 2, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, 2, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, 2, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -2, 2, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 2, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 2, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -1, 2, -1),
                    //layer 4
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -4, 3, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -4, 3, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -4, 3, -1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -3, 3, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -3, 3, -1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -2, 3, 1),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -2, 3, 0),
                    new MultiBlockPart(ModBlocks.STEEL_DECO_BLOCK.get(), -2, 3, -1)
    ));

    public static final ArrayList<MultiBlockPart> ORBITAL_FLAME_CANNON = new ArrayList<MultiBlockPart>(
            Arrays.asList(
                    //layer 0
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, -1 ,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, -1 ,1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, -1 ,0),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, -1 ,-1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, -1 ,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -4, -1 ,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -4, -1 ,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -3, -1 ,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -3, -1 ,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -2, -1 ,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -2, -1 ,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, -1 ,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, -1 ,1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, -1 ,0),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, -1 ,-1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, -1 ,-2),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -4, -1, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -4, -1, -1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -2, -1, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -2, -1, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -4, -1, 0),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -3, -1, 1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -3, -1, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -2, -1, 0),
                    new MultiBlockPart(Blocks.DISPENSER, -3, -1, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -1, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -1, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, -1, -1),
                    //layer 1
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, 0,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, 0,1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, 0,0),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, 0,-1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, 0,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -4, 0,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -4, 0,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -3, 0,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -3, 0,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -2, 0,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -2, 0,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, 0,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, 0,1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, 0,0),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, 0,-1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -1, 0,-2),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -4, 0, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -4, 0, -1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -2, 0, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -2, 0, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -4, 0, 0),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -3, 0, 1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -3, 0, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -2, 0, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, 0, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, 0, -1),
                    //layer 2
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, 1,1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, 1,0),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -5, 1,-1),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -4, 1,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -4, 1,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -3, 1,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -3, 1,-2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -2, 1,2),
                    new MultiBlockPart(Blocks.DEEPSLATE_BRICKS, -2, 1,2),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -4, 1, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -4, 1, -1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -2, 1, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -2, 1, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -4, 1, 0),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -3, 1, 1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -3, 1, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -2, 1, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, 1, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, 1, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, 1, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, 1, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, 1, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, 0, 1, -1),
                    //layer 3
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -4, 2, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -4, 2, -1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -2, 2, 1),
                    new MultiBlockPart(Blocks.GOLD_BLOCK, -2, 2, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -4, 2, 0),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -3, 2, 1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -3, 2, -1),
                    new MultiBlockPart(Blocks.NETHER_BRICKS, -2, 2, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, 2, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, 2, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -1, 2, -1),
                    //layer 4
                    new MultiBlockPart(Blocks.OBSIDIAN, -4, 3, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -4, 3, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -4, 3, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, 3, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -3, 3, -1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -2, 3, 1),
                    new MultiBlockPart(Blocks.OBSIDIAN, -2, 3, 0),
                    new MultiBlockPart(Blocks.OBSIDIAN, -2, 3, -1)
    ));
}