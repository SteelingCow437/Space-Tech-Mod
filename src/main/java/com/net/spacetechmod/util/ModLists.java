package com.net.spacetechmod.util;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.fluid.ModFluids;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Arrays;

public class ModLists {

    //A class that contains the lists of things that I use to improve the performance of things
    //that benefit from using switches, such as barrels and the hammer. These lists didn't
    //want to play nice in any other class, so here they are. Consider this to be the backbone of
    //the mod's "API" I guess. I don't feel like learning how to make an API/implement a proper Forge
    //one, so here this is.

    public static final ArrayList<Item> BUCKET_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.OIL_BUCKET.get()));

    public static final ArrayList<Item> BOTTLE_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.OIL_BOTTLE.get(), ModItems.LAVA_BOTTLE.get()));

    public static final ArrayList<FlowingFluid> FLUIDS_INDEX = new ArrayList<FlowingFluid>(
            Arrays.asList(ModFluids.CRUDE_OIL.get(), Fluids.WATER, Fluids.FLOWING_WATER, Fluids.LAVA, Fluids.FLOWING_LAVA,
                    ModFluids.HONEY.get()));

    public static final ArrayList<BlockEntityType> MACHINE_INDEX = new ArrayList<BlockEntityType>
            (Arrays.asList(ModBlockEntities.DYNAMO.get()));

    public static final ArrayList<Item> HAMMER_INGREDIENT_LIST = new ArrayList<Item>(
            Arrays.asList(Items.IRON_INGOT, Items.COAL, Items.CHARCOAL, Items.COPPER_INGOT, ModItems.TIN_INGOT.get()));

    public static final ArrayList<Item> SCULK_ALTAR_INGREDIENT_LIST = new ArrayList<Item>
            (Arrays.asList(ModItems.TITANIUM_INGOT.get(), Items.GLASS_BOTTLE, Items.AMETHYST_SHARD, Items.BOOK));

    public static final ArrayList<MobEffect> SCULK_HEART_EFFECT_LIST = new ArrayList<MobEffect>(
            Arrays.asList(MobEffects.DARKNESS, MobEffects.CONFUSION, MobEffects.DIG_SLOWDOWN, MobEffects.MOVEMENT_SLOWDOWN,
                    MobEffects.POISON, MobEffects.HUNGER, MobEffects.WEAKNESS, MobEffects.WITHER));

    public static final ArrayList<MobEffect> CALIBRATED_SCULK_HEART_EFFECT_LIST = new ArrayList<MobEffect>(
            Arrays.asList(MobEffects.REGENERATION, MobEffects.FIRE_RESISTANCE, MobEffects.DAMAGE_BOOST, MobEffects.DAMAGE_RESISTANCE,
                    MobEffects.ABSORPTION, MobEffects.CONDUIT_POWER, MobEffects.NIGHT_VISION, MobEffects.LUCK));
}
