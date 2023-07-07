package com.net.spacetechmod.util;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class ModBlockTags {
        public static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Spacetechmod.MOD_ID, name));
        }

        public static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation(Spacetechmod.MOD_ID, name));
        }
        //block tags here

        public static final TagKey<Block> PORTAL_FRAME_BLOCKS
                = tag("portal_frame_blocks");
    }
    public static class ModItemTags {
        public static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Spacetechmod.MOD_ID, name));
        }

        public static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation(Spacetechmod.MOD_ID, name));
        }
        //item tags here
    }
}
