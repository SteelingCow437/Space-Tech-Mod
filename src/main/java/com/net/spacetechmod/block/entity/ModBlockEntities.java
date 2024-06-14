package com.net.spacetechmod.block.entity;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.fluid.BasicFluidBarrelBlockEntity;
import com.net.spacetechmod.block.entity.machine.AirMachineBlockEntity;
import com.net.spacetechmod.block.entity.machine.ForgingTableBlockEntity;
import com.net.spacetechmod.block.entity.machine.UnAlloyMachineBlockEntity;
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
    //Fluid container blocks
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BasicFluidBarrelBlockEntity>> IRON_BARREL =
            BLOCK_ENTITIES.register("basic_fluid_barrel", () -> //what a mouthful
                    BlockEntityType.Builder.of(BasicFluidBarrelBlockEntity::new,
                            ModBlocks.IRON_BARREL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
