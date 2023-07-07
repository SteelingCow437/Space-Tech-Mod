package com.net.spacetechmod.block.entity.sculk;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class CalibratedSculkTrapBlockEntity extends BlockEntity {
    public CalibratedSculkTrapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CALIBRATED_SCULK_TRAP.get(), pos, state);
    }

    private final AABB boundingBox = new AABB(worldPosition.getX() - 10, worldPosition.getY() - 10, worldPosition.getZ() - 10,
    worldPosition.getX() + 10, worldPosition.getY() + 10, worldPosition.getZ() + 10);
    private static int timer = 0;
    public boolean targetsPlayers = false;
    Random random = new Random();

    public void setPlayerTargeting() {
        targetsPlayers = !targetsPlayers;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CalibratedSculkTrapBlockEntity entity) {
        if(!level.isClientSide && timer >= 400) {
            List<Entity> entities = level.getEntities(null, entity.boundingBox);
            Entity targetEntity = entities.get(entity.random.nextInt(0, entities.size()));
            if(targetEntity instanceof LivingEntity) {
                if(entity.targetsPlayers) {
                    entity.springTrap(targetEntity, pos);
                }
                else {
                    if(!(targetEntity instanceof Player)) {
                        entity.springTrap(targetEntity, pos);
                    }
                    else {
                        while(targetEntity instanceof Player) {
                            targetEntity = entities.get(entity.random.nextInt(0, entities.size()));
                        }
                        entity.springTrap(targetEntity, pos);
                    }
                }
            }
        }
        else if(timer < 400) {
            ++timer;
        }
    }

    private void springTrap(Entity targetEntity, BlockPos pos) {
        targetEntity.setPos(new Vec3(pos.getX(), pos.getY() + 1, pos.getZ()));
        ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 9));
        if(targetEntity.isAlive()) {
            ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
        } else {
            ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));
        }
    }
}
