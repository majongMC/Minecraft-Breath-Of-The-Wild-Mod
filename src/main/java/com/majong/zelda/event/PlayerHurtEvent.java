package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class PlayerHurtEvent {
	@SubscribeEvent
	public static void onPlayerHurt(LivingDamageEvent event) {
		if(event.getEntityLiving() instanceof PlayerEntity&&!event.getEntityLiving().level.isClientSide) {
			PlayerEntity player=(PlayerEntity) event.getEntityLiving();
			//DataManager.preventnull(player);
			long respondtime=player.level.getGameTime()-PlayerUseShield.PLAYER_LAST_USE_SHIELD.get(player);
			if(respondtime<=ZeldaConfig.SHIELD.get()) {
				if(!(event.getSource().getEntity() instanceof LivingEntity))
					return;
				LivingEntity source=(LivingEntity) event.getSource().getEntity();
				if(source==null)
					return;
				source.hurt(new EntityDamageSource("player",player).setThorns(), event.getAmount()*5+5);
				event.setCanceled(true);
				float yaw=player.yHeadRot;
				float f = 2F;
				double mz = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
				double mx = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
				source.setDeltaMovement(source.getDeltaMovement().add(mx,0.1, mz));
				return;
			}
			if(DataManager.data.get(player).unlocked[2]&&DataManager.data.get(player).skill[2]>0&&event.getSource().getEntity() instanceof LivingEntity&&event.getAmount()>0&&player.isShiftKeyDown()) {
				LivingEntity source=(LivingEntity) event.getSource().getEntity();
				source.hurt(new EntityDamageSource("hero",player).setThorns(), event.getAmount()*10+10);
				DataManager.data.get(player).skill[2]--;
				DataManager.sendzeldaplayerdatapack(player);
				event.setCanceled(true);
			}
		}
	}
}
