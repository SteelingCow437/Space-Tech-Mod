package com.spacetechmod.client;

import com.spacetechmod.client.renderer.ModDimensionSpecialEffects;
import com.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.client.main.Main;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;

public class ClientHandler {

    @SubscribeEvent
    public static void dimensionEffects(RegisterDimensionSpecialEffectsEvent event){
        event.register(ModDimensions.MOON_TYPE.location(), new ModDimensionSpecialEffects.MoonEffects());
    }
}
