package com.net.spacetechmod.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class SoulDartEntity extends AbstractArrow {
    public SoulDartEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public static class SoulDartItem extends ArrowItem {
        public SoulDartItem(Properties properties) {
            super(properties);
        }
        @Override
        public AbstractArrow createArrow(Level level, ItemStack arrowStack, LivingEntity shooter) {
            return new SoulDartEntity(ModEntities.SOUL_DART.get(), level);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult ray) {
        Player player = level.getNearestPlayer(TargetingConditions.forCombat(), this.getX(), this.getY(), this.getZ());
        this.setBaseDamage(5.0);
        assert player != null;
        player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
        super.onHitEntity(ray);

    }

    @Override
    protected void onHitBlock(BlockHitResult ray) {
        super.onHitBlock(ray);
    }

    @Override
    protected ItemStack getPickupItem() {
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1f, Explosion.BlockInteraction.NONE);
        return null;
    }

    @Override
    protected void tickDespawn() {
        if(this.inGroundTime >= 1200) {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4f, Explosion.BlockInteraction.NONE);
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
