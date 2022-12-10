package com.majong.zelda.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientUtils {
	public static PlayerEntity GetClientPlayer() {
		return Minecraft.getInstance().player;
	}
	public static World GetClientLevel() {
		return Minecraft.getInstance().level;
	}
	public static void ClientStopSound() {
		Minecraft.getInstance().getSoundManager().stop();
	}
	public static int getfps() {
		String fpsstr=Minecraft.getInstance().fpsString;
		System.out.println(fpsstr);
		int fps=60;
		for(int i=0;i<fpsstr.length();i++) {
			if(fpsstr.charAt(i)==' ') {
				String fpss=fpsstr.substring(0,i);
				fps=Integer.valueOf(fpss);
				break;
			}
		}
		if(fps==0)
			fps=1;
		return fps;
	}
	public static double fpsratio() {
		return getfps()/60.0;
	}
}
