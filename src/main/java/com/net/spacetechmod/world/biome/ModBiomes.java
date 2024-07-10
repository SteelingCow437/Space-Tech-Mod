package com.net.spacetechmod.world.biome;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {

    //Register biomes here!
    public static final ResourceKey<Biome> MOON_BIOME = register("moon_biome");

    private static ResourceKey<Biome> register(String register) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, register));
    }
}
