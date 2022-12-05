package com.net.spacetechmod.item.custom.sculk;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.custom.sculk.SculkDimPortalBlock;
import com.net.spacetechmod.item.ModCreativeModeTab;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class EchoItem extends Item {
    public EchoItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.STM_ITEMS)
                .stacksTo(1)
                .rarity(Rarity.RARE));
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        if(player != null) {
            if(player.level.dimension() == ModDimensions.SCULKDIM_KEY
                    || player.level.dimension() == Level.OVERWORLD) {
                for(Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getClickedPos().relative(direction);
                    if(((SculkDimPortalBlock) ModBlocks.SCULKDIM_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos)) {
                        level.playSound(player, framePos,
                                SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.CONSUME;
                    }
                    else if(player.isShiftKeyDown()) {
                        player.giveExperienceLevels(25);
                        if(player.getOffhandItem().is(ModItems.ECHO.get())) {
                            player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                        }
                        else {
                            player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                        }
                        return InteractionResult.SUCCESS;
                    }
                    else return InteractionResult.FAIL;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
