package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.item.custom.armor.SpaceSuitChestplateItem;
import com.net.spacetechmod.item.custom.space.PlanetKeyItem;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlanetDirectoryBlockEntity extends BlockEntity {

    public PlanetDirectoryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLANET_DIRECTORY.get(), pos, state);
    }

    //Planets
    private boolean overworld = true;
    private boolean moon = false;


    private int selectedPlanet = 0;

    public int getSelectedPlanet() {
        return selectedPlanet;
    }

    public ItemInteractionResult useItem(ItemStack stack, Player player) {
        PlanetKeyItem item;
        SpaceSuitChestplateItem plate;
        if(stack.getItem() instanceof PlanetKeyItem) {
            item = (PlanetKeyItem) stack.getItem();
            switch(ModLists.PLANET_LIST.indexOf(item.getDestination())) {
                case 1 -> {if(!moon) {moon = true; return ItemInteractionResult.CONSUME;}}
            }
            return ItemInteractionResult.FAIL;
        }
        if(stack.getItem() instanceof SpaceSuitChestplateItem) {
            plate = (SpaceSuitChestplateItem) stack.getItem();
            if(checkUnlockStatus()) {
                plate.selectedPlanet = ModLists.PLANET_LIST.get(selectedPlanet);
            }
            else {
                player.sendSystemMessage(Component.literal("Planet locked!"));
            }
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.FAIL;
    }

    private boolean checkUnlockStatus() {
        switch(selectedPlanet) {
            case 0 -> {if(overworld) {return true;}}
            case 1 -> {if(moon) {return true;}}
        }
        return false;
    }

    public void useWithoutItem(Player player) {
        if(!player.isShiftKeyDown()) {
            ++selectedPlanet;
            if(selectedPlanet > ModLists.PLANET_LIST.size()) {
                selectedPlanet = 0;
            }
            player.sendSystemMessage(Component.literal("Planet selected: " + ModLists.PLANET_LIST.get(selectedPlanet)));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putBoolean("overworld", overworld);
        tag.putBoolean("moon", moon);
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        overworld = tag.getBoolean("overworld");
        moon = tag.getBoolean("moon");
        super.loadAdditional(tag, provider);
    }
}