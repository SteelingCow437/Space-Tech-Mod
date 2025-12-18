package com.astronautica.item.custom.space;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class PlanetKeyItem extends Item {
    private ResourceKey<Level> destinationPlanet;

    public PlanetKeyItem(ResourceKey<Level> planet) {
        super(new Properties()
                .stacksTo(1)
                .fireResistant());
        destinationPlanet = planet;
    }

    public ResourceKey<Level> getDestination() {
        return destinationPlanet;
    }
}