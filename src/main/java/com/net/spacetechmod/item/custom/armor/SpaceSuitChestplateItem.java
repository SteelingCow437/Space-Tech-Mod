package com.net.spacetechmod.item.custom.armor;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.item.custom.tool.ModArmorItem;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SpaceSuitChestplateItem extends ModArmorItem {

    public SpaceSuitChestplateItem() {
        super(ModArmorMaterials.SPACESUIT, Type.CHESTPLATE, new Properties().fireResistant());
    }
    Level level;
    Player player;
    public int selectedPlanetNumber = 0;
    private ResourceKey<Level> selectedPlanet;

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getLevel().getBlockState(context.getClickedPos()).getBlock() == ModBlocks.PLANET_DIRECTORY.get()) {
            level = context.getLevel();
            player = context.getPlayer();
            ++selectedPlanetNumber;
            if(selectedPlanetNumber > ModLists.PLANET_LIST.size()) {
                selectedPlanetNumber = 0;
            }
            else {
                selectedPlanet = ModLists.PLANET_LIST.get(selectedPlanetNumber);
            }
        }
        return super.useOn(context);
    }

    public ResourceKey<Level> getSelectedPlanet() {
        return selectedPlanet;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }
}
