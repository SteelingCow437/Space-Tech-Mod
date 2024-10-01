package com.net.spacetechmod.world.dimension.effect;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.IDimensionSpecialEffectsExtension;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class MoonEffects extends DimensionSpecialEffects implements IDimensionSpecialEffectsExtension {

    public MoonEffects(float cloudLevel, boolean hasGround, SkyType skyType, boolean forceBrightnessLightmap, boolean constantAmbientLight) {
        super(Float.NaN, true, SkyType.END, false, true);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 vec3, float v) {
        return vec3;
    }

    @Override
    public boolean isFoggyAt(int i, int i1) {
        return false;
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f modelViewMatrix, Matrix4f projectionMatrix) {
        return false;
    }
}
