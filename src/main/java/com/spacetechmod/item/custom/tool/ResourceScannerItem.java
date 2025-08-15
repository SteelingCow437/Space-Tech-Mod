package com.spacetechmod.item.custom.tool;

import com.spacetechmod.data.ModDataStorage;
import com.spacetechmod.util.ModLists;
import com.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class ResourceScannerItem extends Item {

    public ResourceScannerItem() {
        super(new Properties()
                .stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.getCooldowns().addCooldown(this, 60);
        MinecraftServer server = level.getServer();
        ServerLevel moon;
        try {
            moon = server.getLevel(ModDimensions.MOON);
        } catch (NullPointerException exception) {
            return InteractionResultHolder.fail(player.getMainHandItem());
        }
        ItemStack stack = player.getItemInHand(usedHand);
        BlockPos dropPos = player.getOnPos();
        BlockPos pos = stack.get(ModDataStorage.LINKED_ORBITAL_CORE);
        Block block = moon.getBlockState(pos).getBlock();

        //needs a check for the block at the core position
        AABB bounds = new AABB(player.getX() - 16, -62, player.getZ() - 16, player.getX() + 16, player.getY(), player.getZ() + 16);
        if (!moon.isClientSide && !player.level().isClientSide) {

            //TODO: this lol

        }
        return InteractionResultHolder.success(player.getMainHandItem());
    }

}
