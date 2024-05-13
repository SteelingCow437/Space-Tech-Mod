package com.net.spacetechmod.block.entity.sculk;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Random;

public class CalibratedSculkTrapBlockEntity extends BlockEntity {
    public CalibratedSculkTrapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CALIBRATED_SCULK_TRAP.get(), pos, state);
    }

    private final AABB boundingBox = new AABB(getBlockPos().getX() - 10, getBlockPos().getY() - 10, getBlockPos().getZ() - 10,
    getBlockPos().getX() + 10, getBlockPos().getY() + 10, getBlockPos().getZ() + 10);
    private static int timer = 0;
    public boolean targetsPlayers = false;
    Random random = new Random();

    public void setPlayerTargeting() {
        targetsPlayers = !targetsPlayers;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CalibratedSculkTrapBlockEntity entity) {
        if(!level.isClientSide && timer >= 400) {
            List<Entity> entities = level.getEntities(null, entity.boundingBox);
            if (!entities.isEmpty()) {
                for (int i = 0; i < entities.size(); ++i) {
                    if(entities.get(i) instanceof ItemEntity) {
                        entities.remove(i);
                    }
                }
                Entity targetEntity = entities.get(entity.random.nextInt(0, entities.size()));
                if(targetEntity instanceof LivingEntity) {
                    if(entity.targetsPlayers) {
                        entity.springTrap(targetEntity, pos);
                        timer = 0;
                    } else {
                        while(targetEntity instanceof Player) {
                            entities.remove(targetEntity);
                            if(!entities.isEmpty()) {
                                targetEntity = entities.get(entity.random.nextInt(0, entities.size()));
                            }
                            else {
                                break;
                            }
                        }
                        entity.springTrap(targetEntity, pos);
                    }
                }
            }
        }
        else {
            if(timer >= 400) {
                timer = 0;
            }
            else {
                ++timer;
            }
        }
    }

    private void springTrap(Entity targetEntity, BlockPos pos) {
        if(targetEntity instanceof LivingEntity) {
            targetEntity.teleportTo(pos.getX(), pos.getY() + 1, pos.getZ());
            ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 9));
            targetEntity.hurt(magicDamage(targetEntity), 4.0f);
        }
    }

    public DamageSource magicDamage(Entity entity) {
        return entity.damageSources().magic();
    }

    //Saving & loading
    @Override
    public void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        nbt.putBoolean("targetsPlayers", targetsPlayers);
        super.saveAdditional(nbt, provider);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        targetsPlayers = nbt.getBoolean("targetsPlayers");
        super.loadAdditional(nbt, provider);
    }
}
