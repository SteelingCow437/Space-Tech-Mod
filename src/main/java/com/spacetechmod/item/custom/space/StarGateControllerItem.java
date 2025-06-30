package com.spacetechmod.item.custom.space;

import com.spacetechmod.block.entity.machine.WarpDriveBlockEntity;
import com.spacetechmod.data.ModDataStorage;
import net.minecraft.core.BlockPos;
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

public class StarGateControllerItem extends Item {
    public StarGateControllerItem() {
        super(new Properties()
                .fireResistant()
                .stacksTo(1)
                .rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!player.isShiftKeyDown() && !level.isClientSide) {
            Item item = player.getItemInHand(hand).getItem();
            if(item instanceof StarGateControllerItem) {
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                player.getItemInHand(hand).set(ModDataStorage.SGC_DESTINATION, new BlockPos(0, 0, 0));
                return InteractionResultHolder.success(player.getItemInHand(hand));
            }
        }
        return InteractionResultHolder.fail(player.getMainHandItem());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockEntity entity = level.getBlockEntity(context.getClickedPos());
        if (entity instanceof SignBlockEntity && !level.isClientSide) {
            String messageX = ((SignBlockEntity) entity).getFrontText().getMessage(0, false).getString();
            String messageY = ((SignBlockEntity) entity).getFrontText().getMessage(1, false).getString();;
            String messageZ = ((SignBlockEntity) entity).getFrontText().getMessage(2, false).getString();
            try {
                context.getItemInHand().set(ModDataStorage.SGC_DESTINATION, new BlockPos(
                        Integer.parseInt(messageX), Integer.parseInt(messageY), Integer.parseInt(messageZ)
                ));
            } catch (Exception e) {
                context.getItemInHand().set(ModDataStorage.SGC_DESTINATION, new BlockPos(0, 0, 0));
            }
            return InteractionResult.SUCCESS;
        }
        else if(entity instanceof WarpDriveBlockEntity && !level.isClientSide && context.getItemInHand().get(ModDataStorage.SGC_DESTINATION) != null) {
            ((WarpDriveBlockEntity) entity).setDestination(context.getItemInHand().get(ModDataStorage.SGC_DESTINATION), context.getPlayer());
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
            x = stack.get(ModDataStorage.SGC_DESTINATION).getX();
            y = stack.get(ModDataStorage.SGC_DESTINATION).getY();
            z = stack.get(ModDataStorage.SGC_DESTINATION).getZ();
        }
        catch(Exception e) {
            x = 0;
            y = 0;
            z = 0;
        }
        list.add(Component.literal("Destination: " + x + ", " + y + ", " + z));
        super.appendHoverText(stack, context, list, flag);
    }
}
