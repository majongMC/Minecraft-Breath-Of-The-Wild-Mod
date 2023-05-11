package com.majong.zelda.block;

import javax.annotation.Nullable;

import com.majong.zelda.tileentity.PotTileEntity;
import com.majong.zelda.tileentity.TileEntityLoader;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;

public class PotBlock extends BaseEntityBlock{

	public PotBlock() {
		super(BlockBehaviour.Properties.of(Material.METAL,MaterialColor.STONE).strength(2.5F).requiresCorrectToolForDrops().noOcclusion());
		// TODO �Զ����ɵĹ��캯�����
	}
	/*@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }*/
	@Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new PotTileEntity(pPos,pState);
		//return null;
	}
	@Override
	public RenderShape getRenderShape(BlockState p_60550_) {
	      return RenderShape.MODEL;
	   }
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if(!worldIn.isClientSide&&worldIn.getBlockEntity(pos) instanceof PotTileEntity) {
			PotTileEntity tile=(PotTileEntity) worldIn.getBlockEntity(pos);
			if(!player.getMainHandItem().isEmpty()) {
				player.setItemInHand(InteractionHand.MAIN_HAND, tile.tryacceptitem(player.getMainHandItem()));
			}
			else{
				if(player.isShiftKeyDown()) {
					player.setItemInHand(InteractionHand.MAIN_HAND, tile.tryextractitem());
				}
				else
					tile.start(player);
			}
		}
		return InteractionResult.SUCCESS;
	}
	@Override
	public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
		if(!worldIn.isClientSide) {
			PotTileEntity tile=(PotTileEntity) worldIn.getBlockEntity(pos);
			for(int i=0;i<5;i++) {
				if(!tile.getStack(i).isEmpty()) {
					Entity itemdrops=new ItemEntity(worldIn,pos.getX(),pos.getY(),pos.getZ(),tile.getStack(i));
					worldIn.addFreshEntity(itemdrops);
				}
			}
		}
		super.playerWillDestroy(worldIn, pos, state, player);
	}
	/*@Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> drops = super.getDrops(state, builder);
		drops.add(new ItemStack(BlockLoader.ITEM_POT.get(),1));
		return drops;
	}*/
	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, TileEntityLoader.POT_TILE_ENTITY.get(),
                PotTileEntity::tick);
    }
}
