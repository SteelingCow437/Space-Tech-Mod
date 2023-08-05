package com.net.spacetechmod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
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
        public void addDurability(TickEvent.PlayerTickEvent event) {
            if (event.phase == TickEvent.Phase.END || event.player.level().isClientSide()) return;

            time++;
            if (time < 20) return;

            time = 0;
            EquipmentSlot[] slots = EquipmentSlot.values();
            for (EquipmentSlot slot : slots) {
                ItemStack itemStack = event.player.getItemBySlot(slot);
                if (itemStack.isEmpty() || itemStack.getEnchantmentLevel(ModEnchantments.MAGIC_REPAIR.get()) <= 0 || itemStack.getDamageValue() <= 0 || event.player.experienceLevel <= 0)
                    continue;
                itemStack.setDamageValue(itemStack.getDamageValue() - 1);
                event.player.giveExperiencePoints(-1);
            }
        }
    }


    @Override
    public int getMaxLevel() {
        return 1;
    }
}
