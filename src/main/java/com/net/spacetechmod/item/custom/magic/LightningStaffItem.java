package com.net.spacetechmod.item.custom.magic;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

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
    Random random = new Random();

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(charge >= 20) {
            List<Entity> entities = level.getEntities(null, new AABB(player.getOnPos().getX() - 20, player.getOnPos().getY() - 20, player.getOnPos().getZ() - 20,
                    player.getOnPos().getX() + 20, player.getOnPos().getY() + 20, player.getOnPos().getZ() + 20));
            entities.remove(player);
            if(!entities.isEmpty()) {
                LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                bolt.setDamage(10);
                Entity target = entities.get(random.nextInt(0, entities.size()));
                bolt.setPos(target.getOnPos().getCenter());
                charge -= 40;
                level.addFreshEntity(bolt);
            }
        }
        level.playSound(player, player.getOnPos(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 2.0f, 2.0f);
        return super.use(level, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int number, boolean bool) {
        if(!level.isClientSide && entity instanceof Player) {
            if((charge < 200 && (((Player) entity).experienceLevel > 0) || ((Player) entity).getAbilities().instabuild)) {
                charge++;
                ((Player) entity).giveExperiencePoints(-5);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        component.add(Component.literal("Charge: " + charge + " / 200"));
    }
}
