package com.net.spacetechmod.world.biome.surface;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.world.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class MoonSurfaceRules {

    private static final SurfaceRules.RuleSource MOON_DIRT = makeStateRule(ModBlocks.MOON_DIRT.get());
    private static final SurfaceRules.RuleSource MOON_ROCK = makeStateRule(ModBlocks.MOON_ROCK.get());

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource moonSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, MOON_DIRT), MOON_ROCK);

        return SurfaceRules.sequence
                (SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.MOON_BIOME),

                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, moonSurface)))); //what it defaults to
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
