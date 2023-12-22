package com.net.spacetechmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModTiers {
    public static final SimpleTier TITANIUM = new SimpleTier(3, 1250, 7f,
            2f, 15, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));

    public static final SimpleTier COPPER = new SimpleTier(2, 240, 5f,
            1f, 8, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(Items.COPPER_INGOT));

    public static final SimpleTier TURTLE = new SimpleTier(4, 2500, 8f,
            4f, 20, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(Items.TURTLE_HELMET));

    public static final SimpleTier SCULK = new SimpleTier(3, 500, 4f,
            4, 50, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ModItems.SCULK_INGOT.get()));

    public static final SimpleTier SPACESUIT = new SimpleTier(3, 1500, 7f,
            2f, 15, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.TITAN_STEEL_INGOT.get()));
}
