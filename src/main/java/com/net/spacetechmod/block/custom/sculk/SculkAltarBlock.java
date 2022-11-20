package com.net.spacetechmod.block.custom.sculk;

import com.net.spacetechmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
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

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()) {
            if(pPlayer.experienceLevel >= 75 && pPlayer.isHolding(ModItems.TITANIUM_INGOT.get())) {
                ItemStack stack = ModItems.SCULK_INGOT.get().getDefaultInstance();
                pPlayer.addItem(stack);
                pPlayer.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                pPlayer.experienceLevel -= 100;
            }
            else if(pPlayer.experienceLevel >= 250 && pPlayer.isHolding(Items.GLASS_BOTTLE) && pPlayer.getOffhandItem() == ModItems.SOUL_CRYSTAL.get().getDefaultInstance()) {
                ItemStack stack = ModItems.SOUL_BOTTLE.get().getDefaultInstance();
                pPlayer.addItem(stack);
                pPlayer.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                pPlayer.experienceLevel -= 250;
            }

            else if(pPlayer.experienceLevel >= 20 && pPlayer.isHolding(Items.AMETHYST_SHARD)) {
                ItemStack stack = Items.ECHO_SHARD.getDefaultInstance();
                pPlayer.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                pPlayer.addItem(stack);
                pPlayer.experienceLevel -= 20;
            }

        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
}
