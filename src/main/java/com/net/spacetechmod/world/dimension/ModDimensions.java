package com.net.spacetechmod.world.dimension;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {

    public static final ResourceKey<Level> SCULKDIM =
            ResourceKey.create(Registries.DIMENSION, new ResourceLocation(Spacetechmod.MOD_ID, "sculkdim"));
    public static final ResourceKey<DimensionType> SCULKDIM_TYPE = register("sculkdim_type");

    private static ResourceKey<DimensionType> register(String register) {
        return ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(Spacetechmod.MOD_ID, register));
    }
}
