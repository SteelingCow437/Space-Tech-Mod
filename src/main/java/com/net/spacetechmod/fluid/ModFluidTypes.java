package com.net.spacetechmod.fluid;

import com.mojang.math.Vector3f;
import com.net.spacetechmod.Spacetechmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL= new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL= new ResourceLocation("block/water_flow");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Spacetechmod.MOD_ID);


    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
