package com.spacetechmod.item;

import com.spacetechmod.Spacetechmod;
import com.spacetechmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Spacetechmod.MOD_ID);

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> STM_BLOCKS = CREATIVE_MODE_TABS.register("stm_blocks", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.TITANIUM_BLOCK.get())).title(Component.literal("STM Blocks")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> STM_ITEMS = CREATIVE_MODE_TABS.register("stm_items", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AQUAMARINE.get())).title(Component.literal("STM Items")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> STM_TOOLS = CREATIVE_MODE_TABS.register("stm_tools", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TITANIUM_AXE.get())).title(Component.literal("STM Tools")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> STM_FOODS = CREATIVE_MODE_TABS.register("stm_foods", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LEAN.get())).title(Component.literal("STM Foods")).build());
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> STM_MACHINES = CREATIVE_MODE_TABS.register("stm_machines", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.FORGING_TABLE.get())).title(Component.literal("STM Machines")).build());
    /*public static DeferredHolder<CreativeModeTab, CreativeModeTab> STM_FLUIDS = CREATIVE_MODE_TABS.register("stm_fluids", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LAVA_BOTTLE.get())).title(Component.literal("STM Fluids")).build());*/
}
