package com.net.spacetechmod.block.entity.sculk;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SculkTrapBlockEntity extends BlockEntity {
    public SculkTrapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SCULK_TRAP.get(), pos, state);
    }

    private final double x = this.getBlockPos().getX();
    private final double y = this.getBlockPos().getY();
    private final double z = this.getBlockPos().getZ();
    private static int count = 0;
    public static void tick(Level level, BlockPos pos, BlockState state, SculkTrapBlockEntity entity) {
        Player player = level.getNearestPlayer(TargetingConditions.forNonCombat(), entity.x, entity.y, entity.z);
        if(player != null && player.getServer() != null && player.distanceToSqr(entity.x, entity.y, entity.z) <= 250 && count >= 400) {
            ServerLevel serverLevel = player.getServer().getLevel(player.level().dimension());
            player.teleportTo(entity.x, entity.y + 1, entity.z);
            count = 0;
            SpawnUtil.trySpawnMob(EntityType.WARDEN, MobSpawnType.TRIGGERED, serverLevel, pos, 20, 8, 8, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
            level.playSound(player, player.getOnPos(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.HOSTILE, 2.0f, 2.0f);
        }
        else if(count < 400) {
            count++;
        }
    }
}