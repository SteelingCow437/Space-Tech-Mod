package com.net.spacetechmod.block.custom.machine;

import com.mojang.serialization.MapCodec;
import com.net.spacetechmod.block.entity.machine.ForgingTableBlockEntity;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
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

    private static final IntegerProperty STAMP = IntegerProperty.create("stamp", 0, 2);

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
        use(state, level, pos, player);
        return super.useWithoutItem(state, level, pos, player, result);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        use(state, level, pos, player);
        return super.useItemOn(stack, state, level, pos, player, hand, result);
    }

    public void use(BlockState state, Level level, BlockPos pos, Player player) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof ForgingTableBlockEntity) {
            if(player.getMainHandItem().getItem() == ModItems.HAMMER.get()) {
                ((ForgingTableBlockEntity) entity).craft(player);
            }
            else if(player.getMainHandItem() == ItemStack.EMPTY) {
                ((ForgingTableBlockEntity) entity).removeItem(player);
            }
            else {
                ((ForgingTableBlockEntity) entity).setStamp(player.getMainHandItem().getItem(), player);
                ((ForgingTableBlockEntity) entity).addIngredient(player.getMainHandItem().getItem(), player, player.getMainHandItem().getCount());
            }
            stampNumber = ((ForgingTableBlockEntity) entity).getStamp();
            level.setBlock(pos, state.setValue(STAMP, ((ForgingTableBlockEntity) entity).getStamp()), 1);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ForgingTableBlockEntity(pos, state);
    }
}
