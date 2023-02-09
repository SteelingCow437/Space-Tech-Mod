package com.net.spacetechmod.item.custom;

import com.net.spacetechmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class HammerItem extends Item {
    public HammerItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        Block block = level.getBlockState(pos).getBlock();
        ItemStack offhand = player.getOffhandItem();
        if(!level.isClientSide && block == Blocks.SMITHING_TABLE && player.isHolding(ModItems.HAMMER.get())) {
            if(offhand.is(Items.IRON_INGOT)) {
                player.addItem(ModItems.IRON_POWDER.get().getDefaultInstance());
                offhand.shrink(1);
                return InteractionResult.SUCCESS;
            }
            if(offhand.is(Items.COAL) || player.getOffhandItem().is(Items.CHARCOAL)) {
                player.addItem(ModItems.CARBON_POWDER.get().getDefaultInstance());
                offhand.shrink(1);
                return InteractionResult.SUCCESS;
            }
            if(offhand.is(Items.COPPER_INGOT)) {
                player.addItem(ModItems.COPPER_POWDER.get().getDefaultInstance());
                offhand.shrink(1);
                return InteractionResult.SUCCESS;
            }
            if(offhand.is(ModItems.TIN_INGOT.get())) {
                player.addItem(ModItems.TIN_POWDER.get().getDefaultInstance());
                offhand.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }
}
