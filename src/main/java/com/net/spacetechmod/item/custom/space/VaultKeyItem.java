package com.net.spacetechmod.item.custom.space;

import net.minecraft.world.item.Item;

public class VaultKeyItem extends Item {
    public boolean bossKey;

    public VaultKeyItem(boolean isBossKey) {
        super(new Properties()
                .stacksTo(64)
                .fireResistant());
        bossKey = isBossKey;
    }
}
