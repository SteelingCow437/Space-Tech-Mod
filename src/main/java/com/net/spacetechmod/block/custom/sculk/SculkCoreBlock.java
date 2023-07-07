package com.net.spacetechmod.block.custom.sculk;

import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.item.custom.magic.TeleportMarkerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SculkCoreBlock extends Block {
    public SculkCoreBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(player.getMainHandItem().getItem() == Items.ENDER_PEARL) {
            player.getMainHandItem().shrink(1);
            Item item = ModItems.TELEPORT_MARKER.get();
            ((TeleportMarkerItem) item).homePos = pos.above();
            player.addItem(item.getDefaultInstance());
            level.playSound(player, pos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 2.0f, 2.0f);
            return InteractionResult.SUCCESS;
        } else {
            level.playSound(player, pos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 2.0f, 2.0f);
            return InteractionResult.FAIL;
        }
    }
}
