package com.majong.zelda.block;

import javax.annotation.Nullable;

import com.majong.zelda.data.DataManager;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.network.GuiMessagePack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.tileentity.HasTempleIDTileEntity.TempleFinishTileEntity;
import com.majong.zelda.world.dimension.TempleDimensionData;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.PacketDistributor;

public class TempleFinishBlock extends TempleStartBlock{
	@Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new TempleFinishTileEntity(pPos,pState);
		//return null;
	}
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if(!worldIn.isClientSide) {
			TempleFinishTileEntity tile=(TempleFinishTileEntity) worldIn.getBlockEntity(pos);
			if(tile.getID()==0)
				tile.setID(DataManager.data.get(player).intemple);
			if(tile.isConquered(player)) {
				TempleDimensionData.ExitTemple(worldIn, player);
				return InteractionResult.SUCCESS;
			}
			if(!player.addItem(new ItemStack(ItemLoader.SPIRIT_ORB.get(),1))) {
				player.sendSystemMessage(Component.translatable("msg.zelda.inventoryfull"));
				return InteractionResult.SUCCESS;
			}
			tile.addConqueredPlayer(player);
			Networking.FOODMESSAGEPACK.send(
	                PacketDistributor.PLAYER.with(
	                        () -> (ServerPlayer) player
	                ),
	                new GuiMessagePack(2,0,0));
		}
		return InteractionResult.SUCCESS;
	}
}
