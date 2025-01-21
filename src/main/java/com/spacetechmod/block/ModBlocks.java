package com.spacetechmod.block;

import com.spacetechmod.Spacetechmod;
import com.spacetechmod.block.custom.dungeon.StarGateCoreBlock;
import com.spacetechmod.block.custom.dungeon.StarGatePortalBlock;
import com.spacetechmod.block.custom.dungeon.VaultDoorBlock;
import com.spacetechmod.block.custom.machine.*;
import com.spacetechmod.block.custom.multiblock.OrbitalFlameCoreBlock;
import com.spacetechmod.block.custom.multiblock.OrbitalTNTCoreBlock;
import com.spacetechmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Spacetechmod.MOD_ID);

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> ret = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties()));
        return ret;
    }
    private static <T extends Block> DeferredBlock<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    //Register blocks below this line
    //The stuff above is just to declare the block class

    //IMPORTANT: DO NOT ofFullCopy any block entities, the game won't start!

    //titanium
    public static final DeferredBlock<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(9.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> RAW_TITANIUM_BLOCK = registerBlock("raw_titanium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)
                    .strength(9f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> TITANIUM_ORE_DEEPSLATE = registerBlock("titanium_ore_deepslate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(5f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> AQUAMARINE_ORE = registerBlock("aquamarine_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> TIN_ORE_DEEPSLATE = registerBlock("tin_ore_deepslate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(5f).requiresCorrectToolForDrops()));

    //Fluid Tanks
    /*public static final DeferredBlock<Block> IRON_BARREL = registerBlock("iron_barrel",
            () -> new IronBarrelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(5f).requiresCorrectToolForDrops()));*/

    //machines
    public static final DeferredBlock<Block> FORGING_TABLE = registerBlock("forging_table",
            () -> new ForgingTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMITHING_TABLE)
                    .strength(4.5f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> AIR_MACHINE = registerBlock("air_machine",
            () -> new AirMachineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(4.5f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ALLOY_REVERSAL_MACHINE = registerBlock("un_alloy_machine",
            () -> new UnAlloyMachineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(4.5f).requiresCorrectToolForDrops()));

    //Space equipment stuffs
    public static final DeferredBlock<Block> PLANET_DIRECTORY = registerBlock("planet_directory",
            () -> new PlanetDirectoryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARTOGRAPHY_TABLE)
                    .strength(5.0f)));


    //Moon blocks
    public static final DeferredBlock<Block> MOON_DIRT = registerBlock("moon_dirt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
                    .strength(0.5f)));

    public static final DeferredBlock<Block> MOON_ROCK = registerBlock("moon_rock",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(1.5f)));

    public static final DeferredBlock<Block> VAULT_DOOR = registerBlock("vault_door",
            () -> new VaultDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)
                    .strength(99f).noLootTable().explosionResistance(99f)));

    public static final DeferredBlock<Block> VAULT_BRICK = registerBlock("vault_brick",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)
                    .strength(-1.0f).explosionResistance(3600000f).noLootTable().isValidSpawn(Blocks::never)));

    //Stargate stuff
    public static final DeferredBlock<Block> STARGATE_CORE = registerBlock("stargate_core",
            () -> new StarGateCoreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)
                    .strength(8f).requiresCorrectToolForDrops().explosionResistance(99f)));

    public static final DeferredBlock<Block> STARGATE_FRAME = registerBlock("stargate_frame",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)
                    .strength(8f).requiresCorrectToolForDrops().explosionResistance(99f)));

    public static final DeferredBlock<Block> STARGATE_PORTAL = registerBlockWithoutBlockItem("stargate_portal",
            () -> new StarGatePortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL)
                    .strength(99f).noLootTable()));

    //Rocketman!
    public static final DeferredBlock<Block> TNT_COMPRESSOR = registerBlock("tnt_compressor",
            () -> new TNTCompressorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)
                    .requiresCorrectToolForDrops()));

    //Deco blocks
    public static final DeferredBlock<Block> STEEL_DECO_BLOCK = registerBlock("steel_deco_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(3f).requiresCorrectToolForDrops()));

    //Multiblocks
    public static final DeferredBlock<Block> ORBITAL_TNT_CORE = registerBlock("orbital_tnt_core",
            () -> new OrbitalTNTCoreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ORBITAL_FLAME_CORE = registerBlock("orbital_flame_core",
            () -> new OrbitalFlameCoreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(3f).requiresCorrectToolForDrops()));
}
