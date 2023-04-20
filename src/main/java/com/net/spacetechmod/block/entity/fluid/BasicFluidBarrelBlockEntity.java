package com.net.spacetechmod.block.entity.fluid;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.fluid.ModFluids;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public class BasicFluidBarrelBlockEntity extends BlockEntity {

    private final int capacity = 24; //in bottles. 1 bottle is 1/3 of a bucket.
    private Fluid fluidType = Fluids.EMPTY;
    private int amount = 0;
    public BasicFluidBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASIC_FLUID_BARREL.get(), pos, state);
        if(!ModFluids.FLUIDS_INDEX.contains(ModFluids.CRUDE_OIL)) {
            addFluids();
        }
    }

    public void onRightClick(Item item, Player player) {
        if (player.getMainHandItem() == ItemStack.EMPTY) {
            player.sendSystemMessage(Component.literal(getFluidName() + ", " + amount + " / " + capacity));
        }
        if(!player.isShiftKeyDown()) {
            if(item == Items.BUCKET && amount >= 3) {
                fillBucket(player);
            }
            else if(item == Items.GLASS_BOTTLE && amount >= 1) {
                fillBottle(player);
            }
        }
    }

    private void fillBucket(Player player) {
        if(player.getMainHandItem() == Items.BUCKET.getDefaultInstance()) {
            player.getMainHandItem().shrink(1);
            player.addItem(fluidType.getBucket().getDefaultInstance());
        }
        else if(player.getOffhandItem() == Items.BUCKET.getDefaultInstance()) {
            player.getOffhandItem().shrink(1);
            player.addItem(fluidType.getBucket().getDefaultInstance());
        }
    }

    private void fillBottle(Player player) {
        if(player.getMainHandItem() == Items.GLASS_BOTTLE.getDefaultInstance()) {
            getBottle(player);
            player.getMainHandItem().shrink(1);
        }
        else if(player.getOffhandItem() == Items.GLASS_BOTTLE.getDefaultInstance()) {
            getBottle(player);
            player.getOffhandItem().shrink(1);
        }
    }

    private String getFluidName() {
        String name;
        switch(ModFluids.FLUIDS_INDEX.indexOf(fluidType)) {
            default -> name = "None";
            case 0 -> name = "Crude Oil";
            case 1, 2 -> name = "Water";
            case 3, 4 -> name = "Lava";
        }
        return name;
    }

    private void getBottle(Player player) {
        if(ModFluids.FLUIDS_INDEX.contains(fluidType)) {
            switch(ModFluids.FLUIDS_INDEX.indexOf(fluidType)) {
                default -> throw new RuntimeException("BALLS");
                case 0 -> player.addItem(ModItems.OIL_BOTTLE.get().getDefaultInstance());
                case 1, 2 -> player.addItem(Items.POTION.getDefaultInstance());
                case 3, 4 -> player.addItem(ModItems.LAVA_BOTTLE.get().getDefaultInstance());
                case 5 -> player.addItem(Items.HONEY_BOTTLE.getDefaultInstance());
            }
        }
    }

    public void addFluids() {
        ModFluids.FLUIDS_INDEX.add(0, ModFluids.CRUDE_OIL.get());
        ModFluids.FLUIDS_INDEX.add(1, Fluids.WATER);
        ModFluids.FLUIDS_INDEX.add(2, Fluids.FLOWING_WATER);
        ModFluids.FLUIDS_INDEX.add(3, Fluids.LAVA);
        ModFluids.FLUIDS_INDEX.add(4, Fluids.FLOWING_LAVA);
        ModFluids.FLUIDS_INDEX.add(5, ModFluids.HONEY.get());
    }
}
