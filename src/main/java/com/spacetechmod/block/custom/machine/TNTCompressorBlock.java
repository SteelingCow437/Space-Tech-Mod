package com.spacetechmod.block.custom.machine;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
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
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

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

    private int charge = 0;
    private static final int maxCharge = 512;
    private Level blockLevel;
    private BlockPos worldPosition;

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState newState, boolean simulate) {
        blockLevel = level;
        worldPosition = pos;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(stack.getItem() == Items.TNT) {
            if(player.isShiftKeyDown() && (charge + stack.getCount() <= maxCharge)) {
                charge += stack.getCount();
                stack.shrink(stack.getCount());
            }
            else if(charge + 1 <= maxCharge){
                stack.shrink(1);
                ++charge;
            }
            return ItemInteractionResult.SUCCESS;
        }
        if(charge == 512) {
            level.setBlock(pos, state.setValue(CHARGED, true), 1);
        }
        return ItemInteractionResult.FAIL;
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        if(!player.isShiftKeyDown()) {
            player.sendSystemMessage(Component.literal("Charge: " + charge + " / " + maxCharge));
            return InteractionResult.SUCCESS;
        }
        else if(charge > 0){
            int odd = charge % 64;
            ItemStack stack = new ItemStack(Items.TNT, 64);
            for(int i = 0; i < charge / 64; ++i) {
                player.addItem(stack);
                charge -= 64;
            }
            stack.setCount(odd);
            player.addItem(stack);
            charge = 0;
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        boolean lit = state.getValue(LIT);
        if(lit) {
            tryFireCannon();
        }
    }

    private void tryFireCannon() {
        if(charge == maxCharge) {
            blockLevel.explode(null, worldPosition.getX(), worldPosition.getY() + 1,
                    worldPosition.getZ(), 2048, Level.ExplosionInteraction.TNT);
            charge = 0;
        }
        else {
            blockLevel.playSound(null, worldPosition, SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 2.0f, 2.0f);
        }
    }


}


