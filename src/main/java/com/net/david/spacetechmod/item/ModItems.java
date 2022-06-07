package com.net.david.spacetechmod.item;

import com.net.david.spacetechmod.Spacetechmod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Spacetechmod.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    //Declare all items below this comment
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register( "titanium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));
    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));


}
