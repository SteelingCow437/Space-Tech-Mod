package com.net.spacetechmod.world.feature;

import com.net.spacetechmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;


public class ModConfiguredFeatures {
    //define ore veins (normal and deepslate) below this line!
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TITANIUM_ORE = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE_DEEPSLATE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TITANIUM_ORE = FeatureUtils.register("titanium_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TITANIUM_ORE, 5));
                                                                    //Vein size
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_AQUAMARINE_ORE = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.AQUAMARINE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.AQUAMARINE_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> AQUAMARINE_ORE = FeatureUtils.register("aquamarine_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_AQUAMARINE_ORE, 4));
                                                                    //Vein size


}
