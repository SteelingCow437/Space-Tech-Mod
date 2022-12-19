package com.net.spacetechmod;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.enchantment.ModEnchantments;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.painting.ModPaintings;
import com.net.spacetechmod.potion.ModPotions;
import com.net.spacetechmod.util.BetterBrewingRecipe;
import com.net.spacetechmod.villager.ModPOIs;
import com.net.spacetechmod.world.dimension.ModDimensions;
import com.net.spacetechmod.world.feature.ModConfiguredFeatures;
import com.net.spacetechmod.world.feature.ModPlacedFeatures;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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
@SuppressWarnings("ALL")
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
        ModDimensions.register();
        ModEnchantments.register(eventBus);

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

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.STRENGTH,
                    ModItems.SOUL_CRYSTAL.get(), ModPotions.SOUL_CHARGE.get()));


            //And above this line
        });

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SCULKDIM_PORTAL.get(), RenderType.translucent());
        }
    }



}