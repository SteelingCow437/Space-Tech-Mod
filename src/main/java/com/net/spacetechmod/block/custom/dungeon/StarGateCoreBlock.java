package com.net.spacetechmod.block.custom.dungeon;

import com.mojang.serialization.MapCodec;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.block.entity.dungeon.StarGateCoreBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class StarGateCoreBlock extends BaseEntityBlock {

    public StarGateCoreBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<StarGateCoreBlock> CODEC = simpleCodec(StarGateCoreBlock::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof StarGateCoreBlockEntity) {
            if(!((StarGateCoreBlockEntity) entity).ACTIVE) {
                if(((StarGateCoreBlockEntity) entity).checkForCompletion(player)) {
                    ((StarGateCoreBlockEntity) entity).makePortalOnDirection();
                    level.playSound(player, player.getOnPos(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 2.0f, 2.0f);
                }
            }
            else {
                ((StarGateCoreBlockEntity) entity).breakPortalOnDirection();
                level.playSound(player, player.getOnPos(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 2.0f, 2.0f);
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StarGateCoreBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.STARGATE_CORE.get(),
                StarGateCoreBlockEntity::tick);
    }
}
