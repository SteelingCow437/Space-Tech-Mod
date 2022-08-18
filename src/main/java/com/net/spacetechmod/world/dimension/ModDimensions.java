package com.net.spacetechmod.world.dimension;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
    public static final ResourceKey<Level> SDIM_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Spacetechmod.MOD_ID, "sdim"));

    public static final ResourceKey<DimensionType> SDIM_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY,
                    new ResourceLocation(Spacetechmod.MOD_ID, "sdim"));

    public static void register() {
        System.out.println("Registering ModDimensions for " + Spacetechmod.MOD_ID);
    }
}
