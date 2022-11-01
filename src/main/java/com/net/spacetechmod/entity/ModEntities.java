package com.net.spacetechmod.entity;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Spacetechmod.MOD_ID);

    public static final RegistryObject<EntityType<SoulDartEntity>> SOUL_DART = ENTITY_TYPES.register("soul_dart",
            () -> EntityType.Builder.of(SoulDartEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build("soul_dart"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}