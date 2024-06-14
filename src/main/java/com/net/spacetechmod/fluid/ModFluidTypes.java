package com.net.spacetechmod.fluid;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID,"block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID,"block/water_flow");
    public static final ResourceLocation FLUID_OVERLAY_RL = ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID,"misc/in_fluid");
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Spacetechmod.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> HONEY_FLUID_TYPE = register("honey_fluid",
            FluidType.Properties.create().lightLevel(3).density(15).viscosity(8).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_BLOCK_STEP));

    private static DeferredHolder<FluidType, FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, FLUID_OVERLAY_RL,
                0xA1E038D0, new Vector3f(224 / 225f, 56f / 225f, 208f / 225f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
