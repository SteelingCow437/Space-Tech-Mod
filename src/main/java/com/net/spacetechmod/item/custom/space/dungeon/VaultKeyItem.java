package com.net.spacetechmod.item.custom.space.dungeon;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class VaultKeyItem extends Item {
    public boolean bossKey;

    public VaultKeyItem(boolean isBossKey) {
        super(new Properties()
                .stacksTo(64)
                .fireResistant());
        bossKey = isBossKey;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        if(bossKey) {
            components.add(Component.literal("This key will give you boss loot!"));
        }
        else {
            components.add(Component.literal("This key will give you normal loot!"));
        }
    }
}
