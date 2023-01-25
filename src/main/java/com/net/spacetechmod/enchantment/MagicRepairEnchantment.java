package com.net.spacetechmod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class MagicRepairEnchantment extends Enchantment {
    public MagicRepairEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
    static int time = 0;

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class DurabilityIncreaseHandler {
        @SubscribeEvent
        public static void addDurability(TickEvent.PlayerTickEvent event){
            if (event.phase == TickEvent.Phase.END || event.player.level.isClientSide()) return;
            if(time >= 20) {
                if(event.player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.HEAD).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.HEAD).setDamageValue(event.player.getItemBySlot(EquipmentSlot.HEAD).getDamageValue() - 1);
                    event.player.giveExperiencePoints(-1);
                }
                else if(event.player.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.CHEST).setDamageValue(event.player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() - 1);
                    event.player.giveExperiencePoints(-1);
                }
                else if(event.player.getItemBySlot(EquipmentSlot.LEGS).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.LEGS).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.LEGS).setDamageValue(event.player.getItemBySlot(EquipmentSlot.LEGS).getDamageValue() - 1);
                    event.player.giveExperiencePoints(-1);
                }
                else if(event.player.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.FEET).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.FEET).setDamageValue(event.player.getItemBySlot(EquipmentSlot.FEET).getDamageValue() - 1);
                    event.player.giveExperiencePoints(-1);
                }
                else if(event.player.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.MAINHAND).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.MAINHAND).setDamageValue(event.player.getItemBySlot(EquipmentSlot.MAINHAND).getDamageValue() - 1);
                    event.player.giveExperiencePoints(-1);
                }
                else if(event.player.getItemBySlot(EquipmentSlot.OFFHAND).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.OFFHAND).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.OFFHAND).setDamageValue(event.player.getItemBySlot(EquipmentSlot.OFFHAND).getDamageValue() - 1);
                    event.player.giveExperiencePoints(-1);
                }
                time = 0;
            }
            else {
                time++;
            }
        }
    }


    @Override
    public int getMaxLevel() {
        return 1;
    }
}
