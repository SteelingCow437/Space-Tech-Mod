package com.net.spacetechmod.potion;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.effect.ModEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(Registries.POTION, Spacetechmod.MOD_ID);


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    //Register all potions below this line!
    public static final DeferredHolder<Potion, Potion> LEAN_2 = POTIONS.register("lean_2",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 600, 4),
                    new MobEffectInstance(MobEffects.HARM, 600, 4)));

    public static final DeferredHolder<Potion, Potion> OIL = POTIONS.register("oil",
            () -> new Potion(new MobEffectInstance(ModEffects.OILEFFECT.getDelegate(), 600, 0)));
}
