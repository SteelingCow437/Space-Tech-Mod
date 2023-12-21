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
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if(!pLivingEntity.level().isClientSide() && pLivingEntity instanceof Player) {
            if(((Player) pLivingEntity).experienceLevel <= 0) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 0));
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
            }
            else {
                ((Player) pLivingEntity).giveExperiencePoints(-1);
            }
        }
        if(!(pLivingEntity instanceof Player)) {
            pLivingEntity.kill();
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
