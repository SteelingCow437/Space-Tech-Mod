package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ForgingTableBlockEntity extends BlockEntity {
    public ForgingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORGING_TABLE.get(), pos, state);
    }
    public Item stamp = null;

    public ItemStack ingredient = ItemStack.EMPTY;
    private Item result = null;

    public void craft(Player player) {
        if (level != null && !level.isClientSide) {
            if (ingredient != null) {
                if (stamp == ModItems.PLATE_STAMP.get()) {
                    switch (ModLists.FORGING_TABLE_INGREDIENT_LIST.indexOf(ingredient.getItem())) {
                        case 0 -> result = ModItems.IRON_PLATE.get();
                        case 1 -> result = ModItems.COPPER_PLATE.get();
                        case 2 -> result = ModItems.TITANIUM_PLATE.get();
                        case 3 -> result = ModItems.STEEL_PLATE.get();
                        case 4 -> result = ModItems.BRONZE_PLATE.get();
                        case 6 -> result = ModItems.TITAN_STEEL_PLATE.get();
                        default -> player.sendSystemMessage(Component.literal("Invalid recipe!"));
                    }
                    givePlate(player);
                    level.playSound(player, player.getOnPos(), SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 2.0f, 2.0f);
                } else if (stamp == ModItems.WIRE_STAMP.get()) {
                    switch (ModLists.FORGING_TABLE_INGREDIENT_LIST.indexOf(ingredient.getItem())) {
                        case 1 -> result = ModItems.COPPER_WIRING.get();
                        case 5 -> result = ModItems.COPPER_REDSTIDE_WIRING.get();
                        default -> player.sendSystemMessage(Component.literal("Invalid recipe!"));
                    }
                    giveWire(player);
                    level.playSound(player, player.getOnPos(), SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 2.0f, 2.0f);
                }
            }
        }
    }
    public void givePlate(Player player) {
        if(result != null) {
            player.addItem(new ItemStack(result, ingredient.getCount()));
            ingredient = ItemStack.EMPTY;
            result = null;
        }
    }

    public void giveWire(Player player) {
        if(result != null) {
            player.addItem(new ItemStack(result, 8 * ingredient.getCount()));
            ingredient = ItemStack.EMPTY;
            result = null;
        }
    }

    public void addIngredient(Item item, Player player, int count) {
        if(level != null && !level.isClientSide) {
            if (ModLists.FORGING_TABLE_INGREDIENT_LIST.contains(item)) {
                if (ingredient == null || ingredient == ItemStack.EMPTY) {
                    ingredient = new ItemStack(item, count);
                    player.getMainHandItem().shrink(1);
                } else if (ingredient.is(item)) {
                    ingredient.setCount(ingredient.getCount() + count);
                    player.getMainHandItem().shrink(1);
                }
            }
        }
    }

    public void setStamp(Item item, Player player) {
        if(level != null && !level.isClientSide) {
            if (item == ModItems.PLATE_STAMP.get() || item == ModItems.WIRE_STAMP.get()) {
                if (stamp != null) {
                    player.addItem(new ItemStack(stamp, 1));
                }
                stamp = item;
                player.getMainHandItem().shrink(1);
            }
        }
    }

    public void removeItem(Player player) {
        if(level != null && !level.isClientSide) {
            if (stamp != null && ingredient != null) {
                player.addItem(new ItemStack(stamp, 1));
                player.addItem(ingredient);
                stamp = null;
                ingredient = null;
            } else if (ingredient != null) {
                player.addItem(ingredient);
                ingredient = null;
            } else if (stamp != null) {
                player.addItem(new ItemStack(stamp, 1));
                stamp = null;
            }
        }
    }
    @Override
    public void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        nbt.putInt("stamp_type", ModLists.FORGING_TABLE_STAMP_LIST.indexOf(stamp));
        nbt.putInt("ingredient", ModLists.FORGING_TABLE_INGREDIENT_LIST.indexOf(ingredient.getItem()));
        nbt.putInt("ingredient_count", ingredient.getCount());
        super.saveAdditional(nbt, provider);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        switch(nbt.getInt("stamp_type")) {
            case 0 -> stamp = null;
            case 1 -> stamp = ModItems.PLATE_STAMP.get();
            case 2 -> stamp = ModItems.WIRE_STAMP.get();
        }
        Item tempItem = null;
        switch(nbt.getInt("ingredient")) {
            case 0 -> tempItem = Items.IRON_INGOT;
            case 1 -> tempItem = Items.COPPER_INGOT;
            case 2 -> tempItem = ModItems.TITANIUM_INGOT.get();
            case 3 -> tempItem = ModItems.STEEL_INGOT.get();
            case 4 -> tempItem = ModItems.BRONZE_INGOT.get();
        }
        if(tempItem != null) {
            ingredient = new ItemStack(tempItem, nbt.getInt("ingredient_count"));
        }
        super.loadAdditional(nbt, provider);
    }


    public int getStamp() {
        if(stamp == null) {
            return 0;
        }
        else {
            return ModLists.FORGING_TABLE_STAMP_LIST.indexOf(stamp);
        }
    }
}
