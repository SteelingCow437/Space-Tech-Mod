package com.net.spacetechmod.block.entity.sculk;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;
import java.util.Random;

public class SculkTrapBlockEntity extends BlockEntity {
    public SculkTrapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SCULK_TRAP.get(), pos, state);
    }

    private final double x = this.getBlockPos().getX();
    private final double y = this.getBlockPos().getY();
    private final double z = this.getBlockPos().getZ();
    private static int count = 0;
    private static Random random = new Random();

    public static void tick(Level level, BlockPos pos, BlockState state, SculkTrapBlockEntity entity) {
        Player player = level.getNearestPlayer(TargetingConditions.forNonCombat(), entity.x, entity.y, entity.z);
        if(player != null && player.getServer() != null && count >= 400) {
            triggerTrap(player, entity, level);
            count = 0;
        }
        else if(count < 400) {
            count++;
        }
    }

    private static void triggerTrap(Player player, SculkTrapBlockEntity entity, Level level) {
        Warden warden = new Warden(EntityType.WARDEN, player.level());
        if(player.distanceToSqr(entity.x, entity.y, entity.z) <= 250) {
            player.setPos(new Vec3(entity.x, entity.y + 1, entity.z));
            warden.setPos(player.getX() + random.nextInt(-15, 15), player.getY(), player.getZ() + random.nextInt(-15, 15));
            level.addFreshEntity(warden);
            level.playSound(player, player.getOnPos(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.HOSTILE, 2.0f, 2.0f);
        }
    }
}