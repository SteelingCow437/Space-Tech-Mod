package com.spacetechmod.client.renderer;

import com.spacetechmod.Spacetechmod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class ModDimensionSpecialEffects {

    private final Minecraft minecraft = Minecraft.getInstance();

    private static final ResourceLocation SUN_LOCATION = ResourceLocation.withDefaultNamespace("textures/environment/sun.png");
    private static final ResourceLocation EARTH_LOCATION = ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "textures/environment/moon/earth.png");
    private static final ResourceLocation SKY_LOCATION = ResourceLocation.withDefaultNamespace("end_sky");

    @OnlyIn(Dist.CLIENT)
    public static class MoonEffects extends DimensionSpecialEffects {
        public MoonEffects() {
            super(Float.NaN, false, SkyType.NONE, true, false);
        }

        public Vec3 getBrightnessDependentFogColor(Vec3 vec3, float i) {
            return vec3.scale(0.15F);
        }

        public boolean isFoggyAt(int i, int ii) {
            return false;
        }

        @Nullable
        public float[] getSunriseColor(float i, float ii) {
            return null;
        }
    }
}
