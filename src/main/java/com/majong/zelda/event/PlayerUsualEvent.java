package com.majong.zelda.event;

import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.RockGiantEntity;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.gui.OpenDialogBox;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.sound.SoundLoader;
import com.majong.zelda.util.EntityFreezer;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class PlayerUsualEvent {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
		if(event.getEntity().level.isClientSide) {
			ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),ClientUtils.GetClientPlayer().blockPosition(), SoundLoader.WAKE_UP.get(), SoundCategory.AMBIENT, 10f, 1f);
		}
	}
	@SubscribeEvent
	public static void onPlayerRightClickEntity(EntityInteract event) {
		PlayerEntity player=event.getPlayer();
		if(player!=null&&!player.level.isClientSide) {
			ItemStack stack=player.getItemInHand(Hand.MAIN_HAND);
			if(stack.getItem()!=ItemLoader.SHIKA_STONE.get())
	    		return;
	    	CompoundNBT nbt = stack.getOrCreateTagElement("static");
	    	Entity target=event.getTarget();
	    	if(nbt.contains("activated")&&nbt.getBoolean("activated")&&target instanceof MobEntity) {
	    		EntityFreezer.FreezeMobEntity((MobEntity) target, 200);
	    		nbt.putBoolean("activated", false);
	    	}
		}
	}
	@SubscribeEvent
	public static void onPlayerDestroyBlock(HarvestCheck event) {
		if(!event.getEntity().level.isClientSide&&event.canHarvest()) {
			PlayerEntity player=event.getPlayer();
			if(player.level.dimension().location().equals(DimensionType.OVERWORLD_EFFECTS)&&player.getY()<40) {
				if(Math.random()<0.0005*ZeldaConfig.ROCKGIANT.get()) {
					RockGiantEntity entity=new RockGiantEntity(EntityLoader.ROCK_GIANT.get(),event.getEntity().level);
					BlockPos pos=new BlockPos(player.getX()+randomint(), player.getY(), player.getZ()+randomint());
					int count=0;
					while(!(player.level.getBlockState(pos).getBlock()==Blocks.AIR)) {
						pos=new BlockPos(player.getX()+randomint(), player.getY(), player.getZ()+randomint());
						count++;
						if(count>30) {
							break;
						}
					}
					entity.setPos(player.getX()+randomint(), player.getY(), player.getZ()+randomint());
					event.getEntity().level.addFreshEntity(entity);
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
