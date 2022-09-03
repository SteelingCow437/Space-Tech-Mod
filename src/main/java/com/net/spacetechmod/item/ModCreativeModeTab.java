package com.net.spacetechmod.item;

import com.net.spacetechmod.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab STM_BLOCKS = new CreativeModeTab("STM_BLOCKS") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.TITANIUM_BLOCK.get());
        }
    };
    public static final CreativeModeTab STM_ITEMS = new CreativeModeTab("STM_ITEMS") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TITANIUM_INGOT.get());
        }
    };
    public static final CreativeModeTab STM_TOOLS = new CreativeModeTab("STM_TOOLS") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TITANIUM_SWORD.get());
        }
    };
    public static final CreativeModeTab STM_FOODS = new CreativeModeTab("STM_FOODS") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.LEAN.get());
        }
    };
    public static final CreativeModeTab STM_MACHINES = new CreativeModeTab("STM_MACHINES") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.LEAN.get());
        }
    };

}
