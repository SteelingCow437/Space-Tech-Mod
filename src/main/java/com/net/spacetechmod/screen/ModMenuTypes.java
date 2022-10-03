package com.net.spacetechmod.screen;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.screen.alloyfurnace.AlloyFurnaceMenu;
import com.net.spacetechmod.screen.burnerpress.BurnerPressMenu;
import com.net.spacetechmod.screen.machinetable.MachineTableMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Spacetechmod.MOD_ID);


    public static final RegistryObject<MenuType<AlloyFurnaceMenu>> ALLOY_FURNACE_MENU =
            registerMenuType(AlloyFurnaceMenu::new, "alloy_furnace_menu");

    public static final RegistryObject<MenuType<BurnerPressMenu>> BURNER_PRESS_MENU =
            registerMenuType(BurnerPressMenu::new, "burner_press_menu");

    public static final RegistryObject<MenuType<MachineTableMenu>> MACHINE_TABLE_MENU =
            registerMenuType(MachineTableMenu::new, "machine_table_menu");



    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
