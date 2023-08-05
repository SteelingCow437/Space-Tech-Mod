package com.net.spacetechmod.block.custom.sculk;

import com.net.spacetechmod.enchantment.ModEnchantments;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SculkAltarBlock extends Block {
    public SculkAltarBlock(Properties properties) {
        super(properties);
    }

    ItemStack stack;
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!level.isClientSide()) {
            switch(ModLists.SCULK_ALTAR_INGREDIENT_LIST.indexOf(player.getMainHandItem().getItem())) {
                case 0 -> {
                    if(player.experienceLevel >= 75) {
                        stack = ModItems.SCULK_INGOT.get().getDefaultInstance();
                        player.addItem(stack);
                        player.getMainHandItem().shrink(1);
                        player.experienceLevel -= 75;
                    }
                }
                case 1 -> {
                    if(player.experienceLevel >= 250) {
                        if(player.getOffhandItem() == ModItems.SOUL_CRYSTAL.get().getDefaultInstance()) {
                            stack = ModItems.SOUL_BOTTLE.get().getDefaultInstance();
                            player.addItem(stack);
                            player.getMainHandItem().shrink(1);
                            player.getOffhandItem().shrink(1);
                            player.experienceLevel -= 250;
                        }
                    }
                }
                case 2 -> {
                    if(player.experienceLevel >= 20) {
                        stack = Items.ECHO_SHARD.getDefaultInstance();
                        player.getMainHandItem().shrink(1);
                        player.addItem(stack);
                        player.experienceLevel -= 20;
                    }
                }
                case 3 -> {
                    if(player.experienceLevel >= 100) {
                        player.getMainHandItem().enchant(ModEnchantments.MAGIC_REPAIR.get(), 0);
                        player.experienceLevel -= 100;
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
