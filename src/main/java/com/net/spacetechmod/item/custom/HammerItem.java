package com.net.spacetechmod.item.custom;

import com.net.spacetechmod.item.ModCreativeModeTab;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class HammerItem extends Item {
    public HammerItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.STM_TOOLS)
                .stacksTo(1)
                .rarity(Rarity.COMMON));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if(!level.isClientSide) {
            if(player.isHolding(ModItems.HAMMER.get()) && player.getOffhandItem().is(Items.IRON_INGOT)) {
                for(int i = 0; i < player.getOffhandItem().getCount(); i++) {
                    player.addItem(ModItems.IRON_POWDER.get().getDefaultInstance());
                }
                player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            }
            if(player.isHolding(ModItems.HAMMER.get()) && player.getOffhandItem().is(Items.COAL) || player.getOffhandItem().is(Items.CHARCOAL)) {
                for(int i = 0; i < player.getOffhandItem().getCount(); i++) {
                    player.addItem(ModItems.CARBON_POWDER.get().getDefaultInstance());
                }
                player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            }
            if(player.isHolding(ModItems.HAMMER.get()) && player.getOffhandItem().is(Items.COPPER_INGOT)) {
                for(int i = 0; i < player.getOffhandItem().getCount(); i++) {
                    player.addItem(ModItems.COPPER_POWDER.get().getDefaultInstance());
                }
                player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
