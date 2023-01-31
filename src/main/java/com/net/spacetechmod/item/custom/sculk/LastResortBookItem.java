package com.net.spacetechmod.item.custom.sculk;

import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.item.ModArmorMaterials;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Objects;

public class LastResortBookItem extends Item {

    public LastResortBookItem() {
        super(new Properties()
                .rarity(Rarity.RARE)
                .stacksTo(1));
    }

    @Nullable
    public static GlobalPos getSpawnPosition(Level level) {
        return level.dimensionType().natural() ? GlobalPos.of(level.dimension(), level.getSharedSpawnPos()) : null;
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        if(player != null) {
            Explosion explosion = new Explosion(level, player, player.getX(), player.getY(), player.getZ(), 50f, false, Explosion.BlockInteraction.KEEP);
            if(hasSculkSetOn(player) || player.hasEffect(ModEffects.SOUL_CHARGE_EFFECT.get()) && player.experienceLevel >= 75) {
                explosion.explode();
                player.experienceLevel -= 75;
                player.setHealth(5);
                player.setPos(Vec3.atCenterOf(Objects.requireNonNull(getSpawnPosition(level)).pos()));
                return InteractionResult.SUCCESS;
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
