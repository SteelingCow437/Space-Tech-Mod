package com.net.spacetechmod.block.custom.machine;

import com.mojang.serialization.MapCodec;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.block.entity.machine.AirMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class AirMachineBlock extends BaseEntityBlock {
    public AirMachineBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<AirMachineBlock> CODEC = simpleCodec(AirMachineBlock::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AirMachineBlockEntity(pos, state);
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
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof AirMachineBlockEntity) {
            if(player.getMainHandItem().getBurnTime(RecipeType.SMELTING) > 0) {
                ((AirMachineBlockEntity) entity).addFuel(player);
            }
            else {
                ((AirMachineBlockEntity) entity).getTimeRemaining(player);
            }
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.AIR_MACHINE.get(), AirMachineBlockEntity::tick);
    }

}
