package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class ServerTick {
	@SubscribeEvent
	public static void onServerTick(ServerTickEvent event) {
		Level level=event.getServer().overworld();
		if(!ZeldaConfig.WEATHER_CHANGE.get()||!event.getServer().getGameRules().getBoolean(GameRules.RULE_WEATHER_CYCLE))
			return;
		//Level level=Minecraft.getInstance().getSingleplayerServer().getLevel(Level.OVERWORLD);
		if(level==null)
			return;
		long time=level.getGameTime();
		if(time%1200==0&&Math.random()<0.02*ZeldaConfig.WEATHER_CHANGE_CHANCE.get()) {
			if(level.isRaining()) {
				level.getLevelData().setRaining(false);
				((ServerLevelData) ((ServerLevel)level).getLevelData()).setThundering(false);
			}
			else
			{
				level.getLevelData().setRaining(true);
				if(Math.random()<0.5)
					((ServerLevelData) ((ServerLevel)level).getLevelData()).setThundering(true);
				else
					((ServerLevelData) ((ServerLevel)level).getLevelData()).setThundering(false);
			}
		}
	}
}
