package com.majong.zelda.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class HasTempleIDTileEntity extends TileEntity{
	private int templeID=0;
	/*public HasTempleIDTileEntity() {
		super(TileEntityLoader.TEMPLE_ENTRY_TILE_ENTITY.get());
		// TODO 自动生成的构造函数存根
	}*/
	public HasTempleIDTileEntity(TileEntityType<?> p_i48289_1_) {
		super(p_i48289_1_);
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
	public static class TempleEntryTileEntity extends HasTempleIDTileEntity{

		public TempleEntryTileEntity() {
			super(TileEntityLoader.TEMPLE_ENTRY_TILE_ENTITY.get());
			// TODO 自动生成的构造函数存根
		}
		
	}
	public static class TempleFinishTileEntity extends HasTempleIDTileEntity{
		private CompoundNBT conqueredplayer=new CompoundNBT();
		public TempleFinishTileEntity() {
			super(TileEntityLoader.TEMPLE_FINISH_TILE_ENTITY.get());
			// TODO 自动生成的构造函数存根
		}
		@Override
	    public void load(BlockState state, CompoundNBT nbt) {
			super.load(state, nbt);
			this.conqueredplayer=nbt.getCompound("conqueredplayer");
		}
		@Override
	    public CompoundNBT save(CompoundNBT tag) {
			tag.put("conqueredplayer", this.conqueredplayer);
			return super.save(tag);
		}
		public boolean isConquered(PlayerEntity player) {
			return conqueredplayer.contains(player.getUUID().toString());
		}
		public void addConqueredPlayer(PlayerEntity player) {
			conqueredplayer.put(player.getUUID().toString(), new CompoundNBT());
		}
	}
}
