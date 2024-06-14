package com.net.spacetechmod.block.custom.fluid;

import com.mojang.serialization.MapCodec;
import com.net.spacetechmod.block.entity.fluid.BasicFluidBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class IronBarrelBlock extends BaseEntityBlock {

    public IronBarrelBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<IronBarrelBlock> CODEC = simpleCodec(IronBarrelBlock::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        use(level, pos, player);
        return super.useWithoutItem(state, level, pos, player, result);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        use(level, pos, player);
        return super.useItemOn(stack, state, level, pos, player, hand, result);
    }

    public void use(Level level, BlockPos pos, Player player) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            Item item = player.getMainHandItem().getItem();
            if(entity instanceof BasicFluidBarrelBlockEntity) {
                ((BasicFluidBarrelBlockEntity) entity).onRightClick(item, player);
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BasicFluidBarrelBlockEntity(pos, state);
    }
}
