package com.spacetechmod.block.custom.dungeon;

import com.spacetechmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class VaultDoorBlock extends Block {
    public VaultDoorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            Item item = stack.getItem();
            if(item == ModItems.VAULT_KEY.get()) {
                openDoor(level, pos);
                player.getItemInHand(hand).shrink(1);
                return ItemInteractionResult.CONSUME;
            }
            return ItemInteractionResult.FAIL;
        }
        return ItemInteractionResult.FAIL;
    }

    private void openDoor(Level level, BlockPos pos) {
        BlockPos originPos = new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() - 4);
        for(int a = 0; a < 9; ++a) {
            for(int b = 0; b < 9; ++b) {
                level.setBlockAndUpdate(new BlockPos(originPos.getX() + a, originPos.getY(), originPos.getZ() + b), Blocks.AIR.defaultBlockState());
            }
        }
    }
}