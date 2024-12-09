package com.spacetechmod.block.custom.multiblock;

import com.spacetechmod.data.ModDataStorage;
import com.spacetechmod.item.ModItems;
import com.spacetechmod.util.MultiBlockPart;
import net.minecraft.core.BlockPos;
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
        for(int i = 0; i < structure.size(); ++i) {
            if(level.getBlockState(new BlockPos(originPos.getX() + structure.get(i).relativeX, originPos.getY() + structure.get(i).relativeY, originPos.getZ() + structure.get(i).relativeZ)).getBlock() != structure.get(i).block) {
                return false;
            }
        }
        return true;
    }
}
