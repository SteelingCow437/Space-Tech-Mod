package com.net.spacetechmod.block.custom;

import com.net.spacetechmod.block.entity.ModAbstractFurnaceBlockEntity;
import com.net.spacetechmod.world.inventory.ModAbstractContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public abstract class ModAbstractFurnaceBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    protected ModAbstractFurnaceBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            this.openContainer(level, pos, player);
            return InteractionResult.CONSUME;
        }
    }

    protected abstract void openContainer(Level level, BlockPos pos, Player player);

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
        if(stack.hasCustomHoverName()) {
            BlockEntity entity1 = level.getBlockEntity(pos);
            if(entity1 instanceof ModAbstractFurnaceBlockEntity) {
                ((ModAbstractFurnaceBlockEntity)entity1).setCustomName(stack.getDisplayName());
            }
        }
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean bool) {
        if(!state.is(state1.getBlock())) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof ModAbstractFurnaceBlockEntity) {
                if(level instanceof ServerLevel) {
                    Containers.dropContents(level, pos, (ModAbstractFurnaceBlockEntity)entity);
                }
                level.updateNeighbourForOutputSignal(pos, this);            
            }
            super.onRemove(state, level, pos, state1, bool);
        }
    }
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return ModAbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        definition.add(FACING, LIT);
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createModFurnaceTicker(Level level, BlockEntityType<T> type, BlockEntityType<? extends ModAbstractFurnaceBlockEntity> entity) {
        return level.isClientSide ? null : createTickerHelper(type, entity, ModAbstractFurnaceBlockEntity::serverTick);
    }
}
