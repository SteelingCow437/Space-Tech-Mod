package com.astronautica.item.custom.tool;

import com.astronautica.effect.ModEffects;
import com.astronautica.util.ModLists;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;


public class ModArmorItem extends ArmorItem {

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties settings) {
        super(material, type, settings);
    }

    private int timer = 0;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player && !level.isClientSide() && hasFullSuitOfArmorOn(player)) {
            if(timer >= 90) {
                evaluateArmorEffects(player);
                timer = 0;
            }
            else {
                ++timer;
            }
        }
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return true;
    }

    private void evaluateArmorEffects(Player player) {
        for(Holder<ArmorMaterial> material : ModLists.ARMOR_MATERIAL_INDEX) {
            if(hasSameSetOfArmorOn(material, player)) {
                addEffectToPlayer(player, material);
            }
        }
    }

    private void addEffectToPlayer(Player player, Holder<ArmorMaterial> material) {
        switch(ModLists.ARMOR_MATERIAL_INDEX.indexOf(material)) {
            case 1 -> {
                if(player.level().isRaining()) {
                    copperEffect(player);
                }
            }
            case 2 -> {
                if(player.isInWaterRainOrBubble()) {
                    turtleMasterArmorInWater(player);
                }
                else {
                    turtleMasterArmorOnLand(player);
                }
            }
            case 3 -> spaceSuit(player);
            case 4 -> evaluateZ7Effect(player);
        }
    }

    protected boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }

    protected boolean hasSameSetOfArmorOn(Holder<ArmorMaterial> material, Player player) {
        for(ItemStack armorStack : player.getArmorSlots()) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        if(boots.getMaterial() == material && leggings.getMaterial() == material
                && chestplate.getMaterial() == material && helmet.getMaterial() == material) {
            return true;
        }
        else {
            return false;
        }
    }

    //Lists of armor effects!
    private void copperEffect(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0, false, false));
    }

    private void spaceSuit(Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT, 100, 0, false, false));
    }

    private void turtleMasterArmorInWater(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 1, false, false));
    }

    private void turtleMasterArmorOnLand(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, false, false));
    }

    private void evaluateZ7Effect(Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT, 100, 0, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 4, false, false));
        if(player.isInWaterRainOrBubble()) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 0, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0, false, false));
        }
        if(player.isOnFire()) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0, false, false));
        }
    }
}

