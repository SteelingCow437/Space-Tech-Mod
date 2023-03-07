package com.net.spacetechmod.fluid;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Spacetechmod.MOD_ID);

    public static final RegistryObject<FlowingFluid> STEAM = FLUIDS.register("steam",
            () -> new ForgeFlowingFluid.Source(ModFluids.STEAM_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SATURATED_STEAM = FLUIDS.register("saturated_steam",
            () -> new ForgeFlowingFluid.Source(ModFluids.STEAM_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SUPERHEATED_STEAM = FLUIDS.register("superheated_steam",
            () -> new ForgeFlowingFluid.Source(ModFluids.STEAM_FLUID_PROPERTIES));
    public static final ForgeFlowingFluid.Properties STEAM_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.STEAM_TYPE, STEAM, null).slopeFindDistance(1).levelDecreasePerBlock(1);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
