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
        public static void takeDurability(TickEvent.PlayerTickEvent event) {
            if (event.phase == TickEvent.Phase.END || event.player.level().isClientSide()) return;

            EquipmentSlot[] slots = EquipmentSlot.values();
            for (EquipmentSlot slot : slots) {
                ItemStack itemStack = event.player.getItemBySlot(slot);
                if (itemStack.isEmpty()) continue;

                int enchantmentLevel = itemStack.getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get());
                if (enchantmentLevel > 0 && itemStack.isDamaged() && itemStack.getMaxDamage() > 0) {
                    itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                    if (itemStack.isDamaged() && itemStack.getDamageValue() > itemStack.getMaxDamage()) {
                        event.player.setItemSlot(slot, ItemStack.EMPTY);
                    }
                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
