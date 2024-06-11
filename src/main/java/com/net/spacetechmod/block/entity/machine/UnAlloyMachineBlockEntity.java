package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.custom.machine.UnAlloyMachineBlock;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.CommonHooks;

public class UnAlloyMachineBlockEntity extends BlockEntity {
    public UnAlloyMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UN_ALLOY_MACHINE.get(), pos, state);
    }

    private int timer = 0;
    private static int tick = 0;
    public int fuelTime = 0;

    public void use(Player player, ItemStack input) {
        if(ModLists.FORGING_TABLE_INGREDIENT_LIST.contains(input.getItem()) && input.getBurnTime(RecipeType.SMELTING) > 0) {
            fuelTime += input.getBurnTime(RecipeType.SMELTING);
            input.shrink(input.getCount());
        }
        else if(ModLists.FORGING_TABLE_INGREDIENT_LIST.contains(input.getItem())) {
            if(input.getCount() % 2 == 0 && player != null && timer >= 100) {
                int count = input.getCount();
                int total = count / 2;
                Item item = input.getItem();
                switch(ModLists.FORGING_TABLE_INGREDIENT_LIST.indexOf(item)) {
                    default -> {
                        player.sendSystemMessage(Component.literal("Invalid Recipe!"));
                    }

                    case 3 -> {
                        player.addItem(new ItemStack(Items.IRON_INGOT, total));
                        player.addItem(new ItemStack(Items.COAL, total));
                        timer -= 100;
                        player.getMainHandItem().shrink(count);
                    }

                    case 4 -> {
                        player.addItem(new ItemStack(Items.COPPER_INGOT, total));
                        player.addItem(new ItemStack(ModItems.TIN_INGOT.get(), total));
                        timer -= 100;
                        player.getMainHandItem().shrink(count);
                    }

                    case 5 -> {
                        player.addItem(new ItemStack(Items.COPPER_INGOT, total));
                        player.addItem(new ItemStack(Items.REDSTONE, total));
                        timer -= 100;
                        player.getMainHandItem().shrink(count);
                    }

                    case 6 -> {
                        player.addItem(new ItemStack(ModItems.TITANIUM_INGOT.get(), total));
                        player.addItem(new ItemStack(ModItems.STEEL_INGOT.get(), total));
                        timer -= 100;
                        player.getMainHandItem().shrink(count);
                    }
                }
            }
            else if(player == null) {
                level.playSound(null, worldPosition, SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 2.0f, 2.0f);
            }
        }
        else {
            player.sendSystemMessage(Component.literal("Time remaining: " + fuelTime / 20 + " seconds"));
        }
    }

    public void updateBlock() {
        boolean active = (fuelTime > 0);
        BlockEntity entity = level.getBlockEntity(worldPosition);
        Block block = level.getBlockState(worldPosition).getBlock();
        if(entity instanceof UnAlloyMachineBlockEntity && block instanceof UnAlloyMachineBlock) {
            ((UnAlloyMachineBlock) block).setState(worldPosition, this.getBlockState(), level, active);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, UnAlloyMachineBlockEntity entity) {
        if(tick >= 20 && entity.fuelTime > 0) {
            level.playSound(null, pos, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 3.0f, 3.0f);
            tick = 0;
            entity.updateBlock();
        }
        else if(tick < 20) {
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
