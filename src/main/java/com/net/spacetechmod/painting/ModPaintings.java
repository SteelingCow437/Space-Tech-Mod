package com.net.spacetechmod.painting;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_MOTIVES =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Spacetechmod.MOD_ID);
    public static void register(IEventBus eventBus) {
        PAINTING_MOTIVES.register(eventBus);
    }
    //register all paintings below this line!

    public static final RegistryObject<PaintingVariant> MORBIUS =
            PAINTING_MOTIVES.register("morbius", () -> new PaintingVariant(64, 96));

    public static final RegistryObject<PaintingVariant> SAULGOODMAN =
            PAINTING_MOTIVES.register("saulgoodman", () -> new PaintingVariant(48, 64));

    public static final RegistryObject<PaintingVariant> POOP =
            PAINTING_MOTIVES.register("poop", () -> new PaintingVariant(64, 64));

    public static final RegistryObject<PaintingVariant> TROLL =
            PAINTING_MOTIVES.register("troll", () -> new PaintingVariant(64, 64));
}
