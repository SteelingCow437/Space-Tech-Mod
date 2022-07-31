package com.net.spacetechmod;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.painting.ModPaintings;
import com.net.spacetechmod.potion.ModPotions;
import com.net.spacetechmod.util.BetterBrewingRecipe;
import com.net.spacetechmod.world.feature.ModConfiguredFeatures;
import com.net.spacetechmod.world.feature.ModPlacedFeatures;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Spacetechmod.MOD_ID)
public class Spacetechmod {

    public static final String MOD_ID = "spacetechmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Spacetechmod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);

        // Register the eventbus for all of the items and such
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModPotions.register(eventBus);
        ModPaintings.register(eventBus);
        ModEffects.register(eventBus);
        ModPlacedFeatures.register(eventBus);
        ModConfiguredFeatures.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            //declare all potion recipes under this line
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.THICK,
                    ModItems.LEAN.get(), ModPotions.LEAN_2.get()));


            //And above this line
        });

    }
}