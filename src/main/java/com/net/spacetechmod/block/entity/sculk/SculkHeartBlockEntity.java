package com.net.spacetechmod.block.entity.sculk;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SculkHeartBlockEntity extends BlockEntity {

    double x = this.getBlockPos().getX();
    double y = this.getBlockPos().getY();
    double z = this.getBlockPos().getZ();

    public SculkHeartBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SCULK_HEART.get(), pos, state);
    }
    public void summonWarden(ServerLevel level) {
        Player player = level.getNearestPlayer(TargetingConditions.forNonCombat(), x, y, z);
        assert player != null;
        player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 0));
        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
        SpawnUtil.trySpawnMob(EntityType.WARDEN, MobSpawnType.TRIGGERED, level, this.getBlockPos(), 20, 5, 6, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER).isPresent();
    }
}