package com.net.spacetechmod.util;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ModAttributeModifiers {

    /*
    A collection of every attribute modifer that I create. Probably didn't need its own class, but I like to
    overcomplicate everything.
    Who knows, I might end up needing this class later on.
     */

    //Gravity modifiers
    public static final AttributeModifier MOON_GRAVITY = new AttributeModifier("LUNAR_SURFACE_GRAVITY", -0.066, AttributeModifier.Operation.ADD_VALUE);

    public static final AttributeModifier MARS_GRAVITY = new AttributeModifier("MARS_SURFACE_GRAVITY", -0.049, AttributeModifier.Operation.ADD_VALUE);

}
