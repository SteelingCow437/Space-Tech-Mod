package com.net.spacetechmod.item.custom.magic_books;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class FreezeBookItem extends Item {
    public FreezeBookItem() {
        super(new Properties()
                .rarity(Rarity.RARE)
                .stacksTo(1));
    }
}
