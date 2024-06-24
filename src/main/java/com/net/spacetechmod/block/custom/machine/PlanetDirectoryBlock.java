package com.net.spacetechmod.block.custom.machine;

import com.net.spacetechmod.item.custom.armor.SpaceSuitChestplateItem;
import com.net.spacetechmod.item.custom.space.PlanetKeyItem;
import com.net.spacetechmod.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Arrays;
import java.util.List;

public class PlanetDirectoryBlock extends Block {

    public PlanetDirectoryBlock(Properties properties) {
        super(properties);
    }

    private List<ResourceKey<Level>> UNLOCKED_PLANETS = Arrays.asList(Level.OVERWORLD);
    private List<Item> USED_KEYS = List.of();

    public int selectedPlanetNumber = 0;
    private ResourceKey<Level> selectedPlanet;

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(stack.getItem() instanceof PlanetKeyItem) {
            if(!USED_KEYS.contains(stack.getItem())) {
                UNLOCKED_PLANETS.add(UNLOCKED_PLANETS.size(), ((PlanetKeyItem) stack.getItem()).getDestination());
                USED_KEYS.add(USED_KEYS.size(), stack.getItem());
                return ItemInteractionResult.CONSUME;
            }
            else {
                player.sendSystemMessage(Component.literal("Planet already unlocked!"));
            }
        }
        else if(stack.getItem() instanceof SpaceSuitChestplateItem) {
            ((SpaceSuitChestplateItem) stack.getItem()).selectedPlanet = UNLOCKED_PLANETS.get(selectedPlanetNumber);
            selectedPlanet = UNLOCKED_PLANETS.get(selectedPlanetNumber);
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        if(!player.isShiftKeyDown()) {
            ++selectedPlanetNumber;
            if(selectedPlanetNumber > UNLOCKED_PLANETS.size()) {
                selectedPlanetNumber = 0;
            }
            player.sendSystemMessage(Component.literal("Planet Selected: " + getNames()));
        }
        else {
            player.sendSystemMessage(Component.literal("Unlocked Planets: " + getUnlockedPlanets().toString()));
        }
        return InteractionResult.SUCCESS;
    }

    private StringBuilder getUnlockedPlanets() {
        StringBuilder toReturn = new StringBuilder();
        for(int i = 0; i < ModLists.PLANET_LIST.size(); ++i) {
            if(UNLOCKED_PLANETS.contains(ModLists.PLANET_LIST.get(i))) {
                toReturn.append(getNames(i));
            }
        }
        return toReturn;
    }

    private String getNames(int id) {
        return switch (id) {
            default -> "None!";
            case 0 -> "Overworld, ";
            case 1 -> "Moon, ";
        };
    }

    private String getNames() {
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
}