package com.net.spacetechmod.world.biome;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;

public class ModBiomes {



    private static ResourceKey<Biome> register(String register) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Spacetechmod.MOD_ID, register));
    }
}
