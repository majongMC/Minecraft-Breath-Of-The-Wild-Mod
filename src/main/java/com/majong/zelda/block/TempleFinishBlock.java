package com.majong.zelda.block;

import java.util.UUID;

import javax.annotation.Nullable;

import com.majong.zelda.data.DataManager;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.network.GuiMessagePack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.tileentity.HasTempleIDTileEntity.TempleFinishTileEntity;
import com.majong.zelda.world.dimension.TempleDimensionData;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class TempleFinishBlock extends TempleStartBlock{
	@Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TempleFinishTileEntity();
	}
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isClientSide) {
			TempleFinishTileEntity tile=(TempleFinishTileEntity) worldIn.getBlockEntity(pos);
			if(tile.getID()==0)
				tile.setID(DataManager.data.get(player).intemple);
			if(tile.isConquered(player)) {
				TempleDimensionData.ExitTemple(worldIn, player);
				return ActionResultType.SUCCESS;
			}
			if(!player.addItem(new ItemStack(ItemLoader.SPIRIT_ORB.get(),1))) {
				player.sendMessage(new TranslationTextComponent("msg.zelda.inventoryfull"), UUID.randomUUID());
				return ActionResultType.SUCCESS;
			}
			tile.addConqueredPlayer(player);
			Networking.FOODMESSAGEPACK.send(
	                PacketDistributor.PLAYER.with(
	                        () -> (ServerPlayerEntity) player
	                ),
	                new GuiMessagePack(2,0,0));
		}
		return ActionResultType.SUCCESS;
	}
}
