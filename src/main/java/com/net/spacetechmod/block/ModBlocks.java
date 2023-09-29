package com.net.spacetechmod.block;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.custom.fluid.IronBarrelBlock;
import com.net.spacetechmod.block.custom.machine.ForgingTableBlock;
import com.net.spacetechmod.block.custom.machine.OilPumpBlock;
import com.net.spacetechmod.block.custom.machine.WireBlock;
import com.net.spacetechmod.block.custom.sculk.*;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.world.tree.SculkTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Spacetechmod.MOD_ID);

    private static <T extends Block> RegistryObject<T>
    registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static <T extends Block> RegistryObject<Item>
    registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    //Register blocks below this line
    //The stuff above is just to declare the block class

    //titanium
    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(9f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RAW_TITANIUM_BLOCK = registerBlock("raw_titanium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                    .strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_ORE_DEEPSLATE = registerBlock("titanium_ore_deepslate",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AQUAMARINE_ORE = registerBlock("aquamarine_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TIN_ORE_DEEPSLATE = registerBlock("tin_ore_deepslate",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(5f).requiresCorrectToolForDrops()));

    //sculk

    public static final RegistryObject<Block> SCULKDIM_PORTAL = registerBlockWithoutBlockItem("sculkdim_portal",
            SculkDimPortalBlock::new);

    public static final RegistryObject<Block> SCULK_HEART = registerBlock("sculk_heart",
            () -> new SculkHeartBlock(BlockBehaviour.Properties.copy(Blocks.SCULK)
                    .strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SCULK_MAW = registerBlock("sculk_maw",
            () -> new SculkMawBlock(BlockBehaviour.Properties.copy(Blocks.SCULK)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CORRUPTED_BONE = registerBlock("corrupted_bone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)
                    .strength(6f).requiresCorrectToolForDrops().explosionResistance(2.5f)));

    public static final RegistryObject<Block> SCULK_TRAP = registerBlock("sculk_trap",
            () -> new SculkTrapBlock(BlockBehaviour.Properties.copy(Blocks.SCULK_SHRIEKER)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SCULK_ALTAR = registerBlock("sculk_altar",
            () -> new SculkAltarBlock(BlockBehaviour.Properties.copy(Blocks.SCULK_CATALYST)
                    .strength(10f).noLootTable()));

    public static final RegistryObject<Block> WARDEN_TRAP_BLOCK = registerBlock("warden_trap_block",
            () -> new WardenTrapBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICKS)
                    .strength(12f).noLootTable()));

    public static final RegistryObject<Block> SCULK_CORE = registerBlock("sculk_core",
            () -> new SculkCoreBlock(BlockBehaviour.Properties.copy(Blocks.SCULK_SHRIEKER)
                    .strength(5f).explosionResistance(12f)));

    public static final RegistryObject<Block> CALIBRATED_SCULK_TRAP = registerBlock("calibrated_sculk_trap",
            () -> new CalibratedSculkTrapBlock(BlockBehaviour.Properties.copy(Blocks.SCULK_SHRIEKER)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BONE_SAPLING = registerBlock("bone_sapling",
            () -> new SaplingBlock(new SculkTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    //Fluid Tanks
    public static final RegistryObject<Block> IRON_BARREL = registerBlock("iron_barrel",
            () -> new IronBarrelBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5f).requiresCorrectToolForDrops()));

    //machines

    public static final RegistryObject<Block> FORGING_TABLE = registerBlock("forging_table",
            () -> new ForgingTableBlock(BlockBehaviour.Properties.copy(Blocks.SMITHING_TABLE)
                    .strength(4.5f).requiresCorrectToolForDrops()));


    //Cables
    public static final RegistryObject<Block> WIRE_BLOCK = registerBlock("wire_block",
            () -> new WireBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).strength(3f)));

    //cover yuorself in oyul
    public static final RegistryObject<Block> OIL_DEPOSIT = registerBlock("oil_deposit",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3)
                    .noLootTable()));

    public static final RegistryObject<Block> OIL_PUMP = registerBlock("oil_pump",
            () -> new OilPumpBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).strength(6f)
                    .requiresCorrectToolForDrops()));
}
