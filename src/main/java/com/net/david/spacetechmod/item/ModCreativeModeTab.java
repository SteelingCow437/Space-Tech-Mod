package com.net.david.spacetechmod.item;

import com.net.david.spacetechmod.block.ModBlocks;
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




}
