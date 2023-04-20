package com.net.spacetechmod.block.entity.sculk;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
    public static void tick(Level level, BlockPos pos, BlockState state, SculkTrapBlockEntity pEntity) {
        Player player = level.getNearestPlayer(TargetingConditions.forNonCombat(), pEntity.x, pEntity.y, pEntity.z);
        if(player != null && player.distanceToSqr(pEntity.x, pEntity.y, pEntity.z) < 250 && count >= 200) {
            player.teleportTo(pEntity.getBlockPos().getX(), pEntity.getBlockPos().getY() + 1, pEntity.getBlockPos().getZ());
            count++;
        }
        if(count == 200 && !level.isClientSide()) {
            SpawnUtil.trySpawnMob(EntityType.WARDEN, MobSpawnType.TRIGGERED, ((ServerLevel) level), pos, 20, 5, 6, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER).isPresent();
            count = 0;
        }
        if(player == null || player.distanceToSqr(pEntity.x, pEntity.y, pEntity.z) > 250 && count >= 200) {
            count = 0;
        }
    }
}