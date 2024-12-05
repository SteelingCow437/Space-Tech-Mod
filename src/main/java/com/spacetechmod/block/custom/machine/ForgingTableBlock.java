package com.spacetechmod.block.custom.machine;

import com.mojang.serialization.MapCodec;
import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.block.entity.machine.ForgingTableBlockEntity;
import com.spacetechmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.phys.Vec3;
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
        if(!level.isClientSide) {
            use(state, level, pos, player);
        }
        return super.useWithoutItem(state, level, pos, player, result);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(!level.isClientSide) {
            use(state, level, pos, player);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, result);
    }

    public void use(BlockState state, Level level, BlockPos pos, Player player) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof ForgingTableBlockEntity && !level.isClientSide) {
            if(player.isShiftKeyDown()) {
                ((ForgingTableBlockEntity) entity).removeItem(player);
            }
            else {
                if(player.getMainHandItem().getItem() == ModItems.HAMMER.get()) {
                    ((ForgingTableBlockEntity) entity).craft(player);
                }
                else {
                    ((ForgingTableBlockEntity) entity).setStamp(player.getMainHandItem().getItem(), player);
                    ((ForgingTableBlockEntity) entity).addIngredient(player);
                }
            }
            stampNumber = ((ForgingTableBlockEntity) entity).getStamp();
            level.setBlock(pos, state.setValue(STAMP, ((ForgingTableBlockEntity) entity).getStamp()), 1);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        BlockEntity entity = level.getBlockEntity(pos);
        ItemEntity stamp = new ItemEntity(EntityType.ITEM, level);
        ItemEntity ingredient = new ItemEntity(EntityType.ITEM, level);
        Block block = newState.getBlock();
        if(entity instanceof ForgingTableBlockEntity && block != ModBlocks.FORGING_TABLE.get()) {
            stamp.setPos(new Vec3(pos.getX(), pos.getY() + 1, pos.getZ()));
            ingredient.setPos(new Vec3(pos.getX(), pos.getY() + 1, pos.getZ()));
            stamp.setItem(new ItemStack(((ForgingTableBlockEntity) entity).stamp, 1));
            ingredient.setItem(((ForgingTableBlockEntity) entity).ingredient);
            level.addFreshEntity(stamp);
            level.addFreshEntity(ingredient);
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ForgingTableBlockEntity(pos, state);
    }
}
