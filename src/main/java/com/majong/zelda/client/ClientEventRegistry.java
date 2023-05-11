package com.majong.zelda.client;

import com.majong.zelda.event.PlayerUsualEvent;
import com.majong.zelda.overlays.RenderOverlays;

import majongmc.hllib.common.event.EventBus;

public class ClientEventRegistry {
	public static void register(EventBus bus) {
		bus.addListener(KeyBoardInput::onKeyboardInput);
		bus.addListener(RenderOverlays::onOverlayRender);
		bus.addListener(RenderOverlays::onTooltipRender);
		bus.addListener(PlayerUsualEvent::onPlayerWakeUp);
	}
}
