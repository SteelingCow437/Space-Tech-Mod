package com.net.spacetechmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.net.spacetechmod.Spacetechmod;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
    public class PressRecipe implements Recipe<SimpleContainer> {
        private final ResourceLocation id;
        private final ItemStack output;
        private final NonNullList<Ingredient> recipeItems;

        public PressRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
            this.id = id;
            this.output = output;
            this.recipeItems = recipeItems;
        }

        @Override
        public boolean matches(SimpleContainer container, Level level) {
            if(level.isClientSide()) {
                return false;
            }
            return recipeItems.get(0).test(container.getItem(1));
        }

        @Override
        public NonNullList<Ingredient> getIngredients() {
            return recipeItems;
        }

        @Override
        public ItemStack assemble(SimpleContainer container) {
            return output;
        }

        @Override
        public boolean canCraftInDimensions(int width, int height) {
            return true;
        }

        @Override
        public ItemStack getResultItem() {
            return output.copy();
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return com.net.spacetechmod.recipe.PressRecipe.Serializer.INSTANCE;
        }

        @Override
        public RecipeType<?> getType() {
            return com.net.spacetechmod.recipe.PressRecipe.Type.INSTANCE;
        }

        public static class Type implements RecipeType<com.net.spacetechmod.recipe.PressRecipe> {
            private Type() { }
            public static final com.net.spacetechmod.recipe.PressRecipe.Type INSTANCE = new com.net.spacetechmod.recipe.PressRecipe.Type();
            public static final String ID = "pressing";
        }

        public static class Serializer implements RecipeSerializer<com.net.spacetechmod.recipe.PressRecipe> {
            public static final com.net.spacetechmod.recipe.PressRecipe.Serializer INSTANCE = new com.net.spacetechmod.recipe.PressRecipe.Serializer();
            public static final ResourceLocation ID =
                    new ResourceLocation(Spacetechmod.MOD_ID, "pressing");

            @Override
            public com.net.spacetechmod.recipe.PressRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
                ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

                JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
                NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

                for(int i = 0; i < inputs.size(); i++) {
                    inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
                }
                return new com.net.spacetechmod.recipe.PressRecipe(pRecipeId, output, inputs);
            }

            @Override
            public @Nullable com.net.spacetechmod.recipe.PressRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
                NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

                for(int i = 0; i < inputs.size(); i++) {
                    inputs.set(i, Ingredient.fromNetwork(buf));
                }
                ItemStack output = buf.readItem();
                return new com.net.spacetechmod.recipe.PressRecipe(id, output, inputs);
            }

            @Override
            public void toNetwork(FriendlyByteBuf buf, com.net.spacetechmod.recipe.PressRecipe recipe) {
                buf.writeInt(recipe.getIngredients().size());

                for(Ingredient ing : recipe.getIngredients()) {
                    ing.toNetwork(buf);
                }
                buf.writeItemStack(recipe.getResultItem(), false);
            }
        }
}
