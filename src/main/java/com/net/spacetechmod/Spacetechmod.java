package com.net.spacetechmod;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.fluid.ModFluidTypes;
import com.net.spacetechmod.fluid.ModFluids;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.painting.ModPaintings;
import com.net.spacetechmod.potion.ModPotions;
import com.net.spacetechmod.recipe.ModRecipes;
import com.net.spacetechmod.screen.ModMenuTypes;
import com.net.spacetechmod.screen.alloyfurnace.AlloyFurnaceScreen;
import com.net.spacetechmod.screen.burnerpress.BurnerPressScreen;
import com.net.spacetechmod.screen.machinetable.MachineTableScreen;
import com.net.spacetechmod.util.BetterBrewingRecipe;
import com.net.spacetechmod.villager.ModPOIs;
import com.net.spacetechmod.world.feature.ModConfiguredFeatures;
import com.net.spacetechmod.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
        ModPOIs.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);
        ModFluidTypes.register(eventBus);
        ModFluids.register(eventBus);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            //declare all potion recipes under this line
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.HARMING,
                    ModItems.LEAN.get(), ModPotions.LEAN_2.get()));

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.THICK,
                            Items.WHEAT, ModPotions.OIL.get()));


            //And above this line
        });

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.ALLOY_FURNACE_MENU.get(), AlloyFurnaceScreen::new);
            MenuScreens.register(ModMenuTypes.BURNER_PRESS_MENU.get(), BurnerPressScreen::new);
            MenuScreens.register(ModMenuTypes.MACHINE_TABLE_MENU.get(), MachineTableScreen::new);
        }
    }



}