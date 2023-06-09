package com.majong.zelda.gui;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;

import majongmc.hllib.client.gui.RPGDialogBox;
import majongmc.hllib.client.gui.RPGDialogBox.ButtonAction;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;

public class DialogBoxManager {
	private String[] content;
	private int page=0;
	private final RPGDialogBox box;
	private ButtonAction action=(box,button)->{
		if(this.hasnextpage())
			this.nextpage();
		else
			box.onClose();
	};
	DialogBoxManager(String[] content,LivingEntity entity) {
		this.content=content;
		box=RPGDialogBox.builder().entity(entity).title(Component.translatable("yigateam.dialog.name")).text(content[page]).nextpage(action).onclose((box)->{onClose();}).build();
		Minecraft.getInstance().setScreen(box);
	}
	private void onClose() {
		if(!ZeldaConfig.NPCONLY.get()&&!ZeldaConfigClient.DISABLE_MUSIC.get()) {
		Minecraft.getInstance().getSoundManager().stop();
		EntitySpottedEvent.SoundRemainTime=0;
		Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,Minecraft.getInstance().player.blockPosition(), SoundLoader.FIGHT_ORIGINAL.get(), SoundSource.AMBIENT, 10f, 1f);
		EntitySpottedEvent.SoundRemainTime=2200;
		}
	}
	private boolean hasnextpage() {
		return content.length-1>page;
	}
	private void nextpage() {
		page++;
		box.update(RPGDialogBox.builder().text(content[page]).nextpage(action));
	}
}
