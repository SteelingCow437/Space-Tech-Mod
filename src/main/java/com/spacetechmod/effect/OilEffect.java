package com.spacetechmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class OilEffect extends MobEffect {
    public OilEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide()) {
            double x = livingEntity.getX();
            double y = livingEntity.getY();
            double z = livingEntity.getZ();
            if(livingEntity.isInWaterOrRain()) {
                livingEntity.moveTo(x, y + 0.15, z);
            }
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }
}


