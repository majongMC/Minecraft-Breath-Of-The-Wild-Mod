package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.ServerWorldInfo;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class ServerTick {
	@SubscribeEvent
	public static void onServerTick(ServerTickEvent event) {
		if(Minecraft.getInstance().getSingleplayerServer()==null||!ZeldaConfig.WEATHER_CHANGE.get())
			return;
		World world=Minecraft.getInstance().getSingleplayerServer().getLevel(World.OVERWORLD);
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
