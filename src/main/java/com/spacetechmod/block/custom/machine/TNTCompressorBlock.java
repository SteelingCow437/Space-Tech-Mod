package com.spacetechmod.block.custom.machine;

import com.mojang.serialization.MapCodec;
import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class TNTCompressorBlock extends Block {

    public TNTCompressorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(CHARGED, false));
    }

    public static final MapCodec<TNTCompressorBlock> CODEC = simpleCodec(TNTCompressorBlock::new);

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    private static final BooleanProperty CHARGED = BooleanProperty.create("charged");

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        charge = 0;
    }

    private int charge = 0;
    private static final int maxCharge = 4;

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide) {
            if (stack.getItem() == Items.TNT && stack.getCount() == 64) {
                ++charge;
                stack.shrink(64);
                if (charge >= maxCharge) {
                    level.setBlock(pos, state.setValue(CHARGED, true), 1);
                    level.scheduleTick(pos, this, 20);
                    player.addItem(new ItemStack(ModItems.KAHUNA_CHARGE.get(), 1));
                    charge -= 4;
                }
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        player.sendSystemMessage(Component.literal("Charge: " + charge + " / " + maxCharge));
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlockAndUpdate(pos, state.setValue(CHARGED, false));
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(newState.getBlock() != ModBlocks.TNT_COMPRESSOR.get()) {
            ItemEntity item = new ItemEntity(EntityType.ITEM, level);
            item.setItem(new ItemStack(Items.TNT, 64));
            item.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
            for(int i = 0; i < charge; ++i) {
                level.addFreshEntity(item);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}