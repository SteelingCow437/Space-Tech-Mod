package com.net.spacetechmod;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.fluid.ModFluidTypes;
import com.net.spacetechmod.fluid.ModFluids;
import com.net.spacetechmod.item.ModCreativeModeTab;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.painting.ModPaintings;
import com.net.spacetechmod.potion.ModPotions;
import com.net.spacetechmod.sound.ModSounds;
import com.net.spacetechmod.util.BetterBrewingRecipe;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
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
        ModBlockEntities.register(eventBus);
        //ModEnchantments.register(eventBus);
        ModFluidTypes.register(eventBus);
        ModFluids.register(eventBus);
        ModSounds.register(eventBus);
        ModCreativeModeTab.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(this);
        eventBus.addListener(this::addCreative);
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

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == ModCreativeModeTab.STM_BLOCKS.get()) {
            event.accept(ModBlocks.TITANIUM_BLOCK);
            event.accept(ModBlocks.RAW_TITANIUM_BLOCK);
            event.accept(ModBlocks.TITANIUM_ORE);
            event.accept(ModBlocks.TITANIUM_ORE_DEEPSLATE);
            event.accept(ModBlocks.AQUAMARINE_ORE);
            event.accept(ModBlocks.TIN_ORE);
            event.accept(ModBlocks.TIN_ORE_DEEPSLATE);
            event.accept(ModBlocks.CORRUPTED_BONE);
            event.accept(ModBlocks.SCULK_HEART);
            event.accept(ModBlocks.SCULK_MAW);
            event.accept(ModBlocks.SCULK_TRAP);
            event.accept(ModBlocks.SCULK_ALTAR);
            event.accept(ModBlocks.SCULK_CORE);
            event.accept(ModBlocks.CALIBRATED_SCULK_TRAP);
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
            event.accept(ModItems.ECHO);
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
            event.accept(ModItems.SCULK_HELMET);
            event.accept(ModItems.SCULK_CHESTPLATE);
            event.accept(ModItems.SCULK_LEGGINGS);
            event.accept(ModItems.SCULK_BOOTS);
            event.accept(ModItems.DEBUG_STICK);
            event.accept(ModItems.SPACESUIT_HELMET);
            event.accept(ModItems.SPACESUIT_CHESTPLATE);
            event.accept(ModItems.SPACESUIT_LEGS);
            event.accept(ModItems.SPACESUIT_BOOTS);
        }
        if(event.getTab() == ModCreativeModeTab.STM_FOODS.get()) {
            event.accept(ModItems.BAGUETTE);
            event.accept(ModItems.LEAN);
            event.accept(ModItems.CANADA);
            event.accept(ModItems.ANTIDOTE);
            event.accept(ModItems.CANNED_BREAD);
        }
        if(event.getTab() == ModCreativeModeTab.STM_MAGIC.get()) {
            event.accept(ModItems.SOUL_BOTTLE);
            event.accept(ModItems.SOUL_CRYSTAL);
            event.accept(ModItems.MAGIC_QUIVER);
            event.accept(ModItems.LIGHTNING_STAFF);
            event.accept(ModItems.CALIBRATED_SCULK_HEART);
        }
        if(event.getTab() == ModCreativeModeTab.STM_MACHINES.get()) {
            event.accept(ModBlocks.IRON_BARREL);
            event.accept(ModBlocks.FORGING_TABLE);
            event.accept(ModBlocks.PLANET_DIRECTORY);
            event.accept(ModBlocks.ALLOY_REVERSAL_MACHINE);
            event.accept(ModBlocks.AIR_MACHINE);
        }
        if(event.getTab() == ModCreativeModeTab.STM_FLUIDS.get()) {
            event.accept(ModItems.LAVA_BOTTLE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("The server is starting!");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SCULKDIM_PORTAL.get(), RenderType.translucent());
        }
   }
}