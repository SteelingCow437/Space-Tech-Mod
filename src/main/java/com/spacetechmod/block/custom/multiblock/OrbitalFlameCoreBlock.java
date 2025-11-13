package com.spacetechmod.block.custom.multiblock;

import com.spacetechmod.data.ModDataStorage;
import com.spacetechmod.item.ModItems;
import com.spacetechmod.util.MultiBlockPart;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;

public class OrbitalFlameCoreBlock extends Block {

    public OrbitalFlameCoreBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            ItemStack marker = new ItemStack(ModItems.ORBITAL_MARKER.get(), 1);
            marker.set(ModDataStorage.LINKED_ORBITAL_CORE, pos);
            if(stack.getItem() == ModItems.ORBITAL_FLAME_SHELL.get() && stack.getCount() >= 1) {
                player.addItem(marker);
                stack.shrink(1);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.FAIL;
    }

    public boolean isStructureValid(ArrayList<MultiBlockPart> structure, ServerLevel level, BlockPos originPos) {
        boolean v0 = true;
        /*
        boolean v1 = true;
        boolean v2 = true;
        boolean v3 = true;
         */
        for (MultiBlockPart multiBlockPart : structure) {
            if (level.getBlockState(originPos.offset(multiBlockPart.relativeX, multiBlockPart.relativeY, multiBlockPart.relativeZ)).getBlock() != multiBlockPart.block) {
                v0 = false;
            }
            /*
            if (level.getBlockState(originPos.offset(-multiBlockPart.relativeZ, multiBlockPart.relativeY, multiBlockPart.relativeX)).getBlock() != multiBlockPart.block) {
                v1 = false;
            }
            if (level.getBlockState(originPos.offset(-multiBlockPart.relativeX, multiBlockPart.relativeY, -multiBlockPart.relativeZ)).getBlock() != multiBlockPart.block) {
                v2 = false;
            }
            if (level.getBlockState(originPos.offset(multiBlockPart.relativeZ, multiBlockPart.relativeY, -multiBlockPart.relativeX)).getBlock() != multiBlockPart.block) {
                v3 = false;
            }
            */
        }
        /*
        For debugging purposes only!
        level.players().getFirst().sendSystemMessage(Component.literal("Test 0 Success!"));
        level.players().getFirst().sendSystemMessage(Component.literal("Test 1 Success!"));
        level.players().getFirst().sendSystemMessage(Component.literal("Test 2 Success!"));
        level.players().getFirst().sendSystemMessage(Component.literal("Test 3 Success!"));
         */
        return v0; // || v1 || v2 || v3;
    }
}
