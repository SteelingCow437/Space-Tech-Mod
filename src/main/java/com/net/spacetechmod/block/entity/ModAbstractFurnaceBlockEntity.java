package com.net.spacetechmod.block.entity;

import com.google.common.collect.Maps;
// import com.net.spacetechmod.block.custom.ModAbstractFurnaceBlock;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Map;

public abstract class ModAbstractFurnaceBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {

    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_INPUT_1 = 1;
    protected static final int SLOT_RESULT = 2;
    protected static final int SLOT_FUEL = 3;
    public static final int DATA_LIT_TIME = 0;
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;
    public static final int NUM_DATA_VALUES = 4;
    public static final int BURN_TIME_STANDARD = 200;
    public static final int BURN_COOL_SPEED = 2;

    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    int litTime;
    int litDuration;
    int cookingProgress;
    int cookingTotalTime;

    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int p_58431_) {
            switch (p_58431_) {
                case 0:
                    return ModAbstractFurnaceBlockEntity.this.litTime;
                case 1:
                    return ModAbstractFurnaceBlockEntity.this.litDuration;
                case 2:
                    return ModAbstractFurnaceBlockEntity.this.cookingProgress;
                case 3:
                    return ModAbstractFurnaceBlockEntity.this.cookingTotalTime;
                default:
                    return 0;
            }
        }

        public void set(int p_58433_, int p_58434_) {
            switch (p_58433_) {
                case 0:
                    ModAbstractFurnaceBlockEntity.this.litTime = p_58434_;
                    break;
                case 1:
                    ModAbstractFurnaceBlockEntity.this.litDuration = p_58434_;
                    break;
                case 2:
                    ModAbstractFurnaceBlockEntity.this.cookingProgress = p_58434_;
                    break;
                case 3:
                    ModAbstractFurnaceBlockEntity.this.cookingTotalTime = p_58434_;
            }

        }

        public int getCount() {
            return 4;
        }
    };
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

    private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck;

    protected ModAbstractFurnaceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {
        super(type, pos, state);
        this.quickCheck = RecipeManager.createCheck((RecipeType) recipeType);
        this.recipeType = recipeType;
    }

    public static Map<Item, Integer> getFuel() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        map.put(Items.LAVA_BUCKET, 20000);
        map.put(Items.COAL_BLOCK, 16000);
        map.put(Items.BLAZE_ROD, 2400);
        map.put(Items.COAL, 1600);
        map.put(Items.CHARCOAL, 1600);
        map.put(Items.DRIED_KELP_BLOCK, 4001);
        return map;
    }

    private static boolean isNeverAFurnaceFuel(Item notAFuel) {
        return notAFuel.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
    }

    private static void add(Map<Item, Integer> p_204303_, TagKey<Item> p_204304_, int p_204305_) {
        for (Holder<Item> holder : Registry.ITEM.getTagOrEmpty(p_204304_)) {
            if (!isNeverAFurnaceFuel(holder.value())) {
                p_204303_.put(holder.value(), p_204305_);
            }
        }
    }

    private static void add(Map<Item, Integer> p_58375_, ItemLike p_58376_, int p_58377_) {
        Item item = p_58376_.asItem();
        if (isNeverAFurnaceFuel(item)) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack) null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            p_58375_.put(item, p_58377_);
        }
    }

    private boolean isBurning() {
        return this.litTime > 0;
    }

    public void load(CompoundTag p_155025_) {
        super.load(p_155025_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(p_155025_, this.items);
        this.litTime = p_155025_.getInt("BurnTime");
        this.cookingProgress = p_155025_.getInt("CookTime");
        this.cookingTotalTime = p_155025_.getInt("CookTimeTotal");
        this.litDuration = this.getBurnDuration(this.items.get(1));
        CompoundTag compoundtag = p_155025_.getCompound("RecipesUsed");

        for (String s : compoundtag.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }

    }

    protected void saveAdditional(CompoundTag p_187452_) {
        super.saveAdditional(p_187452_);
        p_187452_.putInt("BurnTime", this.litTime);
        p_187452_.putInt("CookTime", this.cookingProgress);
        p_187452_.putInt("CookTimeTotal", this.cookingTotalTime);
        ContainerHelper.saveAllItems(p_187452_, this.items);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((p_187449_, p_187450_) -> {
            compoundtag.putInt(p_187449_.toString(), p_187450_);
        });
        p_187452_.put("RecipesUsed", compoundtag);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, ModAbstractFurnaceBlockEntity entity) {
        boolean flag = entity.isBurning();
        boolean flag1 = false;
        if (entity.isBurning()) {
            --entity.litTime;
        }

        ItemStack itemstack = entity.items.get(1);
        ItemStack itemstack1 = entity.items.get(2);
        boolean flag2 = !entity.items.get(0).isEmpty();
        boolean flag3 = !itemstack.isEmpty();
        if (entity.isBurning() || flag3 && flag2) {
            Recipe<?> recipe;
            if (flag2) {
                recipe = entity.quickCheck.getRecipeFor(entity, level).orElse(null);
            } else {
                recipe = null;
            }

            int i = entity.getMaxStackSize();
            if (!entity.isBurning() && entity.canBurn(recipe, entity.items, i)) {
                entity.litTime = entity.getBurnDuration(itemstack);
                entity.litDuration = entity.litTime;
                if (entity.isBurning()) {
                    flag1 = true;
                    if (itemstack.hasCraftingRemainingItem())
                        entity.items.set(1, itemstack.getCraftingRemainingItem());
                    else
                    if (flag3) {
                        Item item = itemstack.getItem();
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            entity.items.set(1, itemstack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (entity.isBurning() && entity.canBurn(recipe, entity.items, i)) {
                ++entity.cookingProgress;
                if (entity.cookingProgress == entity.cookingTotalTime) {
                    entity.cookingProgress = 0;
                    entity.cookingTotalTime = getTotalCookTime(level, entity);
                    if (entity.burn(recipe, entity.items, i)) {
                        entity.setRecipeUsed(recipe);
                    }

                    flag1 = true;
                }
            } else {
                entity.cookingProgress = 0;
            }
        } else if (!entity.isBurning() && entity.cookingProgress > 0) {
            entity.cookingProgress = Mth.clamp(entity.cookingProgress - 2, 0, entity.cookingTotalTime);
        }

        if (flag != entity.isBurning()) {
            flag1 = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, Boolean.valueOf(entity.isBurning()));
            level.setBlock(pos, state, 3);
        }

        if (flag1) {
            setChanged(level, pos, state);
        }

    }

    private boolean canBurn(@Nullable Recipe<?> recipe, NonNullList<ItemStack> stack, int i) {
        if (!stack.get(0).isEmpty() && recipe != null) {
            ItemStack itemstack = ((Recipe<WorldlyContainer>) recipe).assemble(this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = stack.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.sameItem(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= i && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    private boolean burn(@Nullable Recipe<?> p_155027_, NonNullList<ItemStack> p_155028_, int p_155029_) {
        if (p_155027_ != null && this.canBurn(p_155027_, p_155028_, p_155029_)) {
            ItemStack itemstack = p_155028_.get(0);
            ItemStack itemstack1 = ((Recipe<WorldlyContainer>) p_155027_).assemble(this);
            ItemStack itemstack2 = p_155028_.get(2);
            if (itemstack2.isEmpty()) {
                p_155028_.set(2, itemstack1.copy());
            } else if (itemstack2.is(itemstack1.getItem())) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !p_155028_.get(1).isEmpty() && p_155028_.get(1).is(Items.BUCKET)) {
                p_155028_.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
            return true;
        } else {
            return false;
        }
    }
    protected int getBurnDuration(ItemStack p_58343_) {
        if (p_58343_.isEmpty()) {
            return 0;
        } else {
            Item item = p_58343_.getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(p_58343_, this.recipeType);
        }
    }
    private static int getTotalCookTime(Level p_222693_, ModAbstractFurnaceBlockEntity p_222694_) {
        return p_222694_.quickCheck.getRecipeFor(p_222694_, p_222693_).map(AbstractCookingRecipe::getCookingTime).orElse(200);
    }

    public static boolean isFuel(ItemStack p_58400_) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(p_58400_, null) > 0;
    }
    public int getContainerSize() {
        return this.items.size();
    }

    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }
}