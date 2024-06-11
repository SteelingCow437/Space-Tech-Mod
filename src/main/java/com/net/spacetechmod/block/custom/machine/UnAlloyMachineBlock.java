package com.net.spacetechmod.block.custom.machine;

import com.mojang.serialization.MapCodec;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.block.entity.machine.UnAlloyMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class UnAlloyMachineBlock extends BaseEntityBlock {

    private static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public boolean active = false;

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    public UnAlloyMachineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(ACTIVE, active));
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof UnAlloyMachineBlockEntity) {
            ItemStack stack = player.getMainHandItem();
            ((UnAlloyMachineBlockEntity) entity).use(player, stack);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public void setState(BlockPos pos, BlockState state, Level level, boolean active) {
        level.setBlock(pos, state.setValue(ACTIVE, active), 1);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new UnAlloyMachineBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.UN_ALLOY_MACHINE.get(),
                UnAlloyMachineBlockEntity::tick);
    }
}
