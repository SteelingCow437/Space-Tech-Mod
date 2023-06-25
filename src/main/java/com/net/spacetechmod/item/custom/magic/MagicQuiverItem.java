package com.net.spacetechmod.item.custom.magic;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MagicQuiverItem extends Item {
    public MagicQuiverItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .fireResistant());
    }

    private static final ArrayList<MobEffect> list = new ArrayList<MobEffect>();

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(player.getOffhandItem().getItem() == Items.BOW || player.getOffhandItem().getItem() == Items.CROSSBOW) {
            player.getOffhandItem().setDamageValue((int) (player.getOffhandItem().getDamageValue() * 0.9));
        }
        if(player.isShiftKeyDown() && player.experienceLevel >= 13) {
            player.addItem(new ItemStack(Items.ARROW, 64));
            player.giveExperiencePoints(-320);
        }
        else if(!player.isShiftKeyDown() && player.experienceLevel > 1) {
            player.addItem(new ItemStack(Items.ARROW, 1));
            player.giveExperiencePoints(-5);
        }
        level.playSound(player, player.getOnPos(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 2.0f, 2.0f);
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        component.add(Component.literal("Yes, the texture is Terraria's Endless Quiver. Cry about it."));
    }
}
