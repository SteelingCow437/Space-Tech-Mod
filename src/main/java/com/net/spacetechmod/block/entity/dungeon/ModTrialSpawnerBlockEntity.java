package com.net.spacetechmod.block.entity.dungeon;

import com.net.spacetechmod.block.custom.dungeon.ModTrialSpawnerBlock;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.item.custom.space.dungeon.VaultKeyItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ModTrialSpawnerBlockEntity extends BlockEntity {

    public ModTrialSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MOD_TRIAL_SPAWNER.get(), pos, state);
    }

    private static int tick = 0;
    private int seconds = 0;
    private static final int maxCooldown = 12000;
    private int cooldown = 0;
    private boolean isActive = false;
    private int spawnCount = 0;
    private int clickCount = 0;
    private LivingEntity enemy;
    private int enemyIndex;
    private boolean isBoss = false;
    Random random = new Random();

    //Enemies
    private Zombie zombie = new Zombie(EntityType.ZOMBIE, level);
    private Husk husk = new Husk(EntityType.HUSK, level);
    private Skeleton skeleton = new Skeleton(EntityType.SKELETON,level);
    private Stray stray = new Stray(EntityType.STRAY,level);
    private Bogged bogged = new Bogged(EntityType.BOGGED,level);
    private Vindicator vindicator = new Vindicator(EntityType.VINDICATOR, level);
    private Evoker evoker = new Evoker(EntityType.EVOKER,level);
    private Spider spider = new Spider(EntityType.SPIDER,level);
    private Slime slime = new Slime(EntityType.SLIME,level);
    private MagmaCube magmaCube = new MagmaCube(EntityType.MAGMA_CUBE, level);
    private WitherSkeleton witherSkeleton = new WitherSkeleton(EntityType.WITHER_SKELETON, level);

    public void setBoss() {
        isBoss = !isBoss;
        Block block = level.getBlockState(worldPosition).getBlock();
        if(block instanceof ModTrialSpawnerBlock) {
            ((ModTrialSpawnerBlock) block).setState(worldPosition, this.getBlockState(), level, isBoss);
        }
    }

    public void tryStartBattle() {
        if(cooldown == 0) {
            startBattle();
        }
        else {
            level.playSound(null, worldPosition, SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 2.0f, 2.0f);
        }
    }

    private Item getKey() {
        if(isBoss) {
            return new VaultKeyItem(true);
        }
        else {
            return new VaultKeyItem(false);
        }
    }

    private void startBattle() {
        isActive = true;
    }
    private void endBattle(Level level, BlockPos pos) {
        isActive = false;
        cooldown = maxCooldown;
        ItemEntity entity = new ItemEntity(level, pos.getX(),pos.getY() + 1 , pos.getZ(), new ItemStack(getKey(), 1));
        level.addFreshEntity(entity);
        level.playSound(null, pos, SoundEvents.TRIAL_SPAWNER_EJECT_ITEM, SoundSource.BLOCKS,2.0f,2.0f);
        spawnCount = 0;
        enemy.removeAllEffects();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ModTrialSpawnerBlockEntity entity) {
        if(entity.isActive) {
            if(entity.isBoss) {
                entity.enemy.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 2));
                entity.enemy.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 2));
            }
            if(entity.spawnCount < 30 && tick >= 20) {
                entity.enemy.setPos(entity.getNewEnemyPos());
                level.addFreshEntity(entity.enemy);
                ++entity.spawnCount;
            }
        }
        if(tick < 20) {
            ++tick;
        }
        if(entity.cooldown > 0) {
            --entity.cooldown;
        }
        else {
            tick = 0;
            if(entity.isActive) {
                ++entity.seconds;
                if(entity.seconds > 31) {
                    entity.seconds = 0;
                    entity.endBattle(level, pos);
                }
            }
        }
    }

    private Vec3 getNewEnemyPos() {
        int x = random.nextInt(-4, 4);
        int z = random.nextInt(-4, 4);
        return new Vec3(worldPosition.getX() + x, worldPosition.getY(), worldPosition.getZ() + z);
    }

    public void setMob() {
        ++clickCount;
        if(clickCount <= 10) {
            switch(clickCount) {
                case 0 -> {
                    enemy = zombie;
                    enemyIndex = 0;
                }
                case 1 -> {
                    enemy = husk;
                    enemyIndex = 1;
                }
                case 2 -> {
                    enemy = skeleton;
                    enemyIndex = 2;
                }
                case 3 -> {
                    enemy = stray;
                    enemyIndex = 3;
                }
                case 4 -> {
                    enemy = bogged;
                    enemyIndex = 4;
                }
                case 5 -> {
                    enemy = vindicator;
                    enemyIndex = 5;
                }
                case 6 -> {
                    enemy = evoker;
                    enemyIndex = 6;
                }
                case 7 -> {
                    enemy = spider;
                    enemyIndex = 7;
                }
                case 8 -> {
                    enemy = slime;
                    enemyIndex = 8;
                }
                case 9 -> {
                    enemy = magmaCube;
                    enemyIndex = 9;
                }
                case 10 -> {
                    enemy = witherSkeleton;
                    enemyIndex = 10;
                }
            }
        }
        else {
            clickCount = 0;
        }
        level.playSound(null, worldPosition, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundSource.BLOCKS, 2.0f, 2.0f);
    }

    private LivingEntity getEnemy(int index) {
        LivingEntity toReturn = null;
        switch(index) {
            case 0 -> toReturn = zombie;
            case 1 -> toReturn = husk;
            case 2 -> toReturn = skeleton;
            case 3 -> toReturn = stray;
            case 4 -> toReturn = bogged;
            case 5 -> toReturn = vindicator;
            case 6 -> toReturn = evoker;
            case 7 -> toReturn = spider;
            case 8 -> toReturn = slime;
            case 9 -> toReturn = magmaCube;
            case 10 -> toReturn = witherSkeleton;
        }
        return toReturn;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        nbt.putInt("seconds", seconds);
        nbt.putInt("cooldown", cooldown);
        nbt.putBoolean("active", isActive);
        nbt.putInt("spawnCount", spawnCount);
        nbt.putInt("clickCount", clickCount);
        nbt.putInt("enemyIndex", enemyIndex);
        nbt.putBoolean("isBoss", isBoss);
        super.saveAdditional(nbt, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        seconds = nbt.getInt("seconds");
        cooldown = nbt.getInt("cooldown");
        isActive = nbt.getBoolean("active");
        spawnCount = nbt.getInt("spawnCount");
        clickCount = nbt.getInt("clickCount");
        enemyIndex = nbt.getInt("enemyIndex");
        enemy = getEnemy(nbt.getInt("enemyIndex"));
        isBoss = nbt.getBoolean("isBoss");
        super.loadAdditional(nbt, provider);
    }
}