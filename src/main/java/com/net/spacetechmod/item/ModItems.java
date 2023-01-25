package com.net.spacetechmod.item;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.item.custom.HammerItem;
import com.net.spacetechmod.item.custom.ModArmorItem;
import com.net.spacetechmod.item.custom.sculk.*;
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

    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", HammerItem::new);
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
    public static final RegistryObject<Item> CANADA = ITEMS.register("canada",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_FOODS).food(ModFoods.CANADA)));
    public static final RegistryObject<Item> LEAN = ITEMS.register( "lean",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_FOODS).food(ModFoods.LEAN)));

    public static final RegistryObject<Item> BAGUETTE = ITEMS.register("baguette",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_FOODS).food(ModFoods.BAGUETTE)));

    public static final RegistryObject<Item> ANTIDOTE = ITEMS.register("antidote",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_FOODS).food(ModFoods.ANTIDOTE)));
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

    //steel stuff

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    //plates n' wires

    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    public static final RegistryObject<Item> TITANIUM_PLATE = ITEMS.register("titanium_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    public static final RegistryObject<Item> COPPER_WIRING = ITEMS.register("copper_wiring",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

    //circuits

    public static final RegistryObject<Item> BASIC_CIRCUIT = ITEMS.register("basic_circuit",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));



    //sculk thangs n' stuff

    public static final RegistryObject<Item> ECHO = ITEMS.register("echo", EchoItem::new);

    public static final RegistryObject<Item> SOUL_CRYSTAL = ITEMS.register("soul_crystal",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).tab(ModCreativeModeTab.STM_SCULK)));
    public static final RegistryObject<Item> SCULK_INGOT = ITEMS.register("sculk_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).tab(ModCreativeModeTab.STM_SCULK)));

    public static final RegistryObject<Item> SWORD_BOOK = ITEMS.register("sword_book", SwordBookItem::new);

    public static final RegistryObject<Item> WARDEN_BOOK = ITEMS.register("warden_book", WardenBookItem::new);

    public static final RegistryObject<Item> FREEZE_BOOK = ITEMS.register("freeze_book", FreezeBookItem::new);

    public static final RegistryObject<Item> BOOST_BOOK = ITEMS.register("boost_book", BoostBookItem::new);

    public static final RegistryObject<Item> LAST_RESORT_BOOK = ITEMS.register("last_resort", LastResortBookItem::new);

    public static final RegistryObject<Item> FREEZE_TIME_BOOK = ITEMS.register("freeze_time", FreezeTimeBookItem::new);

    public static final RegistryObject<Item> SOUL_BOTTLE = ITEMS.register("soul_bottle",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).tab(ModCreativeModeTab.STM_SCULK)));

    public static final RegistryObject<Item> SCULK_HELMET = ITEMS.register("sculk_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SCULK, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.STM_SCULK)));

    public static final RegistryObject<Item> SCULK_CHESTPLATE = ITEMS.register("sculk_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.SCULK, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.STM_SCULK)));

    public static final RegistryObject<Item> SCULK_LEGGINGS = ITEMS.register("sculk_leggings",
            () -> new ModArmorItem(ModArmorMaterials.SCULK, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.STM_SCULK)));

    public static final RegistryObject<Item> SCULK_BOOTS = ITEMS.register("sculk_boots",
            () -> new ModArmorItem(ModArmorMaterials.SCULK, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.STM_SCULK)));

    //Alloy Powders
    public static final RegistryObject<Item> IRON_POWDER = ITEMS.register("iron_powder",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));
    public static final RegistryObject<Item> CARBON_POWDER = ITEMS.register("carbon_powder",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));
    public static final RegistryObject<Item> STEEL_BLEND = ITEMS.register("steel_blend",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));
    public static final RegistryObject<Item> COPPER_POWDER = ITEMS.register("copper_powder",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));
    public static final RegistryObject<Item> COPPER_REDSTIDE_BLEND = ITEMS.register("copper_redstide_blend",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));
    public static final RegistryObject<Item> COPPER_REDSTIDE_INGOT = ITEMS.register("copper_redstide_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));
    public static final RegistryObject<Item> TITANIUM_POWDER = ITEMS.register("titanium_powder",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STM_ITEMS)));

}