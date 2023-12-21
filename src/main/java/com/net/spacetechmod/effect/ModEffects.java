package com.net.spacetechmod.effect;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(Registries.MOB_EFFECT, Spacetechmod.MOD_ID);


    public static final DeferredHolder<MobEffect, MobEffect> OILEFFECT = MOB_EFFECTS.register("oileffect",
            () -> new OilEffect(MobEffectCategory.NEUTRAL, 0));

    public static final DeferredHolder<MobEffect, MobEffect> ANTIDOTE_EFFECT = MOB_EFFECTS.register("antidote_effect",
            () -> new AntidoteEffect(MobEffectCategory.NEUTRAL, 0));

    public static final DeferredHolder<MobEffect, MobEffect> SOUL_CONSUMPTION_EFFECT = MOB_EFFECTS.register("soul_consumption_effect",
            () -> new SoulConsumptionEffect(MobEffectCategory.HARMFUL, 0));

    public static final DeferredHolder<MobEffect, MobEffect> CANNED_BREAD_EFFECT = MOB_EFFECTS.register("canned_bread_effect",
            () -> new CannedBreadEffect(MobEffectCategory.BENEFICIAL, 0));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }


}