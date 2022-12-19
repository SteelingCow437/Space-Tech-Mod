package com.net.spacetechmod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class MagicRepairEnchantment extends Enchantment {
    public MagicRepairEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class DurabilityIncreaseHandler {
        @SubscribeEvent
        public static void takeDurability(TickEvent.PlayerTickEvent event){
            if (event.phase == TickEvent.Phase.END || event.player.level.isClientSide()) return;
            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get(), event.player);
            if(event.player.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0) {
                event.player.getItemBySlot(EquipmentSlot.CHEST).setDamageValue(-1);
                if(event.player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() > 0) {
                    event.player.getItemBySlot(EquipmentSlot.CHEST).setDamageValue(0);
                }
            }
        }
    }


    @Override
    public int getMaxLevel() {
        return 2;
    }
}
