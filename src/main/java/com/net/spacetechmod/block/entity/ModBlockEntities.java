package com.net.spacetechmod.block.entity;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.fluid.BasicFluidBarrelBlockEntity;
import com.net.spacetechmod.block.entity.machine.ForgingTableBlockEntity;
import com.net.spacetechmod.block.entity.machine.GeneratorBlockEntity;
import com.net.spacetechmod.block.entity.machine.OilPumpBlockEntity;
import com.net.spacetechmod.block.entity.machine.WireBlockEntity;
import com.net.spacetechmod.block.entity.sculk.CalibratedSculkTrapBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkHeartBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkMawBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkTrapBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Spacetechmod.MOD_ID);

    //sculk
    public static final RegistryObject<BlockEntityType<SculkHeartBlockEntity>> SCULK_HEART =
            BLOCK_ENTITIES.register("sculk_heart", () ->
                    BlockEntityType.Builder.of(SculkHeartBlockEntity::new,
                            ModBlocks.SCULK_HEART.get()).build(null));

    public static final RegistryObject<BlockEntityType<SculkMawBlockEntity>> SCULK_MAW =
            BLOCK_ENTITIES.register("sculk_maw", () ->
                    BlockEntityType.Builder.of(SculkMawBlockEntity::new,
                            ModBlocks.SCULK_MAW.get()).build(null));

    public static final RegistryObject<BlockEntityType<SculkTrapBlockEntity>> SCULK_TRAP =
            BLOCK_ENTITIES.register("sculk_trap", () ->
                    BlockEntityType.Builder.of(SculkTrapBlockEntity::new,
                            ModBlocks.SCULK_TRAP.get()).build(null));

    public static final RegistryObject<BlockEntityType<CalibratedSculkTrapBlockEntity>> CALIBRATED_SCULK_TRAP =
            BLOCK_ENTITIES.register("calibrated_sculk_trap", () ->
                    BlockEntityType.Builder.of(CalibratedSculkTrapBlockEntity::new,
                            ModBlocks.CALIBRATED_SCULK_TRAP.get()).build(null));

    //Machine

    public static final RegistryObject<BlockEntityType<ForgingTableBlockEntity>> FORGING_TABLE =
            BLOCK_ENTITIES.register("forging_table", () ->
                    BlockEntityType.Builder.of(ForgingTableBlockEntity::new,
                            ModBlocks.FORGING_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<GeneratorBlockEntity>> GENERATOR =
            BLOCK_ENTITIES.register("generator", () ->
                    BlockEntityType.Builder.of(GeneratorBlockEntity::new,
                            ModBlocks.GENERATOR.get()).build(null));

    //Fluid container blocks
    public static final RegistryObject<BlockEntityType<BasicFluidBarrelBlockEntity>> IRON_BARREL =
            BLOCK_ENTITIES.register("basic_fluid_barrel", () -> //what a mouthful
                    BlockEntityType.Builder.of(BasicFluidBarrelBlockEntity::new,
                            ModBlocks.IRON_BARREL.get()).build(null));

    //wires
    public static final RegistryObject<BlockEntityType<WireBlockEntity>> WIRE =
            BLOCK_ENTITIES.register("wire", () ->
                    BlockEntityType.Builder.of(WireBlockEntity::new,
                            ModBlocks.WIRE_BLOCK.get()).build(null));

    //oil
    public static final RegistryObject<BlockEntityType<OilPumpBlockEntity>> OIL_PUMP =
            BLOCK_ENTITIES.register("oil_pump", () ->
                    BlockEntityType.Builder.of(OilPumpBlockEntity::new,
                            ModBlocks.OIL_PUMP.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
