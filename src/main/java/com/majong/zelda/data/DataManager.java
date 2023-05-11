package com.majong.zelda.data;

import java.util.HashMap;
import java.util.Map;

import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ZeldaNBTPack;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class DataManager {
	public static Map<Player,ZeldaPlayerData> data=new HashMap<>();
	public static CompoundTag readtonbt(Player player) {
		preventnull(player);
		CompoundTag nbt=new CompoundTag();
		ZeldaPlayerData playerdata=data.get(player);
		nbt.putBoolean("waterunlocked", playerdata.unlocked[0]);
		nbt.putBoolean("windunlocked", playerdata.unlocked[1]);
		nbt.putBoolean("fireunlocked", playerdata.unlocked[2]);
		nbt.putBoolean("thunderunlocked", playerdata.unlocked[3]);
		nbt.putIntArray("skill", playerdata.skill);
		nbt.putIntArray("cd", playerdata.cd);
		nbt.putInt("intemple", playerdata.intemple);
		return nbt;
	}
	public static void writefromnbt(CompoundTag nbt,Player player) {
		preventnull(player);
		ZeldaPlayerData playerdata=data.get(player);
		playerdata.unlocked[0]=nbt.getBoolean("waterunlocked");
		playerdata.unlocked[1]=nbt.getBoolean("windunlocked");
		playerdata.unlocked[2]=nbt.getBoolean("fireunlocked");
		playerdata.unlocked[3]=nbt.getBoolean("thunderunlocked");
		playerdata.skill=nbt.getIntArray("skill");
		playerdata.cd=nbt.getIntArray("cd");
		playerdata.intemple=nbt.getInt("intemple");
	}
	public static void sendzeldaplayerdatapack(Player player) {
		Networking.ZELDANBT.send((ServerPlayer) player,new ZeldaNBTPack(1,readtonbt(player)));
	}
	public static void preventnull(Player player) {
		if(data.get(player)==null)
			data.put(player, new ZeldaPlayerData(player));
	}
	public static void removedata(Player player) {
		data.remove(player);
	}
	public static void AdjustAllSkills(Player player,boolean unlocked) {
		ZeldaPlayerData playerdata=data.get(player);
		for(int i=0;i<4;i++) {
			playerdata.unlocked[i]=unlocked;
		}
	}
}
