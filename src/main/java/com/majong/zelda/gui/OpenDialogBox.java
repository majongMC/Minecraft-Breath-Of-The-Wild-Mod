package com.majong.zelda.gui;

import java.util.Random;

import com.majong.zelda.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;

public class OpenDialogBox {
	public OpenDialogBox() {
		int maxmessage=0,length=0,select=0;
		while(true) {
			if(!hasvalue("yigateam.dialog."+maxmessage+"_0"))
				break;
			maxmessage++;
		}
		//select=(int) (Math.random()*(maxmessage+1));
		select=Mth.nextInt(new Random(), 0, maxmessage-1);
		while(true) {
			if(!hasvalue("yigateam.dialog."+select+"_"+length))
				break;
			length++;
		}
		if(length==0)
			length++;
		String[] dialog=new String[length];
		for(int i=0;i<length;i++) {
			dialog[i]=new TranslatableComponent("yigateam.dialog."+select+"_"+i).getString();
		}
		Minecraft.getInstance().setScreen(new DialogBox(new TranslatableComponent(Utils.MOD_ID + ".dialog"),dialog));
	}
	private boolean hasvalue(String string) {
		return !string.equals(new TranslatableComponent(string).getString());
	}
}
