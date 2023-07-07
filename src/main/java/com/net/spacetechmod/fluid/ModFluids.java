package com.net.spacetechmod.fluid;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.item.ModItems;
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

    public static final RegistryObject<FlowingFluid> CRUDE_OIL = FLUIDS.register("crude_oil",
            () -> new ForgeFlowingFluid.Source(ModFluids.CRUDE_OIL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HONEY = FLUIDS.register("honey",
            () -> new ForgeFlowingFluid.Source(ModFluids.HONEY_PROPERTIES));


    public static final ForgeFlowingFluid.Properties CRUDE_OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.OIL_FLUID_TYPE, ModFluids.CRUDE_OIL, ModFluids.CRUDE_OIL)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(null)
            .bucket(ModItems.OIL_BUCKET);

    public static final ForgeFlowingFluid.Properties HONEY_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.HONEY_FLUID_TYPE, ModFluids.HONEY, ModFluids.HONEY);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
