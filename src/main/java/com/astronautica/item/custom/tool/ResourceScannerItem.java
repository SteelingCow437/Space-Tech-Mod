package com.astronautica.item.custom.tool;

import com.astronautica.block.custom.multiblock.ResourceRadarBlock;
import com.astronautica.data.ModDataStorage;
import com.astronautica.util.ModLists;
import com.astronautica.util.ModMultiBlockStructures;
import com.astronautica.world.dimension.ModDimensions;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResourceScannerItem extends Item {

    public ResourceScannerItem() {
        super(new Properties()
                .stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide) {
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
            boolean correctCore = false;
            if(block instanceof ResourceRadarBlock) {
                correctCore = ((ResourceRadarBlock) block).isStructureValid(ModMultiBlockStructures.RESOURCE_RADAR, moon, pos);
            }
            if (!moon.isClientSide && !player.level().isClientSide && correctCore) {
                AABB bounds = new AABB(dropPos.getX() - 16, -62, dropPos.getZ() - 16, dropPos.getX() + 16, dropPos.getY() + 1, dropPos.getZ() + 16);
                List<BlockState> B = new ArrayList<BlockState>(level.getBlockStates(bounds).toList());
                String s;
                for (int i = 0; i < ModLists.RESOURCE_SCANNER_TARGETS.size(); ++i) {
                    s = ModLists.RESOURCE_SCANNER_TARGETS.get(i).getName().getString() + ": " + Collections.frequency(B, ModLists.RESOURCE_SCANNER_TARGETS.get(i).defaultBlockState());
                    player.sendSystemMessage(Component.literal(s));
                }
            }
            return InteractionResultHolder.success(player.getMainHandItem());
        }
        return InteractionResultHolder.fail(player.getMainHandItem());
    }
}
