package com.majong.zelda.block;

import java.util.List;

import javax.annotation.Nullable;

import com.majong.zelda.tileentity.PotTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class PotBlock extends Block{

	public PotBlock() {
		super(Properties.of(Material.STONE).strength(2.5F).harvestLevel(0).harvestTool(ToolType.PICKAXE).noOcclusion());
		// TODO 自动生成的构造函数存根
	}
	@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
	@Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new PotTileEntity();
	}
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isClientSide&&worldIn.getBlockEntity(pos) instanceof PotTileEntity) {
			PotTileEntity tile=(PotTileEntity) worldIn.getBlockEntity(pos);
			if(!player.getMainHandItem().isEmpty()) {
				player.setItemInHand(Hand.MAIN_HAND, tile.tryacceptitem(player.getMainHandItem()));
			}
			else{
				if(player.isShiftKeyDown()) {
					player.setItemInHand(Hand.MAIN_HAND, tile.tryextractitem());
				}
				else
					tile.start(player);
			}
		}
		return ActionResultType.SUCCESS;
	}
	@Override
	public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
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
	@Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> drops = super.getDrops(state, builder);
		drops.add(new ItemStack(BlockLoader.ITEM_POT.get(),1));
		return drops;
	}
}
