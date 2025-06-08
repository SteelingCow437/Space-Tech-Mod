package com.spacetechmod.item.custom.space;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BigKahunaItem extends Item {
    public BigKahunaItem() {
        super(new Properties()
                .fireResistant()
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON));
    }

    private final ExplosionDamageCalculator calculator = new SimpleExplosionDamageCalculator(
            false, false, Optional.of(768f), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()));

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
            if(player.getY() >= 500) {
                player.setDeltaMovement(player.getDeltaMovement().x, player.getDeltaMovement().y * 12000, player.getDeltaMovement().z);
                player.getItemInHand(usedHand).shrink(1);
            }
            else {
                player.sendSystemMessage(Component.literal("You must be at or above Y: 500 to use this!"));
            }
        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Use at Y: 500 or above!"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
