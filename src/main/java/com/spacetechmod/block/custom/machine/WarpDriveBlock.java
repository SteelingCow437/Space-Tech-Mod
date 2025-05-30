package com.spacetechmod.block.custom.machine;

import com.mojang.serialization.MapCodec;
import com.spacetechmod.block.entity.machine.WarpDriveBlockEntity;
import com.spacetechmod.item.custom.space.WarpDriveToolItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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

public class WarpDriveBlock extends BaseEntityBlock {

    public WarpDriveBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public static final MapCodec<WarpDriveBlock> CODEC = simpleCodec(WarpDriveBlock::new);
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof WarpDriveBlockEntity) {
                if(player.isShiftKeyDown()) {
                    player.sendSystemMessage(Component.literal("Ship warping to new position!"));
                    player.sendSystemMessage(Component.literal("ship size: " + ((WarpDriveBlockEntity) entity).shipSizeX + " " + ((WarpDriveBlockEntity) entity).shipSizeY + " " + ((WarpDriveBlockEntity) entity).shipSizeZ));
                    ((WarpDriveBlockEntity) entity).warp(level);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            Item item = player.getMainHandItem().getItem();
            if(entity instanceof WarpDriveBlockEntity && !player.getMainHandItem().isEmpty() && item instanceof WarpDriveToolItem) {
                int x = ((WarpDriveToolItem) item).getX();
                int y = ((WarpDriveToolItem) item).getY();
                int z = ((WarpDriveToolItem) item).getZ();
                int dir = ((WarpDriveToolItem) item).getDirectionNumber();
                if(((WarpDriveToolItem) item).getMode()) {
                    ((WarpDriveBlockEntity) entity).setParameters(x, y, z, dir);
                    player.sendSystemMessage(Component.literal("Destination set!"));
                }
                else {
                    ((WarpDriveBlockEntity) entity).setSize(x, y, z);
                    player.sendSystemMessage(Component.literal("Ship Size Changed!"));
                }
            }
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WarpDriveBlockEntity(pos, state);
    }
}
