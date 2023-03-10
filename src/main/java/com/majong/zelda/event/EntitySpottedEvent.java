package com.majong.zelda.event;

import com.majong.zelda.api.overlays.ZeldaHealthBarApi;
import com.majong.zelda.entity.BokoBrinEntity;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.Lynel;
import com.majong.zelda.entity.MollyBrinEntity;
import com.majong.zelda.entity.WalkingGuardianEntity;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.network.HealthBarPack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;
import com.majong.zelda.util.Linkage;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class EntitySpottedEvent {
	public static int SoundRemainTime=0;
	@SubscribeEvent
	public static void onEntitySpotted(LivingSetAttackTargetEvent event) {
		LivingEntity target=event.getTarget();
		Entity entity=event.getEntity();
		if(target instanceof YigaTeamMemberEntity&&!((YigaTeamMemberEntity)target).isactivated()&&event.getEntity() instanceof IronGolem&&!entity.level.isClientSide) {
			IronGolem golem=(IronGolem) entity;
			golem.setTarget(null);
			return;
		}
		if(target instanceof Player&&!target.level.isClientSide) {
			if(entity instanceof GuardianEntity) {
				if(entity.level.getGameTime()%20==7||entity.level.getGameTime()%20==6) {
					if(entity instanceof WalkingGuardianEntity) {
						Networking.SOUND.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayer) target
		                    ),
		                    new SoundPack(2,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
					}
					else {
						Networking.SOUND.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayer) event.getTarget()
			                    ),
			                    new SoundPack(3,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
					}
				}
			}
			if(entity instanceof MollyBrinEntity||entity instanceof BokoBrinEntity) {
				if(entity.level.getGameTime()%20==8||entity.level.getGameTime()%20==9) {
						Networking.SOUND.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayer) target
			                    ),
			                    new SoundPack(4,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
				}
			}
			if(entity instanceof Lynel) {
				if(entity.level.getGameTime()%20==10||entity.level.getGameTime()%20==11) {
						Networking.SOUND.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayer) target
			                    ),
			                    new SoundPack(13,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
				}
			}
			if(Linkage.isHinox(entity)) {
				LivingEntity cyclops=(LivingEntity) entity;
				Player player=(Player) target;
				ZeldaHealthBarApi.DisplayHealthBarServer(cyclops.getHealth()/cyclops.getMaxHealth(), HealthBarPack.HINOX, player);
				if(entity.level.getGameTime()%20==9||entity.level.getGameTime()%20==10) {
					Networking.SOUND.send(
				      PacketDistributor.PLAYER.with(
				                            () -> (ServerPlayer) target
				             ),
				             new SoundPack(9,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
					}
				}
		}
	}
}
