package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Random;

public class StirlingEngineBlockEntity extends AbstractMachineBlockEntity {
    public static int timer = 0;
    public int networkId;
    public int maxPower = 10000;
    public int powerStored = 0;
    public int fuelTime = 0;
    public int maxFuelTime = 80000;
    public Item fuelItem;
    Random random = new Random();

    public int getId() {
        return networkId;
    }

    ArrayList<Item> energyValueList = new ArrayList<Item>();

    public void setEnergyValueList(ArrayList<Item> energyValueList) {
        this.energyValueList = energyValueList;
        energyValueList.add(20, Items.COAL);
        energyValueList.add(20, Items.CHARCOAL);
        energyValueList.add(5, Items.ACACIA_PLANKS);
        energyValueList.add(5, Items.BAMBOO_PLANKS);
        energyValueList.add(5, Items.BIRCH_PLANKS);
        energyValueList.add(5, Items.CRIMSON_PLANKS);
        energyValueList.add(5, Items.WARPED_PLANKS);
        energyValueList.add(5, Items.OAK_PLANKS);
        energyValueList.add(5, Items.SPRUCE_PLANKS);
        energyValueList.add(5, Items.JUNGLE_PLANKS);
    }

    public void setFuelItem(Player player) {
        fuelItem = player.getMainHandItem().getItem();
    }

    public ArrayList<Item> getEnergyValueList() {
        return energyValueList;
    }

    public void setId() {
        networkId = random.nextInt(0, 1023);
    }

    public StirlingEngineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STIRLING_ENGINE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, StirlingEngineBlockEntity entity) {
        if(entity.fuelTime > 0) {
            entity.fuelTime--;
            entity.powerStored += entity.getEnergyValueList().indexOf(entity.fuelItem);
        }
        if(timer >= 20) {
            level.playSound(level.getNearestPlayer(TargetingConditions.DEFAULT, pos.getX(), pos.getY(), pos.getZ()), pos, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 2.0f, 2.0f);
            timer = 0;
        }
        timer++;
    }

}
