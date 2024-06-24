package com.net.spacetechmod.item.custom.armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnergyShieldItem extends Item {
    public EnergyShieldItem() {
        super(new Properties()
                .fireResistant()
                .stacksTo(1)
                .durability(100));
    }

    private int timer = 0;
    private boolean canExplode = false;
    private MobEffectInstance resistance = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 5, 10);

    public void damage(int incoming) {
        setDamage(this.getDefaultInstance(), incoming);
        if(this.getDamage(this.getDefaultInstance()) > 80) {
            canExplode = true;
        }
    }

    private void repair() {
        setDamage(this.getDefaultInstance(), this.getDamage(this.getDefaultInstance()) - 1);
    }

    public int checkDamage() {
        return this.getDamage(this.getDefaultInstance());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int number, boolean bool) {
        if(stack.getDamageValue() < 99 && entity instanceof Player) {
            if(timer % 5 == 0) {
                ((Player) entity).addEffect(resistance);
            }
        }
        if(stack.isDamaged() && timer >= 20) {
            repair();
            timer = 0;
        }
        if(canExplode) {
            if(entity instanceof Player) {
                ((Player) entity).addEffect(resistance);
                level.explode(entity, entity.getX(), entity.getY(), entity.getZ(), 4.0f, Level.ExplosionInteraction.MOB);
            }
            canExplode = false;
        }

        super.inventoryTick(stack, level, entity, number, bool);
    }
}
