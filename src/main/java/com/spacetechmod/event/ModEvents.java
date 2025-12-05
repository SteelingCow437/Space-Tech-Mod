package com.spacetechmod.event;

import com.spacetechmod.Spacetechmod;
import com.spacetechmod.client.renderer.MoonDimensionSpecialEffects;
import com.spacetechmod.effect.ModEffects;
import com.spacetechmod.item.ModItems;
import com.spacetechmod.item.custom.armor.SpaceSuitChestplateItem;
import com.spacetechmod.item.custom.armor.Z7ChestplateItem;
import com.spacetechmod.util.ModAttributeModifiers;
import com.spacetechmod.util.ModLists;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.EnumSet;

public class ModEvents {

    public static void initModBusEvents(IEventBus eventBus) {
        eventBus.addListener(ModEvents::registerDimEffects);
    }

    private static void registerDimEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(Spacetechmod.MOD_ID, "moon_type"), new MoonDimensionSpecialEffects());
    }


    @EventBusSubscriber(modid = Spacetechmod.MOD_ID)
    public static class NeoForgeEvents {
        //space stuff
        private static int playerBreathTimer = 0;
        private static int playerGravityTimer = 0;
        private static float playerHealth;
        private static LivingEntity entity;
        private static Player player;

        @SubscribeEvent
        public static void onPlayerTick(PlayerTickEvent.Pre event) {
            if (!event.getEntity().level().isClientSide) {
                entity = event.getEntity();
                player = ((Player) entity);
                handleFalls(player, entity.level().dimension());
                if (ModLists.NO_BREATHING_LIST.contains(player.level().dimension()) && !player.getAbilities().instabuild && !player.getAbilities().invulnerable) {
                    if (playerBreathTimer >= 200) {
                        if (!player.hasEffect(ModEffects.SPACE_BREATHING_EFFECT)) {
                            player.sendSystemMessage(Component.literal(
                                    "You can't breathe in space!"
                            ));
                            playerHealth = player.getHealth();
                            player.setHealth(playerHealth -= 10);
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
                if (player.getY() >= 50000) {
                    Item item = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
                    if (item == ModItems.SPACESUIT_CHESTPLATE.get() || item == ModItems.Z7_CHESTPLATE.get()) {
                        MinecraftServer server = player.getServer();
                        ResourceKey<Level> selectedPlanet;
                        try {
                            selectedPlanet = ((SpaceSuitChestplateItem) item).getSelectedPlanet();
                        } catch (Exception e) {
                            selectedPlanet = ((Z7ChestplateItem) item).getSelectedPlanet();
                        }
                        ServerLevel destinationLevel = server.getLevel(selectedPlanet);
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9, 2));
                        player.teleportTo(destinationLevel, player.getX(), 1500, player.getZ(), EnumSet.noneOf(RelativeMovement.class), 2.0f, 2.0f);
                    }
                }
            }
        }

        private static void handleGravity(LivingEntity entity, ResourceKey<Level> planet) {
            AttributeInstance gravity = entity.getAttribute(Attributes.GRAVITY.getDelegate());
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

        private static void handleFalls(LivingEntity entity, ResourceKey<Level> dimension) {
            switch(ModLists.PLANET_LIST.indexOf(dimension)) {
                case 1 -> entity.resetFallDistance();
            }
        }
    }


}