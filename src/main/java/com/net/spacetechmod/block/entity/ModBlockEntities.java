package com.net.spacetechmod.block.entity;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.block.entity.machine.AlloyFurnaceBlockEntity;
import com.net.spacetechmod.block.entity.machine.BurnerPressBlockEntity;
import com.net.spacetechmod.block.entity.machine.MachineTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Spacetechmod.MOD_ID);

    public static final RegistryObject<BlockEntityType<AlloyFurnaceBlockEntity>> ALLOY_FURNACE =
            BLOCK_ENTITIES.register("alloy_furnace", () ->
                    BlockEntityType.Builder.of(AlloyFurnaceBlockEntity::new,
                            ModBlocks.ALLOY_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BurnerPressBlockEntity>> BURNER_PRESS =
            BLOCK_ENTITIES.register("burner_press", () ->
                    BlockEntityType.Builder.of(BurnerPressBlockEntity::new,
                            ModBlocks.BURNER_PRESS.get()).build(null));

    public static final RegistryObject<BlockEntityType<MachineTableBlockEntity>> MACHINE_TABLE =
            BLOCK_ENTITIES.register("machine_table", () ->
                    BlockEntityType.Builder.of(MachineTableBlockEntity::new,
                            ModBlocks.MACHINE_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
