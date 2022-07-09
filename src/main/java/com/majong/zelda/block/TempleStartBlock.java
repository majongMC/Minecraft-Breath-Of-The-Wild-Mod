package com.majong.zelda.block;

import javax.annotation.Nullable;

import com.majong.zelda.tileentity.TempleStartTileEntity;
import com.majong.zelda.tileentity.TileEntityLoader;
import com.majong.zelda.world.dimension.TempleDimensionData;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TempleStartBlock extends TempleEntryBlock{
	@Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new TempleStartTileEntity(pPos,pState);
	}
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if(!worldIn.isClientSide) {
			TempleDimensionData.ExitTemple(worldIn, player);
		}
		return InteractionResult.SUCCESS;
	}
	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, TileEntityLoader.TEMPLE_START_TILE_ENTITY.get(),
                TempleStartTileEntity::tick);
    }
}
