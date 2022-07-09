package com.majong.zelda.event;

import com.majong.zelda.entity.BokoBrinEntity;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;
import com.majong.zelda.entity.WalkingGuardianEntity;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;

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
				if(entity.level.getGameTime()%20==7) {
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
				if(entity.level.getGameTime()%20==8) {
						Networking.SOUND.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayer) target
			                    ),
			                    new SoundPack(4,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
				}
			}
			/*if(Utils.ICE_AND_FIRE_LOADED&&entity instanceof EntityCyclops) {
				EntityCyclops cyclops=(EntityCyclops) entity;
				Player player=(Player) target;
				ZeldaBloodBarApi.DisplayBloodBarServer(cyclops.getHealth()/cyclops.getMaxHealth(), BloodBarPack.SINOX, player);
				if(entity.level.getGameTime()%20==9) {
					Networking.SOUND.send(
				      PacketDistributor.PLAYER.with(
				                            () -> (ServerPlayerEntity) target
				             ),
				             new SoundPack(9,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
					}
				}*/
		}
	}
}
