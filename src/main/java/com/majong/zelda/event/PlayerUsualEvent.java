package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.RockGiantEntity;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.gui.OpenDialogBox;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.sound.SoundLoader;
import com.majong.zelda.util.EntityFreezer;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
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
			Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,Minecraft.getInstance().player.blockPosition(), SoundLoader.WAKE_UP.get(), SoundSource.AMBIENT, 10f, 1f);
		}
	}
	@SubscribeEvent
	public static void onPlayerRightClickEntity(EntityInteract event) {
		if(event.getTarget().getType()==EntityLoader.YIGA_TEAM_MEMBER.get()&&!((YigaTeamMemberEntity)event.getTarget()).isactivated())
		{
			if(event.getLevel().isClientSide) {
				new OpenDialogBox();
			}else {
				if(!ZeldaConfig.NPCONLY.get())
				((YigaTeamMemberEntity)event.getTarget()).activate();
			}
		}
		Player player=event.getEntity();
		if(player!=null&&!player.level.isClientSide) {
			ItemStack stack=player.getItemInHand(InteractionHand.MAIN_HAND);
			if(stack.getItem()!=ItemLoader.SHIKA_STONE.get())
	    		return;
	    	CompoundTag nbt = stack.getOrCreateTagElement("static");
	    	Entity target=event.getTarget();
	    	if(nbt.contains("activated")&&nbt.getBoolean("activated")&&target instanceof Mob) {
	    		EntityFreezer.FreezeMob((Mob) target, 200);
	    		nbt.putBoolean("activated", false);
	    	}
		}
	}
	@SubscribeEvent
	public static void onPlayerDestroyBlock(HarvestCheck event) {
		if(!event.getEntity().level.isClientSide&&event.canHarvest()) {
			Player player=event.getEntity();
			if(player.level.dimension().location().equals(Level.OVERWORLD.location())&&player.getY()<40) {
				if(Math.random()<0.001*ZeldaConfig.ROCKGIANT.get()) {
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
