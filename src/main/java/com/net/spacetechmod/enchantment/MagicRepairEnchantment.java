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
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
    static int time = 0;
    int level = getMaxLevel();

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class DurabilityIncreaseHandler {
        @SubscribeEvent
        public static void takeDurability(TickEvent.PlayerTickEvent event){
            if (event.phase == TickEvent.Phase.END || event.player.level.isClientSide()) return;
            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get(), event.player);
            if(time >= 20) {
                if(event.player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.HEAD).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.HEAD).setDamageValue(event.player.getItemBySlot(EquipmentSlot.HEAD).getDamageValue() - level);
                    event.player.giveExperiencePoints(-1);
                }
                if(event.player.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.CHEST).setDamageValue(event.player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() - level);
                    event.player.giveExperiencePoints(-1);
                }
                if(event.player.getItemBySlot(EquipmentSlot.LEGS).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.LEGS).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.LEGS).setDamageValue(event.player.getItemBySlot(EquipmentSlot.LEGS).getDamageValue() - level);
                    event.player.giveExperiencePoints(-1);
                }
                if(event.player.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.FEET).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.FEET).setDamageValue(event.player.getItemBySlot(EquipmentSlot.FEET).getDamageValue() - level);
                    event.player.giveExperiencePoints(-1);
                }
                if(event.player.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.MAINHAND).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.MAINHAND).setDamageValue(event.player.getItemBySlot(EquipmentSlot.MAINHAND).getDamageValue() - level);
                    event.player.giveExperiencePoints(-1);
                }
                if(event.player.getItemBySlot(EquipmentSlot.OFFHAND).getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) > 0 && event.player.getItemBySlot(EquipmentSlot.OFFHAND).getDamageValue() > 0 && event.player.experienceLevel > 0) {
                    event.player.getItemBySlot(EquipmentSlot.OFFHAND).setDamageValue(event.player.getItemBySlot(EquipmentSlot.OFFHAND).getDamageValue() - level);
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
        return 2;
    }
}
