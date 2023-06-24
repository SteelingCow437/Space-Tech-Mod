package com.net.spacetechmod.effect;

import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.sound.ModSounds;
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
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        ItemStack stack = ModItems.TIN_CAN.get().getDefaultInstance();
        if (!entity.level().isClientSide()) {
            if(entity instanceof Player) {
                entity.playSound(ModSounds.CANNED_BREAD.get(), 2.0f, 2.0f);
                ((Player) entity).addItem(stack);
            }
        }
        super.applyEffectTick(entity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
