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
	}
	public static void onTooltipRender(ItemTooltipEvent event) {
		ItemStack stack=event.getItemStack();
		if(ConductiveItem.isConductive(stack.getItem())) {
			event.getToolTip().add(Component.translatable("tooltip.zelda.conductive").withStyle(ChatFormatting.YELLOW));
		}
	}
}
