package com.net.spacetechmod.villager;

import com.google.common.collect.ImmutableSet;
import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.block.ModBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, Spacetechmod.MOD_ID);

    public static final RegistryObject<PoiType> SDIM_PORTAL =
            POI.register("sdim_portal", () -> new PoiType(
                    ImmutableSet.copyOf(ModBlocks.SDIM_PORTAL.get().getStateDefinition().getPossibleStates()),
                    0, 1));

    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }


}
