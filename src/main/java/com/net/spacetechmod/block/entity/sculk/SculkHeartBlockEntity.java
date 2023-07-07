package com.net.spacetechmod.block.entity.sculk;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SculkHeartBlockEntity extends BlockEntity {

    private final double x = this.getBlockPos().getX();
    private final double y = this.getBlockPos().getY();
    private final double z = this.getBlockPos().getZ();
    private static int timer = 0;

    public SculkHeartBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SCULK_HEART.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SculkHeartBlockEntity entity) {
        if(timer >= 30) {
            level.playSound(level.getNearestPlayer(TargetingConditions.DEFAULT, entity.x, entity.y, entity.z), entity.x, entity.y, entity.z, SoundEvents.WARDEN_HEARTBEAT, SoundSource.BLOCKS, 2.0f, 2.0f);
            timer = 0;
        }
        else {
            timer++;
        }
    }
    public void summonWarden(ServerLevel level) {
        Player player = level.getNearestPlayer(TargetingConditions.forNonCombat(), x, y, z);
        if(this.level != null && player != null) {
            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 1));
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 1));
            SpawnUtil.trySpawnMob(EntityType.WARDEN, MobSpawnType.TRIGGERED, level, this.getBlockPos(), 20, 5, 6, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
        }
    }
}