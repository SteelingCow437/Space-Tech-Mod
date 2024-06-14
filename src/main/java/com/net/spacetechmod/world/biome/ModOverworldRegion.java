package com.net.spacetechmod.world.biome;

import net.minecraft.resources.ResourceLocation;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class ModOverworldRegion extends Region {

    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    /* Uncomment if you want to add any custom biomes to the overworld.
    For now, there are none, but that may change in the future.
    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FOREST, ModBiomes.MOON_BIOME);
        });
    }

     */
}
