package com.net.spacetechmod.block.custom.sculk;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class WardenTrapBlock extends Block {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public WardenTrapBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, Boolean.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos())));
    }
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2, boolean bool) {
        if (!level.isClientSide) {
            boolean flag = state.getValue(LIT);
            if(flag != level.hasNeighborSignal(pos)) {
                if(flag) {
                    level.scheduleTick(pos, this, 4);
                }
                else {
                    level.setBlock(pos, state.cycle(LIT), 2);
                }
            }

        }
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if(!level.isClientSide) {
            SpawnUtil.trySpawnMob(EntityType.WARDEN, MobSpawnType.TRIGGERED, level, pos, 20, 1, 1, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
        }
        super.tick(state, level, pos, source);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(LIT);
    }
}
