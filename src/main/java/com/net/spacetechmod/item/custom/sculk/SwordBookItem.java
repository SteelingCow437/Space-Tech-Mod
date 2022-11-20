package com.net.spacetechmod.item.custom.sculk;

import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.enchantment.ModEnchantments;
import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.item.ModCreativeModeTab;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantments;

public class SwordBookItem extends Item  {
    public SwordBookItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.STM_TOOLS)
                .rarity(Rarity.RARE)
                .stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getPlayer() != null) {
            if(hasSculkSetOn(context.getPlayer()) || context.getPlayer().hasEffect(ModEffects.SOUL_CHARGE_EFFECT.get()) && context.getPlayer().experienceLevel >= 20) {
                ItemStack stack = Items.NETHERITE_SWORD.getDefaultInstance();
                stack.enchant(Enchantments.SHARPNESS, 5);
                stack.enchant(Enchantments.KNOCKBACK, 2);
                stack.enchant(ModEnchantments.MAGIC_DECAY.get(), 1);
                context.getPlayer().addItem(stack);
                context.getPlayer().giveExperienceLevels(-20);
                return InteractionResult.SUCCESS;
            }
            else {
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
