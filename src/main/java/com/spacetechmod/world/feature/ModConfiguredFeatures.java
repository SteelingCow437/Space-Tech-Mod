package com.spacetechmod.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.spacetechmod.Spacetechmod;
import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.util.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    //ores
    public static final ResourceKey<ConfiguredFeature<?, ?>> TITANIUM_ORE_KEY = registerKey("titanium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AQUAMARINE_ORE_KEY = registerKey("aquamarine_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = registerKey("tin_ore");

    //moon ores
    public static final ResourceKey<ConfiguredFeature<?, ?>> ENRICHED_STONE_KEY = registerKey("enriched_stone");

    //ore configs
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TITANIUM_ORE = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.TITANIUM_ORE.get().defaultBlockState()),
                    OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.TITANIUM_ORE_DEEPSLATE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> AQUAMARINE_ORE = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.AQUAMARINE_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TIN_ORE = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.TIN_ORE.get().defaultBlockState()),
                    OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.TIN_ORE_DEEPSLATE.get().defaultBlockState())));

    //moon ore config
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_ENRICHED_STONE = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(new TagMatchTest(ModTags.ModBlockTags.MOON_STONE_REPLACEABLES), ModBlocks.ENRICHED_MOON_ROCK.get().defaultBlockState())));


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        //ores
        register(context, TITANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_TITANIUM_ORE.get(), 8)); //size of veins
        register(context, AQUAMARINE_ORE_KEY, Feature.ORE, new OreConfiguration(AQUAMARINE_ORE.get(), 4)); //size of veins
        register(context, TIN_ORE_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_TIN_ORE.get(), 11)); //size of veins
        //moon ores
        register(context, ENRICHED_STONE_KEY, Feature.ORE, new OreConfiguration(MOON_ENRICHED_STONE.get(), 16)); //size of veins

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
