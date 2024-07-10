package com.net.spacetechmod.event;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.item.custom.armor.SpaceSuitChestplateItem;
import com.net.spacetechmod.util.ModAttributeModifiers;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.EnumSet;

public class ModEvents {
    @EventBusSubscriber(modid = Spacetechmod.MOD_ID)
    public static class NeoForgeEvents {
        //space stuff
        private static int playerBreathTimer = 0;
        private static int playerGravityTimer = 0;
        private static float playerHealth;
        private static Player player;

        @SubscribeEvent
        public static void onPlayerTick(PlayerTickEvent.Pre event) {
            if (!event.getEntity().level().isClientSide) {
                player = event.getEntity();
                if (!ModLists.SAFE_BREATHING_LIST.contains(player.level().dimension()) && !player.getAbilities().instabuild) {
                    if (playerBreathTimer >= 40) {
                        if (!player.hasEffect(ModEffects.SPACE_BREATHING_EFFECT.getDelegate())) {
                            player.sendSystemMessage(Component.literal(
                                    "You can't breathe in space!"
                            ));
                            playerHealth = player.getHealth();
                            player.setHealth(playerHealth -= 2);
                        }
                        playerBreathTimer = 0;
                    } else {
                        ++playerBreathTimer;
                    }
                }
                if (playerGravityTimer >= 20) {
                    handleGravity(player, player.level().dimension());
                    playerGravityTimer = 0;
                } else {
                    ++playerGravityTimer;
                }
                if (player.getY() >= 70000) {
                    Item item = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
                    if (item == ModItems.SPACESUIT_CHESTPLATE.get()) {
                        MinecraftServer server = player.getServer();
                        ResourceKey<Level> selectedPlanet = ((SpaceSuitChestplateItem) item).getSelectedPlanet();
                        ServerLevel destinationLevel = server.getLevel(selectedPlanet);
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9, 2));
                        player.teleportTo(destinationLevel, 0, 100, 0, EnumSet.noneOf(RelativeMovement.class), 2.0f, 2.0f);
                        handleGravity(player, destinationLevel.dimension());
                    }
                }
            }
        }

        private static void handleGravity(Player player, ResourceKey<Level> planet) {
            AttributeInstance gravity = player.getAttribute(Attributes.GRAVITY.getDelegate());
            switch (ModLists.PLANET_LIST.indexOf(planet)) {
                default -> {
                    for (int i = 0; i < ModLists.GRAVITY_CONSTANTS.size(); ++i) {
                        if (gravity.hasModifier(ModLists.GRAVITY_CONSTANTS.get(i).id())) {
                            gravity.removeModifier(ModLists.GRAVITY_CONSTANTS.get(i));
                        }
                    }
                }
                case 1 -> {
                    if (!gravity.hasModifier(ModAttributeModifiers.MOON_GRAVITY.id())) {
                        gravity.addTransientModifier(ModAttributeModifiers.MOON_GRAVITY);
                    }
                }

            }
        }
    }
}