package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.List;

public class AirMachineBlockEntity extends BlockEntity {

    public AirMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AIR_MACHINE.get(), pos, state);
    }

    private static int timer = 0;

    public int timeRemaining = 0;

    private int x = worldPosition.getX();
    private int y = worldPosition.getY();
    private int z = worldPosition.getZ();

    public void getTimeRemaining(Player player) {
        player.sendSystemMessage(Component.literal("Time remaining: " + timeRemaining / 20 + " seconds"));
    }

    public void addFuel(Player player) {
        ItemStack input = new ItemStack(player.getMainHandItem().getItem(), 1);
        timeRemaining += CommonHooks.getBurnTime(input, RecipeType.SMELTING);
        level.playSound(null, worldPosition, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 2.0f, 2.0f);
        player.getMainHandItem().shrink(1);
    }

    public List getPlayersInRange(Level level) {
        AABB bounds = new AABB(x - 25, y - 25, z - 25, x + 25, y + 25, z + 25);
        List<Player> list = level.getEntitiesOfClass(Player.class, bounds);
        return list;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AirMachineBlockEntity entity) {
        if(timer % 20 == 0 && entity.timeRemaining > 0) {
            level.playSound(null, pos, SoundEvents.BEACON_AMBIENT, SoundSource.BLOCKS, 4.0f, 4.0f);
            if(entity.timeRemaining < 1200) {
                Player player = level.getNearestPlayer(TargetingConditions.DEFAULT, pos.getX(), pos.getY(), pos.getZ());
                if(player != null) {
                    player.sendSystemMessage(Component.literal("60 seconds until the air machine runs out of fuel!"));
                }
            }
        }
        if(timer >= 100 && entity.timeRemaining > 0) {
            for(Object player : entity.getPlayersInRange(level)) {
                ((Player) player).addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT.get(), 110, 0));
            }
            timer = 0;
        }
        else if(timer < 200) {
            ++timer;
        }
        if(entity.timeRemaining > 0) {
            --entity.timeRemaining;
        }
    }


}
