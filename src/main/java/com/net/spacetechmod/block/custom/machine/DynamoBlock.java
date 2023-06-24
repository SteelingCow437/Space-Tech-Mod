package com.net.spacetechmod.block.custom.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.block.entity.machine.DynamoBlockEntity;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class DynamoBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public DynamoBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE =
            Block.box(0, 0, 0, 16, 10, 16);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
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
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        BlockEntity entity = level.getBlockEntity(pos);
        BlockEntityType<?> neighborEntity = level.getBlockEntity(neighbor).getType();
        if(entity instanceof DynamoBlockEntity) {
            if(level.getBlockEntity(neighbor) == null) {
                ((DynamoBlockEntity) entity).outputWattage = 0;
            }
            else if (ModLists.MACHINE_INDEX.contains(neighborEntity)) {
                if (((DynamoBlockEntity) entity).isActive) {
                    switch (ModLists.MACHINE_INDEX.indexOf(neighborEntity)) {
                        default -> {
                            ((DynamoBlockEntity) entity).outputWattage = 0;
                            entity.setChanged();
                        }
                        case 1 -> {
                            ((DynamoBlockEntity) entity).outputWattage = 50;
                            entity.setChanged();
                        }
                    }
                } else {
                    ((DynamoBlockEntity) entity).outputWattage = 0;
                }
            }
        }
        super.onNeighborChange(state, level, pos, neighbor);
    }
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof DynamoBlockEntity) {
            ((DynamoBlockEntity) entity).outputWattage = 0;
            entity.setChanged();
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof DynamoBlockEntity) {
                player.sendSystemMessage(Component.literal("Output Wattage: " + ((DynamoBlockEntity) entity).getOutput()));
            }
        }
        return InteractionResult.SUCCESS;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DynamoBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.DYNAMO.get(),
                DynamoBlockEntity::tick);
    }
}
