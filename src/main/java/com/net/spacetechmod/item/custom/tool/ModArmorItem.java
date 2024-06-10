package com.net.spacetechmod.item.custom.tool;

import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.util.ModLists;
import com.net.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.ITeleporter;
import net.neoforged.neoforge.registries.DeferredHolder;


public class ModArmorItem extends ArmorItem {

    public ModArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties settings) {
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

        if(helmet.getMaterial().value() == material && breastplate.getMaterial().value() == material &&
                leggings.getMaterial().value() == material && boots.getMaterial().value() == material) {
            fullSetMaterial = helmet.getMaterial().value();
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
            if (hasFullSuitOfArmorOn(player)) {
                for(int i = 0; i < ModLists.ARMOR_MATERIAL_INDEX.size(); ++i) {
                    if(hasSameSetOfArmorOn(ModLists.ARMOR_MATERIAL_INDEX.get(i).value(), player)) {
                        setEffects(player, world);
                        break;
                    }
                }
            }
        }
    }

    public void setEffects(Player player, Level world) {
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
                if(!ModLists.SAFE_BREATHING_LIST.contains(player.level().dimension())) {
                    spaceSuit(player);
                }
            }
        }
    }

    //add methods for set bonuses here!
    private void spaceSuit(Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT.getDelegate(), 40, 0));
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

