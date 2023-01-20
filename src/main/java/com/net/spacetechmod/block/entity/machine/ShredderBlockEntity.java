package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.networking.ModMessages;
import com.net.spacetechmod.networking.packet.EnergySyncS2CPacket;
import com.net.spacetechmod.util.ModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ShredderBlockEntity extends BlockEntity{

    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(50000, 500) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
            desiredEnergy = 50000 - ENERGY_STORAGE.getEnergyStored();
        }
    };

    public int desiredEnergy = 50000 - getEnergyStorage().getEnergyStored();

    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergy(energy);
    }

    private LazyOptional<IEnergyStorage> lazyEnergyHandler =
            LazyOptional.empty();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    public void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("shredder_energy", ENERGY_STORAGE.getEnergyStored());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ENERGY_STORAGE.setEnergy(nbt.getInt("shredder_energy"));
    }

    public void craft(Player player) {
        if(getEnergyStorage().getEnergyStored() >= 500 && !player.getMainHandItem().is(Items.STICK)) {
            ItemStack stack = player.getMainHandItem();
            int count = player.getMainHandItem().getCount();
            //recipes down here
            if(stack.is(Items.GLOWSTONE) && getEnergyStorage().getEnergyStored() >= count * 500) {
                player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                for(int i = 0; i < count * 4; i++) {
                    player.addItem(Items.GLOWSTONE_DUST.getDefaultInstance());
                    getEnergyStorage().extractEnergy(125, false); //this needs to be 500 divided by the amount of output you want per item
                }
            }
            if(stack.is(Items.BLAZE_ROD) && getEnergyStorage().getEnergyStored() >= count * 500) {
                player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                for(int i = 0; i < count * 4; i++) {
                    player.addItem(Items.BLAZE_POWDER.getDefaultInstance());
                    getEnergyStorage().extractEnergy(125, false); //this needs to be 500 divided by the amount of output you want per item
                }
            }
        }
        else if(player.getMainHandItem().is(Items.STICK)) {
            player.sendSystemMessage(Component.literal("You have " + getEnergyStorage().getEnergyStored() + " / " + getEnergyStorage().getMaxEnergyStored() + " energy"));
        }
        else {
            player.sendSystemMessage(Component.literal("Not enough energy! " + getEnergyStorage().getEnergyStored() + " / " + getEnergyStorage().getMaxEnergyStored()));
        }
    }

    public ShredderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SHREDDER.get(), pos, state);
    }
}
