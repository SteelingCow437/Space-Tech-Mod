package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneratorBlockEntity extends BlockEntity {
    public GeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GENERATOR.get(), pos, state);
    }
    public boolean active = false;

    public Item fuel;
    public static final int burnEnergy = 250;
    public int outputEnergy = 0;

    public int burnTime = 0;

    public int getFuelTimeValue(Item item) {
        if (ForgeHooks.getBurnTime(item.getDefaultInstance(), RecipeType.SMELTING) != 0) {
            return ForgeHooks.getBurnTime(item.getDefaultInstance(), RecipeType.SMELTING);
        }
        return 0;
    }

    public void addFuel(ItemStack stack) {
        Item item = stack.getItem();
        if(fuel == null) {
            fuel = stack.getItem();
            stack.shrink(1);
            burnTime = getFuelTimeValue(stack.getItem());
            outputEnergy = burnEnergy;
        }
        else if(item == fuel) {
            stack.shrink(1);
            burnTime += getFuelTimeValue(stack.getItem());
            outputEnergy = burnEnergy;
        }
        else {
            level.playSound(null, worldPosition, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 2.0f, 2.0f);
        }
    }

    public void removeFuel() {
        if(burnTime <= 0 || fuel == null) {
            fuel = null;
            outputEnergy = 0;
        }
    }

    private ArrayList<BlockPos> adjacents = new ArrayList<BlockPos>(
            Arrays.asList(worldPosition.above(), worldPosition.below(), worldPosition.north(),
                    worldPosition.south(), worldPosition.east(), worldPosition.west()));


    public void updateNeighbors(Level level) {
        BlockEntity entity;
        for(BlockPos pos : adjacents) {
            if(level.getBlockEntity(pos) != null && level.getBlockEntity(pos) instanceof WireBlockEntity) {
                entity = level.getBlockEntity(pos);
                if(active) {
                    ((WireBlockEntity) entity).addEnergyPerTick(outputEnergy);
                }
                else {
                    ((WireBlockEntity) entity).addEnergyPerTick(-outputEnergy);
                }
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GeneratorBlockEntity entity) {

    }


    

}
