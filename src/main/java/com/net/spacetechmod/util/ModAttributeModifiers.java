package com.net.spacetechmod.util;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ModAttributeModifiers {

    /*
    A collection of every attribute modifer that I create. Probably didn't need its own class, but I like to
    overcomplicate everything.
    Who knows, I might end up needing this class later on.
     */

    //Gravity modifiers
    public static final AttributeModifier MOON_GRAVITY = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "lunar_surface_gravity"), -0.066, AttributeModifier.Operation.ADD_VALUE);

    public static final AttributeModifier MARS_GRAVITY = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "mars_surface_gravity"), -0.049, AttributeModifier.Operation.ADD_VALUE);

}
