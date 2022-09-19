package com.net.spacetechmod.world.feature;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Spacetechmod.MOD_ID);

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
        public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
            return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
        }

        public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
            return orePlacement(CountPlacement.of(p_195344_), p_195345_);
        }

        public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
            return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
        }

        public static final RegistryObject<PlacedFeature> TITANIUM_ORE_PLACED = PLACED_FEATURES.register("titanium_ore_placed",
                () -> new PlacedFeature(ModConfiguredFeatures.TITANIUM_ORE.getHolder().get(),
                        commonOrePlacement(12, //veins per chunk
                                HeightRangePlacement.uniform(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> AQUAMARINE_ORE_PLACED = PLACED_FEATURES.register("aquamarine_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.AQUAMARINE_ORE.getHolder().get(),
                    commonOrePlacement(5, //veins per chunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-30), VerticalAnchor.absolute(20)))));

    public static final RegistryObject<PlacedFeature> OIL_DEPOSIT_PLACED = PLACED_FEATURES.register("oil_deposit_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.OIL_DEPOSIT.getHolder().get(),
                    rareOrePlacement(1, //veins per chunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75)))));
}
