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
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
    //register entities below this line!
    public static final RegistryObject<EntityType<BulletEntity>> BULLET = ENTITY_TYPES.register("bullet",
            () -> EntityType.Builder.of((EntityType.EntityFactory<BulletEntity>) BulletEntity::new,
                    MobCategory.MISC).sized(0.1f, 0.1f).build("bullet"));
}
