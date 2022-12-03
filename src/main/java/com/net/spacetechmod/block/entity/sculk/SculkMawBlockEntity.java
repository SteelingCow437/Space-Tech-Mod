package com.net.spacetechmod.block.entity.sculk;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SculkMawBlockEntity extends BlockEntity {
    public SculkMawBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SCULK_MAW.get(), pos, state);
    }

    private final double x = this.getBlockPos().getX();
    private final double y = this.getBlockPos().getY();
    private final double z = this.getBlockPos().getZ();

    private static int time = 0;

    public static void tick(Level level, BlockPos pos, BlockState state, SculkMawBlockEntity pEntity) {
        Player player = level.getNearestPlayer(TargetingConditions.forNonCombat(), pEntity.x, pEntity.y, pEntity.z);
        if (player != null && player.distanceToSqr(pEntity.x, pEntity.y, pEntity.z) < 100 && time >= 20) {
            if(player.experienceLevel >= 1) {
                player.experienceLevel--;
                player.addEffect(new MobEffectInstance(ModEffects.SOUL_CONSUMPTION_EFFECT.get(), 25, 0));
                time = 0;
            }
            if(player.experienceLevel <= 0) {
                player.addEffect(new MobEffectInstance(ModEffects.SOUL_CONSUMPTION_EFFECT.get(), 25, 0));
            }
        }
        else {
            time++;
        }
    }

}
