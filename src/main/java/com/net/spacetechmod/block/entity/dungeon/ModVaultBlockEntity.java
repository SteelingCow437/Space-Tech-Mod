package com.net.spacetechmod.block.entity.dungeon;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ModVaultBlockEntity extends BlockEntity {

    private final int MAX_COOLDOWN = 12000; //ticks
    public int COOLDOWN = 0; //current cooldown in ticks

    Random random = new Random();
    ItemEntity drop = new ItemEntity(EntityType.ITEM, level);

    public ModVaultBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MOD_VAULT.get(), pos, state);
    }

    public void giveLoot(boolean isBoss, ResourceKey<Level> dimension) {
        drop.setPos(worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ());
        switch(ModLists.PLANET_LIST.indexOf(dimension)) {
            case 0 -> overworldLoot(isBoss);
            case 1 -> moonLoot(isBoss);
        }
    }

    private void overworldLoot(boolean boss) {
        if(boss) {
            for(int i = 0; i < 9; ++i) {
                drop.setItem(new ItemStack(OVERWORLD_BOSS_LOOT.get(random.nextInt(0, 8)), random.nextInt(1, 5)));
                level.addFreshEntity(drop);
            }
        }
        else {
            for(int i = 0; i < 9; ++i) {
                drop.setItem(new ItemStack(OVERWORLD_LOOT.get(random.nextInt(0, 8)), random.nextInt(1, 3)));
                level.addFreshEntity(drop);
            }
        }
        COOLDOWN = MAX_COOLDOWN;
    }

    private void moonLoot(boolean boss) {
        //hehe not done yet
    }


    private ArrayList<Item> OVERWORLD_LOOT = new ArrayList<>(Arrays.asList(
            Items.DIAMOND, Items.GOLD_INGOT, ModItems.AQUAMARINE.asItem(), Items.TNT, Items.IRON_INGOT,
            Items.LAPIS_LAZULI, Items.EMERALD, ModItems.TITAN_STEEL_INGOT.asItem(), ModItems.LEAN.asItem()
    ));
    private ArrayList<Item> OVERWORLD_BOSS_LOOT = new ArrayList<>(Arrays.asList(
            Items.DIAMOND, Items.GOLD_BLOCK, ModItems.AQUAMARINE.asItem(), Items.IRON_BLOCK, ModItems.MOON_KEY.asItem(),
            Items.ENCHANTED_GOLDEN_APPLE, Items.EMERALD, ModItems.TITAN_STEEL_INGOT.asItem(), ModItems.ANTIDOTE.asItem()
    ));
    /*
    TODO: this lol
    private ArrayList<Item> MOON_LOOT;
    private ArrayList<Item> MOON_BOSS_LOOT;
     */

    public static void tick(Level level, BlockPos pos, BlockState state, ModVaultBlockEntity entity) {
        if(entity.COOLDOWN > 0) {
            --entity.COOLDOWN;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        nbt.putInt("cooldown", COOLDOWN);
        super.saveAdditional(nbt, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        COOLDOWN = nbt.getInt("cooldown");
        super.loadAdditional(nbt, provider);
    }
}