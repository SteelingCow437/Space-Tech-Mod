package com.net.spacetechmod.item.custom.magic;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class DeathMarkerStaffItem extends Item {

    public DeathMarkerStaffItem() {
        super(new Properties()
                .rarity(Rarity.RARE)
                .fireResistant()
                .stacksTo(1));
    }

    private int charge = 100;
    private int selectedTime;
    private int timer = 0;
    Random random = new Random();
    private boolean isBeingUsed = false;
    @Nullable Player targetPlayer;
    @Nullable Player nonTargetPlayer;
    Husk husk;
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(charge == 100) {
            nonTargetPlayer = player;
            targetPlayer = level.getNearestPlayer(TargetingConditions.forCombat(), player);
            if(targetPlayer != null && nonTargetPlayer != null) {
                husk = new Husk(EntityType.HUSK, targetPlayer.level());
                targetPlayer.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 200, 0));
                level.playSound(targetPlayer, targetPlayer.getOnPos(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.HOSTILE, 2.0f, 2.0f);
                if(targetPlayer != player) {
                    selectedTime = random.nextInt(1200, 12000);
                    isBeingUsed = true;
                    charge = 0;
                }
            }
        }
        return super.use(level, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int number, boolean bool) {
        if(!level.isClientSide) {
            if(targetPlayer != null && nonTargetPlayer != null) {
                if(isBeingUsed) {
                    if(timer < selectedTime) {
                        timer++;
                    }
                    else {
                        timer = 0;
                        for(int i = 0; i < 50; ++i) {
                            husk.setPos(targetPlayer.getX() + random.nextDouble(-10, 10), targetPlayer.getY(), targetPlayer.getZ() + random.nextDouble(-10, 10));
                            level.addFreshEntity(husk);
                        }
                        isBeingUsed = false;
                    }
                }
                else {
                    if(charge < 100 && nonTargetPlayer.experienceLevel > 0) {
                        nonTargetPlayer.giveExperiencePoints(-20);
                        charge++;
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        component.add(Component.literal("Charge: " + charge + " / 100"));
    }

}
