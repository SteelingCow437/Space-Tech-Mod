package com.net.spacetechmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier TITANIUM = new ForgeTier(3, 1250, 7f,
            2f, 15, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));

    public static final ForgeTier COPPER = new ForgeTier(2, 190, 5f,
            1f, 8, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(Items.COPPER_INGOT));




}
