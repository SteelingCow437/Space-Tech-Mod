package com.net.spacetechmod.world.biome;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerraBlender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "overworld"), 5));
    }
}
