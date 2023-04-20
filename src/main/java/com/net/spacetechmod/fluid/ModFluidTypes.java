package com.net.spacetechmod.fluid;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation FLUID_OVERLAY_RL = new ResourceLocation(Spacetechmod.MOD_ID, "misc/in_fluid");
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Spacetechmod.MOD_ID);

    public static final RegistryObject<FluidType> OIL_FLUID_TYPE = register("oil_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.ELDER_GUARDIAN_DEATH));

    public static final RegistryObject<FluidType> HONEY_FLUID_TYPE = register("honey_fluid",
            FluidType.Properties.create().lightLevel(3).density(15).viscosity(8).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_BLOCK_STEP));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, FLUID_OVERLAY_RL,
                0xA1E038D0, new Vector3f(224 / 225f, 56f / 225f, 208f / 225f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
