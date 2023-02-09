package com.net.spacetechmod.effect;

import com.net.spacetechmod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CannedBreadEffect extends MobEffect {
    public CannedBreadEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        ItemStack stack = ModItems.TIN_CAN.get().getDefaultInstance();
        if (!pLivingEntity.level.isClientSide()) {
            if(pLivingEntity instanceof Player) {
                pLivingEntity.playSound(SoundEvents.DONKEY_AMBIENT);
                ((Player) pLivingEntity).addItem(stack);
            }
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
