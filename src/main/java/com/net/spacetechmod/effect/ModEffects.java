package com.net.spacetechmod.effect;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Spacetechmod.MOD_ID);


    public static final RegistryObject<MobEffect> OILEFFECT = MOB_EFFECTS.register("oileffect",
            () -> new OilEffect(MobEffectCategory.NEUTRAL, 0));

    public static final RegistryObject<MobEffect> ANTIDOTE_EFFECT = MOB_EFFECTS.register("antidote_effect",
            () -> new AntidoteEffect(MobEffectCategory.NEUTRAL, 0));

    public static final RegistryObject<MobEffect> SOUL_CONSUMPTION_EFFECT = MOB_EFFECTS.register("soul_consumption_effect",
            () -> new SoulConsumptionEffect(MobEffectCategory.HARMFUL, 0));

    public static final RegistryObject<MobEffect> SOUL_CHARGE_EFFECT = MOB_EFFECTS.register("soul_charge_effect",
            () -> new SoulChargeEffect(MobEffectCategory.BENEFICIAL, 0));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }


}