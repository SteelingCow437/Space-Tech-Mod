package com.spacetechmod.item.custom.space.orbital;

import com.spacetechmod.block.custom.multiblock.OrbitalTNTCoreBlock;
import com.spacetechmod.data.ModDataStorage;
import com.spacetechmod.util.ModLists;
import com.spacetechmod.util.ModMultiBlockStructures;
import com.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class OrbitalMarkerItem extends Item {

    private int machineIndex;
    private ResourceKey<Level> dimension;

    public OrbitalMarkerItem() {
        super(new Properties()
                .stacksTo(1)
                .fireResistant());
    }

    public void setIndex(int index) {
        machineIndex = index;
    }

    private String getName(int machineIndex) {
        switch(machineIndex) {
            case 0 -> {
                return "Orbital TNT Cannon";
            }
        }
        return "Nothing";
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        MinecraftServer server = context.getLevel().getServer();
        ServerLevel moon;
        try {
            moon = server.getLevel(ModDimensions.MOON);
        } catch (NullPointerException exception) {
            return InteractionResult.FAIL;
        }
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        BlockPos clickedPos = context.getClickedPos();
        BlockPos pos = stack.get(ModDataStorage.LINKED_ORBITAL_CORE);
        Block block = moon.getBlockState(pos).getBlock();
        dimension = moon.dimension();
        if (!moon.isClientSide && !player.level().isClientSide) {
            switch (ModLists.ORBITAL_CORES.indexOf(block)) {
                case 0 -> orbitalTntCannon(moon, pos, block, clickedPos, player, stack);
                default -> player.sendSystemMessage(Component.literal("Cannon core is missing!"));
            }
        }
        return InteractionResult.SUCCESS;
    }

    private void orbitalTntCannon(ServerLevel level, BlockPos pos, Block block, BlockPos clickedPos, Player player, ItemStack stack) {
        PrimedTnt tnt = new PrimedTnt(EntityType.TNT, player.level());
        tnt.setPos(Vec3.atCenterOf(new BlockPos(clickedPos.getX(), clickedPos.getY() + 10, clickedPos.getZ())));
        if(((OrbitalTNTCoreBlock) block).isStructureValid(ModMultiBlockStructures.ORBITAL_TNT_CANNON, level, pos)) {
            player.level().addFreshEntity(tnt);
            player.getInventory().removeItem(stack);
        }
        else {
            player.sendSystemMessage(Component.literal("Cannon is incomplete!"));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Linked to " + getName(machineIndex) + " at " + stack.get(ModDataStorage.LINKED_ORBITAL_CORE) + " in dimension " + dimension));

    }
}
