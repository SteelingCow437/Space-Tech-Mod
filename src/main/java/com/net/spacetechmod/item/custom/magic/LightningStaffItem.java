package com.net.spacetechmod.item.custom.magic;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class LightningStaffItem extends Item {
    public LightningStaffItem() {
        super(new Properties()
                .rarity(Rarity.RARE)
                .stacksTo(1)
                .fireResistant()
                .durability(11));
    }
    public int charge = 0;
    private boolean isBeingUsed = false;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        isBeingUsed = true;
        level.playSound(player, player.getOnPos(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 2.0f, 2.0f);
        return super.use(level, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int number, boolean bool) {
        if(!level.isClientSide && entity instanceof Player) {
            Random random = new Random();
            BlockPos playerPos = entity.getOnPos();
            if(charge < 100 && !isBeingUsed && (((Player) entity).experienceLevel > 0 || ((Player) entity).getAbilities().instabuild)) {
                charge++;
                ((Player) entity).giveExperiencePoints(-5);
            }
            if(isBeingUsed && charge > 0) {
                LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                bolt.setDamage(10);
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 4));
                bolt.setPos(playerPos.getX() + random.nextInt(-10, 10), playerPos.getY(), playerPos.getZ() + random.nextInt(-10, 10));
                level.addFreshEntity(bolt);
                charge--;
            }
            if(charge == 0 || entity.isShiftKeyDown()) {
                isBeingUsed = false;
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        component.add(Component.literal("Charge: " + charge + " / 100"));
    }
}
