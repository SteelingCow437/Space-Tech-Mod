package com.net.david.spacetechmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier TITANIUM = new ForgeTier(3, 1475, 0f,
            2f, 15, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));




}
