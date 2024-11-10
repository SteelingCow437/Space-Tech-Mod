package com.spacetechmod.world.biome;

import com.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {

    //Register biomes here!
    public static final ResourceKey<Biome> MOON_BIOME = register("moon_biome");

    private static ResourceKey<Biome> register(String register) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, register));
    }
}
