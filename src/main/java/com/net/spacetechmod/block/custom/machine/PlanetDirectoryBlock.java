package com.net.spacetechmod.block.custom.machine;

import com.mojang.serialization.MapCodec;
import com.net.spacetechmod.block.entity.machine.PlanetDirectoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class PlanetDirectoryBlock extends BaseEntityBlock {

    private static final IntegerProperty SELECTED_PLANET = IntegerProperty.create("selected_planet", 0, 1);
    //change this every time a new planet is added!

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
        builder.add(SELECTED_PLANET);
    }

    public PlanetDirectoryBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(SELECTED_PLANET, selectedPlanet));
    }

    public static final MapCodec<PlanetDirectoryBlock> CODEC = simpleCodec(PlanetDirectoryBlock::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    BlockEntity entity;
    public int selectedPlanet = 0;

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        entity = level.getBlockEntity(pos);
        if(entity instanceof PlanetDirectoryBlockEntity) {
            ((PlanetDirectoryBlockEntity) entity).useItem(stack, player);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult resp_60508_ult) {
        entity = level.getBlockEntity(pos);
        if(entity instanceof PlanetDirectoryBlockEntity) {
            ((PlanetDirectoryBlockEntity) entity).useWithoutItem(player);
            selectedPlanet = ((PlanetDirectoryBlockEntity) entity).getSelectedPlanet();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PlanetDirectoryBlockEntity(pos, state);
    }
}