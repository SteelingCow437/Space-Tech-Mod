package com.spacetechmod.world.noise;

import com.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class ModNoiseSettings {

    public static final ResourceKey<NoiseGeneratorSettings> MOON_NOISE =
            register("moon_noise");

    private static ResourceKey<NoiseGeneratorSettings> register(String name) {
        return ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, name));
    }

}
