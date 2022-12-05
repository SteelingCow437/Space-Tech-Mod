package com.net.spacetechmod.item.custom.sculk;

import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.item.ModCreativeModeTab;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

public class FreezeBookItem extends Item {
    public FreezeBookItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.STM_TOOLS)
                .rarity(Rarity.RARE)
                .stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if(player != null) {
            if(hasSculkSetOn(player) || player.hasEffect(ModEffects.SOUL_CHARGE_EFFECT.get()) && player.experienceLevel >= 10) {
                player.experienceLevel -= 10;
                Player target = (Player) context.getLevel().getNearbyPlayers(TargetingConditions.forCombat(), player, AABB.of(BoundingBox.infinite()));
                if(target != player && player.distanceTo(target) < 30) {
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 9));
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.FAIL;
                }
                return InteractionResult.FAIL;
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
