package com.spacetechmod.item.custom.space;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
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

    private ExplosionDamageCalculator calculator = new SimpleExplosionDamageCalculator(
            true, false, Optional.of(256f), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()));

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(player.getY() >= 10000) {
            level.explode(null, null, calculator, player.getX(),
                    player.getY() - 1, player.getZ(), 128f, true,
                    Level.ExplosionInteraction.TRIGGER, ParticleTypes.GUST_EMITTER_SMALL,
                    ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.GENERIC_EXPLODE);
            player.getItemInHand(usedHand).shrink(1);
        }
        else {
            player.sendSystemMessage(Component.literal("You must be at or above Y: 10,000 to use this!"));
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Use at Y: 10,000 or above!"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
