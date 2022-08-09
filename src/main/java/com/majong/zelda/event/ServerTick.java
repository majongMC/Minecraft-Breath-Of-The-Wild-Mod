package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.ServerWorldInfo;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class ServerTick {
	public static MinecraftServer server;
	@SubscribeEvent
	public static void onServerTick(ServerTickEvent event) {
		if(server==null||!ZeldaConfig.WEATHER_CHANGE.get()||!server.getGameRules().getBoolean(GameRules.RULE_WEATHER_CYCLE))
			return;
		World world=server.overworld();
		//World world=Minecraft.getInstance().getSingleplayerServer().getLevel(World.OVERWORLD);
		if(world==null)
			return;
		long time=world.getGameTime();
		if(time%1200==0&&Math.random()<0.05*ZeldaConfig.WEATHER_CHANGE_CHANCE.get()) {
			if(world.isRaining()) {
				world.getLevelData().setRaining(false);
				((ServerWorldInfo) ((ServerWorld)world).getLevelData()).setThundering(false);
			}
			else
			{
				world.getLevelData().setRaining(true);
				if(Math.random()<0.5)
					((ServerWorldInfo) ((ServerWorld)world).getLevelData()).setThundering(true);
				else
					((ServerWorldInfo) ((ServerWorld)world).getLevelData()).setThundering(false);
			}
		}
	}
}
