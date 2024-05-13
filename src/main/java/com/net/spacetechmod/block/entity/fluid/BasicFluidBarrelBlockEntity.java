package com.net.spacetechmod.block.entity.fluid;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
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

    public final int capacity = 24; //in bottles. 1 bottle is 1/3 of a bucket.
    public Fluid fluidType = Fluids.EMPTY;
    public int amount = 0;
    public BasicFluidBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_BARREL.get(), pos, state);
    }

    public void onRightClick(Item item, Player player) {
        if(player.isShiftKeyDown()) {
            if(player.isHolding(ItemStack.EMPTY.getItem())) {
                player.sendSystemMessage(Component.literal(getFluidName() + ", " + amount + " / " + capacity + " bottles"));
            }
        }
        else if(!player.isShiftKeyDown()) {
            if(amount == 0) {
                fluidType = Fluids.EMPTY;
            }
            if(ModLists.BUCKET_LIST.contains(item) && amount <= 21) {
                fillBarrelFromBucket(player, item);
            }
            else if(ModLists.BOTTLE_LIST.contains(item) && amount <= 23) {
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
        switch(ModLists.FLUIDS_INDEX.indexOf(fluidType)) {
            default -> name = "None";
            case 0, 1 -> name = "Water";
            case 2, 3 -> name = "Lava";
            case 4 -> name = "Honey";
        }
        return name;
    }

    private void getBottle(Player player) {
        switch(ModLists.FLUIDS_INDEX.indexOf(fluidType)) {
            default -> player.sendSystemMessage(Component.literal("Houston, we have a problem!"));
            case 0, 1 -> player.addItem(Items.POTION.getDefaultInstance());
            case 2, 3 -> player.addItem(ModItems.LAVA_BOTTLE.get().getDefaultInstance());
            case 4 -> player.addItem(Items.HONEY_BOTTLE.getDefaultInstance());
        }
    }

    public void fillBarrelFromBucket(Player player, Item item) {
        if(ModLists.BUCKET_LIST.contains(item)) {
            if(fluidType == Fluids.EMPTY) {
                fluidType = ((BucketItem) item).content;
                player.getMainHandItem().shrink(1);
                player.addItem(Items.BUCKET.getDefaultInstance());
                amount += 3;
                setChanged(level, getBlockPos(), getBlockState());
            }
            else if(fluidType.isSame(((BucketItem) item).content)) {
                player.getMainHandItem().shrink(1);
                player.addItem(Items.BUCKET.getDefaultInstance());
                amount += 3;
                setChanged(level, getBlockPos(), getBlockState());
            }
        }
    }

    public void fillBarrelFromBottle(Player player, Item item) {
        if(ModLists.BOTTLE_LIST.contains(item)) {
            int index = ModLists.BOTTLE_LIST.indexOf(item);
            if(fluidType == Fluids.EMPTY) {
                fluidType = ModLists.FLUIDS_INDEX.get(index);
                player.getMainHandItem().shrink(1);
                player.addItem(Items.GLASS_BOTTLE.getDefaultInstance());
                amount++;
                setChanged(level, getBlockPos(), getBlockState());
            }
            else {
                if(fluidType.isSame(ModLists.FLUIDS_INDEX.get(index))) {
                    amount++;
                    player.getMainHandItem().shrink(1);
                    player.addItem(Items.GLASS_BOTTLE.getDefaultInstance());
                    setChanged(level, getBlockPos(), getBlockState());
                }
            }
            setChanged(level, getBlockPos(), getBlockState());
        }
    }
    //Saving & loading
    @Override
    public void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        nbt.putInt("fluid_amount", amount);
        nbt.putInt("fluid_type", getIndex());
        super.saveAdditional(nbt, provider);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        amount = nbt.getInt("fluid_amount");
        fluidType = ModLists.FLUIDS_INDEX.get(nbt.getInt("fluid_type"));
        super.loadAdditional(nbt, provider);
    }

    public int getIndex() {
        return ModLists.FLUIDS_INDEX.indexOf(fluidType);
    }
}
