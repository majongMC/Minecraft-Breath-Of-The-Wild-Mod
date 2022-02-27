package com.majong.zelda.tileentity;

import java.util.UUID;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class TempleEntryTileEntity extends TileEntity{
	private int templeID=0;
	public TempleEntryTileEntity() {
		super(TileEntityLoader.TEMPLE_ENTRY_TILE_ENTITY.get());
		// TODO �Զ����ɵĹ��캯�����
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
	public void TeleportToTemple(Entity entity) {
		if(this.templeID==0) {
			templeID=1;
		}
		entity.sendMessage(new TranslationTextComponent("������˼������ά�Ȼ�û�����ã����ע�������¡�"), UUID.randomUUID());
	}
}
