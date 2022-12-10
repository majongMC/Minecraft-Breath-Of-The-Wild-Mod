package com.majong.zelda.overlays;

import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.util.ConductiveItem;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class RenderOverlays {
	private static long lastrecieve=0;
	private static long lastattack=0;
	private static double percentage=1,last=1,delay=1;
	public static boolean rendering=false;
	private static ITextComponent name=new TranslationTextComponent("");
	private static String at="";
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
				delay=delay-0.002/ClientUtils.fpsratio();
			}
			BloodBar bar=new BloodBar(event.getMatrixStack());
			bar.render(percentage, delay,name,at);
		}
		else
			rendering=false;
	}
	@SubscribeEvent
	public static void onTooltipRender(ItemTooltipEvent event) {
		ItemStack stack=event.getItemStack();
		if(ConductiveItem.isConductive(stack.getItem())) {
			event.getToolTip().add(new TranslationTextComponent("tooltip.zelda.conductive").withStyle(TextFormatting.YELLOW));
		}
	}
	@Deprecated//请使用api中的方法
	public static void DisplayBloodBar(double percentage,ITextComponent name,String at) {
		lastrecieve=Minecraft.getInstance().level.getGameTime();
		RenderOverlays.percentage=percentage;
		RenderOverlays.name=name;
		RenderOverlays.at=at;
	}
}
