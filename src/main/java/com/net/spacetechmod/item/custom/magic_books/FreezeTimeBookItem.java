package com.net.spacetechmod.item.custom.magic_books;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class FreezeTimeBookItem extends Item {

    public FreezeTimeBookItem() {
        super(new Properties()
                .rarity(Rarity.EPIC)
                .stacksTo(1));
    }
}
