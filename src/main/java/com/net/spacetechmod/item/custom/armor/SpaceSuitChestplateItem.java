package com.net.spacetechmod.item.custom.armor;

import com.net.spacetechmod.block.ModBlocks;
import com.net.spacetechmod.item.ModArmorMaterials;
import com.net.spacetechmod.item.custom.tool.ModArmorItem;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

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
            player.sendSystemMessage(Component.literal("Planet Selected: " + getPlanetNames()));
        }
        return super.useOn(context);
    }

    public ResourceKey<Level> getSelectedPlanet() {
        if(selectedPlanet == null) {
            return ModLists.PLANET_LIST.get(0);
        }
        return selectedPlanet;
    }

    public String getPlanetNames() {
        switch(ModLists.PLANET_LIST.indexOf(selectedPlanet)) {
            case 0 -> {
                return "Overworld / Earth";
            }

            case 1 -> {
                return "The Moon";
            }
        }
        return "Yet to be selected!";
    }

    @Override
   public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("Destination: " + getPlanetNames()));
        super.appendHoverText(stack, context, list, flag);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }
}
