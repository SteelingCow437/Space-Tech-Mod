package com.net.spacetechmod.item.custom.sculk;

import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.item.ModCreativeModeTab;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class WardenBookItem extends Item {
    public WardenBookItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.STM_TOOLS)
                .rarity(Rarity.RARE)
                .stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getPlayer() != null) {
            if(hasSculkSetOn(context.getPlayer()) && context.getPlayer().experienceLevel >= 30) {
                Level level = context.getLevel();
                Player target = level.getNearestPlayer(TargetingConditions.forCombat(), context.getPlayer().getX(), context.getPlayer().getY(), context.getPlayer().getZ());
                if(target != context.getPlayer() && target != null && context.getPlayer().distanceTo(target) < 30) {
                    SpawnUtil.trySpawnMob(EntityType.WARDEN, MobSpawnType.TRIGGERED, ((ServerLevel) level), target.getOnPos(), 1, 1, 1, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
                    context.getPlayer().experienceLevel -= 30;
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.FAIL;
    }


    private boolean hasSculkSetOn(Player player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        ArmorMaterial material = ModArmorMaterials.SCULK;
        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
