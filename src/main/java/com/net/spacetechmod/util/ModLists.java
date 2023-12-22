package com.net.spacetechmod.util;

import com.net.spacetechmod.fluid.ModFluids;
import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Arrays;

public class ModLists {

    //A class that contains the lists of things that I use to improve the performance of things
    //that benefit from using switches, such as barrels and the hammer. These lists didn't
    //want to play nice in any other class, so here they are. Consider this to be the mod's "API"
    //I guess. I don't feel like learning how to make an API/implement a proper Neoforged
    //one, so here this is.

    //general lists
    public static final ArrayList<Item> BUCKET_LIST = new ArrayList<Item>();

    public static final ArrayList<Item> BOTTLE_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.LAVA_BOTTLE.get()));

    public static final ArrayList<Fluid> FLUIDS_INDEX = new ArrayList<Fluid>(
            Arrays.asList(Fluids.WATER, Fluids.FLOWING_WATER, Fluids.LAVA, Fluids.FLOWING_LAVA,
                    ModFluids.HONEY.get()));

    public static final ArrayList<BlockEntityType> MACHINE_INDEX = new ArrayList<BlockEntityType>
            (Arrays.asList());

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

    public static final ArrayList<Item> FORGING_TABLE_INGREDIENT_LIST = new ArrayList<Item>(
            Arrays.asList(Items.IRON_INGOT, Items.COPPER_INGOT, ModItems.TITANIUM_INGOT.get(), ModItems.STEEL_INGOT.get(),
                    ModItems.BRONZE_INGOT.get(), ModItems.COPPER_REDSTIDE_INGOT.get()));

    public static final ArrayList<Item> FORGING_TABLE_STAMP_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.DEBUG_STICK.get(), ModItems.PLATE_STAMP.get(), ModItems.WIRE_STAMP.get()));

    public static final ArrayList<ModArmorMaterials> ARMOR_MATERIAL_INDEX = new ArrayList<ModArmorMaterials>(
            Arrays.asList(ModArmorMaterials.TITANIUM, ModArmorMaterials.COPPER, ModArmorMaterials.TURTLE,
                    ModArmorMaterials.SCULK, ModArmorMaterials.SPACESUIT));

    public static final ArrayList<ResourceKey<Level>> SAFE_BREATHING_LIST = new ArrayList<>(
            Arrays.asList(Level.OVERWORLD, Level.NETHER, Level.END, ModDimensions.SCULKDIM)
    );
}
