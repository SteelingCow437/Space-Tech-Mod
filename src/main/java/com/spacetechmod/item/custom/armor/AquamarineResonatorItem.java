package com.spacetechmod.item.custom.armor;

import com.spacetechmod.item.ModArmorMaterials;
import com.spacetechmod.item.custom.tool.ModArmorItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class AquamarineResonatorItem extends ModArmorItem {
    public AquamarineResonatorItem() {
        super(ModArmorMaterials.RESONATOR, Type.CHESTPLATE, new Properties()
                .fireResistant().stacksTo(1).rarity(Rarity.RARE));
    }

    private int timer = 0;
    Item slotItem;


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(!level.isClientSide) {
            slotItem = ((Player) entity).getItemBySlot(EquipmentSlot.CHEST).getItem();
            if (slotItem instanceof AquamarineResonatorItem) {
                if (timer >= 399) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 4, false, false));
                    timer = 0;
                } else {
                    ++timer;
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("A full-body shield, brought to you by Byzanium technology!"));
        super.appendHoverText(stack, context, list, flag);
    }
}