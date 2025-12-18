package com.astronautica.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.astronautica.Astronautica;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class MoonDimensionSpecialEffects extends DimensionSpecialEffects {

    private final Minecraft minecraft = Minecraft.getInstance();

    private static final ResourceLocation SUN_LOCATION = ResourceLocation.fromNamespaceAndPath(Astronautica.MOD_ID,"textures/environment/moon/sun.png");
    private static final ResourceLocation EARTH_LOCATION = ResourceLocation.fromNamespaceAndPath(Astronautica.MOD_ID, "textures/environment/moon/earth.png");
    private static final ResourceLocation SKY_LOCATION = ResourceLocation.fromNamespaceAndPath(Astronautica.MOD_ID, "textures/environment/moon/moon_sky.png");

    public MoonDimensionSpecialEffects() {
        super(Float.NaN, true, SkyType.END, false, true);
    }

    public Vec3 getBrightnessDependentFogColor(Vec3 color, float brightness) {
        return Vec3.ZERO;
    }

    public boolean isFoggyAt(int pX, int pY) {
        return false;
    }

    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        PoseStack posestack = new PoseStack();
        posestack.mulPose(modelViewMatrix);
        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, SKY_LOCATION);
        Tesselator tesselator = Tesselator.getInstance();

        for (int i = 0; i < 6; i++) {
            posestack.pushPose();
            if (i == 1) posestack.mulPose(Axis.XP.rotationDegrees(90.0F));
            if (i == 2) posestack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            if (i == 3) posestack.mulPose(Axis.XP.rotationDegrees(180.0F));
            if (i == 4) posestack.mulPose(Axis.ZP.rotationDegrees(90.0F));
            if (i == 5) posestack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
            Matrix4f matrix4f = posestack.last().pose();
            BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.addVertex(matrix4f, -100.0F, -100.0F, -100.0F).setUv(0.0F, 0.0F).setColor(-14145496);
            bufferbuilder.addVertex(matrix4f, -100.0F, -100.0F, 100.0F).setUv(0.0F, 16.0F).setColor(-14145496);
            bufferbuilder.addVertex(matrix4f, 100.0F, -100.0F, 100.0F).setUv(16.0F, 16.0F).setColor(-14145496);
            bufferbuilder.addVertex(matrix4f, 100.0F, -100.0F, -100.0F).setUv(16.0F, 0.0F).setColor(-14145496);
            BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
            posestack.popPose();
        }

        RenderSystem.depthMask(false); // fixed it!
        RenderSystem.disableBlend();
        posestack.pushPose();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        posestack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        posestack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 120.0F));
        Matrix4f matrix4f1 = posestack.last().pose();
        float f12 = 30.0F;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, SUN_LOCATION);
        BufferBuilder bufferbuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder1.addVertex(matrix4f1, -f12, 100.0F, -f12).setUv(0.0F, 0.0F);
        bufferbuilder1.addVertex(matrix4f1, f12, 100.0F, -f12).setUv(1.0F, 0.0F);
        bufferbuilder1.addVertex(matrix4f1, f12, 100.0F, f12).setUv(1.0F, 1.0F);
        bufferbuilder1.addVertex(matrix4f1, -f12, 100.0F, f12).setUv(0.0F, 1.0F);
        BufferUploader.drawWithShader(bufferbuilder1.buildOrThrow());
        RenderSystem.setShaderTexture(0, EARTH_LOCATION);
        bufferbuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder1.addVertex(matrix4f1, -f12, -100.0F, f12).setUv(0.0F, 0.0F);
        bufferbuilder1.addVertex(matrix4f1, f12, -100.0F, f12).setUv(1.0F, 0.0F);
        bufferbuilder1.addVertex(matrix4f1, f12, -100.0F, -f12).setUv(1.0F, 1.0F);
        bufferbuilder1.addVertex(matrix4f1, -f12, -100.0F, -f12).setUv(0.0F, 1.0F);
        BufferUploader.drawWithShader(bufferbuilder1.buildOrThrow());
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        float starBrightness = 1.0F;
        RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
        minecraft.levelRenderer.starBuffer.bind();
        minecraft.levelRenderer.starBuffer.drawWithShader(posestack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
        VertexBuffer.unbind();
        setupFog.run();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        posestack.popPose();
        RenderSystem.depthMask(true);
        return true;
    }
}
