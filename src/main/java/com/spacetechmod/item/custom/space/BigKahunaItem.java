package com.spacetechmod.item.custom.space;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class BigKahunaItem extends Item {
    public BigKahunaItem() {
        super(new Properties()
                .fireResistant()
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
            if(player.getY() >= 300) {
                player.setDeltaMovement(player.getDeltaMovement().x, player.getDeltaMovement().y * 12000, player.getDeltaMovement().z);
                player.getItemInHand(usedHand).shrink(1);
            }
            else {
                if(!level.isClientSide()) {
                    player.sendSystemMessage(Component.literal("You must be at or above Y: 300 to use this!"));
                }
            }
        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Use at Y: 300 or above!"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
