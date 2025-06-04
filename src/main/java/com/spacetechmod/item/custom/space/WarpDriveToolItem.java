package com.spacetechmod.item.custom.space;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;

import java.util.List;

public class WarpDriveToolItem extends Item {
    public WarpDriveToolItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.EPIC)
                .fireResistant()
                .setNoRepair());
    }

    private int x;
    private int y;
    private int z;
    private int direction;
    //0 is forward, 1 is right, 2 is backward, 3 is left

    private boolean mode = true;
    //true is coords, false is ship size


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }
    public int getDirectionNumber() {
        return direction;
    }
    public boolean getMode() {
        return mode;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(player.isShiftKeyDown() && !level.isClientSide) {
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            mode = !mode;
            x = 0;
            y = 0;
            z = 0;
            direction = 0;
            player.sendSystemMessage(Component.literal("Mode Changed!"));
            return InteractionResultHolder.success(player.getMainHandItem());
        }
        return InteractionResultHolder.fail(player.getMainHandItem());
    }

    private String getDirection() {
        switch(direction) {
            case 0 -> {
                return "Forward";
            }
            case 1 -> {
                return "Right";
            }
            case 2 -> {
                return "Backward";
            }
            case 3 -> {
                return "Left";
            }
        }
        return "Forward";
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockEntity entity = level.getBlockEntity(context.getClickedPos());
        if (entity instanceof SignBlockEntity && !level.isClientSide) {
            String messageX = ((SignBlockEntity) entity).getFrontText().getMessage(0, false).getString();
            String messageY = ((SignBlockEntity) entity).getFrontText().getMessage(1, false).getString();
            String messageZ = ((SignBlockEntity) entity).getFrontText().getMessage(2, false).getString();
            String messageDir;
            if(mode) {
                messageDir = ((SignBlockEntity) entity).getFrontText().getMessage(3, false).getString();
            }
            else {
                messageDir = "0";
            }
            try {
                x = Integer.parseInt(messageX);
                y = Integer.parseInt(messageY);
                z = Integer.parseInt(messageZ);
                direction = Integer.parseInt(messageDir);
            } catch (Exception e) {
                x = 0;
                y = 0;
                z = 0;
                direction = 0;
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        if(mode) {
            list.add(Component.literal("Destination: " + x + ", " + y + ", " + z + ", " +
                    "Direction: " + getDirection()));
        }
        else {
            list.add(Component.literal("Ship Length, Width, and Height: " + 2*x + ", " + 2*z + ", " + 2*y));
        }
        super.appendHoverText(stack, context, list, flag);
    }

}
