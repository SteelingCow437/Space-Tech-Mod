package com.spacetechmod.block.entity.machine;

import com.spacetechmod.block.entity.ModBlockEntities;
import com.spacetechmod.util.ModLists;
import com.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlanetDirectoryBlockEntity extends BlockEntity {

    public PlanetDirectoryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLANET_DIRECTORY.get(), pos, state);
    }

    private int selectedPlanet = 0;

    private final boolean overworld = true;
    private boolean moon = false;

    public int getSelectedPlanetNumber() {
        return selectedPlanet;
    }

    public ResourceKey<Level> getSelectedPlanet() {
        switch(selectedPlanet) {
            case 1 -> {
                return ModDimensions.MOON;
            }
            default -> {
                return Level.OVERWORLD;
            }
        }
    }

    public void unlockPlanet(ResourceKey<Level> planet) {
        switch(ModLists.PLANET_LIST.indexOf(planet)) {
            case 1 -> {
                if(!moon) {moon = true;}
            }
        }
        setChanged();
    }

    public void selectNewPlanet() {
        if(checkUnlockStatus(selectedPlanet + 1)) {
            ++selectedPlanet;
        }
        else {
            selectedPlanet = 0;
        }
        setChanged();
    }

    private boolean checkUnlockStatus(int number) {
        switch(number) {
            case 0 -> {
                return overworld;
            }
            case 1 -> {
                return moon;
            }
            default -> {
                return false;
            }
        }
    }



    //one day, one day ;)
    private String getSelectedPlanetName(ResourceKey<Level> selectedPlanet) {
        switch(ModLists.PLANET_LIST.indexOf(selectedPlanet)) {
            case 1 -> {
                return "The Moon";
            }
            default -> {
                return "Overworld";
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putBoolean("moon", moon);
        tag.putInt("selected_planet", selectedPlanet);
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        moon = tag.getBoolean("moon");
        selectedPlanet = tag.getInt("selected_planet");
        super.loadAdditional(tag, provider);
    }
}