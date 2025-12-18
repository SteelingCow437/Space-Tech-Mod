package com.astronautica.world.noise;

import com.astronautica.Astronautica;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class ModNoiseSettings {

    public static final ResourceKey<NoiseGeneratorSettings> MOON_NOISE =
            register("moon_noise");

    private static ResourceKey<NoiseGeneratorSettings> register(String name) {
        return ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(Astronautica.MOD_ID, name));
    }

}
