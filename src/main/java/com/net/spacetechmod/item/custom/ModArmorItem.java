package com.net.spacetechmod.item.custom;

import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;


public class ModArmorItem extends ArmorItem {

    private static int sculkSetCooldown = 0;

    public ModArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);
    }
    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasSameSetOfArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (!world.isClientSide()) {
            if (hasFullSuitOfArmorOn(player)) {
                //add the if statements for armor here!
                if(hasSameSetOfArmorOn(ModArmorMaterials.COPPER, player) && world.isThundering()) {
                    copperArmor(player);
                }
                if(hasSameSetOfArmorOn(ModArmorMaterials.TURTLE, player) && player.isUnderWater()) {
                    turtleMasterArmorInWater(player);
                }
                if(hasSameSetOfArmorOn(ModArmorMaterials.TURTLE, player) && !player.isUnderWater()) {
                    turtleMasterArmorOnLand(player);
                }
                if(hasSameSetOfArmorOn(ModArmorMaterials.SCULK, player)) {
                    sculkSetBonus(player);
                    sculkSetCooldown++;
                }
                if(hasSameSetOfArmorOn(ModArmorMaterials.SCULK, player) && player.experienceLevel < 100) {
                    player.giveExperiencePoints(1);
                }
            }
        }
    }

    //add methods for set bonuses here!
    private void copperArmor(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1));
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
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1));
    }
    private void sculkSetBonus(Player player) {
        ItemStack stack = ModItems.COPPER_SWORD.get().getDefaultInstance();
        if(sculkSetCooldown >= 600 && player.isHolding(Items.STICK) && player.experienceLevel >= 20) {
            sculkSetCooldown = 0;
            player.addItem(stack);
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 9));
            player.level.explode(player, player.getX(), player.getY(), player.getZ(), 5.0f, Explosion.BlockInteraction.NONE);
            player.giveExperienceLevels(-20);
        }

    }
}

