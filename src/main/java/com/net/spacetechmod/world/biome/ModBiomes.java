package com.net.spacetechmod.world.biome;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    public static final ResourceKey<Biome> SCULK_WASTES =
            ResourceKey.create(Registries.BIOME, new ResourceLocation(Spacetechmod.MOD_ID, "biome"));

}
