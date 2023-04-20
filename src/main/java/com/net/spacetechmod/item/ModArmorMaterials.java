package com.net.spacetechmod.item;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    TITANIUM("titanium", 78, new int[]{3, 6, 7, 3}, 12, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.1F, () -> {
        return Ingredient.of(ModItems.TITANIUM_INGOT.get());
    }),
    COPPER("copper", 29, new int[]{1, 5, 6, 2}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),

    TURTLE("turtle_master", 156, new int[]{4, 7, 9, 4}, 20, SoundEvents.ARMOR_EQUIP_TURTLE, 2.0F, 0.5F, () -> {
        return Ingredient.of(Items.TURTLE_HELMET);
    }),

    SCULK("sculk", 30, new int[]{2, 5, 6, 2}, 50, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.5F, 0.0F, () -> {
        return Ingredient.of(ModItems.SCULK_INGOT.get());
    });


    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue,
                      SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> ingredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(ingredient);
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return getDurabilityForSlot(type.getSlot());
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return getDefenseForSlot(type.getSlot());
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return Spacetechmod.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
