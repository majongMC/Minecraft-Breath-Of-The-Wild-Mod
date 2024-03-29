package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;

import net.minecraft.entity.Entity;
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
			event.setCanceled(TryReflect((PlayerEntity) event.getEntityLiving(),event.getSource().getEntity(),event.getAmount()));
		}
	}
	public static boolean TryReflect(PlayerEntity player,Entity source,float amount) {
		long respondtime=player.level.getGameTime()-PlayerUseShield.PLAYER_LAST_USE_SHIELD.get(player);
		if(respondtime<=ZeldaConfig.SHIELD.get()) {
			if(source==null||!(source instanceof LivingEntity)||source instanceof PlayerEntity)
				return false;
			float amountback=0;
			float maxhealth=((LivingEntity)source).getMaxHealth();
			if(maxhealth>100) {
				if(amount*3+5>0.1F*maxhealth)
					amountback=0.1F*maxhealth;
				else
					amountback=amount*3+5;
			}
			else
				amountback=amount*3+5;
			source.hurt(new EntityDamageSource("shield",player).setThorns(), amountback);
			float yaw=player.yHeadRot;
			float f = 2F;
			double mz = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
			double mx = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
			source.setDeltaMovement(source.getDeltaMovement().add(mx,0.1, mz));
			return true;
		}
		if(DataManager.data.get(player).unlocked[2]&&DataManager.data.get(player).skill[2]>0&&source instanceof LivingEntity&&source!=player&&amount>0&&player.isShiftKeyDown()) {
			float amountback=0;
			float maxhealth=((LivingEntity)source).getMaxHealth();
			if(maxhealth>100) {
				if(amount*6+10>0.15F*maxhealth)
					amountback=0.15F*maxhealth;
				else
					amountback=amount*6+10;
			}
			else
				amountback=amount*6+10;
			source.hurt(new EntityDamageSource("hero",player).setThorns(), amountback);
			DataManager.data.get(player).skill[2]--;
			DataManager.sendzeldaplayerdatapack(player);
			return true;
		}
		return false;
	}
}
