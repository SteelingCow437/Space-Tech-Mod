package com.net.spacetechmod.item;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spacetechmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab STM_BLOCKS;
    public static CreativeModeTab STM_ITEMS;
    public static CreativeModeTab STM_TOOLS;
    public static CreativeModeTab STM_FOODS;
    public static CreativeModeTab STM_SCULK;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        STM_BLOCKS = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_blocks"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.TITANIUM_BLOCK.get())).title(Component.literal("STM Blocks")).build());
        STM_FOODS = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_foods"),
                builder -> builder.icon(() -> new ItemStack(ModItems.LEAN.get())).title(Component.literal("STM Foods")).build());
        STM_TOOLS = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_tools"),
                builder -> builder.icon(() -> new ItemStack(ModItems.TITANIUM_SWORD.get())).title(Component.literal("STM Tools")).build());
        STM_SCULK = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_sculk"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.SCULK_HEART.get())).title(Component.literal("STM Sculk")).build());
        STM_ITEMS = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_items"),
                builder -> builder.icon(() -> new ItemStack(ModItems.AQUAMARINE.get())).title(Component.literal("STM Items")).build());
    }
}
