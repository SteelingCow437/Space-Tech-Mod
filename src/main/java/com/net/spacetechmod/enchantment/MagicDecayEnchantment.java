package com.net.spacetechmod.enchantment;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class MagicDecayEnchantment extends Enchantment {

    private static int damageValue = 0;

    public MagicDecayEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.VANISHABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class DurabilityRemovalHandler {
        @SubscribeEvent
        public static void takeDurability(TickEvent.PlayerTickEvent event){
            if (event.phase == TickEvent.Phase.END || event.player.level.isClientSide()) return;
            if(event.player.getMainHandItem().getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get()) > 0) {
                event.player.getMainHandItem().setDamageValue(damageValue);
                damageValue++;
                if(damageValue >= event.player.getMainHandItem().getMaxDamage() && event.player.getMainHandItem().getEnchantmentLevel(ModEnchantments.MAGIC_DECAY.get()) > 0) {
                    event.player.getMainHandItem().setDamageValue(event.player.getMainHandItem().getMaxDamage());
                    event.player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    damageValue = 0;
                }
            }
        }
    }


    @Override
    public int getMaxLevel() {
        return 1;
    }


}
