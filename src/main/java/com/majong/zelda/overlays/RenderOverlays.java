package com.majong.zelda.overlays;

import com.majong.zelda.util.ConductiveItem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class RenderOverlays {
	private static long lastrecieve=0;
	private static long lastattack=0;
	private static double percentage=1,last=1,delay=1;
	public static boolean rendering=false;
	private static Component name=Component.translatable("");
	private static String at="";
	@SubscribeEvent
	public static void onOverlayRender(RenderGuiOverlayEvent.Post event) {
		/*if (event.getType() != RenderGuiOverlayEvent.ElementType.ALL) {
	           return;
	       }*/
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
				delay=delay-0.0005;
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
	@SubscribeEvent
	public static void onTooltipRender(ItemTooltipEvent event) {
		ItemStack stack=event.getItemStack();
		if(ConductiveItem.isConductive(stack.getItem())) {
			event.getToolTip().add(Component.translatable("tooltip.zelda.conductive").withStyle(ChatFormatting.YELLOW));
		}
	}
}
