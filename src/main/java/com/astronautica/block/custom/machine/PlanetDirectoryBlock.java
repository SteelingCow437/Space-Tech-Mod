package com.astronautica.block.custom.machine;

import com.mojang.serialization.MapCodec;
import com.astronautica.block.entity.machine.PlanetDirectoryBlockEntity;
import com.astronautica.data.ModDataStorage;
import com.astronautica.item.custom.armor.SpaceSuitChestplateItem;
import com.astronautica.item.custom.armor.Z7ChestplateItem;
import com.astronautica.item.custom.space.PlanetKeyItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
            entity = level.getBlockEntity(pos);
            Item item = stack.getItem();
            if (entity instanceof PlanetDirectoryBlockEntity) {
                if (item instanceof PlanetKeyItem) {
                    ((PlanetDirectoryBlockEntity) entity).unlockPlanet(((PlanetKeyItem) item).getDestination());
                    return ItemInteractionResult.SUCCESS;
                }
                if (item instanceof SpaceSuitChestplateItem || item instanceof Z7ChestplateItem) {
                    try {
                        ((SpaceSuitChestplateItem) item).selectedPlanet = ((PlanetDirectoryBlockEntity) entity).getSelectedPlanet();
                    } catch (ClassCastException e) {
                        ((Z7ChestplateItem) item).selectedPlanet = ((PlanetDirectoryBlockEntity) entity).getSelectedPlanet();
                    }
                    stack.set(ModDataStorage.SELECTED_PLANET, ((PlanetDirectoryBlockEntity) entity).getSelectedPlanet());
                    return ItemInteractionResult.SUCCESS;
                } else {
                    use(state, level, pos, player);
                }
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.FAIL;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player) {
        entity = level.getBlockEntity(pos);
        if(entity instanceof PlanetDirectoryBlockEntity) {
            ((PlanetDirectoryBlockEntity) entity).selectNewPlanet();
            selectedPlanet = ((PlanetDirectoryBlockEntity) entity).getSelectedPlanetNumber();
            level.setBlock(pos, state.setValue(SELECTED_PLANET, ((PlanetDirectoryBlockEntity) entity).getSelectedPlanetNumber()), 1);
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