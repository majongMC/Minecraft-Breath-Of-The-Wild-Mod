package com.majong.zelda.gui;

import com.majong.zelda.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class OpenDialogBox {
	private String[] text,text1,text2;
	public OpenDialogBox(int text) {
		init();
		switch(text) {
		case 0:Minecraft.getInstance().setScreen(new DialogBox(new TranslationTextComponent(Utils.MOD_ID + ".dialog"),this.text));break;
		case 1:Minecraft.getInstance().setScreen(new DialogBox(new TranslationTextComponent(Utils.MOD_ID + ".dialog"),text1));break;
		case 2:Minecraft.getInstance().setScreen(new DialogBox(new TranslationTextComponent(Utils.MOD_ID + ".dialog"),text2));break;
		default:Minecraft.getInstance().setScreen(new DialogBox(new TranslationTextComponent(Utils.MOD_ID + ".dialog"),this.text));
		}
	}
	private void init() {
		text=new String[2];
		text[0]="呜呜...... 我们不会再见面了......";
		text[1]="可盖大人的仇敌！";
		text1=new String[5];
		text1[0]="呜呜......";
		text1[1]="呜呜...... 我想要......";
		text1[2]="......烤三文鱼";
		text1[3]="还有......";
		text1[4]="可盖大人的仇敌！";
		text2=new String[4];
		text2[0]="风景真美啊......";
		text2[1]="好好记住这片美景吧......";
		text2[2]="因为这将是你.....临终前的风景！";
		text2[3]="可盖大人的仇敌！";
	}
}
