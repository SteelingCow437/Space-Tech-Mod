package com.spacetechmod.data;

import com.spacetechmod.Spacetechmod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataStorage {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Spacetechmod.MOD_ID);


    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> LINKED_ORBITAL_CORE = register("linked_orbital_core",
            builder -> builder.persistent(BlockPos.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ResourceKey<Level>>> SELECTED_PLANET = register(
            "selected_planet", builder -> builder.persistent(Level.RESOURCE_KEY_CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Vec3i>> SHIP_SIZE = register(
            "ship_size", builder -> builder.persistent(Vec3i.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> SGC_DESTINATION = register(
            "sgc_destination", builder -> builder.persistent(BlockPos.CODEC));


    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                           UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }


    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
