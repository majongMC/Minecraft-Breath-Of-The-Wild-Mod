package com.majong.zelda.data;

import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.world.entity.player.Player;

public class ZeldaPlayerData {
	public Player player;
	public boolean[] unlocked= {true,true,true,true};
	public int skill[]= {1,3,3,3};
	public int cd[]= {ZeldaConfig.WATER.get(),ZeldaConfig.WIND.get(),ZeldaConfig.FIRE.get(),ZeldaConfig.THUNDER.get()};
	public int intemple=0;
	public ZeldaPlayerData(Player player) {
		this.player=player;
	}
}
