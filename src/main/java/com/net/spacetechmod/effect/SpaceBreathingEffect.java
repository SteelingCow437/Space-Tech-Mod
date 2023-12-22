package com.net.spacetechmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SpaceBreathingEffect extends MobEffect {
    protected SpaceBreathingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    //we don't need anything here because all the work is done in ModEvents :)
}
