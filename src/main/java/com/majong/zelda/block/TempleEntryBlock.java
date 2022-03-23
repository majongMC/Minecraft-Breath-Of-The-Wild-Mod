package com.majong.zelda.block;

import java.util.UUID;

import javax.annotation.Nullable;

import com.majong.zelda.data.DataManager;
import com.majong.zelda.tileentity.HasTempleIDTileEntity;
import com.majong.zelda.tileentity.HasTempleIDTileEntity.TempleEntryTileEntity;
import com.majong.zelda.world.dimension.DimensionInit;
import com.majong.zelda.world.dimension.TempleDimensionData;
import com.majong.zelda.world.dimension.TempleTeleporter;
import com.majong.zelda.world.structure.ModStructures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

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
		if(!worldIn.isClientSide) {
			if(TempleDimensionData.occupied) {
				player.sendMessage(new TranslationTextComponent("服务器繁忙，请稍后再试"), UUID.randomUUID());
				return ActionResultType.SUCCESS;
			}
			HasTempleIDTileEntity tile=(HasTempleIDTileEntity) worldIn.getBlockEntity(pos);
			CompoundNBT data=TempleDimensionData.get(worldIn).DATA;
			if(tile.getID()<=1) {
				TempleDimensionData.occupied=true;
				int templeID=TempleDimensionData.AllocateNewTemple(worldIn);
				tile.setID(templeID);
				int[] position={pos.getX(),pos.getY(),pos.getZ()};
				TempleDimensionData.getTempleData(worldIn, templeID).putIntArray("temple_location", position);
				TempleDimensionData.get(worldIn).setDirty();
				DataManager.data.get(player).intemple=templeID;
				player.setGameMode(GameType.ADVENTURE);
				DataManager.AdjustAllSkills(player, false);
				ServerWorld temple=worldIn.getServer().getLevel(DimensionInit.TEMPLE_DIMENSION);
				BlockPos searchpos=player.blockPosition();
				player.changeDimension(temple,new TempleTeleporter());
				player.addEffect(new EffectInstance(Effects.SLOW_FALLING,60,8));
				BlockPos templepos;
				while(true) {
				templepos = temple.findNearestMapFeature(ModStructures.TEMPLES.get(), searchpos, 100, false);
				//boolean out=TempleDimensionData.isAllocated(worldIn, templepos);
				//LogManager.getLogger().info("allocated"+out);
				if(!TempleDimensionData.isAllocated(worldIn, templepos))
					break;
				searchpos=searchpos.offset(1000, 0, 0);
				}
				player.teleportTo(templepos.getX(),160,templepos.getZ());
				player.addEffect(new EffectInstance(Effects.SLOW_FALLING,60,8));
			}else {
				player.setGameMode(GameType.ADVENTURE);
				DataManager.AdjustAllSkills(player, false);
				DataManager.data.get(player).intemple=tile.getID();
				player.changeDimension(worldIn.getServer().getLevel(DimensionInit.TEMPLE_DIMENSION),new TempleTeleporter());
				player.addEffect(new EffectInstance(Effects.SLOW_FALLING,60,8));
				int[] templepos=data.getCompound(Integer.toString(tile.getID())).getIntArray("startpoint");
				player.teleportTo(templepos[0]+0.5, templepos[1]+2, templepos[2]+0.5);
			}
		}
		return ActionResultType.SUCCESS;
	}
}
