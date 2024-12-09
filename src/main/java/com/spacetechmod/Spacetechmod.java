package com.spacetechmod;

import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.data.ModDataStorage;
import com.spacetechmod.effect.ModEffects;
import com.spacetechmod.item.ModCreativeModeTab;
import com.spacetechmod.item.ModItems;
import com.spacetechmod.potion.ModPotions;
import com.spacetechmod.sound.ModSounds;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@SuppressWarnings("ALL")
@Mod(Spacetechmod.MOD_ID)
public class Spacetechmod {

    public static final String MOD_ID = "spacetechmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Spacetechmod(IEventBus eventBus, ModContainer modContainer) {
        // Register the setup method for modloading
        eventBus.addListener(this::setup);

        // Register the eventbus for all of the items and such

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModPotions.register(eventBus);
        ModEffects.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModSounds.register(eventBus);
        ModCreativeModeTab.register(eventBus);
        ModDataStorage.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(this);
        eventBus.addListener(this::addCreative);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            //declare all potion recipes under this line
             /* PotionBrewing.addRecipe(new BetterBrewingRecipe(Potions.HARMING.value(),
                    ModItems.LEAN.get(), ModPotions.LEAN_2.get()));

            PotionBrewing.addRecipe(new BetterBrewingRecipe(Potions.THICK.value(),
                            Items.WHEAT, ModPotions.OIL.get()));
            */
            //And above this line
        });

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == ModCreativeModeTab.STM_BLOCKS.get()) {
            event.accept(ModBlocks.TITANIUM_BLOCK);
            event.accept(ModBlocks.RAW_TITANIUM_BLOCK);
            event.accept(ModBlocks.TITANIUM_ORE);
            event.accept(ModBlocks.TITANIUM_ORE_DEEPSLATE);
            event.accept(ModBlocks.AQUAMARINE_ORE);
            event.accept(ModBlocks.TIN_ORE);
            event.accept(ModBlocks.TIN_ORE_DEEPSLATE);
            event.accept(ModBlocks.STARGATE_FRAME);
            event.accept(ModBlocks.STEEL_DECO_BLOCK);
        }
        if(event.getTab() == ModCreativeModeTab.STM_ITEMS.get()) {
            event.accept(ModItems.TITANIUM_INGOT);
            event.accept(ModItems.RAW_TITANIUM);
            event.accept(ModItems.TITANIUM_PLATE);
            event.accept(ModItems.AQUAMARINE);
            event.accept(ModItems.BLANK_STAMP);
            event.accept(ModItems.PLATE_STAMP);
            event.accept(ModItems.WIRE_STAMP);
            event.accept(ModItems.STEEL_INGOT);
            event.accept(ModItems.STEEL_PLATE);
            event.accept(ModItems.COPPER_PLATE);
            event.accept(ModItems.COPPER_WIRING);
            event.accept(ModItems.COPPER_POWDER);
            event.accept(ModItems.CARBON_POWDER);
            event.accept(ModItems.IRON_POWDER);
            event.accept(ModItems.TITANIUM_POWDER);
            event.accept(ModItems.STEEL_BLEND);
            event.accept(ModItems.COPPER_REDSTIDE_BLEND);
            event.accept(ModItems.COPPER_REDSTIDE_INGOT);
            event.accept(ModItems.TIN_CAN);
            event.accept(ModItems.TIN_INGOT);
            event.accept(ModItems.RAW_TIN);
            event.accept(ModItems.TIN_POWDER);
            event.accept(ModItems.BRONZE_BLEND);
            event.accept(ModItems.BRONZE_INGOT);
            event.accept(ModItems.BRONZE_PLATE);
            event.accept(ModItems.IRON_PLATE);
            event.accept(ModItems.COPPER_REDSTIDE_WIRING);
            event.accept(ModItems.TITAN_STEEL_BLEND);
            event.accept(ModItems.TITAN_STEEL_INGOT);
            event.accept(ModItems.KAHUNA_CHARGE);
            event.accept(ModItems.KAHUNA_SHELL);
            event.accept(ModItems.BIG_KAHUNA);
            event.accept(ModItems.MOON_KEY);
            event.accept(ModItems.ORBITAL_CASING);
        }
        if(event.getTab() == ModCreativeModeTab.STM_TOOLS.get()) {
            event.accept(ModItems.TITANIUM_SWORD);
            event.accept(ModItems.TITANIUM_PICKAXE);
            event.accept(ModItems.TITANIUM_AXE);
            event.accept(ModItems.TITANIUM_SHOVEL);
            event.accept(ModItems.TITANIUM_HOE);
            event.accept(ModItems.TITANIUM_HELMET);
            event.accept(ModItems.TITANIUM_CHESTPLATE);
            event.accept(ModItems.TITANIUM_LEGGINGS);
            event.accept(ModItems.TITANIUM_BOOTS);
            event.accept(ModItems.HAMMER);
            event.accept(ModItems.COPPER_HELMET);
            event.accept(ModItems.COPPER_CHESTPLATE);
            event.accept(ModItems.COPPER_LEGGINGS);
            event.accept(ModItems.COPPER_BOOTS);
            event.accept(ModItems.COPPER_SWORD);
            event.accept(ModItems.COPPER_PICKAXE);
            event.accept(ModItems.COPPER_AXE);
            event.accept(ModItems.COPPER_SHOVEL);
            event.accept(ModItems.COPPER_HOE);
            event.accept(ModItems.TURTLE_MASTER_HELMET);
            event.accept(ModItems.TURTLE_MASTER_CHESTPLATE);
            event.accept(ModItems.TURTLE_MASTER_LEGGINGS);
            event.accept(ModItems.TURTLE_MASTER_BOOTS);
            event.accept(ModItems.DEBUG_STICK);
            event.accept(ModItems.SPACESUIT_HELMET);
            event.accept(ModItems.SPACESUIT_CHESTPLATE);
            event.accept(ModItems.SPACESUIT_LEGS);
            event.accept(ModItems.SPACESUIT_BOOTS);
            event.accept(ModItems.ORBITAL_TNT_SHELL);
            event.accept(ModItems.ORBITAL_FLAME_SHELL);
        }
        if(event.getTab() == ModCreativeModeTab.STM_FOODS.get()) {
            event.accept(ModItems.BAGUETTE);
            event.accept(ModItems.LEAN);
            event.accept(ModItems.CANADA);
            event.accept(ModItems.ANTIDOTE);
            event.accept(ModItems.CANNED_BREAD);
        }
        if(event.getTab() == ModCreativeModeTab.STM_MACHINES.get()) {
            //event.accept(ModBlocks.IRON_BARREL);
            event.accept(ModBlocks.FORGING_TABLE);
            event.accept(ModBlocks.PLANET_DIRECTORY);
            event.accept(ModBlocks.ALLOY_REVERSAL_MACHINE);
            event.accept(ModBlocks.AIR_MACHINE);
            event.accept(ModBlocks.TNT_COMPRESSOR);
            event.accept(ModBlocks.STARGATE_CORE);
            event.accept(ModBlocks.ORBITAL_TNT_CORE);
            event.accept(ModBlocks.ORBITAL_FLAME_CORE);
        }
        /*if(event.getTab() == ModCreativeModeTab.STM_FLUIDS.get()) {
            event.accept(ModItems.LAVA_BOTTLE);
        }*/
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("The server is starting!");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
   }
}