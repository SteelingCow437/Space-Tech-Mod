package com.spacetechmod.item.custom.tool;

import com.spacetechmod.block.entity.machine.WarpDriveBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;

import java.util.List;

public class ShipBlueprintItem extends Item {
    public ShipBlueprintItem() {
        super(new Properties()
                .stacksTo(1));
    }

    private int sizeX = 0;
    private int sizeY = 0;
    private int sizeZ = 0;

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockEntity entity = level.getBlockEntity(context.getClickedPos());
        if (entity instanceof SignBlockEntity && !level.isClientSide) {
            String messageX = ((SignBlockEntity) entity).getFrontText().getMessage(0, false).getString();
            String messageY = ((SignBlockEntity) entity).getFrontText().getMessage(1, false).getString();
            String messageZ = ((SignBlockEntity) entity).getFrontText().getMessage(2, false).getString();
            try {
                sizeX = Integer.parseInt(messageX);
                sizeY = Integer.parseInt(messageY);
                sizeZ = Integer.parseInt(messageZ);
            } catch (Exception e) {
                sizeX = 0;
                sizeY = 0;
                sizeZ = 0;
            }
            return InteractionResult.SUCCESS;
        }
        else if(entity instanceof WarpDriveBlockEntity && !level.isClientSide) {
            ((WarpDriveBlockEntity) entity).setInitialSize(sizeX, sizeY, sizeZ, context.getPlayer(), true);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("Ship Dimensions: " + sizeX + ", " + sizeY + ", " + sizeZ));
        super.appendHoverText(stack, context, list, flag);
    }
}
