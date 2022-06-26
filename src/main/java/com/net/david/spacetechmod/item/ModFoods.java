package com.net.david.spacetechmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    //Declare foodstuffs below this line!
    public static final FoodProperties LEAN = (new FoodProperties.Builder()).nutrition(8).saturationMod(2f).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1), 1F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 1), 1F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 1), 1F).build();


    public static final FoodProperties BAGUETTE = (new FoodProperties.Builder()).nutrition(15).saturation(10f).build();

}
