package com.net.spacetechmod.world.dimension;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {

    public static final ResourceKey<Level> MOON =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "moon"));

    public static final ResourceKey<DimensionType> MOON_TYPE = register("moon_type");




    private static ResourceKey<DimensionType> register(String register) {
        return ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, register));
    }
}
