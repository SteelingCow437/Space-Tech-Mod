package com.net.spacetechmod;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.enchantment.ModEnchantments;
import com.net.spacetechmod.fluid.ModFluidTypes;
import com.net.spacetechmod.fluid.ModFluids;
import com.net.spacetechmod.item.ModCreativeModeTab;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.networking.ModMessages;
import com.net.spacetechmod.painting.ModPaintings;
import com.net.spacetechmod.potion.ModPotions;
import com.net.spacetechmod.sound.ModSounds;
import com.net.spacetechmod.util.BetterBrewingRecipe;
import com.net.spacetechmod.villager.ModPOIs;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        ModPOIs.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModEnchantments.register(eventBus);
        ModFluidTypes.register(eventBus);
        ModFluids.register(eventBus);
        ModSounds.register(eventBus);
        ModCreativeModeTab.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
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
            ModMessages.register();
        });

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == ModCreativeModeTab.STM_BLOCKS.get()) {
            event.accept(ModBlocks.TITANIUM_BLOCK);
            event.accept(ModBlocks.RAW_TITANIUM_BLOCK);
            event.accept(ModBlocks.TITANIUM_ORE);
            event.accept(ModBlocks.TITANIUM_ORE_DEEPSLATE);
            event.accept(ModBlocks.AQUAMARINE_ORE);
            event.accept(ModBlocks.SCULK_HEART);
            event.accept(ModBlocks.SCULK_MAW);
            event.accept(ModBlocks.SCULK_TRAP);
            event.accept(ModBlocks.SCULK_ALTAR);
            event.accept(ModBlocks.TIN_ORE);
            event.accept(ModBlocks.TIN_ORE_DEEPSLATE);
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
        }
        if(event.getTab() == ModCreativeModeTab.STM_MACHINES.get()) {
            event.accept(ModBlocks.IRON_BARREL);
        }
        if(event.getTab() == ModCreativeModeTab.STM_FLUIDS.get()) {
            event.accept(ModItems.OIL_BOTTLE);
            event.accept(ModItems.OIL_BUCKET);
            event.accept(ModItems.LAVA_BOTTLE);
        }
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