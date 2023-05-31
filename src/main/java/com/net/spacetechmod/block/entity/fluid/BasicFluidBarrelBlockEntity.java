package com.net.spacetechmod.block.entity.fluid;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.fluid.ModFluids;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public class BasicFluidBarrelBlockEntity extends BlockEntity {

    private final int capacity = 24; //in bottles. 1 bottle is 1/3 of a bucket.
    public Fluid fluidType = Fluids.EMPTY;
    private int amount = 0;
    public BasicFluidBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_BARREL.get(), pos, state);
    }

    public void onRightClick(Item item, Player player) {
        addLists();
        if(player.isShiftKeyDown()) {
            if(player.isHolding(ItemStack.EMPTY.getItem())) {
                player.sendSystemMessage(Component.literal(getFluidName() + ", " + amount + " / " + capacity + " bottles"));
            }
        }
        else if(!player.isShiftKeyDown()) {
            if(amount == 0) {
                fluidType = Fluids.EMPTY;
            }
            if(ModItems.BUCKET_LIST.contains(item) && amount <= 21) {
                fillBarrelFromBucket(player, item);
            }
            else if(ModItems.BOTTLE_LIST.contains(item) && amount <= 23) {
                fillBarrelFromBottle(player, item);
            }
            else if(item == Items.BUCKET.asItem() && amount >= 3) {
                fillBucket(player);
            }
            else if(item == Items.GLASS_BOTTLE.asItem() && amount >= 1) {
                fillBottle(player);
            }
            setChanged(level, getBlockPos(), getBlockState());
        }
    }

    private void fillBucket(Player player) {
        player.getMainHandItem().shrink(1);
        player.addItem(fluidType.getBucket().getDefaultInstance());
        amount -= 3;
        setChanged(level, getBlockPos(), getBlockState());
    }

    private void fillBottle(Player player) {
        getBottle(player);
        player.getMainHandItem().shrink(1);
        amount--;
        setChanged(level, getBlockPos(), getBlockState());
    }

    private String getFluidName() {
        String name;
        switch(ModFluids.FLUIDS_INDEX.indexOf(fluidType)) {
            default -> name = "None";
            case 0 -> name = "Crude Oil";
            case 1, 2 -> name = "Water";
            case 3, 4 -> name = "Lava";
            case 5 -> name = "Honey";
        }
        return name;
    }

    private void getBottle(Player player) {
        switch(ModFluids.FLUIDS_INDEX.indexOf(fluidType)) {
            default -> player.sendSystemMessage(Component.literal("Houston, we have a problem!"));
            case 0 -> player.addItem(ModItems.OIL_BOTTLE.get().getDefaultInstance());
            case 1, 2 -> player.addItem(Items.POTION.getDefaultInstance());
            case 3, 4 -> player.addItem(ModItems.LAVA_BOTTLE.get().getDefaultInstance());
            case 5 -> player.addItem(Items.HONEY_BOTTLE.getDefaultInstance());
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

    public void addBottles() {
        ModItems.BOTTLE_LIST.add(0, ModItems.OIL_BOTTLE.get().asItem());
        ModItems.BOTTLE_LIST.add(1, Items.POTION.asItem());
        ModItems.BOTTLE_LIST.add(3, ModItems.LAVA_BOTTLE.get().asItem());
        ModItems.BOTTLE_LIST.add(5, Items.HONEY_BOTTLE.asItem());
    }

    public void addBuckets() {
        ModItems.BUCKET_LIST.add(0, ModItems.OIL_BUCKET.get().asItem());
        ModItems.BUCKET_LIST.add(1, Items.WATER_BUCKET.asItem());
        ModItems.BUCKET_LIST.add(3, Items.LAVA_BUCKET.asItem());
    }

    public void fillBarrelFromBucket(Player player, Item item) {
        if(ModItems.BUCKET_LIST.contains(item)) {
            if(fluidType == Fluids.EMPTY) {
                fluidType = ((BucketItem) item).getFluid();
                player.getMainHandItem().shrink(1);
                player.addItem(Items.BUCKET.getDefaultInstance());
                amount += 3;
                setChanged(level, getBlockPos(), getBlockState());
            }
            else if(fluidType.isSame(((BucketItem) item).getFluid())) {
                player.getMainHandItem().shrink(1);
                player.addItem(Items.BUCKET.getDefaultInstance());
                amount += 3;
                setChanged(level, getBlockPos(), getBlockState());
            }
        }
    }

    public void fillBarrelFromBottle(Player player, Item item) {
        if(ModItems.BOTTLE_LIST.contains(item)) {
            int index = ModItems.BOTTLE_LIST.indexOf(item);
            if(fluidType == Fluids.EMPTY) {
                fluidType = ModFluids.FLUIDS_INDEX.get(index);
                player.getMainHandItem().shrink(1);
                player.addItem(Items.GLASS_BOTTLE.getDefaultInstance());
                amount++;
                setChanged(level, getBlockPos(), getBlockState());
            }
            else {
                if(fluidType.isSame(ModFluids.FLUIDS_INDEX.get(index))) {
                    amount++;
                    player.getMainHandItem().shrink(1);
                    player.addItem(Items.GLASS_BOTTLE.getDefaultInstance());
                    setChanged(level, getBlockPos(), getBlockState());
                }
            }
            setChanged(level, getBlockPos(), getBlockState());
        }
    }

    public void addLists() {
        if(!ModItems.BOTTLE_LIST.contains(ModItems.OIL_BOTTLE.get())) {
            addBottles();
        }
        if(!ModItems.BUCKET_LIST.contains(ModItems.OIL_BUCKET.get())) {
            addBuckets();
        }
        if(!ModFluids.FLUIDS_INDEX.contains(ModFluids.CRUDE_OIL.get())) {
            addFluids();
        }
    }
    //Saving & loading
    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.putInt("fluid_amount", amount);
        nbt.putInt("fluid_type", getIndex());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        amount = nbt.getInt("fluid_amount");
        fluidType = ModFluids.FLUIDS_INDEX.get(nbt.getInt("fluid_type"));
        super.load(nbt);
    }

    public int getIndex() {
        return ModFluids.FLUIDS_INDEX.indexOf(fluidType);
    }
}
