package com.net.spacetechmod.item.custom.sculk;

import com.net.spacetechmod.effect.ModEffects;
import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.item.ModCreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class FreezeTimeBookItem extends Item {

    public FreezeTimeBookItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.STM_TOOLS)
                .rarity(Rarity.EPIC)
                .stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("This is a joke item. It makes the game pause for 10 seconds. No, you didn't crash the game"));
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getPlayer() != null) {
            if(hasSculkSetOn(context.getPlayer()) || context.getPlayer().hasEffect(ModEffects.SOUL_CHARGE_EFFECT.get()) && context.getPlayer().experienceLevel >= 100) {
                context.getPlayer().experienceLevel -= 100;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
