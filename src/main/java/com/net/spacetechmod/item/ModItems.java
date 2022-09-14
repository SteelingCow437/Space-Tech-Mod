package com.net.spacetechmod.item;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.item.custom.ModArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
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

    //Declare all items below this line

    //begin of titanium stuff
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register( "titanium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));
    public static final RegistryObject<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new SwordItem(ModTiers.TITANIUM, 3, -2.4f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(ModTiers.TITANIUM, 1, -2.8f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(ModTiers.TITANIUM, 5, -3f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(ModTiers.TITANIUM, 1, -3f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(ModTiers.TITANIUM, -2, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));
    //end of titanium stuff

    //begin of foodstuffs
    public static final RegistryObject<Item> LEAN = ITEMS.register( "lean",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_FOODS).food(ModFoods.LEAN)));

    public static final RegistryObject<Item> BAGUETTE = ITEMS.register("baguette",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_FOODS).food(ModFoods.BAGUETTE)));
    //end of foodstuffs


    //begin of copper stuff
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModTiers.COPPER, 3, -2.4f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModTiers.COPPER, 2, -2.8f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModTiers.COPPER, 7, -3.15f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModTiers.COPPER, 2, -3f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModTiers.COPPER, -1, -1.5f,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));
    //end of copper stuff

    //begin of turtle master stuff

    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    public static final RegistryObject<Item> TURTLE_MASTER_HELMET = ITEMS.register("turtle_master_helmet",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TURTLE_MASTER_CHESTPLATE = ITEMS.register("turtle_master_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TURTLE_MASTER_LEGGINGS = ITEMS.register("turtle_master_leggings",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));

    public static final RegistryObject<Item> TURTLE_MASTER_BOOTS = ITEMS.register("turtle_master_boots",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.STM_TOOLS)));
    //end of turtle master stuff

    //stamps

    public static final RegistryObject<Item> BLANK_STAMP = ITEMS.register("blank_stamp",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    public static final RegistryObject<Item> PLATE_STAMP = ITEMS.register("plate_stamp",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    public static final RegistryObject<Item> WIRE_STAMP = ITEMS.register("wire_stamp",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    //begin of steel stuff

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

}

