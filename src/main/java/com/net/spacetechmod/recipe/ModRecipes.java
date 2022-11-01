package com.net.spacetechmod.recipe;

import com.net.spacetechmod.Spacetechmod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes<T> {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Spacetechmod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<AlloyFurnaceRecipe>> ALLOYING_SERIALIZER =
            SERIALIZERS.register("alloying", () -> AlloyFurnaceRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<PressRecipe>> PRESSING_SERIALIZER =
            SERIALIZERS.register("pressing", () -> PressRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}

