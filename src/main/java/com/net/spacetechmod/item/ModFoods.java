package com.net.spacetechmod.item;

import com.net.spacetechmod.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    //Declare foodstuffs below this line!
    public static final FoodProperties LEAN = (new FoodProperties.Builder()).nutrition(8).saturationModifier(2f).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1), 1F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 1), 1F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 1), 1F).build();
    public static final FoodProperties BAGUETTE = (new FoodProperties.Builder()).nutrition(15).saturationModifier(10f).build();

    public static final FoodProperties ANTIDOTE = (new FoodProperties.Builder()).nutrition(0).saturationModifier(0).effect(new MobEffectInstance(ModEffects.ANTIDOTE_EFFECT.getDelegate(), 20, 1), 1f).build();

    public static final FoodProperties CANADA = (new FoodProperties.Builder()).nutrition(5).saturationModifier(6).build();

    public static final FoodProperties CANNED_BREAD = (new FoodProperties.Builder()).nutrition(10).saturationModifier(8f).effect(new MobEffectInstance(ModEffects.CANNED_BREAD_EFFECT.getDelegate(), 1, 1), 1f).build();
}