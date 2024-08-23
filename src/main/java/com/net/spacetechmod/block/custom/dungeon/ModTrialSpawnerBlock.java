package com.net.spacetechmod.block.custom.dungeon;

import com.mojang.serialization.MapCodec;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.block.entity.dungeon.ModTrialSpawnerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ModTrialSpawnerBlock extends BaseEntityBlock {

    public ModTrialSpawnerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(BOSS, boss));
    }

    private static final BooleanProperty BOSS = BooleanProperty.create("boss");

    public boolean boss = false;

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
        builder.add(BOSS);
    }

    public static final MapCodec<ModTrialSpawnerBlock> CODEC = simpleCodec(ModTrialSpawnerBlock::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof ModTrialSpawnerBlockEntity) {
            if(!player.isShiftKeyDown() && !player.getAbilities().instabuild) {
                ((ModTrialSpawnerBlockEntity) entity).tryStartBattle();
                return InteractionResult.SUCCESS;
            }
            else if(player.getAbilities().instabuild && player.isShiftKeyDown()) {
                ((ModTrialSpawnerBlockEntity) entity).setMob();
            }
            else if(player.getAbilities().instabuild && !player.isShiftKeyDown()) {
                ((ModTrialSpawnerBlockEntity) entity).setBoss();
            }
        }
        return InteractionResult.FAIL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModTrialSpawnerBlockEntity(pos, state);
    }

    public void setState(BlockPos pos, BlockState state, Level level, boolean active) {
        level.setBlock(pos, state.setValue(BOSS, boss), 1);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.MOD_TRIAL_SPAWNER.get(),
                ModTrialSpawnerBlockEntity::tick);
    }
}
