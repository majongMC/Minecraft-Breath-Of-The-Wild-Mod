package com.majong.zelda.event;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.github.alexthe666.iceandfire.entity.EntityCyclops;
import com.majong.zelda.Utils;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;
import com.majong.zelda.entity.PokBrinEntity;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class EntityDead {
	@SubscribeEvent
	public static void onEntityDead(LivingDeathEvent event) {
		if(event.getEntity() instanceof PlayerEntity&&!event.getEntity().level.isClientSide) {
			if(DataManager.data.get(event.getEntity()).skill[0]==1) {
				((PlayerEntity)event.getEntity()).setHealth(((PlayerEntity)event.getEntity()).getMaxHealth());
				((PlayerEntity) event.getEntity()).addEffect(new EffectInstance(Effects.ABSORPTION,1200,2));
				event.setCanceled(true);
				DataManager.data.get(event.getEntity()).skill[0]=0;
				DataManager.sendzeldaplayerdatapack((PlayerEntity) event.getEntity());
				return;
			}
			Networking.SOUND.send(
                    PacketDistributor.PLAYER.with(
                            () -> (ServerPlayerEntity) event.getEntity()
                    ),
                    new SoundPack());
			Networking.SOUND.send(
                    PacketDistributor.PLAYER.with(
                            () -> (ServerPlayerEntity) event.getEntity()
                    ),
                    new SoundPack(1,new BlockPos(event.getEntity().getX(),event.getEntity().getY(),event.getEntity().getZ())));
		}
		if(event.getEntity() instanceof GuardianEntity||event.getEntity() instanceof MollyBrinEntity||event.getEntity() instanceof PokBrinEntity||(Utils.ICE_AND_FIRE_LOADED&&event.getEntity() instanceof EntityCyclops&&!event.getEntity().level.isClientSide)) {
			List<PlayerEntity> playerlist= event.getEntity().level.getEntitiesOfClass(PlayerEntity.class,event.getEntity().getBoundingBox().inflate(64, 32, 64) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO 自动生成的方法存根
					if(t instanceof PlayerEntity) 
						return true;
					else
						return false;
				}});
    		Iterator<PlayerEntity> it=playerlist.iterator();
    		while(it.hasNext()) {
    			PlayerEntity player=(PlayerEntity) it.next();
    			Networking.SOUND.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new SoundPack());
    		}
		}
	}
}
