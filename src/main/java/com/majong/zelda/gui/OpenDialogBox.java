package com.majong.zelda.gui;

import com.majong.zelda.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class OpenDialogBox {
	private String[] text,text1;
	public OpenDialogBox(int text) {
		init();
		switch(text) {
		case 0:Minecraft.getInstance().setScreen(new DialogBox(new TranslationTextComponent(Utils.MOD_ID + ".dialog"),this.text));break;
		case 1:Minecraft.getInstance().setScreen(new DialogBox(new TranslationTextComponent(Utils.MOD_ID + ".dialog"),text1));break;
		default:Minecraft.getInstance().setScreen(new DialogBox(new TranslationTextComponent(Utils.MOD_ID + ".dialog"),this.text));
		}
	}
	private void init() {
		text=new String[2];
		text[0]="����...... ���ǲ����ټ�����......";
		text[1]="�ɸǴ��˵ĳ�У�";
		text1=new String[5];
		text1[0]="����......";
		text1[1]="����...... ����Ҫ......";
		text1[2]="......��������";
		text1[3]="����......";
		text1[4]="�ɸǴ��˵ĳ�У�";
	}
}
