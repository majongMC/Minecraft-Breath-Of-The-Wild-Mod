package com.majong.zelda.tileentity;

import com.majong.zelda.data.DataManager;
import com.majong.zelda.world.dimension.TempleDimensionData;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class TempleStartTileEntity extends BlockEntity{
	private int templeID=0;
	public TempleStartTileEntity(BlockPos pWorldPosition, BlockState pBlockState) {
		super(TileEntityLoader.TEMPLE_START_TILE_ENTITY.get(),pWorldPosition,pBlockState);
		// TODO �Զ����ɵĹ��캯�����
	}
	public void setID(int id) {
		this.templeID=id;
		setChanged(this.level, this.worldPosition, this.getBlockState());
	}
	public int getID() {
		return templeID;
	}
	@Override
    public void load(CompoundTag nbt) {
		super.load(nbt);
		this.templeID=nbt.getInt("templeID");
	}
	@Override
    public void saveAdditional(CompoundTag tag) {
		tag.putInt("templeID", templeID);
		super.saveAdditional(tag);
	}

	public static void tick(Level level, BlockPos pos, BlockState pState, TempleStartTileEntity tile) {
		// TODO �Զ����ɵķ������
		if(!level.isClientSide&&tile.templeID==0) {
			Player player=level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 80, false);
			if(player!=null&&DataManager.data.get(player).intemple!=0) {
				tile.templeID=DataManager.data.get(player).intemple;
				setChanged(level, pos, pState);
				for(int i=-1;i<2;i++)
					for(int j=-1;j<2;j++) {
						BlockPos search=pos.offset(i, -2, j);
						if(level.getBlockState(search).getBlock()==Blocks.COMMAND_BLOCK) {
							player.teleportTo(search.getX()+0.5, search.getY()+2, search.getZ()+0.5);
							int[] startpoint= {search.getX(), search.getY(), search.getZ()};
							TempleDimensionData.getTempleData(level, tile.templeID).putIntArray("startpoint", startpoint);
							TempleDimensionData.get(level).setDirty();
							player.setDeltaMovement(Vec3.ZERO);
							TempleDimensionData.occupied=false;
							return;
						}
					}
				player.teleportTo(pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5);
				int[] startpoint= {pos.getX(), pos.getY(), pos.getZ()};
				TempleDimensionData.getTempleData(level, tile.templeID).putIntArray("startpoint", startpoint);
				TempleDimensionData.get(level).setDirty();
				player.setDeltaMovement(Vec3.ZERO);
				TempleDimensionData.occupied=false;
			}
		}
	}
	
}
