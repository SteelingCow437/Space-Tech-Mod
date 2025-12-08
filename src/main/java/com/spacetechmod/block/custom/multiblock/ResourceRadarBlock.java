package com.spacetechmod.block.custom.multiblock;

import com.spacetechmod.data.ModDataStorage;
import com.spacetechmod.item.ModItems;
import com.spacetechmod.util.MultiBlockPart;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;

public class ResourceRadarBlock extends Block {

    public ResourceRadarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            if(stack.getItem() == ModItems.RESOURCE_SCANNER.get()) {
                stack.set(ModDataStorage.LINKED_ORBITAL_CORE, pos);
                player.sendSystemMessage(Component.literal("Scanner linked!"));
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.FAIL;
    }

    //this one is symmetrical!
    public boolean isStructureValid(ArrayList<MultiBlockPart> structure, ServerLevel level, BlockPos originPos) {
        boolean v0 = true;
        for (MultiBlockPart multiBlockPart : structure) {
            if (level.getBlockState(originPos.offset(multiBlockPart.getRX(), multiBlockPart.getRY(), multiBlockPart.getRZ())).getBlock() != multiBlockPart.getBlock()) {
                v0 = false;
            }
        }
        return v0;
    }
}
