package com.spacetechmod.item.custom.space.orbital;

import com.spacetechmod.block.custom.multiblock.OrbitalFlameCoreBlock;
import com.spacetechmod.block.custom.multiblock.OrbitalTNTCoreBlock;
import com.spacetechmod.data.ModDataStorage;
import com.spacetechmod.util.ModLists;
import com.spacetechmod.util.ModMultiBlockStructures;
import com.spacetechmod.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Random;

public class OrbitalMarkerItem extends Item {

    //instance stuff
    Random random = new Random();

    private int timer = 0;
    private int firedShells = 0;

    private boolean tntCannonActive = false;
    private boolean flameCannonActive = false;

    private Level textLevel;

    private BlockPos setPos;

    public OrbitalMarkerItem() {
        super(new Properties()
                .stacksTo(8)
                .fireResistant());
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
        if (!moon.isClientSide && !player.level().isClientSide) {
            switch (ModLists.ORBITAL_CORES.indexOf(block)) {
                case 0 -> {
                    orbitalTntCannon(moon, pos, block, dropPos, player);
                    player.getItemInHand(usedHand).shrink(1);
                }

                case 1 -> {
                    orbitalFlameCannon(moon, pos, block, dropPos, player);
                    player.getItemInHand(usedHand).shrink(1);
                }

                default -> player.sendSystemMessage(Component.literal("Cannon core is missing!"));
            }
        }
        return InteractionResultHolder.success(player.getMainHandItem());
    }

    private void orbitalTntCannon(ServerLevel level, BlockPos pos, Block block, BlockPos dropPos, Player player) {
        setPos = dropPos;
        if(((OrbitalTNTCoreBlock) block).isStructureValid(ModMultiBlockStructures.ORBITAL_TNT_CANNON, level, pos)) {
            tntCannonActive = true;
        }
        else {
            player.sendSystemMessage(Component.literal("Cannon is incomplete!"));
        }
    }

    private void orbitalFlameCannon(ServerLevel level, BlockPos pos, Block block, BlockPos dropPos, Player player) {
        setPos = dropPos;
        if(((OrbitalFlameCoreBlock) block).isStructureValid(ModMultiBlockStructures.ORBITAL_FLAME_CANNON, level, pos)) {
            flameCannonActive = true;
        }
        else {
            player.sendSystemMessage(Component.literal("Cannon is incomplete!"));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(!level.isClientSide) {
            textLevel = level;
            ++timer;
            if (timer >= 20) {
                timer = 0;
            }
            if(firedShells >= 32) {
                tntCannonActive = false;
                flameCannonActive = false;
                firedShells = 0;
            }
            if (tntCannonActive) {
                PrimedTnt tnt = new PrimedTnt(EntityType.TNT, level);
                tnt.setFuse(400);
                tnt.setDeltaMovement(0.0, -2, 0.0);
                tnt.setPos(setPos.getX() + random.nextInt(-15, 15), setPos.getY() + 100, setPos.getZ() + random.nextInt(-15, 15));
                level.addFreshEntity(tnt);
                ++firedShells;
            }
            if (flameCannonActive) {
                LargeFireball fireball = new LargeFireball(EntityType.FIREBALL, level);
                fireball.setDeltaMovement(0.0, -0.01, 0.0);
                fireball.setPos(setPos.getX() + random.nextInt(-15, 15), setPos.getY() + 100, setPos.getZ() + random.nextInt(-15, 15));
                level.addFreshEntity(fireball);
                ++firedShells;
            }
        }
    }

    private String getCannonName(ItemStack stack) {
        MinecraftServer server;
        ServerLevel moon;
        Block core;
        BlockPos pos;
        try {
            server = textLevel.getServer();
            moon = server.getLevel(ModDimensions.MOON);
            pos = stack.get(ModDataStorage.LINKED_ORBITAL_CORE);
            core = moon.getBlockState(pos).getBlock();
        } catch (Exception e) {
            return "";
        }
        switch(ModLists.ORBITAL_CORES.indexOf(core)) {
            case 0 -> {
                return "Orbital TNT Cannon";
            }

            case 1 -> {
                return "Orbital Flame Cannon";
            }
        }
        return "Nothing!";
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.literal("Linked to: " + getCannonName(stack)));
    }
}