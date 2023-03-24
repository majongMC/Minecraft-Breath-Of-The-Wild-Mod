package com.majong.zelda.event;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class PlayerUseShield {
	public static final Map<Player,Long> PLAYER_LAST_USE_SHIELD=new HashMap<>();
	public static final Map<Player,Boolean> SHIELD_REFLECT_ACCOMPLISH=new HashMap<>();
	@SubscribeEvent
	public static void onPlayerUseSheild(LivingEntityUseItemEvent.Start event) {
		LivingEntity entity=event.getEntityLiving();
		if(!entity.level.isClientSide&&entity instanceof Player) {
			ItemStack stack=event.getItem();
			if(stack.getItem() instanceof ShieldItem) {
				PLAYER_LAST_USE_SHIELD.put((Player) entity,entity.level.getGameTime());
		}
	}
	}
	@SubscribeEvent
	public static void onPlayerFinishUseSheild(LivingEntityUseItemEvent.Stop event) {
		LivingEntity entity=event.getEntityLiving();
		if(!entity.level.isClientSide&&entity instanceof Player) {
			ItemStack stack=event.getItem();
			if(stack.getItem() instanceof ShieldItem) {
				if(SHIELD_REFLECT_ACCOMPLISH.get((Player) entity))
					PlayerUseShield.SHIELD_REFLECT_ACCOMPLISH.put((Player) entity,false);
				else
					((Player) entity).getCooldowns().addCooldown(stack.getItem(), 20);
			}
		}
	}
}
