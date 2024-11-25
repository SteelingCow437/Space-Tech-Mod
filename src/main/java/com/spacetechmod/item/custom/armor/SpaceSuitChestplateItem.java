package com.spacetechmod.item.custom.armor;

import com.spacetechmod.item.ModArmorMaterials;
import com.spacetechmod.item.custom.tool.ModArmorItem;
import com.spacetechmod.util.ModLists;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpaceSuitChestplateItem extends ModArmorItem {

    public SpaceSuitChestplateItem() {
        super(ModArmorMaterials.SPACESUIT, Type.CHESTPLATE, new Properties().fireResistant()
                .durability(ArmorItem.Type.CHESTPLATE.getDurability(28)));
    }

    public ResourceKey<Level> selectedPlanet;

    public ResourceKey<Level> getSelectedPlanet() {
        if(selectedPlanet == null) {
            return ModLists.PLANET_LIST.get(0);
        }
        return selectedPlanet;
    }

    public String getPlanetNames() {
        switch(ModLists.PLANET_LIST.indexOf(selectedPlanet)) {
            case 0 -> {
                return "Overworld / Earth";
            }

            case 1 -> {
                return "The Moon";
            }
        }
        return "Yet to be selected!";
    }

    @Override
   public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("Destination: " + getPlanetNames()));
        super.appendHoverText(stack, context, list, flag);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }
}
