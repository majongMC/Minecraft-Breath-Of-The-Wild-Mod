package com.majong.zelda.event;

import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class CriticalHit {
	@SubscribeEvent
	public static void onArrowHit(ProjectileImpactEvent.Arrow event) {
		if(!event.getEntity().world.isRemote&&event.getArrow().func_234616_v_() instanceof PlayerEntity) {
			AbstractArrowEntity arrow=event.getArrow();
			PlayerEntity player=(PlayerEntity) arrow.func_234616_v_();
			LivingEntity target=arrow.world.getClosestEntityWithinAABB(LivingEntity.class,new EntityPredicate().setDistance(10), null, arrow.getPosX(), arrow.getPosY(), arrow.getPosZ(),arrow.getBoundingBox().grow(10, 10, 10));
			if(target!=null&&target!=arrow.func_234616_v_()) {
				AxisAlignedBB box=target.getBoundingBox();
				double proportion=box.getYSize()/box.getXSize();
				if(proportion>1.5) {
					double deltay=arrow.getPosY()-target.getPosY();
					double relativey=deltay/box.getYSize();
					if(relativey>0.6667) {
						target.attackEntityFrom(new EntityDamageSource("player",player).setDamageIsAbsolute(),10);
						target.hurtResistantTime = 0;
						float yaw=player.rotationYawHead;
						float f = 2F;
						double mz = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
						double mx = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
						target.setMotion(target.getMotion().add(mx,0.1, mz));
						event.setCanceled(false);
						Networking.PARTICLE.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayerEntity) player
			                    ),
			                    new ParticlePack(6,arrow.getPosX(),arrow.getPosY(),arrow.getPosZ(),0,0,0));
					}
				}
			}
		}
	}
}
