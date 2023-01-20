package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.networking.ModMessages;
import com.net.spacetechmod.networking.packet.EnergySyncS2CPacket;
import com.net.spacetechmod.util.ModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class StirlingEngineBlockEntity extends BlockEntity {
    private static int timer = 0;
    private int fuelTime = 0;
    private int energy = 0;
    private static int staticFuelTime = 0;
    private static int staticEnergy = 0;
    private static int maxFuelTime = 16000;
    private Block blockAbove;
    private Block blockBelow;
    private Block blockNorth;
    private Block blockSouth;
    private Block blockEast;
    private Block blockWest;

    public StirlingEngineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STIRLING_ENGINE.get(), pos, state);
    }

    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(100000, 1000) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
        }
    };

    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergy(energy);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

        public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        StirlingEngineBlockEntity entity = (StirlingEngineBlockEntity) be;
        Player player = level.getNearestPlayer(TargetingConditions.DEFAULT, pos.getX(), pos.getY(), pos.getZ());
        if(entity.fuelTime > 0 && entity.getEnergyStorage().getEnergyStored() < entity.getEnergyStorage().getMaxEnergyStored()) {
            entity.setEnergyLevel(staticEnergy);
            staticEnergy += 50;
            entity.setEnergyLevel(staticEnergy);
            entity.fuelTime--;
            timer++;
            if(timer >= 10) {
                level.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 1.0f, 1.0f);
                timer = 0;
            }
        }
    }
    private LazyOptional<IEnergyStorage> lazyEnergyHandler =
            LazyOptional.empty();
    private LazyOptional<Integer> timeHandler =
            LazyOptional.empty();

    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
        timeHandler = LazyOptional.of(() -> fuelTime);
    }

    public void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
        timeHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("stirling_engine_energy", ENERGY_STORAGE.getEnergyStored());
        nbt.putInt("stirling_engine_fuel_time", fuelTime);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ENERGY_STORAGE.setEnergy(nbt.getInt("stirling_engine_energy"));
        fuelTime = nbt.getInt("stirling_engine_fuel_time");
    }
    public void addFuel(Player player) {
        ItemStack item = player.getMainHandItem();
        if(item.is(Items.COAL) || item.is(Items.CHARCOAL)) {
            if(fuelTime + 1600 <= maxFuelTime) {
                fuelTime += 1600;
                item.shrink(1);
            }
        }
        if(player.getMainHandItem().is(Items.STICK)) {
            player.sendSystemMessage(Component.literal("You have " + getEnergyStorage().getEnergyStored() + " / " + getEnergyStorage().getMaxEnergyStored() + " energy"));
        }
    }
    private void getBlock(Level level, BlockPos pos) {
        blockAbove = level.getBlockState(pos.above()).getBlock();
        blockBelow = level.getBlockState(pos.below()).getBlock();
        blockNorth = level.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock();
        blockSouth = level.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock();
        blockEast = level.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock();
        blockWest = level.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1)).getBlock();
    }
}
