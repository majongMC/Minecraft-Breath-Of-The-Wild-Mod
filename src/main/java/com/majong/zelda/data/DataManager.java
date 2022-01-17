package com.majong.zelda.data;

import java.util.HashMap;
import java.util.Map;

import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ZeldaPlayerDataPack;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.network.PacketDistributor;

public class DataManager {
	public static Map<PlayerEntity,ZeldaPlayerData> data=new HashMap<>();
	public static CompoundNBT readtonbt(PlayerEntity player) {
		preventnull(player);
		CompoundNBT nbt=new CompoundNBT();
		nbt.putBoolean("waterunlocked", data.get(player).unlocked[0]);
		nbt.putBoolean("windunlocked", data.get(player).unlocked[1]);
		nbt.putBoolean("fireunlocked", data.get(player).unlocked[2]);
		nbt.putBoolean("thunderunlocked", data.get(player).unlocked[3]);
		nbt.putIntArray("skill", data.get(player).skill);
		nbt.putIntArray("cd", data.get(player).cd);
		return nbt;
	}
	public static void writefromnbt(CompoundNBT nbt,PlayerEntity player) {
		preventnull(player);
		data.get(player).unlocked[0]=nbt.getBoolean("waterunlocked");
		data.get(player).unlocked[1]=nbt.getBoolean("windunlocked");
		data.get(player).unlocked[2]=nbt.getBoolean("fireunlocked");
		data.get(player).unlocked[3]=nbt.getBoolean("thunderunlocked");
		data.get(player).skill=nbt.getIntArray("skill");
		data.get(player).cd=nbt.getIntArray("cd");
	}
	public static void sendzeldaplayerdatapack(PlayerEntity player) {
		Networking.ZELDAPLAYERDATA.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayerEntity) player
                ),
                new ZeldaPlayerDataPack(readtonbt(player)));
	}
	public static void preventnull(PlayerEntity player) {
		if(data.get(player)==null)
			data.put(player, new ZeldaPlayerData(player));
	}
	public static void removedata(PlayerEntity player) {
		data.remove(player);
	}
}
