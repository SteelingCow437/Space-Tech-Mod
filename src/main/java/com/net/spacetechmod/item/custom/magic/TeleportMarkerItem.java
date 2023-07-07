package com.net.spacetechmod.item.custom.magic;

import com.net.spacetechmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class TeleportMarkerItem extends Item {
    public TeleportMarkerItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.EPIC)
                .fireResistant()
                .durability(100));
    }

    private int timer = 24000;

    public BlockPos homePos;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int number, boolean bool) {
        if(entity instanceof Player && !level.isClientSide()) {
            if(timer > 0) {
                timer--;
            }
            else if(timer == 0) {
                teleportHome(((Player) entity));
            }
        }
    }

    public void teleportHome(Player player) {
        if(homePos != null) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, 4));
            player.teleportTo(homePos.getX(), homePos.getY(), homePos.getZ());
            int slot = player.getInventory().findSlotMatchingItem(ModItems.TELEPORT_MARKER.get().getDefaultInstance());
            player.getInventory().setItem(slot, ItemStack.EMPTY);
        }
    }

    public int getTimeRemaining() {
        return (timer / 1200);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        component.add(Component.literal("Time remaining: " + getTimeRemaining() + " minutes"));
    }
}
