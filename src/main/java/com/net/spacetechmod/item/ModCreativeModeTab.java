package com.net.spacetechmod.item;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Spacetechmod.MOD_ID);

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
    public static RegistryObject<CreativeModeTab> STM_BLOCKS = CREATIVE_MODE_TABS.register("stm_blocks", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.TITANIUM_BLOCK.get())).title(Component.literal("STM Blocks")).build());
    public static RegistryObject<CreativeModeTab> STM_ITEMS = CREATIVE_MODE_TABS.register("stm_items", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AQUAMARINE.get())).title(Component.literal("STM Items")).build());
    public static RegistryObject<CreativeModeTab> STM_TOOLS = CREATIVE_MODE_TABS.register("stm_tools", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TITANIUM_AXE.get())).title(Component.literal("STM Tools")).build());
    public static RegistryObject<CreativeModeTab> STM_FOODS = CREATIVE_MODE_TABS.register("stm_foods", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LEAN.get())).title(Component.literal("STM Foods")).build());
    public static RegistryObject<CreativeModeTab> STM_MAGIC = CREATIVE_MODE_TABS.register("stm_magic", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SCULK_HEART.get())).title(Component.literal("STM Magic")).build());
    public static RegistryObject<CreativeModeTab> STM_MACHINES = CREATIVE_MODE_TABS.register("stm_machines", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.IRON_BARREL.get())).title(Component.literal("STM Machines")).build());
    public static RegistryObject<CreativeModeTab> STM_FLUIDS = CREATIVE_MODE_TABS.register("stm_fluids", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.OIL_BUCKET.get())).title(Component.literal("STM Fluids")).build());
}
