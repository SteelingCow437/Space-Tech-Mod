package com.spacetechmod.item.custom.tool;

import com.spacetechmod.effect.ModEffects;
import com.spacetechmod.util.ModLists;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class ModArmorItem extends ArmorItem {

    public ModArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties settings) {
        super(material, type, settings);
    }

    private int timer = 0;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player && !level.isClientSide() && hasFullSuitOfArmorOn(player)) {
            if(timer >= 180) {
                evaluateArmorEffects(player);
                timer = 0;
            }
            else {
                ++timer;
            }
        }
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
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }

    private boolean hasSameSetOfArmorOn(Holder<ArmorMaterial> material, Player player) {
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
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0));
    }

    private void spaceSuit(Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT, 200, 0));
    }

    private void turtleMasterArmorInWater(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 200, 1));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 1));
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, 1));
    }

    private void turtleMasterArmorOnLand(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
    }

}

