package com.net.spacetechmod.block;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.custom.fluid.BasicFluidBarrelBlock;
import com.net.spacetechmod.block.custom.sculk.*;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
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
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RAW_TITANIUM_BLOCK = registerBlock("raw_titanium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_ORE_DEEPSLATE = registerBlock("titanium_ore_deepslate",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AQUAMARINE_ORE = registerBlock("aquamarine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TIN_ORE_DEEPSLATE = registerBlock("tin_ore_deepslate",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f).requiresCorrectToolForDrops()));

    //sculk

    public static final RegistryObject<Block> SCULKDIM_PORTAL = registerBlockWithoutBlockItem("sculkdim_portal",
            SculkDimPortalBlock::new);

    public static final RegistryObject<Block> SCULK_HEART = registerBlock("sculk_heart",
            () -> new SculkHeartBlock(BlockBehaviour.Properties.of(Material.SCULK)
                    .strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SCULK_MAW = registerBlock("sculk_maw",
            () -> new SculkMawBlock(BlockBehaviour.Properties.of(Material.SCULK)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CORRUPTED_BONE = registerBlock("corrupted_bone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops().explosionResistance(2.5f)));

    public static final RegistryObject<Block> SCULK_TRAP = registerBlock("sculk_trap",
            () -> new SculkTrapBlock(BlockBehaviour.Properties.of(Material.SCULK)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SCULK_ALTAR = registerBlock("sculk_altar",
            () -> new SculkAltarBlock(BlockBehaviour.Properties.of(Material.SCULK)
                    .strength(10f).noLootTable()));

    public static final RegistryObject<Block> WARDEN_TRAP_BLOCK = registerBlock("warden_trap_block",
            () -> new WardenTrapBlock(BlockBehaviour.Properties.of(Material.SCULK)
                    .strength(12f).noLootTable()));

    //Fluid Tanks
    public static final RegistryObject<Block> BASIC_FLUID_BARREL = registerBlock("basic_barrel",
            () -> new BasicFluidBarrelBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f).requiresCorrectToolForDrops()));

    //machines


}
