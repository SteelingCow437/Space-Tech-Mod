package com.astronautica.block.custom.machine;

import com.mojang.serialization.MapCodec;
import com.astronautica.block.entity.machine.ForgingTableBlockEntity;
import com.astronautica.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ForgingTableBlock extends BaseEntityBlock {

    private static final IntegerProperty STAMP = IntegerProperty.create("stamp", 0, 3);

    public static final MapCodec<ForgingTableBlock> CODEC = simpleCodec(ForgingTableBlock::new);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private int stampNumber = 0;

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(STAMP);
    }

    public ForgingTableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(STAMP, stampNumber));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof ForgingTableBlockEntity && !level.isClientSide) {
            if(player.isShiftKeyDown()) {
                ((ForgingTableBlockEntity) entity).removeItem(player);
            }
            stampNumber = ((ForgingTableBlockEntity) entity).getStamp();
            level.setBlock(pos, state.setValue(STAMP, ((ForgingTableBlockEntity) entity).getStamp()), 1);
        }
        return super.useWithoutItem(state, level, pos, player, result);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof ForgingTableBlockEntity && !level.isClientSide) {
            if(player.getMainHandItem().getItem() == ModItems.HAMMER.get()) {
                ((ForgingTableBlockEntity) entity).craft(player);
            }
            else {
                ((ForgingTableBlockEntity) entity).setStamp(player.getItemInHand(hand).getItem(), player, hand);
                ((ForgingTableBlockEntity) entity).addIngredient(player, hand);
            }
            stampNumber = ((ForgingTableBlockEntity) entity).getStamp();
            level.setBlock(pos, state.setValue(STAMP, ((ForgingTableBlockEntity) entity).getStamp()), 1);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, result);
    }

    private boolean isRenderEntityValid(Level level, BlockPos pos, ForgingTableBlockEntity entity) {
        return entity.getRenderItem() != null;
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        super.destroy(level, pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock() && !level.isClientSide()) {
            if(level.getBlockEntity(pos) instanceof ForgingTableBlockEntity entity) {
                if(level instanceof ServerLevel && !level.isClientSide()) {
                    entity.drops(level, pos);
                    entity.removeRenderItem();
                }
                super.onRemove(state, level, pos, newState, movedByPiston);
            }
            else {
                super.onRemove(state, level, pos, newState, movedByPiston);
            }
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if(level.getBlockEntity(pos) instanceof ForgingTableBlockEntity entity) {
            entity.setupItemEntities(level);
        }
        super.onPlace(state, level, pos, oldState, movedByPiston);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ForgingTableBlockEntity(pos, state);
    }
}