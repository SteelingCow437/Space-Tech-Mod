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

public class OrbitalTNTCoreBlock extends Block {

    public OrbitalTNTCoreBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            ItemStack marker = new ItemStack(ModItems.ORBITAL_MARKER.get(), 1);
            marker.set(ModDataStorage.LINKED_ORBITAL_CORE, pos);
            if(stack.getItem() == ModItems.ORBITAL_TNT_SHELL.get() && stack.getCount() >= 1) {
                player.addItem(marker);
                stack.shrink(1);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.FAIL;
    }

    public boolean isStructureValid(ArrayList<MultiBlockPart> structure, ServerLevel level, BlockPos originPos) {
        return isStructureValid0(structure, level, originPos) || isStructureValid1(structure, level, originPos) ||
                isStructureValid2(structure, level, originPos) || isStructureValid3(structure, level, originPos);
    }

    public boolean isStructureValid0(ArrayList<MultiBlockPart> structure, ServerLevel level, BlockPos originPos) {
        for (MultiBlockPart multiBlockPart : structure) {
            if (level.getBlockState(new BlockPos(originPos.getX() + multiBlockPart.relativeX, originPos.getY() + multiBlockPart.relativeY, originPos.getZ() + multiBlockPart.relativeZ)).getBlock() != multiBlockPart.block) {
                return false;
            }
        }
        //level.players().getFirst().sendSystemMessage(Component.literal("Test 0 Success!")); debug
        return true;
    }

    public boolean isStructureValid1(ArrayList<MultiBlockPart> structure, ServerLevel level, BlockPos originPos) {
        for (MultiBlockPart multiBlockPart : structure) {
            if (level.getBlockState(new BlockPos(originPos.getX() - multiBlockPart.relativeZ, originPos.getY() + multiBlockPart.relativeY, originPos.getZ() + multiBlockPart.relativeX)).getBlock() != multiBlockPart.block) {
                return false;
            }
        }
        //level.players().getFirst().sendSystemMessage(Component.literal("Test 1 Success!")); debug
        return true;
    }

    public boolean isStructureValid2(ArrayList<MultiBlockPart> structure, ServerLevel level, BlockPos originPos) {
        for (MultiBlockPart multiBlockPart : structure) {
            if (level.getBlockState(new BlockPos(originPos.getX() - multiBlockPart.relativeX, originPos.getY() + multiBlockPart.relativeY, originPos.getZ() - multiBlockPart.relativeZ)).getBlock() != multiBlockPart.block) {
                return false;
            }
        }
        //level.players().getFirst().sendSystemMessage(Component.literal("Test 2 Success!")); debug
        return true;
    }

    public boolean isStructureValid3(ArrayList<MultiBlockPart> structure, ServerLevel level, BlockPos originPos) {
        for (MultiBlockPart multiBlockPart : structure) {
            if (level.getBlockState(new BlockPos(originPos.getX() + multiBlockPart.relativeZ, originPos.getY() + multiBlockPart.relativeY, originPos.getZ() - multiBlockPart.relativeX)).getBlock() != multiBlockPart.block) {
                return false;
            }
        }
        //level.players().getFirst().sendSystemMessage(Component.literal("Test 3 Success!")); debug
        return true;
    }


}
