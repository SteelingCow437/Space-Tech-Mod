package com.net.spacetechmod.fluid;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, Spacetechmod.MOD_ID);

    public static final DeferredHolder<Fluid, Fluid> CRUDE_OIL = FLUIDS.register("crude_oil",
            () -> new BaseFlowingFluid.Source(ModFluids.CRUDE_OIL_PROPERTIES));

    public static final DeferredHolder<Fluid, Fluid> HONEY = FLUIDS.register("honey",
            () -> new BaseFlowingFluid.Source(ModFluids.HONEY_PROPERTIES));

    public static final BaseFlowingFluid.Properties CRUDE_OIL_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.OIL_FLUID_TYPE, ModFluids.CRUDE_OIL, ModFluids.CRUDE_OIL)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(null)
            .bucket(ModItems.OIL_BUCKET);

    public static final BaseFlowingFluid.Properties HONEY_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.HONEY_FLUID_TYPE, ModFluids.HONEY, ModFluids.HONEY);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
