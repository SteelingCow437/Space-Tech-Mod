package com.net.spacetechmod.block.custom.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.block.entity.machine.OilPumpBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class OilPumpBlock extends BaseEntityBlock {
    public OilPumpBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof OilPumpBlockEntity) {
            ((OilPumpBlockEntity) entity).active = !((OilPumpBlockEntity) entity).active;
            if (((OilPumpBlockEntity) entity).active) {
                ((OilPumpBlockEntity) entity).drawPower();
            }
            else {
                ((OilPumpBlockEntity) entity).disconnectPower();
            }
            level.playSound(player, pos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 2.0f, 2.0f);
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.OIL_PUMP.get(),
                OilPumpBlockEntity::tick);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OilPumpBlockEntity(pos, state);
    }
}
