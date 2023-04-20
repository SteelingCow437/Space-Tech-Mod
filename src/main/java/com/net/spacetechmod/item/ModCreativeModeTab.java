package com.net.spacetechmod.item;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spacetechmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab STM_BLOCKS;
    public static CreativeModeTab STM_ITEMS;
    public static CreativeModeTab STM_TOOLS;
    public static CreativeModeTab STM_FOODS;
    public static CreativeModeTab STM_MAGIC;
    public static CreativeModeTab STM_MACHINES;
    public static CreativeModeTab STM_FLUIDS;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        STM_BLOCKS = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_blocks"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.TITANIUM_BLOCK.get())).title(Component.literal("STM Blocks")).build());
        STM_FOODS = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_foods"),
                builder -> builder.icon(() -> new ItemStack(ModItems.LEAN.get())).title(Component.literal("STM Foods")).build());
        STM_TOOLS = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_tools"),
                builder -> builder.icon(() -> new ItemStack(ModItems.TITANIUM_SWORD.get())).title(Component.literal("STM Tools")).build());
        STM_MAGIC = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_magic"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.SCULK_HEART.get())).title(Component.literal("STM Magic")).build());
        STM_ITEMS = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_items"),
                builder -> builder.icon(() -> new ItemStack(ModItems.AQUAMARINE.get())).title(Component.literal("STM Items")).build());
        STM_MACHINES = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_machines"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.AQUAMARINE_ORE.get())).title(Component.literal("STM Machines")).build());
        STM_FLUIDS = event.registerCreativeModeTab(new ResourceLocation(Spacetechmod.MOD_ID, "stm_fluids"),
                builder -> builder.icon(() -> new ItemStack(Items.BUCKET)).title(Component.literal("STM Fluids")).build());
    }
}
