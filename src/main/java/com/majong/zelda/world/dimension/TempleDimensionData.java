package com.majong.zelda.world.dimension;

import com.majong.zelda.data.DataManager;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

public class TempleDimensionData extends SavedData{
	private static final String NAME = "ztd";
	public static final int FIRST_ALLOCATE=1;
	public static boolean occupied=false;
	public CompoundTag DATA=init();
	private CompoundTag init() {
		CompoundTag data=new CompoundTag();
		data.putInt("allocated", FIRST_ALLOCATE);
		return data;
	}
	public TempleDimensionData() {
		super();
		//DATA.putInt("allocated", FIRST_ALLOCATE);
		// TODO �Զ����ɵĹ��캯�����
	}
	/*public TempleDimensionData(String name) {
		super(name);
		//DATA.putInt("allocated", FIRST_ALLOCATE);
		// TODO �Զ����ɵĹ��캯�����
	}*/
	public static TempleDimensionData get(Level worldIn) {
        if (!(worldIn instanceof ServerLevel)) {
            throw new RuntimeException("Attempted to get the data from a client Level. This is wrong.");
        }
        ServerLevel Level = worldIn.getServer().getLevel(DimensionInit.TEMPLE_DIMENSION);
        DimensionDataStorage storage = Level.getDataStorage();
        //LogManager.getLogger().info("usesaveddata");
        return storage.computeIfAbsent(TempleDimensionData::load,TempleDimensionData::new, NAME);
    }

	public static TempleDimensionData load(CompoundTag nbt) {
		// TODO �Զ����ɵķ������
		//LogManager.getLogger().info("loadsaveddata");
		TempleDimensionData dat=new TempleDimensionData();
		dat.DATA=nbt.getCompound("ztd");
		if(!dat.DATA.contains("allocated")||dat.DATA.getInt("allocated")==0) {
			dat.DATA.putInt("allocated", FIRST_ALLOCATE);
		}
		return dat;
	}

	@Override
	public CompoundTag save(CompoundTag nbt) {
		// TODO �Զ����ɵķ������
		//LogManager.getLogger().info("savesaveddata");
		nbt.put("ztd",DATA);
		return nbt;
	}
	public static int AllocateNewTemple(Level worldIn) {
		CompoundTag data=get(worldIn).DATA;
		int templeID=data.getInt("allocated")+1;
		data.putInt("allocated", templeID);
		data.put(Integer.toString(templeID), new CompoundTag());
		get(worldIn).setDirty();
		return templeID;
	}
	public static boolean isAllocated(Level worldIn,BlockPos pos) {
		CompoundTag data=get(worldIn).DATA;
		int allocated=data.getInt("allocated");
		int posX=pos.getX();
		int posZ=pos.getZ();
		for(int i=2;i<allocated;i++) {
			if(data.getCompound(Integer.toString(i)).contains("startpoint")) {
			int[] templepos=data.getCompound(Integer.toString(i)).getIntArray("startpoint");
			if(Math.abs(templepos[0]-posX)<64&&Math.abs(templepos[2]-posZ)<64)
				return true;
			}
		}
		return false;
	}
	public static CompoundTag getTempleData(Level worldIn,int templeID) {
		CompoundTag data=get(worldIn).DATA;
		return data.getCompound(Integer.toString(templeID));
	}
	public static void ExitTemple(Level worldIn,Player player){
		player.setHealth(player.getMaxHealth());
		TempleDimensionData.occupied=false;
		((ServerPlayer)player).setGameMode(GameType.SURVIVAL);
		DataManager.AdjustAllSkills(player, true);
		int templeID=DataManager.data.get(player).intemple;
		DataManager.data.get(player).intemple=0;
		ServerLevel overworld=worldIn.getServer().getLevel(Level.OVERWORLD);
		player.changeDimension(overworld,new TempleTeleporter());
		CompoundTag data=getTempleData(worldIn,templeID);
		int[] templepos=data.getIntArray("temple_location");
		player.teleportTo(templepos[0]+0.5, templepos[1]+1, templepos[2]+0.5);
	}
}
