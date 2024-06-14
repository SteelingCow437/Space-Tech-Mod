package com.net.spacetechmod.villager;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI
            = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Spacetechmod.MOD_ID);


    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }


}
