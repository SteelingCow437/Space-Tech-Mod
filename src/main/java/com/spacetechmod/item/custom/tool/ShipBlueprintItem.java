package com.spacetechmod.item.custom.tool;

import com.spacetechmod.block.entity.machine.WarpDriveBlockEntity;
import com.spacetechmod.data.ModDataStorage;
import net.minecraft.core.Vec3i;
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

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockEntity entity = level.getBlockEntity(context.getClickedPos());
        if (entity instanceof SignBlockEntity && !level.isClientSide) {
            String messageX = ((SignBlockEntity) entity).getFrontText().getMessage(0, false).getString();
            String messageY = ((SignBlockEntity) entity).getFrontText().getMessage(1, false).getString();
            String messageZ = ((SignBlockEntity) entity).getFrontText().getMessage(2, false).getString();
            try {
                context.getItemInHand().set(ModDataStorage.SHIP_SIZE, new Vec3i(
                        Integer.parseInt(messageX), Integer.parseInt(messageY), Integer.parseInt(messageZ)));
            } catch (Exception e) {
                context.getItemInHand().set(ModDataStorage.SHIP_SIZE, new Vec3i(0, 0, 0));
            }
            return InteractionResult.SUCCESS;
        }
        else if(entity instanceof WarpDriveBlockEntity && !level.isClientSide) {
            int sizeX = context.getItemInHand().get(ModDataStorage.SHIP_SIZE).getX();
            int sizeY = context.getItemInHand().get(ModDataStorage.SHIP_SIZE).getY();
            int sizeZ = context.getItemInHand().get(ModDataStorage.SHIP_SIZE).getZ();
            ((WarpDriveBlockEntity) entity).setInitialSize(sizeX, sizeY, sizeZ, context.getPlayer(), true);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        int x;
        int y;
        int z;
        try {
            x = stack.get(ModDataStorage.SHIP_SIZE).getX();
            y = stack.get(ModDataStorage.SHIP_SIZE).getY();
            z = stack.get(ModDataStorage.SHIP_SIZE).getZ();
        }
        catch(Exception e) {
            x = 0;
            y = 0;
            z = 0;
        }
        list.add(Component.literal("Ship Dimensions: " + x + ", " +
                y + ", " + z));
        super.appendHoverText(stack, context, list, flag);
    }
}
