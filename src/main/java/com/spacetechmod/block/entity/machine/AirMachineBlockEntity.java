package com.spacetechmod.block.entity.machine;

import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class AirMachineBlockEntity extends BlockEntity {

    public AirMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AIR_MACHINE.get(), pos, state);
    }

    private static int timer = 0;

    private int range = 1;

    public int timeRemaining = 0;

    private int x = worldPosition.getX();
    private int y = worldPosition.getY();
    private int z = worldPosition.getZ();

    public void getTimeRemaining(Player player) {
        player.sendSystemMessage(Component.literal("Time remaining: " + timeRemaining / 20 + " seconds" +
                "\n Range: " + range + " blocks"));
    }

    public void addFuel(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide) {
            ItemStack input = player.getItemInHand(hand);
            timeRemaining += input.getBurnTime(RecipeType.SMELTING);
            level.playSound(null, worldPosition, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 2.0f, 2.0f);
            if(!input.getCraftingRemainingItem().isEmpty()) {
                player.addItem(input.getCraftingRemainingItem());
            }
            player.getItemInHand(hand).shrink(1);
            setChanged();
        }
    }

    public List getPlayersInRange(Level level) {
        AABB bounds = new AABB(x - range, y - range, z - range, x + range, y + range, z + range);
        List<Player> list = level.getEntitiesOfClass(Player.class, bounds);
        return list;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AirMachineBlockEntity entity) {
        if(entity.timeRemaining > 0) {
            if(timer % 20 == 0) {
                level.playSound(null, pos, SoundEvents.BEACON_AMBIENT, SoundSource.BLOCKS, 4.0f, 4.0f);
            }
            if(entity.timeRemaining < 1200) {
                Player player = level.getNearestPlayer(TargetingConditions.DEFAULT, pos.getX(), pos.getY(), pos.getZ());
                if(player != null) {
                    player.sendSystemMessage(Component.literal(entity.timeRemaining / 20 + " seconds until the air machine runs out of fuel!"));
                }
            }
        }
        if(entity.timeRemaining > 0) {
            --entity.timeRemaining;
        }
        if(timer >= 100) {
            if(entity.timeRemaining > 0) {
                for (Object player : entity.getPlayersInRange(level)) {
                    ((Player) player).addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT.getDelegate(), 110, 0));
                }
            }
            timer = 0;
        }
        else {
            ++timer;
        }
    }

    public void setRange() {
        if(range < 20) {
            ++range;
        }
        else {
            range = 1;
        }
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putInt("time", timeRemaining);
        tag.putInt("range", range);
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        timeRemaining = tag.getInt("time");
        range = tag.getInt("range");
        super.loadAdditional(tag, provider);
    }
}