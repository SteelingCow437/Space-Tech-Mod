package com.spacetechmod.item;

import com.spacetechmod.Spacetechmod;
import com.spacetechmod.item.custom.armor.AquamarineResonatorItem;
import com.spacetechmod.item.custom.armor.SpaceSuitChestplateItem;
import com.spacetechmod.item.custom.armor.Z7ChestplateItem;
import com.spacetechmod.item.custom.space.BigKahunaItem;
import com.spacetechmod.item.custom.space.PlanetKeyItem;
import com.spacetechmod.item.custom.space.StarGateControllerItem;
import com.spacetechmod.item.custom.space.orbital.OrbitalMarkerItem;
import com.spacetechmod.item.custom.tool.HammerItem;
import com.spacetechmod.item.custom.tool.ModArmorItem;
import com.spacetechmod.item.custom.tool.ShipBlueprintItem;
import com.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Spacetechmod.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
    //Declare all items below this line

    //begin of titanium stuff
    public static final DeferredItem<Item> HAMMER = ITEMS.register("hammer", HammerItem::new);

    public static final DeferredItem<Item> TITANIUM_INGOT = ITEMS.register( "titanium_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new SwordItem(ModTiers.TITANIUM,
                    new Item.Properties().attributes(SwordItem.createAttributes(ModTiers.TITANIUM, 4, -2.4f))));

    public static final DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(ModTiers.TITANIUM,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModTiers.TITANIUM, 1, -2.8f))));

    public static final DeferredItem<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(ModTiers.TITANIUM,
                    new Item.Properties().attributes(AxeItem.createAttributes(ModTiers.TITANIUM, 4, -3f))));

    public static final DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(ModTiers.TITANIUM,
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModTiers.TITANIUM, 1.5f, -3f))));

    public static final DeferredItem<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(ModTiers.TITANIUM,
                    new Item.Properties().attributes(HoeItem.createAttributes(ModTiers.TITANIUM, 1.5f, -3.0f))));

    public static final DeferredItem<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(22))));

    public static final DeferredItem<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(22))));

    public static final DeferredItem<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(22))));

    public static final DeferredItem<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(22))));

    //end of titanium stuff

    //begin of foodstuffs
    public static final DeferredItem<Item> CANADA = ITEMS.register("canada",
            () -> new Item(new Item.Properties().food(ModFoods.CANADA)));

    public static final DeferredItem<Item> LEAN = ITEMS.register( "lean",
            () -> new Item(new Item.Properties().food(ModFoods.LEAN)));

    public static final DeferredItem<Item> BAGUETTE = ITEMS.register("baguette",
            () -> new Item(new Item.Properties().food(ModFoods.BAGUETTE)));

    public static final DeferredItem<Item> ANTIDOTE = ITEMS.register("antidote",
            () -> new Item(new Item.Properties().food(ModFoods.ANTIDOTE)));

    public static final DeferredItem<Item> CANNED_BREAD = ITEMS.register("canned_bread",
            () -> new Item(new Item.Properties().food(ModFoods.CANNED_BREAD)));
    //end of foodstuffs


    //begin of copper stuff
    public static final DeferredItem<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModTiers.COPPER,
                    new Item.Properties().attributes(SwordItem.createAttributes(ModTiers.COPPER, 3, -2.4f))));

    public static final DeferredItem<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModTiers.COPPER,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModTiers.COPPER, 1, -2.8f))));

    public static final DeferredItem<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModTiers.COPPER,
                    new Item.Properties().attributes(AxeItem.createAttributes(ModTiers.COPPER, 4, -3f))));

    public static final DeferredItem<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModTiers.COPPER,
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModTiers.COPPER, 1.5f, -3f))));

    public static final DeferredItem<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModTiers.COPPER,
                    new Item.Properties().attributes(HoeItem.createAttributes(ModTiers.COPPER, -1, 0))));

    public static final DeferredItem<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(16))));

    public static final DeferredItem<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))));

    public static final DeferredItem<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(16))));

    public static final DeferredItem<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(16))));
    //end of copper stuff

    //begin of turtle master stuff
    public static final DeferredItem<Item> AQUAMARINE = ITEMS.register("aquamarine",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TURTLE_MASTER_HELMET = ITEMS.register("turtle_master_helmet",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(45))));

    public static final DeferredItem<Item> TURTLE_MASTER_CHESTPLATE = ITEMS.register("turtle_master_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(45))));

    public static final DeferredItem<Item> TURTLE_MASTER_LEGGINGS = ITEMS.register("turtle_master_leggings",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(45))));

    public static final DeferredItem<Item> TURTLE_MASTER_BOOTS = ITEMS.register("turtle_master_boots",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(45))));
    //end of turtle master stuff

    //stamps
    public static final DeferredItem<Item> BLANK_STAMP = ITEMS.register("blank_stamp",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> PLATE_STAMP = ITEMS.register("plate_stamp",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> WIRE_STAMP = ITEMS.register("wire_stamp",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> INGOT_STAMP = ITEMS.register("ingot_stamp",
            () -> new Item(new Item.Properties().stacksTo(1)));

    //steel stuff
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    //plates
    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PLATE = ITEMS.register("copper_plate",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_PLATE = ITEMS.register("titanium_plate",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_PLATE = ITEMS.register("bronze_plate",
            () -> new Item(new Item.Properties()));

    //wires
    public static final DeferredItem<Item> COPPER_WIRING = ITEMS.register("copper_wiring",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COPPER_REDSTIDE_WIRING = ITEMS.register("copper_redstide_wiring",
            () -> new Item(new Item.Properties()));

    //Alloy Powders
    public static final DeferredItem<Item> IRON_POWDER = ITEMS.register("iron_powder",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CARBON_POWDER = ITEMS.register("carbon_powder",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_BLEND = ITEMS.register("steel_blend",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_POWDER = ITEMS.register("copper_powder",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_REDSTIDE_BLEND = ITEMS.register("copper_redstide_blend",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_REDSTIDE_INGOT = ITEMS.register("copper_redstide_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_POWDER = ITEMS.register("titanium_powder",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_BLEND = ITEMS.register("bronze_blend",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_POWDER = ITEMS.register("tin_powder",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MINERAL_CLUMP = ITEMS.register("mineral_clump",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TITAN_STEEL_BLEND = ITEMS.register("titan_steel_blend",
            () -> new Item(new Item.Properties()));
    //tin stuff
    public static final DeferredItem<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));
    //bronze
    public static final DeferredItem<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DEBUG_STICK = ITEMS.register("debug_stick",
            () -> new Item(new Item.Properties()));

    //misc. items
    public static final DeferredItem<Item> TIN_CAN = ITEMS.register("tin_can",
            () -> new Item(new Item.Properties()));

    /*Fluid bottles GONE FOR NOW
    public static final DeferredItem<Item> LAVA_BOTTLE = ITEMS.register("lava_bottle",
            () -> new Item(new Item.Properties().stacksTo(64))); */

    //Aeronautics
    public static final DeferredItem<Item> TITAN_STEEL_INGOT = ITEMS.register("titan_steel_ingot",
            () -> new Item(new Item.Properties().stacksTo(64).fireResistant()));

    public static final DeferredItem<Item> TITAN_STEEL_PLATE = ITEMS.register("titan_steel_plate",
            () -> new Item(new Item.Properties().stacksTo(64).fireResistant()));

    public static final DeferredItem<Item> SPACESUIT_HELMET = ITEMS.register("spacesuit_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SPACESUIT, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(28))));

    public static final DeferredItem<Item> SPACESUIT_CHESTPLATE = ITEMS.register("spacesuit_chestplate",
            SpaceSuitChestplateItem::new);

    public static final DeferredItem<Item> SPACESUIT_LEGS = ITEMS.register("spacesuit_legs",
            () -> new ModArmorItem(ModArmorMaterials.SPACESUIT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(28))));

    public static final DeferredItem<Item> SPACESUIT_BOOTS = ITEMS.register("spacesuit_boots",
            () -> new ModArmorItem(ModArmorMaterials.SPACESUIT, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(28))));

    //Z7 Suit! (Yes I know it's a Garden Warfare 2, Mass Effect, AND NASA reference, stop bitching about it!)
    public static final DeferredItem<Item> Z7_HELMET = ITEMS.register("z7_helmet",
            () -> new ModArmorItem(ModArmorMaterials.Z7, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(50))));

    public static final DeferredItem<Item> Z7_CHESTPLATE = ITEMS.register("z7_chestplate",
            Z7ChestplateItem::new);

    public static final DeferredItem<Item> Z7_LEGGINGS = ITEMS.register("z7_leggings",
            () -> new ModArmorItem(ModArmorMaterials.Z7, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(50))));

    public static final DeferredItem<Item> Z7_BOOTS = ITEMS.register("z7_boots",
            () -> new ModArmorItem(ModArmorMaterials.Z7, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(50))));

    //planet keys
    public static final DeferredItem<Item> MOON_KEY = ITEMS.register("moon_key",
            () -> new PlanetKeyItem(ModDimensions.MOON));

    //Stargate controller
    public static final DeferredItem<Item> STARGATE_CONTROLLER = ITEMS.register("stargate_controller",
            StarGateControllerItem::new);

    public static final DeferredItem<Item> SHIP_BLUEPRINT = ITEMS.register("ship_blueprint",
            ShipBlueprintItem::new);

    //The Big Kahuna
    public static final DeferredItem<Item> BIG_KAHUNA = ITEMS.register("big_kahuna",
            BigKahunaItem::new);

    public static final DeferredItem<Item> KAHUNA_CHARGE = ITEMS.register("kahuna_charge",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> KAHUNA_SHELL = ITEMS.register("kahuna_shell",
            () -> new Item(new Item.Properties().stacksTo(1).fireResistant()));


    //Orbital shells
    public static final DeferredItem<Item> ORBITAL_CASING = ITEMS.register("orbital_casing",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final DeferredItem<Item> ORBITAL_TNT_SHELL = ITEMS.register("orbital_tnt_shell",
            () -> new Item(new Item.Properties().stacksTo(8)));

    public static final DeferredItem<Item> ORBITAL_FLAME_SHELL = ITEMS.register("orbital_flame_shell",
            () -> new Item(new Item.Properties().stacksTo(8)));

    //My life for Super Earth!
    public static final DeferredItem<Item> ORBITAL_MARKER = ITEMS.register("orbital_marker",
            OrbitalMarkerItem::new);

    //Byzanium!
    public static final DeferredItem<Item> BYZANIUM_INGOT = ITEMS.register("byzanium_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final DeferredItem<Item> AQUAMARINE_RESONATOR = ITEMS.register("aquamarine_resonator",
            AquamarineResonatorItem::new);

    public static final DeferredItem<Item> VAULT_KEY = ITEMS.register("vault_key",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    //Microcrafting
    public static final DeferredItem<Item> RED_COIL = ITEMS.register("red_coil",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final DeferredItem<Item> TRANSFUNCTIONER = ITEMS.register("transfunctioner",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> COPPER_COIL = ITEMS.register("copper_coil",
            () -> new Item(new Item.Properties().stacksTo(64)));
}