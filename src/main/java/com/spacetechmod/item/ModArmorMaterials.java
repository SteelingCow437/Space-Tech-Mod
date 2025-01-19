package com.spacetechmod.item;

import com.spacetechmod.Spacetechmod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final Holder<ArmorMaterial> TITANIUM = register("titanium", Util.make(new EnumMap<>(ArmorItem.Type.class), health -> {
        health.put(ArmorItem.Type.BOOTS, 3);
        health.put(ArmorItem.Type.LEGGINGS, 5);
        health.put(ArmorItem.Type.CHESTPLATE, 7);
        health.put(ArmorItem.Type.HELMET, 3);
        health.put(ArmorItem.Type.BODY, 6);
    }), 25, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.1F, () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "titanium"))));

    public static final Holder<ArmorMaterial> COPPER = register("copper", Util.make(new EnumMap<>(ArmorItem.Type.class), health -> {
        health.put(ArmorItem.Type.BOOTS, 1);
        health.put(ArmorItem.Type.LEGGINGS, 4);
        health.put(ArmorItem.Type.CHESTPLATE, 5);
        health.put(ArmorItem.Type.HELMET, 2);
        health.put(ArmorItem.Type.BODY, 4);
    }), 29, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F,
            () -> Ingredient.of(Items.COPPER_INGOT),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "copper"))));
                    //new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "copper"), "_overlay", false))); It's not dyeable

    public static final Holder<ArmorMaterial> TURTLE = register("turtle", Util.make(new EnumMap<>(ArmorItem.Type.class), health -> {
        health.put(ArmorItem.Type.BOOTS, 4);
        health.put(ArmorItem.Type.LEGGINGS, 7);
        health.put(ArmorItem.Type.CHESTPLATE, 9);
        health.put(ArmorItem.Type.HELMET, 4);
        health.put(ArmorItem.Type.BODY, 8);
    }), 100, SoundEvents.ARMOR_EQUIP_TURTLE, 2.0F, 0.5F, () -> Ingredient.of(Items.TURTLE_HELMET),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "turtle"))));

    public static final Holder<ArmorMaterial> SPACESUIT = register("spacesuit", Util.make(new EnumMap<>(ArmorItem.Type.class), health -> {
        health.put(ArmorItem.Type.BOOTS, 4);
        health.put(ArmorItem.Type.LEGGINGS, 7);
        health.put(ArmorItem.Type.CHESTPLATE, 8);
        health.put(ArmorItem.Type.HELMET, 4);
        health.put(ArmorItem.Type.BODY, 7);
    }), 40, SoundEvents.ARMOR_EQUIP_IRON, 1.2F, 0.4F, () -> Ingredient.of(ModItems.TITAN_STEEL_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "spacesuit"))));

    public static final Holder<ArmorMaterial> RESONATOR = register("resonator", Util.make(new EnumMap<>(ArmorItem.Type.class), health -> {
                health.put(ArmorItem.Type.BOOTS, 0);
                health.put(ArmorItem.Type.LEGGINGS, 0);
                health.put(ArmorItem.Type.CHESTPLATE, 4);
                health.put(ArmorItem.Type.HELMET, 0);
                health.put(ArmorItem.Type.BODY, 0);
            }), 40, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.0F, 0.0F, () -> Ingredient.of(ModItems.AQUAMARINE_RESONATOR.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "resonator"))));


    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient
    ) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace(name)));
        return register(name, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, list);
    }

    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngridient,
            List<ArmorMaterial.Layer> layers
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, defense.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.withDefaultNamespace(name),
                new ArmorMaterial(enummap, enchantmentValue, equipSound, repairIngridient, layers, toughness, knockbackResistance)
        );
    }
}
