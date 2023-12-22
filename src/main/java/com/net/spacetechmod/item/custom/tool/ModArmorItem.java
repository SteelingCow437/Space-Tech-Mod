package com.net.spacetechmod.item.custom.tool;

import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.util.ModLists;
import com.net.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.ITeleporter;


public class ModArmorItem extends ArmorItem {

    public ModArmorItem(ArmorMaterial material, ArmorItem.Type type, Properties settings) {
        super(material, type, settings);
    }

    public ArmorMaterial fullSetMaterial;

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

        if(helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material) {
            fullSetMaterial = helmet.getMaterial();
            return true;
        }
        else {
            fullSetMaterial = null;
            return false;
        }
    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (!world.isClientSide()) {
            if (hasFullSuitOfArmorOn(player) && hasSameSetOfArmorOn(fullSetMaterial, player)) {
                //add the if statements for armor here!
                switch(ModLists.ARMOR_MATERIAL_INDEX.indexOf(fullSetMaterial)) {
                    case 1 -> {
                        if(world.isThundering()) {
                            copperArmor(player);
                        }
                    }

                    case 2 -> {
                        if(player.isUnderWater()) {
                            turtleMasterArmorInWater(player);
                        }
                        else {
                            turtleMasterArmorOnLand(player);
                        }
                    }

                    case 3 -> {
                        if(player.experienceLevel < 100) {
                            player.giveExperiencePoints(2);
                        }
                    }

                    case 4 -> {
                        if(!ModLists.SAFE_BREATHING_LIST.contains(player.level().dimension())) {
                            spaceSuit(player);
                        }
                    }
                }
            }
        }
    }

    //add methods for set bonuses here!
    private void spaceSuit(Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT.get(), 2, 0));
    }
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
}

