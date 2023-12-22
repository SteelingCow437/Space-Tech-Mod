package com.net.spacetechmod.effect;

import com.net.spacetechmod.item.ModItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class SpaceBreathingEffect extends MobEffect {
    public SpaceBreathingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.SPACESUIT_CHESTPLATE.get()) {

        }






        super.applyEffectTick(entity, amplifier);
    }
}
