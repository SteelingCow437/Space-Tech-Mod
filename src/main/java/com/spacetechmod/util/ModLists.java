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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

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
                    ModItems.BRONZE_INGOT.get(), ModItems.COPPER_REDSTIDE_INGOT.get(), ModItems.TITAN_STEEL_INGOT.get(),
                    ModItems.IRON_PLATE.get(), ModItems.COPPER_PLATE.get(), ModItems.TITANIUM_PLATE.get(), ModItems.STEEL_PLATE.get(),
                    ModItems.BRONZE_PLATE.get(), ModItems.TITAN_STEEL_PLATE.get()));

    public static final ArrayList<Item> FORGING_TABLE_STAMP_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.DEBUG_STICK.get(), ModItems.PLATE_STAMP.get(), ModItems.WIRE_STAMP.get(), ModItems.INGOT_STAMP.get()));

    //Armor
    public static final ArrayList<Holder<ArmorMaterial>> ARMOR_MATERIAL_INDEX = new ArrayList<Holder<ArmorMaterial>>(
            Arrays.asList(ModArmorMaterials.TITANIUM, ModArmorMaterials.COPPER, ModArmorMaterials.TURTLE,
                    ModArmorMaterials.SPACESUIT, ModArmorMaterials.Z7));

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

    public static final ArrayList<Block> WARP_DRIVE_EXCLUSION_LIST = new ArrayList<Block>(
            Arrays.asList(ModBlocks.WARP_DRIVE.get(), Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR, Blocks.BARRIER, Blocks.WATER, Blocks.LAVA,
                    Blocks.STRUCTURE_BLOCK, Blocks.STRUCTURE_VOID, Blocks.FLOWERING_AZALEA, Blocks.FLOWERING_AZALEA_LEAVES,
                    Blocks.POTTED_FLOWERING_AZALEA, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, Blocks.CORNFLOWER,
                    Blocks.POTTED_CORNFLOWER, Blocks.POTTED_TORCHFLOWER, Blocks.SUNFLOWER, Blocks.TORCHFLOWER,
                    Blocks.TORCHFLOWER_CROP, Blocks.ROSE_BUSH, Blocks.WITHER_ROSE, Blocks.POTTED_WITHER_ROSE,
                    Blocks.SHORT_GRASS, Blocks.TALL_GRASS, Blocks.SEAGRASS, Blocks.TALL_SEAGRASS, Blocks.DEAD_BUSH,
                    Blocks.SWEET_BERRY_BUSH, Blocks.POTTED_DEAD_BUSH) //what a mouthful lol
    );
}
