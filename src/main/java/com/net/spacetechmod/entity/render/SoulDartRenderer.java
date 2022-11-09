package com.net.spacetechmod.entity.render;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.entity.SoulDartEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SoulDartRenderer extends ArrowRenderer<SoulDartEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Spacetechmod.MOD_ID, "textures/entity/soul_dart.png");

    public SoulDartRenderer(EntityRendererProvider.Context manager) {
        super(manager);

    }

    @Override
    public ResourceLocation getTextureLocation(SoulDartEntity entity) {
        return TEXTURE;
    }
}
