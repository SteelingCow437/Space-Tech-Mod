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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.EnumSet;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Spacetechmod.MOD_ID)
    public static class NeoForgeEvents {
        private static int playerBreathTimer = 0;
        private static int playerGravityTimer = 0;
        private static float playerHealth;
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.SERVER) {
                if(!ModLists.SAFE_BREATHING_LIST.contains(event.player.level().dimension()) && !event.player.getAbilities().instabuild) {
                    if(playerBreathTimer >= 40) {
                        if(!event.player.hasEffect(ModEffects.SPACE_BREATHING_EFFECT.get())) {
                            event.player.sendSystemMessage(Component.literal(
                                    "You can't breathe in space!"
                            ));
                            playerHealth = event.player.getHealth();
                            event.player.setHealth(playerHealth -= 2);
                        }
                        playerBreathTimer = 0;
                    }
                    else {
                        ++playerBreathTimer;
                    }
                }
                if(playerGravityTimer >= 20) {
                    handleGravity(event.player, event.player.level().dimension());
                    playerGravityTimer = 0;
                }
                else {
                    ++playerGravityTimer;
                }
                if(event.player.getY() >= 70000) {
                    Item item = event.player.getItemBySlot(EquipmentSlot.CHEST).getItem();
                    if(item == ModItems.SPACESUIT_CHESTPLATE.get()) {
                        MinecraftServer server = event.player.getServer();
                        ResourceKey<Level> selectedPlanet =((SpaceSuitChestplateItem) item).getSelectedPlanet();
                        ServerLevel destinationLevel = server.getLevel(selectedPlanet);
                        event.player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9, 2));
                        event.player.teleportTo(destinationLevel, 0, 100, 0, EnumSet.noneOf(RelativeMovement.class), 2.0f, 2.0f);
                        handleGravity(event.player, destinationLevel.dimension());
                    }
                }
            }
        }

        private static void handleGravity(Player player, ResourceKey<Level> planet) {
            AttributeInstance gravity = player.getAttribute(NeoForgeMod.ENTITY_GRAVITY.value());
            switch(ModLists.PLANET_LIST.indexOf(planet)) {
                default -> {
                    for(int i = 0; i < ModLists.GRAVITY_CONSTANTS.size(); ++i) {
                        if(gravity.hasModifier(ModLists.GRAVITY_CONSTANTS.get(i))) {
                            gravity.removeModifier(ModLists.GRAVITY_CONSTANTS.get(i).getId());
                        }
                    }
                }
                case 1 -> {
                    if(!gravity.hasModifier(ModAttributeModifiers.MOON_GRAVITY)) {
                        gravity.addTransientModifier(ModAttributeModifiers.MOON_GRAVITY);
                    }
                }

            }
        }
    }
}
