package com.net.spacetechmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class AntidoteEffect extends MobEffect {

    public AntidoteEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide()) {
            livingEntity.removeAllEffects();
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }
}
