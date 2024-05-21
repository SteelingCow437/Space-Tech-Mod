package com.net.spacetechmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModTiers {
    public static final SimpleTier TITANIUM = new SimpleTier(BlockTags.NEEDS_DIAMOND_TOOL, 1250, 7f,
            2f, 15,
            () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));

    public static final SimpleTier COPPER = new SimpleTier(BlockTags.NEEDS_IRON_TOOL, 240, 5f,
            1f, 8,
            () -> Ingredient.of(Items.COPPER_INGOT));

    public static final SimpleTier TURTLE = new SimpleTier(BlockTags.NEEDS_DIAMOND_TOOL, 2500, 8f,
            4f, 20,
            () -> Ingredient.of(Items.TURTLE_HELMET));

    public static final SimpleTier SCULK = new SimpleTier(BlockTags.NEEDS_IRON_TOOL, 500, 4f,
            4, 50,
            () -> Ingredient.of(ModItems.SCULK_INGOT.get()));

    public static final SimpleTier SPACESUIT = new SimpleTier(BlockTags.NEEDS_DIAMOND_TOOL, 1500, 7f,
            2f, 15,
            () -> Ingredient.of(ModItems.TITAN_STEEL_INGOT.get()));
}
