package com.net.spacetechmod.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;


public class ModPlacedFeatures {
    //declare ore vein rules below this line!
    public static final Holder<PlacedFeature> TITANIUM_ORE_PLACED = PlacementUtils.register("titanium_ore_placed",
            ModConfiguredFeatures.TITANIUM_ORE, ModOrePlacement.commonOrePlacement(12, //Veins Per Chunk
                    BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(120))));
    //           Ore has equal chance of spawning everywhere                                            min ore height                           max ore height


    public static final Holder<PlacedFeature> AQUAMARINE_ORE_PLACED = PlacementUtils.register("aquamarine_ore_placed",
            ModConfiguredFeatures.AQUAMARINE_ORE, ModOrePlacement.commonOrePlacement(4, //Veins Per Chunk
                    BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(30))));
    //           Ore has equal chance of spawning everywhere                                           min ore height                           max ore height

}
