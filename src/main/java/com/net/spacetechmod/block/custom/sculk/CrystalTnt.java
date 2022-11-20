package com.net.spacetechmod.block.custom.sculk;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.concurrent.TimeUnit;

public class CrystalTnt extends Block {
    public CrystalTnt(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide) {
            if(pPlayer.getMainHandItem().is(Items.FLINT_AND_STEEL)) {
                pLevel.playSound(pPlayer, pPos, SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.BLOCKS, 10, 10);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                pLevel.explode(pPlayer, pPos.getX(), pPos.getY(), pPos.getZ(), 40, Explosion.BlockInteraction.BREAK);
                return InteractionResult.CONSUME_PARTIAL;
            }
        }
        return InteractionResult.FAIL;
    }

}
