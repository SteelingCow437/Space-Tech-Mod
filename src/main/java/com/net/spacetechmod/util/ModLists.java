package com.net.spacetechmod.util;

import com.net.spacetechmod.block.entity.ModBlockEntities;
import com.net.spacetechmod.fluid.ModFluids;
import com.net.spacetechmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Arrays;

public class ModLists {

    //A class that contains the lists of things that I use to improve the performance of things
    //that benefit from using switches, such as barrels and the hammer. These lists didn't
    //want to play nice in any other class, so here they are.

    public static final ArrayList<Item> BUCKET_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.OIL_BUCKET.get()));

    public static final ArrayList<Item> BOTTLE_LIST = new ArrayList<Item>(
            Arrays.asList(ModItems.OIL_BOTTLE.get(), ModItems.LAVA_BOTTLE.get()));

    public static final ArrayList<FlowingFluid> FLUIDS_INDEX = new ArrayList<FlowingFluid>(
            Arrays.asList(ModFluids.CRUDE_OIL.get(), Fluids.WATER, Fluids.FLOWING_WATER, Fluids.LAVA, Fluids.FLOWING_LAVA,
                    ModFluids.HONEY.get()));

    public static final ArrayList<BlockEntityType> MACHINE_INDEX = new ArrayList<BlockEntityType>
            (Arrays.asList(ModBlockEntities.DYNAMO.get()));

    public static final ArrayList<Item> HAMMER_INGREDIENT_LIST = new ArrayList<Item>(
            Arrays.asList(Items.IRON_INGOT, Items.COAL, Items.CHARCOAL, Items.COPPER_INGOT, ModItems.TIN_INGOT.get()));

    public static final ArrayList<Item> SCULK_ALTAR_INGREDIENT_LIST = new ArrayList<Item>
            (Arrays.asList(ModItems.TITANIUM_INGOT.get(), Items.GLASS_BOTTLE, Items.AMETHYST_SHARD, Items.BOOK));

    public static final ArrayList<EntityType> SCULK_CORE_LIST = new ArrayList<EntityType>
            (Arrays.asList(EntityType.ALLAY, EntityType.AXOLOTL, EntityType.BAT, EntityType.BEE, EntityType.BLAZE, EntityType.CAMEL,
                    EntityType.CAT, EntityType.CAVE_SPIDER, EntityType.CHICKEN, EntityType.COD, EntityType.COW, EntityType.CREEPER, EntityType.DOLPHIN,
                    EntityType.DONKEY, EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.EVOKER,
                    EntityType.FOX, EntityType.FROG, EntityType.GHAST, EntityType.GLOW_SQUID, EntityType.GOAT, EntityType.GUARDIAN, EntityType.HOGLIN,
                    EntityType.HORSE, EntityType.HUSK, EntityType.IRON_GOLEM, EntityType.LLAMA, EntityType.MAGMA_CUBE, EntityType.MOOSHROOM, EntityType.MULE,
                    EntityType.OCELOT, EntityType.PANDA, EntityType.PARROT, EntityType.PHANTOM, EntityType.PIG, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE,
                    EntityType.PILLAGER, EntityType.POLAR_BEAR, EntityType.PUFFERFISH, EntityType.RABBIT, EntityType.RAVAGER, EntityType.SALMON,
                    EntityType.SHEEP, EntityType.SHULKER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SKELETON_HORSE, EntityType.SLIME,
                    EntityType.SNIFFER, EntityType.SNOW_GOLEM, EntityType.SPIDER, EntityType.SQUID, EntityType.STRAY, EntityType.STRIDER, EntityType.TADPOLE,
                    EntityType.TRADER_LLAMA, EntityType.TROPICAL_FISH, EntityType.TURTLE, EntityType.VEX, EntityType.VILLAGER, EntityType.VINDICATOR, EntityType.WANDERING_TRADER,
                    EntityType.WARDEN, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.WOLF, EntityType.ZOGLIN, EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE,
                    EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN, EntityType.WITHER, EntityType.ENDER_DRAGON)); //Good lord that's a mouthful but trust me its worth it
}
