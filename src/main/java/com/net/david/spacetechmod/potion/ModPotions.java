package com.net.david.spacetechmod.potion;

import com.net.david.spacetechmod.Spacetechmod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, Spacetechmod.MOD_ID);


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    //Register all potions below this line!
    public static final RegistryObject<Potion> LEAN = POTIONS.register("lean",
            () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1),
                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 1)));

    public static final RegistryObject<Potion> LEAN_2 = POTIONS.register("lean_2",
            () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 1),
                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 1),
                    new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 1)));

    public static final RegistryObject<Potion> LEAN_3 = POTIONS.register("lean_3",
            () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 4800, 1),
                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 4800, 1),
                    new MobEffectInstance(MobEffects.DIG_SPEED, 4800, 1),
                    new MobEffectInstance(MobEffects.REGENERATION, 4800, 2),
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 4800, 2)));

    public static final RegistryObject<Potion> LEAN_4 = POTIONS.register("lean_4",
            () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 2),
                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 2),
                    new MobEffectInstance(MobEffects.DIG_SPEED, 6000, 2),
                    new MobEffectInstance(MobEffects.REGENERATION, 6000, 3),
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 6000, 3)));

    public static final RegistryObject<Potion> LEAN_5 = POTIONS.register("lean_5",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 600, 4),
                    new MobEffectInstance(MobEffects.HARM, 600, 50)));

    public static final RegistryObject<Potion> WARDEN_AWAY = POTIONS.register("anti_warden",
            () -> new Potion(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 6000, 9),
                    new MobEffectInstance(MobEffects.HEAL, 6000, 199)));




}
