package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.RockGiantEntity;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class PlayerUsualEvent {
	@SubscribeEvent
	public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
		if(event.getEntity().world.isRemote) {
			Minecraft.getInstance().world.playSound(Minecraft.getInstance().player,Minecraft.getInstance().player.getPosition(), SoundLoader.WAKE_UP.get(), SoundCategory.AMBIENT, 10f, 1f);
		}
	}
	@SubscribeEvent
	public static void onPlayerDestroyBlock(HarvestCheck event) {
		if(!event.getEntity().world.isRemote&&event.canHarvest()) {
			PlayerEntity player=event.getPlayer();
			if(player.world.getDimensionKey().getLocation().equals(DimensionType.OVERWORLD_ID)&&player.getPosY()<40) {
				if(Math.random()<0.0005*ZeldaConfig.ROCKGIANT.get()) {
					RockGiantEntity entity=new RockGiantEntity(EntityLoader.ROCK_GIANT.get(),event.getEntity().world);
					BlockPos pos=new BlockPos(player.getPosX()+randomint(), player.getPosY(), player.getPosZ()+randomint());
					int count=0;
					while(!(player.world.getBlockState(pos).getBlock()==Blocks.AIR)) {
						pos=new BlockPos(player.getPosX()+randomint(), player.getPosY(), player.getPosZ()+randomint());
						count++;
						if(count>30) {
							break;
						}
					}
					entity.setPosition(player.getPosX()+randomint(), player.getPosY(), player.getPosZ()+randomint());
					event.getEntity().world.addEntity(entity);
				}
			}
		}
	}
	private static int randomint() {
		int random=0;
		while(true) {
			random=(int) (32*Math.random()-16);
			if(Math.abs(random)>=8)
				return random;
		}
	}
}
