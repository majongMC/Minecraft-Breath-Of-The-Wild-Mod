package com.majong.zelda.overlays;

import com.majong.zelda.util.ConductiveItem;

import majongmc.hllib.client.event.RenderGuiOverlayEvent;
import majongmc.hllib.client.util.ClientUtils;
import majongmc.hllib.common.event.ItemTooltipEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class RenderOverlays {
	private static long lastrecieve=0;
	private static long lastattack=0;
	private static double percentage=1,last=1,delay=1;
	public static boolean rendering=false;
	private static Component name=Component.translatable("");
	private static String at="";
	private static long lastframe=0;
	public static void onOverlayRender(RenderGuiOverlayEvent.Post event) {
		/*if (event.getType() != RenderGuiOverlayEvent.ElementType.ALL) {
	           return;
	       }*/
		if(lastframe==ClientUtils.frameID)
			return;
		lastframe=ClientUtils.frameID;
		if (Minecraft.getInstance().player == null) {
	           return;
	       }
		ZeldaOverlays zeldaoverlays=new ZeldaOverlays(event.getPoseStack());
		zeldaoverlays.render(Minecraft.getInstance().player);
		if(Minecraft.getInstance().level.getGameTime()-lastrecieve<20L) {
			rendering=true;
			if(last<percentage||delay<percentage) {
				last=percentage;
				delay=percentage;
			}
			if(last>percentage) {
				lastattack=Minecraft.getInstance().level.getGameTime();
				last=percentage;
			}
			if(Minecraft.getInstance().level.getGameTime()-lastattack>10&&percentage<delay) {
				delay=delay-0.004/ClientUtils.fpsratio();
			}
			HealthBar bar=new HealthBar(event.getPoseStack());
			bar.render(percentage, delay,name,at);
		}
		else
			rendering=false;
	}
	@Deprecated//��ʹ��api�еķ���
	public static void DisplayHealthBar(double percentage,Component name,String at) {
		lastrecieve=Minecraft.getInstance().level.getGameTime();
		RenderOverlays.percentage=percentage;
		RenderOverlays.name=name;
		RenderOverlays.at=at;
	}
	public static void onTooltipRender(ItemTooltipEvent event) {
		ItemStack stack=event.getItemStack();
		if(ConductiveItem.isConductive(stack.getItem())) {
			event.getToolTip().add(Component.translatable("tooltip.zelda.conductive").withStyle(ChatFormatting.YELLOW));
		}
	}
}
