package com.net.spacetechmod.item.custom.tool;

import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HammerItem extends Item {
    public HammerItem() {
        super(new Properties()
                .stacksTo(64)
                .rarity(Rarity.COMMON));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        Block block = level.getBlockState(pos).getBlock();
        ItemStack offhand = player.getOffhandItem();
        Item item = offhand.getItem();
        if(!level.isClientSide) {
            if(block == Blocks.SMITHING_TABLE && player.isShiftKeyDown()) {
                switch(ModLists.HAMMER_INGREDIENT_LIST.indexOf(item)) {
                    case 0 -> {player.addItem(ModItems.IRON_POWDER.get().getDefaultInstance()); offhand.shrink(1); return InteractionResult.SUCCESS;}
                    case 1, 2 -> {player.addItem(ModItems.CARBON_POWDER.get().getDefaultInstance()); offhand.shrink(1); return InteractionResult.SUCCESS;}
                    case 3 -> {player.addItem(ModItems.COPPER_POWDER.get().getDefaultInstance()); offhand.shrink(1); return InteractionResult.SUCCESS;}
                    case 4 -> {player.addItem(ModItems.TIN_POWDER.get().getDefaultInstance()); offhand.shrink(1); return InteractionResult.SUCCESS;}
                    default -> {return InteractionResult.FAIL;}
                }
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        component.add(Component.literal("A single-use wooden hammer, perfect for smashing things into different shapes!"));
    }
}
