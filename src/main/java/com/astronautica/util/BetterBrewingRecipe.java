package com.astronautica.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;

public class BetterBrewingRecipe implements IBrewingRecipe
{
    private final Potion input;
    private final Item ingredient;
    private final Potion output;

    public BetterBrewingRecipe(Potion input, Item ingredient, Potion output)
    {
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack input)
    {
        PotionContents inputContents = input.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        return inputContents.getAllEffects() == this.input.getEffects();
    }

    @Override
    public boolean isIngredient(ItemStack ingredient)
    {
        return ingredient.getItem() == this.ingredient;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient)
    {
        if(!this.isInput(input) || !this.isIngredient(ingredient))
        {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = new ItemStack(input.getItem());
      /*  itemStack.setTag(new CompoundTag());
        PotionBrewing.setPotion(itemStack, this.output);

       */
        return itemStack;
    }
}
