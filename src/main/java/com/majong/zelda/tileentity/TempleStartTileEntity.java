package com.majong.zelda.tileentity;

import com.majong.zelda.block.TempleEntryBlock;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.event.EntityTick;
import com.majong.zelda.world.dimension.TempleDimensionData;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class TempleStartTileEntity extends TileEntity implements ITickableTileEntity{
	private int templeID=0;
	public TempleStartTileEntity() {
		super(TileEntityLoader.TEMPLE_START_TILE_ENTITY.get());
		// TODO 自动生成的构造函数存根
	}
	public void setID(int id) {
		this.templeID=id;
	}
	public int getID() {
		return templeID;
	}
	@Override
    public void load(BlockState state, CompoundNBT nbt) {
		super.load(state,nbt);
		this.templeID=nbt.getInt("templeID");
	}
	@Override
    public CompoundNBT save(CompoundNBT tag) {
		tag.putInt("templeID", templeID);
		return super.save(tag);
	}
	@Override
	public void tick() {
		// TODO 自动生成的方法存根
		if(!this.level.isClientSide&&templeID==0) {
			BlockPos pos=this.getBlockPos();
			PlayerEntity player=level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 80, false);
			if(player!=null&&DataManager.data.get(player).intemple!=0) {
				this.templeID=DataManager.data.get(player).intemple;
				for(int i=-1;i<2;i++)
					for(int j=-1;j<2;j++) {
						BlockPos search=pos.offset(i, -2, j);
						if(level.getBlockState(search).getBlock()==Blocks.COMMAND_BLOCK) {
							player.teleportTo(search.getX()+0.5, search.getY()+2, search.getZ()+0.5);
							int[] startpoint= {search.getX(), search.getY(), search.getZ()};
							TempleDimensionData.getTempleData(level, templeID).putIntArray("startpoint", startpoint);
							TempleDimensionData.get(level).setDirty();
							EntityTick.ENTER_TEMPLE_TIME.remove(player);
							player.setDeltaMovement(Vector3d.ZERO);
							TempleEntryBlock.PlayTempleSound(player);
							TempleDimensionData.occupied=false;
							return;
						}
					}
				player.teleportTo(pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5);
				int[] startpoint= {pos.getX(), pos.getY(), pos.getZ()};
				TempleDimensionData.getTempleData(level, templeID).putIntArray("startpoint", startpoint);
				TempleDimensionData.get(level).setDirty();
				EntityTick.ENTER_TEMPLE_TIME.remove(player);
				player.setDeltaMovement(Vector3d.ZERO);
				TempleEntryBlock.PlayTempleSound(player);
				TempleDimensionData.occupied=false;
			}
		}
	}
	
}
