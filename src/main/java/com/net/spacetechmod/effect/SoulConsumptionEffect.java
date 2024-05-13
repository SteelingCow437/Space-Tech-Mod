package com.net.spacetechmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SoulConsumptionEffect extends MobEffect {
    public SoulConsumptionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if(!livingEntity.level().isClientSide() && livingEntity instanceof Player) {
            if(((Player) livingEntity).experienceLevel <= 0) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 0));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
            }
            else {
                ((Player) livingEntity).giveExperiencePoints(-1);
            }
        }
        if(!(livingEntity instanceof Player)) {
            livingEntity.kill();
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }
}
