package com.majong.zelda.event;

import majongmc.hllib.common.event.EventBus;

public class EventRegistry {
	public static void register(EventBus bus) {
		bus.addListener(CriticalHit::onArrowHit);
		bus.addListener(EntityDead::onEntityDead);
		bus.addListener(EntityHurtEvent::onEntityHurt);
		bus.addListener(EntitySpottedEvent::onEntitySpotted);
		bus.addListener(EntityTick::onEntityTick);
		bus.addListener(LinkTime::onPlayerNockArrow);
		bus.addListener(LinkTime::onPlayerLooseArrow);
		bus.addListener(PlayerLoggedEvent::onPlayerLoggedIn);
		bus.addListener(PlayerLoggedEvent::onPlayerLoggedOut);
		bus.addListener(PlayerUseShield::onPlayerUseSheild);
		bus.addListener(PlayerUseShield::onPlayerFinishUseSheild);
		bus.addListener(PlayerUsualEvent::onPlayerRightClickEntity);
		bus.addListener(PlayerUsualEvent::onPlayerDestroyBlock);
	}
}
