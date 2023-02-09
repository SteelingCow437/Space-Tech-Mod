package com.net.spacetechmod.util;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Spacetechmod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation(Spacetechmod.MOD_ID, name));
        }
        //block tags here

        public static final TagKey<Block> PORTAL_FRAME_BLOCKS
                = tag("portal_frame_blocks");
    }
    public static class Items {
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Spacetechmod.MOD_ID, name));
        }
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation(Spacetechmod.MOD_ID, name));
        }
        //item tags here
        public static final TagKey<Item> GENERAL_MAGIC_ITEMS
                = tag("general_magic_items");

        public static final TagKey<Item> SCULK_MAGIC_ITEMS
                = tag("sculk_magic_items");

        public static final TagKey<Item> WATER_MAGIC_ITEMS
                = tag("water_magic_items");

        public static final TagKey<Item> LIGHT_WEAPONS
                = tag("light_weapons");

        public static final TagKey<Item> MEDIUM_WEAPONS
                = tag("medium_weapons");

        public static final TagKey<Item> HEAVY_WEAPONS
                = tag("heavy_weapons");
    }
}
