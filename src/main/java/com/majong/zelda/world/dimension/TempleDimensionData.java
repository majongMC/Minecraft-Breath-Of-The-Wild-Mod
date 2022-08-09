package com.majong.zelda.world.dimension;

import org.apache.logging.log4j.LogManager;

import com.majong.zelda.data.DataManager;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class TempleDimensionData extends WorldSavedData{
	private static final String NAME = "ztd";
	public static final int FIRST_ALLOCATE=1;
	public static boolean occupied=false;
	public CompoundNBT DATA=init();
	private CompoundNBT init() {
		CompoundNBT data=new CompoundNBT();
		data.putInt("allocated", FIRST_ALLOCATE);
		return data;
	}
	public TempleDimensionData() {
		super(NAME);
		//DATA.putInt("allocated", FIRST_ALLOCATE);
		// TODO 自动生成的构造函数存根
	}
	public TempleDimensionData(String name) {
		super(name);
		//DATA.putInt("allocated", FIRST_ALLOCATE);
		// TODO 自动生成的构造函数存根
	}
	public static TempleDimensionData get(World worldIn) {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
        ServerWorld world = worldIn.getServer().getLevel(DimensionInit.TEMPLE_DIMENSION);
        DimensionSavedDataManager storage = world.getDataStorage();
        //LogManager.getLogger().info("usesaveddata");
        return storage.computeIfAbsent(TempleDimensionData::new, NAME);
    }

	@Override
	public void load(CompoundNBT nbt) {
		// TODO 自动生成的方法存根
		//LogManager.getLogger().info("loadsaveddata");
		DATA=nbt.getCompound("ztd");
		if(!DATA.contains("allocated")||DATA.getInt("allocated")==0) {
			DATA.putInt("allocated", FIRST_ALLOCATE);
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		// TODO 自动生成的方法存根
		//LogManager.getLogger().info("savesaveddata");
		nbt.put("ztd",DATA);
		return nbt;
	}
	public static int AllocateNewTemple(World worldIn) {
		CompoundNBT data=get(worldIn).DATA;
		int templeID=data.getInt("allocated")+1;
		data.putInt("allocated", templeID);
		data.put(Integer.toString(templeID), new CompoundNBT());
		get(worldIn).setDirty();
		return templeID;
	}
	public static boolean isAllocated(World worldIn,BlockPos pos) {
		CompoundNBT data=get(worldIn).DATA;
		int allocated=data.getInt("allocated");
		int posX=pos.getX();
		int posZ=pos.getZ();
		//LogManager.getLogger().info(posX+","+posZ);
		for(int i=2;i<allocated;i++) {
			int[] templepos=data.getCompound(Integer.toString(i)).getIntArray("startpoint");
			//LogManager.getLogger().info(templepos[0]+","+templepos[2]);
			if(Math.abs(templepos[0]-posX)<80&&Math.abs(templepos[2]-posZ)<80)
				return true;
		}
		return false;
	}
	public static CompoundNBT getTempleData(World worldIn,int templeID) {
		CompoundNBT data=get(worldIn).DATA;
		return data.getCompound(Integer.toString(templeID));
	}
	public static void ExitTemple(World worldIn,PlayerEntity player){
		player.setGameMode(GameType.SURVIVAL);
		DataManager.AdjustAllSkills(player, true);
		int templeID=DataManager.data.get(player).intemple;
		DataManager.data.get(player).intemple=0;
		ServerWorld overworld=worldIn.getServer().getLevel(World.OVERWORLD);
		player.changeDimension(overworld,new TempleTeleporter());
		CompoundNBT data=getTempleData(worldIn,templeID);
		int[] templepos=data.getIntArray("temple_location");
		player.teleportTo(templepos[0]+0.5, templepos[1]+1, templepos[2]+0.5);
	}
}
