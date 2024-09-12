package com.net.spacetechmod.item.custom.space;

import com.net.spacetechmod.block.entity.dungeon.StarGateCoreBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.ItemInteractionResult;
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

    public int X = 0;
    public int Y = 0;
    public int Z = 0;
    private int selectedParameter = 0;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(player.isShiftKeyDown()) {
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            X = 0;
            Y = 0;
            Z = 0;
            return InteractionResultHolder.success(player.getMainHandItem());
        }
        return InteractionResultHolder.fail(player.getMainHandItem());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockEntity entity = level.getBlockEntity(context.getClickedPos());
        if(entity instanceof SignBlockEntity) {
            String message = ((SignBlockEntity) entity).getFrontText().getMessage(0, false).getString();
            int coordinate = Integer.parseInt(message);
            switch(selectedParameter) {
                case 0 -> X = coordinate;
                case 1 -> Y = coordinate;
                case 2 -> Z = coordinate;
            }
            if(selectedParameter < 2) {
                ++selectedParameter;
            }
            else {
                selectedParameter = 0;
            }
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
        else if(entity instanceof StarGateCoreBlockEntity) {
            ((StarGateCoreBlockEntity) entity).setDestination(X, Y, Z);
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("Destination: " + X + ", " + Y + ", " + Z));
        super.appendHoverText(stack, context, list, flag);
    }
}
