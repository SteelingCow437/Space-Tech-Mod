package com.net.spacetechmod.block.entity;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.fluid.BasicFluidBarrelBlockEntity;
import com.net.spacetechmod.block.entity.machine.*;
import com.net.spacetechmod.block.entity.sculk.CalibratedSculkTrapBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkHeartBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkMawBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkTrapBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Spacetechmod.MOD_ID);
    //sculk
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SculkHeartBlockEntity>> SCULK_HEART =
            BLOCK_ENTITIES.register("sculk_heart",
                    () -> BlockEntityType.Builder.of(SculkHeartBlockEntity::new, ModBlocks.SCULK_HEART.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SculkMawBlockEntity>> SCULK_MAW =
            BLOCK_ENTITIES.register("sculk_maw", () ->
                    BlockEntityType.Builder.of(SculkMawBlockEntity::new,
                            ModBlocks.SCULK_MAW.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SculkTrapBlockEntity>> SCULK_TRAP =
            BLOCK_ENTITIES.register("sculk_trap", () ->
                    BlockEntityType.Builder.of(SculkTrapBlockEntity::new,
                            ModBlocks.SCULK_TRAP.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CalibratedSculkTrapBlockEntity>> CALIBRATED_SCULK_TRAP =
            BLOCK_ENTITIES.register("calibrated_sculk_trap", () ->
                    BlockEntityType.Builder.of(CalibratedSculkTrapBlockEntity::new,
                            ModBlocks.CALIBRATED_SCULK_TRAP.get()).build(null));

    //Machine
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ForgingTableBlockEntity>> FORGING_TABLE =
            BLOCK_ENTITIES.register("forging_table", () ->
                    BlockEntityType.Builder.of(ForgingTableBlockEntity::new,
                            ModBlocks.FORGING_TABLE.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BatteryBlockEntity>> BATTERY =
            BLOCK_ENTITIES.register("battery", () ->
                    BlockEntityType.Builder.of(BatteryBlockEntity::new,
                            ModBlocks.BASIC_BATTERY.get()).build(null));

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
