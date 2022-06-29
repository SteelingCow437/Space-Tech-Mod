package com.net.spacetechmod.world.feature.gen;

import com.net.spacetechmod.world.feature.ModPlacedFeatures;
import com.net.spacetechmod.world.feature.ModPlacedFeatures;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraft.core.Holder;

import java.util.List;

public class ModOreGeneration {
    //add the generation of ore veins in the ore generator
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(ModPlacedFeatures.TITANIUM_ORE_PLACED);
    }
}
