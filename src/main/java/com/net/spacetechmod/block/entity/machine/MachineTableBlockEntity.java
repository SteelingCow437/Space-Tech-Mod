package com.net.spacetechmod.block.entity.machine;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.recipe.MachineTableRecipe;
import com.net.spacetechmod.screen.machinetable.MachineTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MachineTableBlockEntity extends BlockEntity implements MenuProvider {

    protected final ContainerData data;
    private int swag = 2;
    private int jazz = 3;


    private final ItemStackHandler itemHandler = new ItemStackHandler(26) {
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

    public MachineTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MACHINE_TABLE.get(), pos, state);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MachineTableBlockEntity.this.swag;
                    case 1 -> MachineTableBlockEntity.this.jazz;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> MachineTableBlockEntity.this.jazz = value;
                    case 1 -> MachineTableBlockEntity.this.swag = value;
                }
            }

            @Override
            public int getCount() {
                return 26;
            }
        };
    }

    public void setHandler(ItemStackHandler itemStackHandler) {
        for(int i = 0; i < itemStackHandler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, itemStackHandler.getStackInSlot(i));
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new MachineTableMenu(id, inventory, this, this.data);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MachineTableBlockEntity pEntity) {
        if(!level.isClientSide()) {
            return;
        }
        if(hasRecipe(pEntity)) {
            craftItem(pEntity);
            setChanged(level, pos, state);
        }
    }

    public static void craftItem(MachineTableBlockEntity pEntity) {
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for (int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }
        Optional<MachineTableRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(MachineTableRecipe.Type.INSTANCE, inventory, level);

        if (hasRecipe(pEntity)) {
            pEntity.itemHandler.extractItem(0, 1, false);
            pEntity.itemHandler.extractItem(1, 1, false);
            pEntity.itemHandler.extractItem(2, 1, false);
            pEntity.itemHandler.extractItem(3, 1, false);
            pEntity.itemHandler.extractItem(4, 1, false);
            pEntity.itemHandler.extractItem(5, 1, false);
            pEntity.itemHandler.extractItem(6, 1, false);
            pEntity.itemHandler.extractItem(7, 1, false);
            pEntity.itemHandler.extractItem(8, 1, false);
            pEntity.itemHandler.extractItem(9, 1, false);
            pEntity.itemHandler.extractItem(10, 1, false);
            pEntity.itemHandler.extractItem(11, 1, false);
            pEntity.itemHandler.extractItem(12, 1, false);
            pEntity.itemHandler.extractItem(13, 1, false);
            pEntity.itemHandler.extractItem(14, 1, false);
            pEntity.itemHandler.extractItem(15, 1, false);
            pEntity.itemHandler.extractItem(16, 1, false);
            pEntity.itemHandler.extractItem(17, 1, false);
            pEntity.itemHandler.extractItem(18, 1, false);
            pEntity.itemHandler.extractItem(19, 1, false);
            pEntity.itemHandler.extractItem(20, 1, false);
            pEntity.itemHandler.extractItem(21, 1, false);
            pEntity.itemHandler.extractItem(22, 1, false);
            pEntity.itemHandler.extractItem(23, 1, false);
            pEntity.itemHandler.extractItem(24, 1, false);
            pEntity.itemHandler.setStackInSlot(25, new ItemStack(recipe.get().getResultItem().getItem(),
                    pEntity.itemHandler.getStackInSlot(25).getCount() + 1));
        }
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private static boolean hasRecipe(MachineTableBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<MachineTableRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(MachineTableRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(25).getItem() == stack.getItem() || inventory.getItem(25).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(25).getMaxStackSize() > inventory.getItem(25).getCount();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Machine Table");
    }



}

