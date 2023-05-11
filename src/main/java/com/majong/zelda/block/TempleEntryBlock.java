package com.majong.zelda.block;

import org.jetbrains.annotations.Nullable;

import com.majong.zelda.data.DataManager;
import com.majong.zelda.event.EntityTick;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;
import com.majong.zelda.tileentity.HasTempleIDTileEntity;
import com.majong.zelda.tileentity.HasTempleIDTileEntity.TempleEntryTileEntity;
import com.majong.zelda.util.ChangeDimension;
import com.majong.zelda.world.dimension.DimensionInit;
import com.majong.zelda.world.dimension.TempleDimensionData;
import com.majong.zelda.world.structure.ModStructures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;

public class TempleEntryBlock extends BaseEntityBlock{
	private static DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public TempleEntryBlock() {
		super(Properties.of(Material.METAL,MaterialColor.STONE).strength(-1, 3600000.0F).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING,Direction.NORTH));
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getPlayer() != null) {
			return defaultBlockState().setValue(FACING,context.getPlayer().getDirection().getOpposite());
		}
		return defaultBlockState();
    }
	@Override
	public RenderShape getRenderShape(BlockState p_60550_) {
	      return RenderShape.MODEL;
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
        super.createBlockStateDefinition(builder);
	   }
	/*@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }*/
	@Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new TempleEntryTileEntity(pPos,pState);
		//return null;
	}
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if(!worldIn.isClientSide) {
			if(TempleDimensionData.occupied) {
				player.sendSystemMessage(Component.translatable("服务器繁忙，请稍后再试"));
				return InteractionResult.SUCCESS;
			}
			HasTempleIDTileEntity tile=(HasTempleIDTileEntity) worldIn.getBlockEntity(pos);
			CompoundTag data=TempleDimensionData.get(worldIn).DATA;
			if(tile.getID()<=1) {
				TempleDimensionData.occupied=true;
				EntityTick.ENTER_TEMPLE_TIME.put(player, worldIn.getServer().getLevel(Level.OVERWORLD).getGameTime());
				int templeID=TempleDimensionData.AllocateNewTemple(worldIn);
				tile.setID(templeID);
				int[] position={pos.getX(),pos.getY(),pos.getZ()};
				TempleDimensionData.getTempleData(worldIn, templeID).putIntArray("temple_location", position);
				TempleDimensionData.get(worldIn).setDirty();
				DataManager.data.get(player).intemple=templeID;
				((ServerPlayer)player).setGameMode(GameType.ADVENTURE);
				DataManager.AdjustAllSkills(player, false);
				ServerLevel temple=worldIn.getServer().getLevel(DimensionInit.TEMPLE_DIMENSION);
				BlockPos searchpos=player.blockPosition();
				ChangeDimension.toTemple(player);
				player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,100,8));
				BlockPos templepos;
				while(true) {
				templepos = temple.findNearestMapStructure(ModStructures.TEMPLES, searchpos, 100, false);
				//boolean out=TempleDimensionData.isAllocated(worldIn, templepos);
				//LogManager.getLogger().info("allocated"+out);
				if(!TempleDimensionData.isAllocated(worldIn, templepos))
					break;
				searchpos=searchpos.offset(1000, 0, 0);
				}
				player.teleportTo(templepos.getX(),80,templepos.getZ());
				player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,60,8));
			}else {
				if(data.getCompound(Integer.toString(tile.getID())).contains("startpoint")&&data.getCompound(Integer.toString(tile.getID())).getIntArray("startpoint")[1]!=-100) {
				((ServerPlayer)player).setGameMode(GameType.ADVENTURE);
				DataManager.AdjustAllSkills(player, false);
				DataManager.data.get(player).intemple=tile.getID();
				ChangeDimension.toTemple(player);
				player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,60,8));
				int[] templepos=data.getCompound(Integer.toString(tile.getID())).getIntArray("startpoint");
				player.teleportTo(templepos[0]+0.5, templepos[1]+2, templepos[2]+0.5);
				PlayTempleSound(player);
			}else {
				player.sendSystemMessage(Component.translatable("此神庙异常，请寻找其他神庙"));
			}
			}
		}
		return InteractionResult.SUCCESS;
	}
	public static void PlayTempleSound(Player player) {
		Networking.SOUND.send((ServerPlayer) player,new SoundPack(12,new BlockPos((int)player.getX(),(int)player.getY(),(int)player.getZ())));
	}
}
