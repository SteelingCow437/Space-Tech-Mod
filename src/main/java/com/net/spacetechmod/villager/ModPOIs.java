package com.net.spacetechmod.villager;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, Spacetechmod.MOD_ID);

    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }


}
