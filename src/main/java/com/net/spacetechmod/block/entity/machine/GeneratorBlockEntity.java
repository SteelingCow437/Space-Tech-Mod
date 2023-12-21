package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneratorBlockEntity extends BlockEntity {
    public GeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GENERATOR.get(), pos, state);
    }

    private ArrayList<Item> itemList = new ArrayList<Item>(Arrays.asList());
    private ArrayList<BlockPos> ADJACENTS = new ArrayList<BlockPos>(Arrays.asList(
            worldPosition.above(),
            worldPosition.below(),
            worldPosition.north(),
            worldPosition.south(),
            worldPosition.east(),
            worldPosition.west()
    ));

    private ArrayList<BlockEntity> ADJACENT_WIRES = new ArrayList<BlockEntity>(Arrays.asList());
    int itemCount = 0;
    public int burnTime = 0;
    public static final int burnEnergy = 250; //energy per tick
    public boolean active = false;

    public void addItem(ItemStack stack) {
        ItemStack checkStack = new ItemStack(stack.getItem(), 1);
        if(CommonHooks.getBurnTime(checkStack, RecipeType.SMELTING) > 0) {
            if(itemList.isEmpty() || itemList.size() < 5 || itemCount < 5) {
                itemList.add(stack.getItem());
                stack.shrink(1);
                ++itemCount;
                burnTime += CommonHooks.getBurnTime(checkStack, RecipeType.SMELTING);
            }
            else {
                level.playSound(null, getBlockPos(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 2.0f, 2.0f);
            }
        }
    }

    public void getAdjacentWires(Level level) {
        for(BlockPos pos : ADJACENTS) {
            if(level.getBlockEntity(pos) instanceof WireBlockEntity) {
                ADJACENT_WIRES.add(level.getBlockEntity(pos));
            }
        }
    }

    public void updateNeighbors(Level level) {
        if(ADJACENT_WIRES.isEmpty()) {
            getAdjacentWires(level);
        }
        else if(!active) {
            for(BlockEntity entity : ADJACENT_WIRES) {
                ((WireBlockEntity) entity).addEnergyPerTick(burnEnergy);
                active = true;
            }
        }
        else {
            for(BlockEntity entity : ADJACENT_WIRES) {
                ((WireBlockEntity) entity).addEnergyPerTick(-burnEnergy);
                active = false;
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GeneratorBlockEntity entity) {
        if(entity.burnTime > 0) {
            --entity.burnTime;
        }
        if(entity.burnTime > 0 && !entity.active) {
            entity.updateNeighbors(level);
        }
        else if(entity.burnTime <= 0 && entity.active) {
            entity.updateNeighbors(level);
            entity.itemList.clear();
            entity.itemCount = 0;
        }
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.putInt("burntime", burnTime);
        nbt.putBoolean("active", active);
        nbt.putInt("itemcount", itemCount);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        burnTime = nbt.getInt("burntime");
        active = nbt.getBoolean("active");
        itemCount = nbt.getInt("itemcount");
        super.load(nbt);
    }

    public void debug(Player player) {
        player.sendSystemMessage(Component.literal(
                "Is active: " + active + "\n"
                        + "Burn time remaining: " + burnTime + "\n"
                        + "Amount of items in generator: " + itemCount));
    }
}