package com.astronautica.block.custom.dungeon;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class StarGatePortalBlock extends Block {
    public StarGatePortalBlock(Properties properties) {
        super(properties);
    }


    private Vec3 DESTINATION = new Vec3(0, 63, 0);

    public void setDestination(int setx, int sety, int setz) {
        DESTINATION = new Vec3(setx, sety, setz);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.teleportTo(DESTINATION.x, DESTINATION.y, DESTINATION.z);
    }
}
