package com.net.spacetechmod.block;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.custom.fluid.IronBarrelBlock;
import com.net.spacetechmod.block.custom.machine.ForgingTableBlock;
import com.net.spacetechmod.block.custom.machine.GeneratorBlock;
import com.net.spacetechmod.block.custom.machine.OilPumpBlock;
import com.net.spacetechmod.block.custom.machine.WireBlock;
import com.net.spacetechmod.block.custom.sculk.*;
import com.net.spacetechmod.item.ModItems;
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

    //IMPORTANT: DO NOT USE ANY "Properties.copy(Blocks.FURNACE)" IT WILL CRASH THE GAME!!!

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

    //sculk
    public static final DeferredBlock<Block> SCULKDIM_PORTAL = registerBlockWithoutBlockItem("sculkdim_portal",
            SculkDimPortalBlock::new);
    public static final DeferredBlock<Block> SCULK_HEART = registerBlock("sculk_heart",
            () -> new SculkHeartBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK)
                    .strength(1f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SCULK_MAW = registerBlock("sculk_maw",
            () -> new SculkMawBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CORRUPTED_BONE = registerBlock("corrupted_bone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK)
                    .strength(6f).requiresCorrectToolForDrops().explosionResistance(2.5f)));

    public static final DeferredBlock<Block> SCULK_TRAP = registerBlock("sculk_trap",
            () -> new SculkTrapBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK_SHRIEKER)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SCULK_ALTAR = registerBlock("sculk_altar",
            () -> new SculkAltarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK_CATALYST)
                    .strength(10f).noLootTable()));

    public static final DeferredBlock<Block> WARDEN_TRAP_BLOCK = registerBlock("warden_trap_block",
            () -> new WardenTrapBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
                    .strength(12f).noLootTable()));

    public static final DeferredBlock<Block> SCULK_CORE = registerBlock("sculk_core",
            () -> new SculkCoreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK_SHRIEKER)
                    .strength(5f).explosionResistance(12f)));

    public static final DeferredBlock<Block> CALIBRATED_SCULK_TRAP = registerBlock("calibrated_sculk_trap",
            () -> new CalibratedSculkTrapBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK_SHRIEKER)
                    .strength(2f).requiresCorrectToolForDrops()));

    //Fluid Tanks
    public static final DeferredBlock<Block> IRON_BARREL = registerBlock("iron_barrel",
            () -> new IronBarrelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(5f).requiresCorrectToolForDrops()));

    //machines

    public static final DeferredBlock<Block> FORGING_TABLE = registerBlock("forging_table",
            () -> new ForgingTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMITHING_TABLE)
                    .strength(4.5f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GENERATOR = registerBlock("generator",
            () -> new GeneratorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(4.5f).requiresCorrectToolForDrops()));

    //Cables
    public static final DeferredBlock<Block> WIRE_BLOCK = registerBlock("wire_block",
            () -> new WireBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK).strength(3f)));

    //cover yuorself in oyul
    public static final DeferredBlock<Block> OIL_DEPOSIT = registerBlock("oil_deposit",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3)
                    .noLootTable()));

    public static final DeferredBlock<Block> OIL_PUMP = registerBlock("oil_pump",
            () -> new OilPumpBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).strength(6f)
                    .requiresCorrectToolForDrops()));
}
