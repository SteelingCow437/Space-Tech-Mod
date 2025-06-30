package com.spacetechmod.datagen;

import com.spacetechmod.Spacetechmod;
import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Spacetechmod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.RAW_TITANIUM_BLOCK.get())
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TITANIUM_ORE_DEEPSLATE.get())
                .add(ModBlocks.AQUAMARINE_ORE.get())
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.TIN_ORE_DEEPSLATE.get())
                .add(ModBlocks.FORGING_TABLE.get())
                .add(ModBlocks.ALLOY_REVERSAL_MACHINE.get())
                .add(ModBlocks.AIR_MACHINE.get())
                .add(ModBlocks.TNT_COMPRESSOR.get())
                .add(ModBlocks.MOON_ROCK.get())
                .add(ModBlocks.PLANET_DIRECTORY.get())
                .add(ModBlocks.STARGATE_CORE.get())
                .add(ModBlocks.STARGATE_FRAME.get())
                .add(ModBlocks.STEEL_DECO_BLOCK.get())
                .add(ModBlocks.ORBITAL_TNT_CORE.get())
                .add(ModBlocks.ORBITAL_FLAME_CORE.get())
                .add(ModBlocks.WARP_DRIVE.get())
                .add(ModBlocks.ENRICHED_MOON_ROCK.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.MOON_DIRT.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.AQUAMARINE_ORE.get())
                .add(ModBlocks.STARGATE_CORE.get())
                .add(ModBlocks.STARGATE_FRAME.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.RAW_TITANIUM_BLOCK.get())
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TITANIUM_ORE_DEEPSLATE.get())
                .add(ModBlocks.ENRICHED_MOON_ROCK.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.TIN_ORE_DEEPSLATE.get());

        tag(ModTags.ModBlockTags.MOON_STONE_REPLACEABLES)
                .add(ModBlocks.MOON_ROCK.get());

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.STEEL_DECO_BLOCK.get());
    }
}
