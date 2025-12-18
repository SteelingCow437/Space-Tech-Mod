package com.astronautica.block.entity.machine;

import com.astronautica.block.entity.ModBlockEntities;
import com.astronautica.item.ModItems;
import com.astronautica.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class UnAlloyMachineBlockEntity extends BlockEntity {
    public UnAlloyMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UN_ALLOY_MACHINE.get(), pos, state);
    }

    private int timer = 0;
    private static int tick = 0;
    public int fuelTime = 0;

    private Random random = new Random();

    public void use(Player player, ItemStack input, InteractionHand hand) {
        if(input.getBurnTime(RecipeType.SMELTING) > 0) {
            ItemStack fuel = new ItemStack(player.getItemInHand(hand).getItem(), 1);
            fuelTime += fuel.getBurnTime(RecipeType.SMELTING);
            level.playSound(player, worldPosition, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 2.0f, 2.0f);
            if(!fuel.getCraftingRemainingItem().isEmpty()) {
                player.addItem(fuel.getCraftingRemainingItem());
            }
            player.getItemInHand(hand).shrink(1);
            setChanged();
        }
        else if(ModLists.FORGING_TABLE_INGREDIENT_LIST.contains(input.getItem())) {
            if(input.getCount() % 2 == 0 && player != null && timer >= 100) {
                int count = input.getCount();
                int total = count / 2;
                Item item = input.getItem();
                switch(ModLists.FORGING_TABLE_INGREDIENT_LIST.indexOf(item)) {
                    case 3 -> {
                        player.addItem(new ItemStack(Items.IRON_INGOT, total));
                        player.addItem(new ItemStack(Items.COAL, total));
                        player.getItemInHand(hand).shrink(count);
                    }

                    case 4 -> {
                        player.addItem(new ItemStack(Items.COPPER_INGOT, total));
                        player.addItem(new ItemStack(ModItems.TIN_INGOT.get(), total));
                        player.getItemInHand(hand).shrink(count);
                    }

                    case 5 -> {
                        player.addItem(new ItemStack(Items.COPPER_INGOT, total));
                        player.addItem(new ItemStack(Items.REDSTONE, total));
                        player.getItemInHand(hand).shrink(count);
                    }

                    case 6 -> {
                        player.addItem(new ItemStack(ModItems.TITANIUM_INGOT.get(), total));
                        player.addItem(new ItemStack(ModItems.STEEL_INGOT.get(), total));
                        player.getItemInHand(hand).shrink(count);
                    }
                    default -> player.sendSystemMessage(Component.literal("Invalid Recipe!"));
                }
            }
            else if(player == null) {
                level.playSound(null, worldPosition, SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 2.0f, 2.0f);
            }
        }
        else if(input.getItem() == ModItems.MINERAL_CLUMP.asItem() && player != null && timer >= 100) {
            int r = random.nextInt(0, 100);
            if(r <= 19) {
                player.addItem(new ItemStack(Items.LAPIS_LAZULI, 1));
                player.getItemInHand(hand).shrink(1);
            }
            if(r >= 20 && r <= 38) {
                player.addItem(new ItemStack(Items.AMETHYST_SHARD, 1));
                player.getItemInHand(hand).shrink(1);
            }
            if(r > 39 && r <= 57) {
                player.addItem(new ItemStack(Items.EMERALD, 1));
                player.getItemInHand(hand).shrink(1);
            }
            if(r > 58 && r <= 77) {
                player.addItem(new ItemStack(Items.GOLD_INGOT, 1));
                player.getItemInHand(hand).shrink(1);
            }
            if(r > 78 && r <= 97) {
                player.addItem(new ItemStack(Items.DIAMOND, 1));
                player.getItemInHand(hand).shrink(1);
            }
            if(r >= 98) {
                player.addItem(new ItemStack(Items.NETHERITE_SCRAP, 1));
                player.getItemInHand(hand).shrink(1);
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, UnAlloyMachineBlockEntity entity) {
        if(tick >= 20) {
            if(entity.fuelTime > 0) {
                level.playSound(null, pos, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 3.0f, 3.0f);
            }
            tick = 0;
        }
        else {
            ++tick;
        }
        if(entity.fuelTime > 0) {
            if(entity.timer < 110) {
                ++entity.timer;
            }
            --entity.fuelTime;
        }
    }
}
