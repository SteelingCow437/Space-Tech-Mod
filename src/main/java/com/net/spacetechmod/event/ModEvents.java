package com.net.spacetechmod.event;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.item.ModItems;
import com.net.spacetechmod.item.custom.armor.SpaceSuitChestplateItem;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import org.apache.logging.log4j.core.jmx.Server;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Spacetechmod.MOD_ID)
    public static class NeoForgeEvents {
        private static int playerTickEventTimer = 0;
        private static float playerHealth;
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.SERVER) {
                if(!ModLists.SAFE_BREATHING_LIST.contains(event.player.level().dimension()) && !event.player.getAbilities().instabuild) {
                    if(playerTickEventTimer >= 40) {
                        if(!event.player.hasEffect(ModEffects.SPACE_BREATHING_EFFECT.get())) {
                            event.player.sendSystemMessage(Component.literal(
                                    "You can't breathe in space!"
                            ));
                            playerHealth = event.player.getHealth();
                            event.player.setHealth(playerHealth -= 2);
                        }
                        playerTickEventTimer = 0;
                    }
                    else {
                        ++playerTickEventTimer;
                    }
                }
            }
        }

        public static void handlePlayerTeleport(Player player) {
            Item item = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
            if(player.level() instanceof ServerLevel && item == ModItems.SPACESUIT_CHESTPLATE.get()) {
                ServerLevel serverLevel = ((ServerLevel) player.level());
                player.changeDimension(((SpaceSuitChestplateItem) item).getSelectedPlanet());
            }
        }
    }
}
