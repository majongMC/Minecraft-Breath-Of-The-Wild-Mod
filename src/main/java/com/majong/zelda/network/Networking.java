package com.majong.zelda.network;

import com.majong.zelda.Utils;

import majongmc.hllib.common.network.SimpleChannel;
import net.minecraft.resources.ResourceLocation;

public class Networking {
	public static SimpleChannel PARTICLE=new SimpleChannel(new ResourceLocation(Utils.MOD_ID, "particle_pack"),ParticlePack::new);
	public static SimpleChannel SOUND=new SimpleChannel(new ResourceLocation(Utils.MOD_ID, "sound_pack"),SoundPack::new);
	public static SimpleChannel ZELDANBT=new SimpleChannel(new ResourceLocation(Utils.MOD_ID, "zeldanbt_pack"),ZeldaNBTPack::new);
	public static SimpleChannel PACKTOSERVER=new SimpleChannel(new ResourceLocation(Utils.MOD_ID, "pack_to_server"),PackToServer::new);
	public static SimpleChannel GUIMESSAGEPACK=new SimpleChannel(new ResourceLocation(Utils.MOD_ID, "guimessage_pack"),GuiMessagePack::new);
	public static SimpleChannel BAR=new SimpleChannel(new ResourceLocation(Utils.MOD_ID, "bar_pack"),HealthBarPack::new);
	public static void registerClientHandler() {
		PARTICLE.registerClientHandler();
		SOUND.registerClientHandler();
		ZELDANBT.registerClientHandler();
		GUIMESSAGEPACK.registerClientHandler();
		BAR.registerClientHandler();
	}
	public static void registerServerHandler() {
		PACKTOSERVER.registerServerHandler();
		
	}
}
