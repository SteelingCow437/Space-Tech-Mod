package com.net.spacetechmod.util;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.fluid.ModFluids;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Arrays;

public class ModLists {

    //A class with lists for fluid containing items, fluids and their indexes, and
    //machines and their indexes. These lists didn't want to play nicely with any other
    //classes, so here they are for now.
    public static ArrayList<Item> BUCKET_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.OIL_BUCKET.get()));

    public static ArrayList<Item> BOTTLE_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.OIL_BOTTLE.get(), ModItems.LAVA_BOTTLE.get()));

    public static ArrayList<FlowingFluid> FLUIDS_INDEX = new ArrayList<>(
            Arrays.asList(ModFluids.CRUDE_OIL.get(), Fluids.WATER, Fluids.FLOWING_WATER, Fluids.LAVA, Fluids.FLOWING_LAVA,
                    ModFluids.HONEY.get()));

    public static ArrayList<BlockEntityType> MACHINE_INDEX = new ArrayList<BlockEntityType>
            (Arrays.asList(ModBlockEntities.DYNAMO.get()));
}
