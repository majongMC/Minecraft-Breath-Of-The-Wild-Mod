package com.majong.zelda.block;

import javax.annotation.Nullable;

import com.majong.zelda.tileentity.TempleEntryTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TempleEntryBlock extends Block{
	private static DirectionProperty FACING = HorizontalBlock.FACING;
	public TempleEntryBlock() {
		super(Properties.of(Material.STONE).strength(-1).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING,Direction.NORTH));
		// TODO 自动生成的构造函数存根
	}
	@Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
		if (context.getPlayer() != null) {
			return defaultBlockState().setValue(FACING,context.getPlayer().getDirection().getOpposite());
		}
		return defaultBlockState();
    }
	@Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
        super.createBlockStateDefinition(builder);
	   }
	@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
	@Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TempleEntryTileEntity();
	}
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		//if(!worldIn.isClientSide&&player.getMainHandItem().getItem()==ItemLoader.SHIKA_STONE.get()) {
			TempleEntryTileEntity tile=(TempleEntryTileEntity) worldIn.getBlockEntity(pos);
			tile.TeleportToTemple(player);
		//}
		return ActionResultType.SUCCESS;
	}
}
