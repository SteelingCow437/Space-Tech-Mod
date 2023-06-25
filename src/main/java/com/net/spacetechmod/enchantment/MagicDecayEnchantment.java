package com.net.spacetechmod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class MagicDecayEnchantment extends Enchantment {

    public MagicDecayEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class DurabilityRemovalHandler {
        @SubscribeEvent
        public static void takeDurability(TickEvent.PlayerTickEvent event){
            if (event.phase == TickEvent.Phase.END || event.player.level().isClientSide()) return;
            if(event.player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get()) > 0) {
                event.player.getItemBySlot(EquipmentSlot.HEAD).setDamageValue(event.player.getItemBySlot(EquipmentSlot.HEAD).getDamageValue() + 1);
                if(event.player.getItemBySlot(EquipmentSlot.HEAD).getDamageValue() > event.player.getItemBySlot(EquipmentSlot.HEAD).getMaxDamage()) {
                    event.player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                }
            }
            if(event.player.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get()) > 0) {
                event.player.getItemBySlot(EquipmentSlot.CHEST).setDamageValue(event.player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() + 1);
                if(event.player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() > event.player.getItemBySlot(EquipmentSlot.CHEST).getMaxDamage()) {
                    event.player.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
                }
            }
            if(event.player.getItemBySlot(EquipmentSlot.LEGS).getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get()) > 0) {
                event.player.getItemBySlot(EquipmentSlot.LEGS).setDamageValue(event.player.getItemBySlot(EquipmentSlot.LEGS).getDamageValue() + 1);
                if(event.player.getItemBySlot(EquipmentSlot.LEGS).getDamageValue() > event.player.getItemBySlot(EquipmentSlot.LEGS).getMaxDamage()) {
                    event.player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
                }
            }
            if(event.player.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get()) > 0) {
                event.player.getItemBySlot(EquipmentSlot.FEET).setDamageValue(event.player.getItemBySlot(EquipmentSlot.FEET).getDamageValue() + 1);
                if(event.player.getItemBySlot(EquipmentSlot.FEET).getDamageValue() > event.player.getItemBySlot(EquipmentSlot.FEET).getMaxDamage()) {
                    event.player.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
                }
            }
            if(event.player.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get()) > 0) {
                event.player.getItemBySlot(EquipmentSlot.MAINHAND).setDamageValue(event.player.getItemBySlot(EquipmentSlot.MAINHAND).getDamageValue() + 1);
                if(event.player.getItemBySlot(EquipmentSlot.MAINHAND).getDamageValue() > event.player.getItemBySlot(EquipmentSlot.MAINHAND).getMaxDamage()) {
                    event.player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
            }
            if(event.player.getItemBySlot(EquipmentSlot.OFFHAND).getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get()) > 0) {
                event.player.getItemBySlot(EquipmentSlot.OFFHAND).setDamageValue(event.player.getItemBySlot(EquipmentSlot.OFFHAND).getDamageValue() + 1);
                if(event.player.getItemBySlot(EquipmentSlot.OFFHAND).getDamageValue() > event.player.getItemBySlot(EquipmentSlot.OFFHAND).getMaxDamage()) {
                    event.player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
