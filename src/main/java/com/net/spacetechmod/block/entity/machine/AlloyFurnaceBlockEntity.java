package com.net.spacetechmod.block.entity.machine;


import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.recipe.AlloyFurnaceRecipe;
import com.net.spacetechmod.screen.alloyfurnace.AlloyFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlloyFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> true;
                case 1 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    public ItemStack getRenderStack() {
        ItemStack stack;

        if(!itemHandler.getStackInSlot(2).isEmpty()) {
            stack = itemHandler.getStackInSlot(2);
        }
            else {
                stack = itemHandler.getStackInSlot(1);
            }
        return stack;
    }

    public void setHandler(ItemStackHandler itemStackHandler) {
        for(int i = 0; i < itemStackHandler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, itemStackHandler.getStackInSlot(i));
        }
    }

    protected final ContainerData data;
    private static int alloyFuelTime = 0;
    private int alloyProgress = 0;
    private int maxAlloyProgress = 200;

    public AlloyFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_FURNACE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> AlloyFurnaceBlockEntity.this.alloyProgress;
                    case 1 -> AlloyFurnaceBlockEntity.this.maxAlloyProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> AlloyFurnaceBlockEntity.this.alloyProgress = value;
                    case 1 -> AlloyFurnaceBlockEntity.this.maxAlloyProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

        };

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new AlloyFurnaceMenu(id, inventory, this, this.data);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("alloy.progress", this.alloyProgress);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        alloyProgress = nbt.getInt("alloy_furnace.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AlloyFurnaceBlockEntity pEntity) {
        if(!level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity) && hasFuel(pEntity)) {
            pEntity.alloyProgress++;
            pEntity.alloyFuelTime--;
            setChanged(level, pos, state);

            if(pEntity.alloyProgress >= pEntity.maxAlloyProgress) {
                craftItem(pEntity);
            }
            else {
                pEntity.resetProgress();
                setChanged(level, pos, state);
            }

        }

    }

    private void resetProgress() {
        this.alloyProgress = 0;
    }

    public static void craftItem(AlloyFurnaceBlockEntity pEntity) {
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for(int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }
        Optional<AlloyFurnaceRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, inventory, level);

        if(hasRecipe(pEntity)) {
            pEntity.itemHandler.extractItem(1, 1, false);
            pEntity.itemHandler.extractItem(2, 1, false);
            pEntity.itemHandler.setStackInSlot(3, new ItemStack(recipe.get().getResultItem().getItem(),
                    pEntity.itemHandler.getStackInSlot(4).getCount() + 1));

            pEntity.resetProgress();
        }

    }

    private static boolean hasRecipe(AlloyFurnaceBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for(int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<AlloyFurnaceRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory);

    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(4).getItem() == stack.getItem() || inventory.getItem(4).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(4).getMaxStackSize() > inventory.getItem(4).getCount();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Alloy Furnace");
    }

    public static void addFuel(SimpleContainer inventory, ItemStackHandler handler, AlloyFurnaceBlockEntity entity) {
        if(inventory.getItem(0).is(Items.COAL) && alloyFuelTime < 2 && hasRecipe(entity)) {
            alloyFuelTime += 200;
            handler.extractItem(0, 1, false);
        } else if(inventory.getItem(0).is(Items.CHARCOAL) && alloyFuelTime < 2 && hasRecipe(entity)) {
            alloyFuelTime += 200;
            handler.extractItem(0, 1, false);
        }
    }

    public static boolean hasFuel(BlockEntity entity) {
        return alloyFuelTime > 0;
    }
}

