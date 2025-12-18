package com.astronautica.effect;

import com.astronautica.Astronautica;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(Registries.MOB_EFFECT, Astronautica.MOD_ID);


    public static final DeferredHolder<MobEffect, MobEffect> OILEFFECT = MOB_EFFECTS.register("oileffect",
            () -> new OilEffect(MobEffectCategory.NEUTRAL, 0));

    public static final DeferredHolder<MobEffect, MobEffect> ANTIDOTE_EFFECT = MOB_EFFECTS.register("antidote_effect",
            () -> new AntidoteEffect(MobEffectCategory.NEUTRAL, 0));

    public static final DeferredHolder<MobEffect, MobEffect> CANNED_BREAD_EFFECT = MOB_EFFECTS.register("canned_bread_effect",
            () -> new CannedBreadEffect(MobEffectCategory.BENEFICIAL, 0));

    public static final DeferredHolder<MobEffect, MobEffect> SPACE_BREATHING_EFFECT = MOB_EFFECTS.register("space_breathing",
            () -> new SpaceBreathingEffect(MobEffectCategory.BENEFICIAL, 0));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }


}