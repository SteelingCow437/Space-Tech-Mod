package com.net.spacetechmod.painting;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_MOTIVES =
            DeferredRegister.create(Registries.PAINTING_VARIANT, Spacetechmod.MOD_ID);


    public static final DeferredHolder<PaintingVariant, PaintingVariant> MORBIUS =
            PAINTING_MOTIVES.register("morbius", () -> new PaintingVariant(64, 96));

    public static final DeferredHolder<PaintingVariant, PaintingVariant> SAULGOODMAN =
            PAINTING_MOTIVES.register("saulgoodman", () -> new PaintingVariant(48, 64));

    public static final DeferredHolder<PaintingVariant, PaintingVariant> POOP =
            PAINTING_MOTIVES.register("poop", () -> new PaintingVariant(64, 64));

    public static final DeferredHolder<PaintingVariant, PaintingVariant> TROLL =
            PAINTING_MOTIVES.register("troll", () -> new PaintingVariant(64, 64));

    public static void register(IEventBus eventBus) {
        PAINTING_MOTIVES.register(eventBus);
    }
}
