package com.spacetechmod.util;

import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.item.ModArmorMaterials;
import com.spacetechmod.item.ModItems;
import com.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Arrays;

public class ModLists {

    /*A class that contains the lists of things that I use to improve the performance of things
    that benefit from using switches, such as barrels and the hammer. These lists didn't
    want to play nice in any other class, so here they are. Consider this to be the mod's "API"
    I guess. I don't feel like learning how to make an API/implement a proper Neoforged
    one, so here this is. */

    /* Fluids gone until further notice!
    //Fluids
    public static final ArrayList<Item> BUCKET_LIST = new ArrayList<Item>();

    public static final ArrayList<Item> BOTTLE_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.LAVA_BOTTLE.get()));

    public static final ArrayList<Fluid> FLUIDS_INDEX = new ArrayList<Fluid>(
            Arrays.asList(Fluids.WATER, Fluids.FLOWING_WATER, Fluids.LAVA, Fluids.FLOWING_LAVA,
                    ModFluids.HONEY.get()));
     */

    //Machines / Recipes
   /* public static final ArrayList<BlockEntityType> MACHINE_INDEX = new ArrayList<BlockEntityType>
            (Arrays.asList(ModBlockEntities.FORGING_TABLE.get(), ModBlockEntities.AIR_MACHINE.get(), ModBlockEntities.UN_ALLOY_MACHINE.get())); */

    public static final ArrayList<Item> HAMMER_INGREDIENT_LIST = new ArrayList<Item>(
            Arrays.asList(Items.IRON_INGOT, Items.COAL, Items.CHARCOAL, Items.COPPER_INGOT, ModItems.TIN_INGOT.get(),
                    ModItems.TITANIUM_INGOT.get()));

    public static final ArrayList<Item> FORGING_TABLE_INGREDIENT_LIST = new ArrayList<Item>(
            Arrays.asList(Items.IRON_INGOT, Items.COPPER_INGOT, ModItems.TITANIUM_INGOT.get(), ModItems.STEEL_INGOT.get(),
                    ModItems.BRONZE_INGOT.get(), ModItems.COPPER_REDSTIDE_INGOT.get(), ModItems.TITAN_STEEL_INGOT.get()));

    public static final ArrayList<Item> FORGING_TABLE_STAMP_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.DEBUG_STICK.get(), ModItems.PLATE_STAMP.get(), ModItems.WIRE_STAMP.get()));

    //Armor
    public static final ArrayList<Holder<ArmorMaterial>> ARMOR_MATERIAL_INDEX = new ArrayList<Holder<ArmorMaterial>>(
            Arrays.asList(ModArmorMaterials.TITANIUM, ModArmorMaterials.COPPER, ModArmorMaterials.TURTLE,
                    ModArmorMaterials.SPACESUIT));

    //Space stuff
    public static final ArrayList<ResourceKey<Level>> NO_BREATHING_LIST = new ArrayList<ResourceKey<Level>>(
            Arrays.asList(ModDimensions.MOON)
    );

    public static final ArrayList<ResourceKey<Level>> PLANET_LIST = new ArrayList<ResourceKey<Level>>(
            Arrays.asList(Level.OVERWORLD, ModDimensions.MOON)
    );

    public static final ArrayList<AttributeModifier> GRAVITY_CONSTANTS = new ArrayList<AttributeModifier>(
            Arrays.asList(ModAttributeModifiers.MOON_GRAVITY)
    );

    public static final ArrayList<Block> ORBITAL_CORES = new ArrayList<Block>(
            Arrays.asList(ModBlocks.ORBITAL_TNT_CORE.get(), ModBlocks.ORBITAL_FLAME_CORE.get())
    );
}
