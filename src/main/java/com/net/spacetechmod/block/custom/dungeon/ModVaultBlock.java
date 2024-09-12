package com.net.spacetechmod.block.custom.dungeon;

import com.mojang.serialization.MapCodec;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.block.entity.dungeon.ModVaultBlockEntity;
import com.net.spacetechmod.item.custom.space.dungeon.VaultKeyItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ModVaultBlock extends BaseEntityBlock {

    public static final MapCodec<ModVaultBlock> CODEC = simpleCodec(ModVaultBlock::new);

    public ModVaultBlock(Properties properties) {
        super(properties);
    }

    private ResourceKey<Level> DIMENSION;

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    @Override
    protected void onPlace(BlockState originState, Level level, BlockPos pos, BlockState newState, boolean bool) {
        super.onPlace(originState, level, pos, newState, bool);
        DIMENSION = level.dimension();
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof ModVaultBlockEntity) {
            Item item = player.getMainHandItem().getItem();
            boolean isKey = item instanceof VaultKeyItem;
            if(((ModVaultBlockEntity) entity).COOLDOWN == 0) {
                if(isKey) {
                    boolean isBoss = ((VaultKeyItem) item).bossKey;
                    ((ModVaultBlockEntity) entity).giveLoot(isBoss, DIMENSION);
                    return ItemInteractionResult.CONSUME;
                }
                else {
                    return ItemInteractionResult.FAIL;
                }
            }
            else {
                player.sendSystemMessage(Component.literal(
                        "Cooldown Remaining: " + ((ModVaultBlockEntity) entity).COOLDOWN / 20 + " Seconds"));
            }
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ModVaultBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.MOD_VAULT.get(),
                ModVaultBlockEntity::tick);
    }
}
