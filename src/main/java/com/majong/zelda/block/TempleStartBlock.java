package com.majong.zelda.block;

import javax.annotation.Nullable;

import com.majong.zelda.tileentity.TempleStartTileEntity;
import com.majong.zelda.world.dimension.TempleDimensionData;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TempleStartBlock extends TempleEntryBlock{
	@Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TempleStartTileEntity();
	}
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isClientSide) {
			TempleDimensionData.ExitTemple(worldIn, player);
		}
		return ActionResultType.SUCCESS;
	}
}
