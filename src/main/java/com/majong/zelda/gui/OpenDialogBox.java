package com.majong.zelda.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;

public class OpenDialogBox {
	public OpenDialogBox(LivingEntity entity) {
		int maxmessage=0,length=0,select=0;
		while(true) {
			if(!hasvalue("yigateam.dialog."+maxmessage+"_0"))
				break;
			maxmessage++;
		}
		select=Mth.nextInt(RandomSource.create(), 0, maxmessage-1);
		while(true) {
			if(!hasvalue("yigateam.dialog."+select+"_"+length))
				break;
			length++;
		}
		if(length==0)
			length++;
		String[] dialog=new String[length];
		for(int i=0;i<length;i++) {
			dialog[i]=Component.translatable("yigateam.dialog."+select+"_"+i).getString();
		}
		new DialogBoxManager(dialog,entity);
	}
	private boolean hasvalue(String string) {
		return !string.equals(Component.translatable(string).getString());
	}
}
