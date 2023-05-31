package com.net.spacetechmod.block.entity;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.fluid.BasicFluidBarrelBlockEntity;
import com.net.spacetechmod.block.entity.machine.DynamoBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkHeartBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkMawBlockEntity;
import com.net.spacetechmod.block.entity.sculk.SculkTrapBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

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

    //Machine
    public static final RegistryObject<BlockEntityType<DynamoBlockEntity>> DYNAMO =
            BLOCK_ENTITIES.register("dynamo", () ->
                    BlockEntityType.Builder.of(DynamoBlockEntity::new,
                            ModBlocks.DYNAMO.get()).build(null));

    public static final ArrayList<BlockEntityType> MACHINE_INDEX = new ArrayList<BlockEntityType>();

    public static void addMachines() {
        MACHINE_INDEX.add(0, ModBlockEntities.DYNAMO.get());
        MACHINE_INDEX.add(1, BlockEntityType.FURNACE);
    }
    //Fluid container blocks
    public static final RegistryObject<BlockEntityType<BasicFluidBarrelBlockEntity>> IRON_BARREL =
            BLOCK_ENTITIES.register("basic_fluid_barrel", () -> //what a mouthful
                    BlockEntityType.Builder.of(BasicFluidBarrelBlockEntity::new,
                            ModBlocks.IRON_BARREL.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
