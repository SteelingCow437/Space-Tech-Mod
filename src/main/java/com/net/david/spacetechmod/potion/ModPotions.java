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




}
