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
		if(!event.getEntity().level.isClientSide&&event.getArrow().getOwner() instanceof PlayerEntity) {
			AbstractArrowEntity arrow=event.getArrow();
			PlayerEntity player=(PlayerEntity) arrow.getOwner();
			LivingEntity target=arrow.level.getNearestEntity(LivingEntity.class,new EntityPredicate().range(10), null, arrow.getX(), arrow.getY(), arrow.getZ(),arrow.getBoundingBox().inflate(10, 10, 10));
			if(target!=null&&target!=arrow.getOwner()) {
				AxisAlignedBB box=target.getBoundingBox();
				double proportion=box.getYsize()/box.getXsize();
				if(proportion>1.5) {
					double deltay=arrow.getY()-target.getY();
					double relativey=deltay/box.getYsize();
					if(relativey>0.6667) {
						target.hurt(new EntityDamageSource("player",player).bypassMagic(),10);
						target.invulnerableTime = 0;
						float yaw=player.yHeadRot;
						float f = 2F;
						double mz = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
						double mx = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
						target.setDeltaMovement(target.getDeltaMovement().add(mx,0.1, mz));
						event.setCanceled(false);
						Networking.PARTICLE.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayerEntity) player
			                    ),
			                    new ParticlePack(6,arrow.getX(),arrow.getY(),arrow.getZ(),0,0,0));
					}
				}
			}
		}
	}
}
