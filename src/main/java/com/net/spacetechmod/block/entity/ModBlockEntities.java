package com.net.spacetechmod.block.entity;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.machine.AlloyFurnaceBlockEntity;
import com.net.spacetechmod.block.entity.machine.BurnerPressBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkHeartBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkMawBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Spacetechmod.MOD_ID);

    //machine
    public static final RegistryObject<BlockEntityType<AlloyFurnaceBlockEntity>> ALLOY_FURNACE =
            BLOCK_ENTITIES.register("alloy_furnace", () ->
                    BlockEntityType.Builder.of(AlloyFurnaceBlockEntity::new,
                            ModBlocks.ALLOY_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BurnerPressBlockEntity>> BURNER_PRESS =
            BLOCK_ENTITIES.register("burner_press", () ->
                    BlockEntityType.Builder.of(BurnerPressBlockEntity::new,
                            ModBlocks.BURNER_PRESS.get()).build(null));

    //sculk
    public static final RegistryObject<BlockEntityType<SculkHeartBlockEntity>> SCULK_HEART =
            BLOCK_ENTITIES.register("sculk_heart", () ->
                    BlockEntityType.Builder.of(SculkHeartBlockEntity::new,
                            ModBlocks.SCULK_HEART.get()).build(null));

    public static final RegistryObject<BlockEntityType<SculkMawBlockEntity>> SCULK_MAW =
            BLOCK_ENTITIES.register("sculk_maw", () ->
                    BlockEntityType.Builder.of(SculkMawBlockEntity::new,
                            ModBlocks.SCULK_MAW.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
