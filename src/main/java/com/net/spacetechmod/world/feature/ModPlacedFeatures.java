package com.net.spacetechmod.world.feature;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Spacetechmod.MOD_ID);

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
    //declare ore vein rules below this line!
    public static final RegistryObject<PlacedFeature> TITANIUM_ORE_PLACED = PLACED_FEATURES.register("titanium_ore_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                    ModConfiguredFeatures.TITANIUM_ORE, ModOrePlacement.commonOrePlacement(12, //veins per chunk
                    BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.belowTop(121)))));
    //                                      Ore has equal chance of spawning everywhere                  min ore height                           max ore height


    public static final RegistryObject<PlacedFeature> AQUAMARINE_ORE_PLACED = PLACED_FEATURES.register("aquamarine_ore_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                    ModConfiguredFeatures.AQUAMARINE_ORE, ModOrePlacement.commonOrePlacement(4,
                    BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(31)))));
    //                                         Ore has equal chance of spawning everywhere            min ore height                           max ore height

}
