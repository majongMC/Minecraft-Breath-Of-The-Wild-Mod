package com.majong.zelda.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ClientUtils {
	public static long frame=0;
	private static long lastcallframe=0;
	private static int lastfps=60;
	public static Player GetClientPlayer() {
		return Minecraft.getInstance().player;
	}
	public static Level GetClientLevel() {
		return Minecraft.getInstance().level;
	}
	public static void ClientStopSound() {
		Minecraft.getInstance().getSoundManager().stop();
	}
	public static int getfps() {
		if(frame==lastcallframe) {
			return lastfps;
		}
		lastcallframe=frame;
		String fpsstr=Minecraft.getInstance().fpsString;
		System.out.println(fpsstr);
		int fps=60;
		for(int i=0;i<fpsstr.length();i++) {
			if(fpsstr.charAt(i)==' '||fpsstr.charAt(i)=='/') {
				String fpss=fpsstr.substring(0,i);
				fps=Integer.valueOf(fpss);
				break;
			}
		}
		if(fps==0)
			fps=1;
		lastfps=fps;
		return fps;
	}
	public static double fpsratio() {
		return getfps()/60.0;
	}
}
