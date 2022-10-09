package com.net.spacetechmod.block;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.custom.machine.AlloyFurnaceBlock;
import com.net.spacetechmod.block.custom.machine.BurnerPressBlock;
import com.net.spacetechmod.block.custom.SculkDimPortalBlock;
import com.net.spacetechmod.item.ModCreativeModeTab;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
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
    registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item>
    registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    //Register blocks below this line
    //The stuff above is just to declare the block class

    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.STM_BLOCKS);

    public static final RegistryObject<Block> RAW_TITANIUM_BLOCK = registerBlock("raw_titanium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.STM_BLOCKS);
    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.STM_BLOCKS);

    public static final RegistryObject<Block> TITANIUM_ORE_DEEPSLATE = registerBlock("titanium_ore_deepslate",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.STM_BLOCKS);

    public static final RegistryObject<Block> AQUAMARINE_ORE = registerBlock("aquamarine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.STM_BLOCKS);

    // machines
    public static final RegistryObject<Block> ALLOY_FURNACE = registerBlock("alloy_furnace",
            () -> new AlloyFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.STM_MACHINES);


    public static final RegistryObject<Block> BURNER_PRESS = registerBlock("burner_press",
            () -> new BurnerPressBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.STM_MACHINES);

    //cover yourself in oil cover yourself in oil cover yourself in oil oyul funne heeh

    public static final RegistryObject<Block> OIL_DEPOSIT = registerBlock("oil_deposit",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.STM_BLOCKS);

    //sculk

    public static final RegistryObject<Block> SCULK_BONE = registerBlock("sculk_bone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2.0f).requiresCorrectToolForDrops()),
            ModCreativeModeTab.STM_SCULK);

    public static final RegistryObject<Block> SCULKDIM_PORTAL = registerBlockWithoutBlockItem("sculkdim_portal",
            SculkDimPortalBlock::new);

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
}
