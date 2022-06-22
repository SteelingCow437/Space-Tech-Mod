package com.net.david.spacetechmod.world;


import com.net.david.spacetechmod.Spacetechmod;
import com.net.david.spacetechmod.world.feature.gen.ModOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spacetechmod.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        //calls the method of generating modded features in the feature generator

        //call the methods of feature generation below this line in the order of:
        // raw generation, lakes, local modifications, underground structures, surface structures,
        //strongholds, underground ores, underground decorations, fluid springs, vegetal decoration,
        //and top layer modification.

        ModOreGeneration.generateOres(event); //ore generation
    }



}
