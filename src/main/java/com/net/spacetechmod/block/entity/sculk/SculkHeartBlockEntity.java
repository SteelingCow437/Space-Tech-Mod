package com.net.spacetechmod.block.entity.sculk;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class SculkHeartBlockEntity extends BlockEntity {

    private final double x = this.getBlockPos().getX();
    private final double y = this.getBlockPos().getY();
    private final double z = this.getBlockPos().getZ();
    private static int timer = 0;

    Player targetPlayer;
    private Random random = new Random();

    public SculkHeartBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SCULK_HEART.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SculkHeartBlockEntity entity) {
        entity.targetPlayer = level.getNearestPlayer(TargetingConditions.DEFAULT, entity.x, entity.y, entity.z);
        if(timer >= 40 && entity.targetPlayer != null && entity.targetPlayer.distanceToSqr(entity.x, entity.y, entity.z) < 225) {
            level.playSound(entity.targetPlayer, entity.x, entity.y, entity.z, SoundEvents.WARDEN_HEARTBEAT, SoundSource.BLOCKS, 2.0f, 2.0f);
        }
        if(timer >= 80 && entity.targetPlayer != null && entity.targetPlayer.distanceToSqr(entity.x, entity.y, entity.z) < 225) {
            level.playSound(entity.targetPlayer, entity.x, entity.y, entity.z, SoundEvents.WARDEN_HEARTBEAT, SoundSource.BLOCKS, 2.0f, 2.0f);
            entity.targetPlayer.addEffect(new MobEffectInstance(entity.getMobEffect(), 160, 1));
            timer = 0;
        }
        else {
            ++timer;
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

    private MobEffect getMobEffect() {
        return ModLists.SCULK_HEART_EFFECT_LIST.get(random.nextInt(0, 7));
    }
}