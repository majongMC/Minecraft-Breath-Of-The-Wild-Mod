package com.majong.zelda.overlays;

import com.majong.zelda.util.ConductiveItem;

import majongmc.hllib.client.util.ClientUtils;
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
	private static long lastframe=0;
	@SubscribeEvent
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
	@SubscribeEvent
	public static void onTooltipRender(ItemTooltipEvent event) {
		ItemStack stack=event.getItemStack();
		if(ConductiveItem.isConductive(stack.getItem())) {
			event.getToolTip().add(Component.translatable("tooltip.zelda.conductive").withStyle(ChatFormatting.YELLOW));
		}
	}
}
