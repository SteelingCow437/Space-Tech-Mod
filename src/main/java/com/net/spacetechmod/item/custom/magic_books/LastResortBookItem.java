package com.net.spacetechmod.item.custom.magic_books;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class LastResortBookItem extends Item {

    public LastResortBookItem() {
        super(new Properties()
                .rarity(Rarity.RARE)
                .stacksTo(1));
    }
}
