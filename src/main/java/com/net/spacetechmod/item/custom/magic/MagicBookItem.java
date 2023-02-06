package com.net.spacetechmod.item.custom.magic;

import com.net.spacetechmod.enchantment.ModEnchantments;
import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.util.ModTags;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Objects;

public class MagicBookItem extends Item {
    public MagicBookItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .fireResistant());
    }
    private TagKey<Item> general = ModTags.Items.GENERAL_MAGIC_ITEMS;
    private TagKey<Item> sculk = ModTags.Items.SCULK_MAGIC_ITEMS;
    private TagKey<Item> water = ModTags.Items.WATER_MAGIC_ITEMS;

    private String ability1 = "none";
    private String ability2 = "none";
    private String ability3 = "none";
    private String ability4 = "none";
    private String ability5 = "none";

    private final int maxAbilityCount = 5;
    private int abilityCount = 0;
    private int selectedAbility = 1;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();
        if(player.isShiftKeyDown()) {
            selectedAbility++;
            if(selectedAbility > 5) {
                selectedAbility = 1;
            }
            player.displayClientMessage(Component.literal("Ability Selected: " + getAbilitySelected()), false);
        }
        if(stack.is(ModItems.MAGIC_BOOK.get())) {
            if(offHand.getItemHolder().is(general) || offHand.getItemHolder().is(sculk) || offHand.getItemHolder().is(water)) {
                if(selectedAbility == 1) {
                    ability1 = player.getOffhandItem().getDisplayName().getString();
                    level.playSound(player, player.getOnPos(), SoundEvents.ARMOR_EQUIP_LEATHER, SoundSource.PLAYERS, 2.0f, 2.0f);
                }
                if(selectedAbility == 2) {
                    ability2 = player.getOffhandItem().getDisplayName().getString();
                    level.playSound(player, player.getOnPos(), SoundEvents.ARMOR_EQUIP_LEATHER, SoundSource.PLAYERS, 2.0f, 2.0f);
                }
                if(selectedAbility == 3) {
                    ability3 = player.getOffhandItem().getDisplayName().getString();
                    level.playSound(player, player.getOnPos(), SoundEvents.ARMOR_EQUIP_LEATHER, SoundSource.PLAYERS, 2.0f, 2.0f);
                }
                if(selectedAbility == 4) {
                    ability4 = player.getOffhandItem().getDisplayName().getString();
                    level.playSound(player, player.getOnPos(), SoundEvents.ARMOR_EQUIP_LEATHER, SoundSource.PLAYERS, 2.0f, 2.0f);
                }
                if(selectedAbility == 5) {
                    ability5 = player.getOffhandItem().getDisplayName().getString();
                    level.playSound(player, player.getOnPos(), SoundEvents.ARMOR_EQUIP_LEATHER, SoundSource.PLAYERS, 2.0f, 2.0f);
                }
            }
            if(stack.is(this) && offHandValidForUse(player) && !level.isClientSide) {
                cast(level, player);
            }
        }
        return InteractionResultHolder.success(stack);
    }
    private String getAbilitySelected() {
        if(selectedAbility == 1) {
            return ability1;
        }
        if(selectedAbility == 2) {
            return ability2;
        }
        if(selectedAbility == 3) {
            return ability3;
        }
        if(selectedAbility == 4) {
            return ability4;
        }
        if(selectedAbility == 5) {
            return ability5;
        }
        return "I AM THE ONE WHO BREAKS THE CODE";
    }

    private boolean offHandValidForUse(Player player) {
        if(player.getOffhandItem().getItemHolder().is(ModTags.Items.GENERAL_MAGIC_ITEMS)) {
            return false;
        }
        if(player.getOffhandItem().getItemHolder().is(ModTags.Items.SCULK_MAGIC_ITEMS)) {
            return false;
        }
        if(player.getOffhandItem().getItemHolder().is(ModTags.Items.WATER_MAGIC_ITEMS)) {
            return false;
        }
        return true;
    }

    private void cast(Level level, Player player) {
        //turtle/water
        if(hasFullSuitOfArmorOn(player) && hasSameSetOfArmorOn(ModArmorMaterials.TURTLE, player)) {
            if(getAbilitySelected().equalsIgnoreCase("[freeze spell]")) {
                player.experienceLevel -= 10;
                Player target = level.getNearestPlayer(TargetingConditions.DEFAULT, player);
                if(target != null && !level.isClientSide) {
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 4));
                }
            }
            if(getAbilitySelected().equalsIgnoreCase("[mining fatigue spell]")) {
                player.experienceLevel -= 10;
                Player target = level.getNearestPlayer(TargetingConditions.DEFAULT, player);
                if(target != null && !level.isClientSide) {
                    target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200, 4));
                }
            }
        }
        //sculk
        if(hasFullSuitOfArmorOn(player) && hasSameSetOfArmorOn(ModArmorMaterials.SCULK, player)) {
            if(getAbilitySelected().equalsIgnoreCase("[summon warden]")) {
                SpawnUtil.trySpawnMob(EntityType.WARDEN, MobSpawnType.TRIGGERED, (ServerLevel) level, player.getOnPos(), 20, 5, 6, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER).isPresent();
                player.experienceLevel -= 80;
            }
        }
        if(getAbilitySelected().equalsIgnoreCase("[freeze spell]")) {
            player.experienceLevel -= 10;
            Player target = level.getNearestPlayer(TargetingConditions.DEFAULT, player);
            if(target != null && !level.isClientSide) {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 4));
            }
        }
        //general
        if(getAbilitySelected().equalsIgnoreCase("[human rocket]")) {
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 9));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 160, 9));
            player.experienceLevel -= 5;
        }
        if(getAbilitySelected().equalsIgnoreCase("[freeze time]")) {
            player.experienceLevel -= 100;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(getAbilitySelected().equalsIgnoreCase("[summon sword]")) {
            ItemStack stack = Items.NETHERITE_SWORD.getDefaultInstance();
            stack.enchant(Enchantments.SHARPNESS, 5);
            stack.enchant(Enchantments.KNOCKBACK, 2);
            stack.enchant(ModEnchantments.MAGIC_DECAY.get(), 1);
            player.addItem(stack);
            player.experienceLevel -= 20;
        }
        if(getAbilitySelected().equalsIgnoreCase("[last_resort]")) {
            Explosion explosion = new Explosion(level, player, player.getX(), player.getY(), player.getZ(), 50f, false, Explosion.BlockInteraction.KEEP);
            explosion.explode();
            player.experienceLevel -= 75;
            player.setHealth(5);
            player.setPos(Vec3.atCenterOf(Objects.requireNonNull(getSpawnPosition(level)).pos()));
        }
        level.playSound(player, player.getOnPos(), SoundEvents.ILLUSIONER_CAST_SPELL, SoundSource.PLAYERS, 2.0f, 2.0f);
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasSameSetOfArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
    @Nullable
    public static GlobalPos getSpawnPosition(Level level) {
        return level.dimensionType().natural() ? GlobalPos.of(level.dimension(), level.getSharedSpawnPos()) : null;
    }
}
