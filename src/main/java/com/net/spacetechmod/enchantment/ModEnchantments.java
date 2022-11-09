package com.net.spacetechmod.enchantment;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Spacetechmod.MOD_ID);

    public static final RegistryObject<Enchantment> MAGIC_DECAY = ENCHANTMENTS.register("magic_decay", MagicDecayEnchantment::new);
    
    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
