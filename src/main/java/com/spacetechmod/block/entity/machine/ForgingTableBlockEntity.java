package com.spacetechmod.block.entity.machine;

import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.item.ModItems;
import com.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class ForgingTableBlockEntity extends BlockEntity {

    private ItemEntity renderItem;
    ItemEntity dStamp;
    ItemEntity dIngredient;

    public ForgingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORGING_TABLE.get(), pos, state);
    }
    public Item stamp = null;


    public ItemStack ingredient = ItemStack.EMPTY;
    private Item result = null;

    private float rotation = 0;
    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public ItemStack getIngredient() {
        if(ingredient != ItemStack.EMPTY) {
            return ingredient;
        }
        return ItemStack.EMPTY;
    }

    public ItemEntity getRenderItem() {
        return renderItem;
    }

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
                    }
                    givePlateOrIngot(player);
                    level.playSound(player, player.getOnPos(), SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 2.0f, 2.0f);
                } else if (stamp == ModItems.WIRE_STAMP.get()) {
                    switch (ModLists.FORGING_TABLE_INGREDIENT_LIST.indexOf(ingredient.getItem())) {
                        case 1 -> result = ModItems.COPPER_WIRING.get();
                        case 5 -> result = ModItems.COPPER_REDSTIDE_WIRING.get();
                    }
                    giveWire(player);
                    level.playSound(player, player.getOnPos(), SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 2.0f, 2.0f);
                }
                else if(stamp == ModItems.INGOT_STAMP.get()) {
                    switch(ModLists.FORGING_TABLE_INGREDIENT_LIST.indexOf(ingredient.getItem())) {
                        case 7 -> result = Items.IRON_INGOT;
                        case 8 -> result = Items.COPPER_INGOT;
                        case 9 -> result = ModItems.TITANIUM_INGOT.get();
                        case 10 -> result = ModItems.STEEL_INGOT.get();
                        case 11 -> result = ModItems.BRONZE_INGOT.get();
                        case 12 -> result = ModItems.TITAN_STEEL_INGOT.get();
                    }
                    givePlateOrIngot(player);
                    level.playSound(player, player.getOnPos(), SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 2.0f, 2.0f);
                }
                setChanged();
            }
        }
    }
    public void givePlateOrIngot(Player player) {
        if(result != null) {
            player.addItem(new ItemStack(result, ingredient.getCount()));
            ingredient = ItemStack.EMPTY;
            removeRenderItem();
            result = null;
            setChanged();
        }
    }

    public void giveWire(Player player) {
        if(result != null) {
            player.addItem(new ItemStack(result, 8 * ingredient.getCount()));
            ingredient = ItemStack.EMPTY;
            removeRenderItem();
            result = null;
            setChanged();
        }
    }

    public void addIngredient(Player player, InteractionHand hand) {
        if(level != null && !level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            if (ModLists.FORGING_TABLE_INGREDIENT_LIST.contains(stack.getItem())) {
                if (ingredient == null || ingredient == ItemStack.EMPTY) {
                    ingredient = new ItemStack(player.getItemInHand(hand).getItem(), 1);
                    player.getItemInHand(hand).shrink(1);
                    setRenderItem(player.level());
                } else if (ingredient.is(stack.getItem())) {
                    ingredient.grow(1);
                    player.getItemInHand(hand).shrink(1);
                }
            }
            setChanged();
        }
    }

    public void setStamp(Item item, Player player, InteractionHand hand) {
        if(level != null && !level.isClientSide) {
            if (ModLists.FORGING_TABLE_STAMP_LIST.contains(item)) {
                if (stamp != null) {
                    player.addItem(new ItemStack(stamp, 1));
                }
                stamp = item;
                player.getItemInHand(hand).shrink(1);
            }
            setChanged();
        }
    }

    public void removeItem(Player player) {
        if(!player.level().isClientSide) {
            if(ingredient != null) {
                player.addItem(ingredient);
                ingredient = null;
                removeRenderItem();
            }
            if(stamp != null) {
                player.addItem(new ItemStack(stamp, 1));
                stamp = null;
            }
            setChanged();
        }
    }
    @Override
    public void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        if(ModLists.FORGING_TABLE_STAMP_LIST.indexOf(stamp) != 0) {
            nbt.putInt("stamp_type", ModLists.FORGING_TABLE_STAMP_LIST.indexOf(stamp));
        }
        if(!ingredient.isEmpty()) {
            nbt.putInt("ingredient", ModLists.FORGING_TABLE_INGREDIENT_LIST.indexOf(ingredient.getItem()));
        }
        if(ingredient.getCount() > 0) {
            nbt.putInt("ingredient_count", ingredient.getCount());
        }
        super.saveAdditional(nbt, provider);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        switch(nbt.getInt("stamp_type")) {
            case 0 -> stamp = null;
            case 1 -> stamp = ModItems.PLATE_STAMP.get();
            case 2 -> stamp = ModItems.WIRE_STAMP.get();
            case 3 -> stamp = ModItems.INGOT_STAMP.get();
        }
        Item tempItem = null;
        switch(nbt.getInt("ingredient")) {
            case 0 -> tempItem = Items.IRON_INGOT;
            case 1 -> tempItem = Items.COPPER_INGOT;
            case 2 -> tempItem = ModItems.TITANIUM_INGOT.get();
            case 3 -> tempItem = ModItems.STEEL_INGOT.get();
            case 4 -> tempItem = ModItems.BRONZE_INGOT.get();
            case 5 -> tempItem = ModItems.COPPER_REDSTIDE_INGOT.get();
            case 6 -> tempItem = ModItems.TITAN_STEEL_INGOT.get();
            case 7 -> tempItem = ModItems.IRON_PLATE.get();
            case 8 -> tempItem = ModItems.COPPER_PLATE.get();
            case 9 -> tempItem = ModItems.TITANIUM_PLATE.get();
            case 10 -> tempItem = ModItems.STEEL_PLATE.get();
            case 11 -> tempItem = ModItems.BRONZE_PLATE.get();
            case 12 -> tempItem = ModItems.TITAN_STEEL_PLATE.get();
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

    private void setRenderItem(Level level) {
        if(renderItem.isRemoved()) {
            renderItem = new ItemEntity(EntityType.ITEM, level);
        }
        renderItem.setItem(ingredient);
        renderItem.setPos(Vec3.atCenterOf(worldPosition.above()));
        renderItem.setNeverPickUp();
        renderItem.setUnlimitedLifetime();
        level.addFreshEntity(renderItem);
    }

    public void removeRenderItem() {
        renderItem.kill();
    }

    public void drops(Level level, BlockPos pos) {
        if(renderItem.isAlive()) {
            removeRenderItem();
        }
        if(stamp != null) {
            dStamp.setItem(new ItemStack(stamp, 1));
            dStamp.setPos(Vec3.atCenterOf(new Vec3i(pos.getX(), pos.getY(), pos.getZ())));
            level.addFreshEntity(dStamp);
        }
        if(ingredient != null) {
            dIngredient.setItem(getIngredient());
            dIngredient.setPos(Vec3.atCenterOf(new Vec3i(pos.getX(), pos.getY(), pos.getZ())));
            level.addFreshEntity(dIngredient);
        }
    }

    public void setupItemEntities(Level level) {
        renderItem = new ItemEntity(EntityType.ITEM, level);
        dIngredient = new ItemEntity(EntityType.ITEM, level);
        dStamp = new ItemEntity(EntityType.ITEM, level);
    }
}