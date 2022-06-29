package com.net.spacetechmod.painting;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<Motive> PAINTING_MOTIVES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Spacetechmod.MOD_ID);
    public static void register(IEventBus eventBus) {
        PAINTING_MOTIVES.register(eventBus);
    }
    //register all paintings below this line!

    public static final RegistryObject<Motive> MORBIUS =
            PAINTING_MOTIVES.register("morbius", () -> new Motive(64, 96));

    public static final RegistryObject<Motive> SAULGOODMAN =
            PAINTING_MOTIVES.register("saulgoodman", () -> new Motive(48, 64));

    public static final RegistryObject<Motive> POOP =
            PAINTING_MOTIVES.register("poop", () -> new Motive(64, 64));





}
