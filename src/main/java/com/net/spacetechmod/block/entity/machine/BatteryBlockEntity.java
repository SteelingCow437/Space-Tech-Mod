package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.networking.ModMessages;
import com.net.spacetechmod.networking.packet.EnergySyncS2CPacket;
import com.net.spacetechmod.util.ModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BatteryBlockEntity extends BlockEntity {
    public ModEnergyStorage energy = new ModEnergyStorage(500000, 500000) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
        }
    };

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
        lazyEnergyHandler = LazyOptional.of(() -> energy);
    }

    public void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("battery_energy", energy.getEnergyStored());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        energy.setEnergy(nbt.getInt("battery_energy"));
    }

    public IEnergyStorage getEnergyStorage() {
        return energy;
    }

    public void setEnergyLevel(int energy) {
        this.energy.setEnergy(energy);
    }

    public void showEnergy(Player player) {
        if(player.getMainHandItem().is(Items.STICK)) {
            player.sendSystemMessage(Component.literal("You have " + energy.getEnergyStored() + " / " + energy.getMaxEnergyStored()));
        }
    }

    public BatteryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BATTERY.get(), pos, state);
    }
}


