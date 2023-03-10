package com.majong.zelda.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class HasTempleIDTileEntity extends BlockEntity{
	private int templeID=0;
	/*public HasTempleIDTileEntity() {
		super(TileEntityLoader.TEMPLE_ENTRY_TILE_ENTITY.get());
		// TODO �Զ����ɵĹ��캯�����
	}*/
	public HasTempleIDTileEntity(BlockEntityType<?> p_i48289_1_,BlockPos pWorldPosition, BlockState pBlockState) {
		super(p_i48289_1_,pWorldPosition,pBlockState);
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
	public static class TempleEntryTileEntity extends HasTempleIDTileEntity{

		public TempleEntryTileEntity(BlockPos pWorldPosition, BlockState pBlockState) {
			super(TileEntityLoader.TEMPLE_ENTRY_TILE_ENTITY.get(),pWorldPosition,pBlockState);
			// TODO �Զ����ɵĹ��캯�����
		}
		
	}
	public static class TempleFinishTileEntity extends HasTempleIDTileEntity{
		private CompoundTag conqueredplayer=new CompoundTag();
		public TempleFinishTileEntity(BlockPos pWorldPosition, BlockState pBlockState) {
			super(TileEntityLoader.TEMPLE_FINISH_TILE_ENTITY.get(),pWorldPosition,pBlockState);
			// TODO �Զ����ɵĹ��캯�����
		}
		@Override
	    public void load(CompoundTag nbt) {
			super.load(nbt);
			this.conqueredplayer=nbt.getCompound("conqueredplayer");
		}
		@Override
	    public void saveAdditional(CompoundTag tag) {
			tag.put("conqueredplayer", this.conqueredplayer);
			super.saveAdditional(tag);
		}
		public boolean isConquered(Player player) {
			return conqueredplayer.contains(player.getUUID().toString());
		}
		public void addConqueredPlayer(Player player) {
			conqueredplayer.put(player.getUUID().toString(), new CompoundTag());
			setChanged(this.level, this.worldPosition, this.getBlockState());
		}
	}
}
