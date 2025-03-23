package com.spacetechmod.block.entity;

import com.spacetechmod.Spacetechmod;
import com.spacetechmod.block.ModBlocks;
import com.spacetechmod.block.entity.dungeon.StarGateCoreBlockEntity;
import com.spacetechmod.block.entity.machine.AirMachineBlockEntity;
import com.spacetechmod.block.entity.machine.ForgingTableBlockEntity;
import com.spacetechmod.block.entity.machine.PlanetDirectoryBlockEntity;
import com.spacetechmod.block.entity.machine.UnAlloyMachineBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Spacetechmod.MOD_ID);
    //Machine
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ForgingTableBlockEntity>> FORGING_TABLE =
            BLOCK_ENTITIES.register("forging_table", () ->
                    BlockEntityType.Builder.of(ForgingTableBlockEntity::new,
                            ModBlocks.FORGING_TABLE.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirMachineBlockEntity>> AIR_MACHINE =
            BLOCK_ENTITIES.register("air_machine", () ->
                    BlockEntityType.Builder.of(AirMachineBlockEntity::new,
                            ModBlocks.AIR_MACHINE.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UnAlloyMachineBlockEntity>> UN_ALLOY_MACHINE =
            BLOCK_ENTITIES.register("un_alloy_machine", () ->
                    BlockEntityType.Builder.of(UnAlloyMachineBlockEntity::new,
                            ModBlocks.ALLOY_REVERSAL_MACHINE.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PlanetDirectoryBlockEntity>> PLANET_DIRECTORY =
            BLOCK_ENTITIES.register("planet_directory", () ->
                    BlockEntityType.Builder.of(PlanetDirectoryBlockEntity::new,
                            ModBlocks.PLANET_DIRECTORY.get()).build(null));
    //Fluid container blocks
     /*public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BasicFluidBarrelBlockEntity>> IRON_BARREL =
            BLOCK_ENTITIES.register("basic_fluid_barrel", () -> //what a mouthful
                    BlockEntityType.Builder.of(BasicFluidBarrelBlockEntity::new,
                            ModBlocks.IRON_BARREL.get()).build(null)); */

    //StarGate Core
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StarGateCoreBlockEntity>> STARGATE_CORE =
            BLOCK_ENTITIES.register("stargate_core", () ->
                    BlockEntityType.Builder.of(StarGateCoreBlockEntity::new,
                            ModBlocks.STARGATE_CORE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
