package com.spacetechmod.block.custom.dungeon;

import com.mojang.serialization.MapCodec;
import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.block.entity.dungeon.StarGateCoreBlockEntity;
import com.spacetechmod.item.ModItems;
import com.spacetechmod.item.custom.space.StarGateControllerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

public class StarGateCoreBlock extends BaseEntityBlock {

    public StarGateCoreBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<StarGateCoreBlock> CODEC = simpleCodec(StarGateCoreBlock::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    private int x = 0;
    private int y = 0;
    private int z = 0;
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity entity = level.getBlockEntity(pos);
        Item item = stack.getItem();
        if(!level.isClientSide) {
        if(entity instanceof StarGateCoreBlockEntity) {
            if (item == ModItems.STARGATE_CONTROLLER.get() && !((StarGateCoreBlockEntity) entity).ACTIVE) {
                if (((StarGateCoreBlockEntity) entity).checkForCompletion(player)) {
                    x = ((StarGateControllerItem) item).getX();
                    y = ((StarGateControllerItem) item).getY();
                    z = ((StarGateControllerItem) item).getZ();
                    ((StarGateCoreBlockEntity) entity).setDestination(x, y, z);
                    ((StarGateCoreBlockEntity) entity).makePortalOnDirection();
                    level.playSound(player, player.getOnPos(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 2.0f, 2.0f);
                }
            } else if (item == Items.NETHER_STAR) {
                stack.shrink(1);
                player.addItem(new ItemStack(ModItems.STARGATE_CONTROLLER.get(), 1));
            } else {
                ((StarGateCoreBlockEntity) entity).breakPortalOnDirection();
                level.playSound(player, player.getOnPos(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 2.0f, 2.0f);
            }
        }
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof StarGateCoreBlockEntity) {
            ((StarGateCoreBlockEntity) entity).breakPortalOnDirection();
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StarGateCoreBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.STARGATE_CORE.get(),
                StarGateCoreBlockEntity::tick);
    }
}
