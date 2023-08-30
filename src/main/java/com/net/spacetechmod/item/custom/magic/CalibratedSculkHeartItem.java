package com.net.spacetechmod.item.custom.magic;

import com.net.spacetechmod.util.ModLists;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CalibratedSculkHeartItem extends Item {
    public CalibratedSculkHeartItem() {
        super(new Properties()
                .rarity(Rarity.EPIC)
                .fireResistant()
                .stacksTo(1));
    }

    private int selectedEffectIndex = 0;
    private int cooldown = 0;
    private MobEffect effect;

    private void setEffect(Player player) {
        selectedEffectIndex = (selectedEffectIndex + 1) % ModLists.CALIBRATED_SCULK_HEART_EFFECT_LIST.size();
        effect = ModLists.CALIBRATED_SCULK_HEART_EFFECT_LIST.get(selectedEffectIndex);
        if (effect != null) {
            player.sendSystemMessage(Component.literal("Selected " + effect.getDisplayName().getString()));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (cooldown == 0 && player.isShiftKeyDown()) {
            setEffect(player);
            cooldown = 20;
        } else if(cooldown == 0 && !player.isShiftKeyDown()) {
            level.playSound(player, player.getOnPos(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0f, 1.0f);
            player.sendSystemMessage(Component.literal(effectActive ? "Deactivated!" : "Activated!"));
            effectActive = !effectActive;
            cooldown = 20;
        }
        return super.use(level, player, hand);
    }

    private boolean effectActive = false;
    private int timer = 0;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int number, boolean bool) {
        if (entity instanceof Player player) {
            if(cooldown > 0) {
                --cooldown;
            }
            if (player.experienceLevel > 0 && effectActive) {
                if (timer >= 200) {
                    player.addEffect(new MobEffectInstance(effect, 201, 1));
                    timer = 0; // Reset the timer
                    player.giveExperiencePoints(-20);
                } else {
                    timer++;
                }
            }
        }
        super.inventoryTick(stack, level, entity, number, bool);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        if (effect != null) {
            component.add(Component.literal("Selected Effect: " + effect.getDisplayName().getString()));
        }
    }
}