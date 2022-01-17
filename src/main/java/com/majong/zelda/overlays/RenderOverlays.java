package com.majong.zelda.overlays;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class RenderOverlays {
	private static long lastrecieve=0;
	private static long lastattack=0;
	private static double percentage=1,last=1,delay=1;
	public static boolean rendering=false;
	private static ITextComponent name=new TranslationTextComponent("");
	@SubscribeEvent
	public static void onOverlayRender(RenderGameOverlayEvent event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
	           return;
	       }
		if (Minecraft.getInstance().player == null) {
	           return;
	       }
		ZeldaOverlays zeldaoverlays=new ZeldaOverlays(event.getMatrixStack());
		zeldaoverlays.render(Minecraft.getInstance().player);
		if(Minecraft.getInstance().world.getGameTime()-lastrecieve<20L) {
			rendering=true;
			if(last<percentage||delay<percentage) {
				last=percentage;
				delay=percentage;
			}
			if(last>percentage) {
				lastattack=Minecraft.getInstance().world.getGameTime();
				last=percentage;
			}
			if(Minecraft.getInstance().world.getGameTime()-lastattack>10&&percentage<delay) {
				delay=delay-0.002;
			}
			BloodBar bar=new BloodBar(event.getMatrixStack());
			bar.render(percentage, delay,name);
		}
		else
			rendering=false;
	}
	@Deprecated//请使用api中的方法
	public static void DisplayBloodBar(double percentage,ITextComponent name) {
		lastrecieve=Minecraft.getInstance().world.getGameTime();
		RenderOverlays.percentage=percentage;
		RenderOverlays.name=name;
	}
}
