package com.majong.zelda.event;

import com.majong.zelda.entity.Lynel;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class CriticalHit {
	@SubscribeEvent
	public static void onArrowHit(ProjectileImpactEvent event) {
		if(!event.getEntity().level.isClientSide&&event.getProjectile().getOwner() instanceof Player) {
			Projectile arrow=event.getProjectile();
			Player player=(Player) arrow.getOwner();
			LivingEntity target=arrow.level.getNearestEntity(LivingEntity.class,TargetingConditions.forCombat().range(10), null, arrow.getX(), arrow.getY(), arrow.getZ(),arrow.getBoundingBox().inflate(10, 10, 10));
			if(target!=null&&target!=arrow.getOwner()) {
				AABB box=target.getBoundingBox();
				double proportion=box.getYsize()/box.getXsize();
				if(proportion>1.5) {
					double deltay=arrow.getY()-target.getY();
					double relativey=deltay/box.getYsize();
					if(relativey>0.6667) {
						target.hurt(target.level.damageSources().mobAttack(player),10);
						target.invulnerableTime = 0;
						if(target instanceof Lynel)
							((Lynel)target).dizzy();
						float yaw=player.yHeadRot;
						float f = 2F;
						double mz = Math.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
						double mx = -Math.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
						target.setDeltaMovement(target.getDeltaMovement().add(mx,0.1, mz));
						event.setCanceled(false);
						Networking.PARTICLE.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayer) player
			                    ),
			                    new ParticlePack(6,arrow.getX(),arrow.getY(),arrow.getZ(),0,0,0));
					}
				}
			}
		}
	}
}
