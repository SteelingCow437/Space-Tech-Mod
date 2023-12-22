package com.net.spacetechmod.event;

import com.net.spacetechmod.Spacetechmod;
import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Spacetechmod.MOD_ID)
    public static class NeoForgeEvents {
        private static int playerTickEventimer = 0;
        private static float playerHealth;
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.SERVER) {
                if(!ModLists.SAFE_BREATHING_LIST.contains(event.player.level().dimension()) && !event.player.getAbilities().instabuild) {
                    if(!event.player.hasEffect(ModEffects.SPACE_BREATHING_EFFECT.get())) {
                        if(playerTickEventimer >= 40) {
                            event.player.sendSystemMessage(Component.literal(
                                    "You can't breathe in space!"
                            ));
                            playerHealth = event.player.getHealth();
                            event.player.setHealth(playerHealth -= 2);
                            playerTickEventimer = 0;
                        }
                        else {
                            ++playerTickEventimer;
                        }
                    }
                }
            }
        }
    }
}
