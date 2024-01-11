package com.net.spacetechmod.item;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.item.custom.armor.SpaceSuitChestplateItem;
import com.net.spacetechmod.item.custom.magic.*;
import com.net.spacetechmod.item.custom.tool.HammerItem;
import com.net.spacetechmod.item.custom.tool.ModArmorItem;
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
            () -> new SwordItem(ModTiers.TITANIUM, 3, -2.4f,
                    new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(ModTiers.TITANIUM, 1, -2.8f,
                    new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(ModTiers.TITANIUM, 5, -3f,
                    new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(ModTiers.TITANIUM, 1, -3f,
                    new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(ModTiers.TITANIUM, -2, 0f,
                    new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.HELMET,
                    new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.BOOTS,
                    new Item.Properties()));
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
            () -> new SwordItem(ModTiers.COPPER, 3, -2.4f,
                    new Item.Properties()));

    public static final DeferredItem<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModTiers.COPPER, 2, -2.8f,
                    new Item.Properties()));

    public static final DeferredItem<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModTiers.COPPER, 7, -3.15f,
                    new Item.Properties()));

    public static final DeferredItem<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModTiers.COPPER, 2, -3f,
                    new Item.Properties()));

    public static final DeferredItem<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModTiers.COPPER, -1, -1.5f,
                    new Item.Properties()));

    public static final DeferredItem<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final DeferredItem<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));

    public static final DeferredItem<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));

    public static final DeferredItem<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS,
                    new Item.Properties()));
    //end of copper stuff

    //begin of turtle master stuff
    public static final DeferredItem<Item> AQUAMARINE = ITEMS.register("aquamarine",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TURTLE_MASTER_HELMET = ITEMS.register("turtle_master_helmet",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorItem.Type.HELMET,
                    new Item.Properties()));
    public static final DeferredItem<Item> TURTLE_MASTER_CHESTPLATE = ITEMS.register("turtle_master_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final DeferredItem<Item> TURTLE_MASTER_LEGGINGS = ITEMS.register("turtle_master_leggings",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final DeferredItem<Item> TURTLE_MASTER_BOOTS = ITEMS.register("turtle_master_boots",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorItem.Type.BOOTS,
                    new Item.Properties()));
    //end of turtle master stuff

    //stamps
    public static final DeferredItem<Item> BLANK_STAMP = ITEMS.register("blank_stamp",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PLATE_STAMP = ITEMS.register("plate_stamp",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> WIRE_STAMP = ITEMS.register("wire_stamp",
            () -> new Item(new Item.Properties()));
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

    //sculk thangs n' stuff
    public static final DeferredItem<Item> ECHO = ITEMS.register("echo", EchoItem::new);
    public static final DeferredItem<Item> SOUL_CRYSTAL = ITEMS.register("soul_crystal",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> SCULK_INGOT = ITEMS.register("sculk_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> SOUL_BOTTLE = ITEMS.register("soul_bottle",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> SCULK_HELMET = ITEMS.register("sculk_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SCULK, ArmorItem.Type.HELMET,
                    new Item.Properties()));
    public static final DeferredItem<Item> SCULK_CHESTPLATE = ITEMS.register("sculk_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.SCULK, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final DeferredItem<Item> SCULK_LEGGINGS = ITEMS.register("sculk_leggings",
            () -> new ModArmorItem(ModArmorMaterials.SCULK, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final DeferredItem<Item> SCULK_BOOTS = ITEMS.register("sculk_boots",
            () -> new ModArmorItem(ModArmorMaterials.SCULK, ArmorItem.Type.BOOTS,
                    new Item.Properties()));
    public static final DeferredItem<Item> LIGHTNING_STAFF = ITEMS.register("lightning_staff",
            LightningStaffItem::new);

    public static final DeferredItem<Item> MAGIC_QUIVER = ITEMS.register("magic_quiver",
            MagicQuiverItem::new);

    public static final DeferredItem<Item> TELEPORT_MARKER = ITEMS.register("teleport_marker",
            TeleportMarkerItem::new);

    public static final DeferredItem<Item> CALIBRATED_SCULK_HEART = ITEMS.register("calibrated_sculk_heart",
            CalibratedSculkHeartItem::new);

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

    //Fluid buckets

    //Fluid bottles
    public static final DeferredItem<Item> LAVA_BOTTLE = ITEMS.register("lava_bottle",
            () -> new Item(new Item.Properties().stacksTo(64)));

    //Aeronautics
    public static final DeferredItem<Item> TITAN_STEEL_INGOT = ITEMS.register("titan_steel_ingot",
            () -> new Item(new Item.Properties().stacksTo(64).fireResistant()));

    public static final DeferredItem<Item> TITAN_STEEL_PLATE = ITEMS.register("titan_steel_plate",
            () -> new Item(new Item.Properties().stacksTo(64).fireResistant()));

    public static final DeferredItem<Item> SPACESUIT_HELMET = ITEMS.register("spacesuit_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SPACESUIT, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> SPACESUIT_CHESTPLATE = ITEMS.register("spacesuit_chestplate",
            SpaceSuitChestplateItem::new);

    public static final DeferredItem<Item> SPACESUIT_LEGS = ITEMS.register("spacesuit_legs",
            () -> new ModArmorItem(ModArmorMaterials.SPACESUIT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> SPACESUIT_BOOTS = ITEMS.register("spacesuit_boots",
            () -> new ModArmorItem(ModArmorMaterials.SPACESUIT, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant()));
}