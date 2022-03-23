package com.majong.zelda.network;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.entity.BombEntity;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.world.dimension.TempleDimensionData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class PackWithUUID {
	private final UUID uuid;
	private final int type;
    public PackWithUUID(PacketBuffer buffer) {
    	type=buffer.readInt();
    	uuid=buffer.readUUID();
    }

    public PackWithUUID(int type) {
    	this.type=type;
    	this.uuid=Minecraft.getInstance().player.getUUID();
    }

    public void toBytes(PacketBuffer buf) {
    	buf.writeInt(type);
    	buf.writeUUID(uuid);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	switch(type) {
        	case 0:useskill();break;
        	case 1:placebomb(true);break;
        	case 2:placebomb(false);break;
        	case 3:detonate();break;
        	case 4:teleporttooverworld();break;
        	}
        });
        ctx.get().setPacketHandled(true);
    }
    private void teleporttooverworld() {
    	PlayerEntity player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	TempleDimensionData.ExitTemple(player.level, player);
    }
    private void useskill() {
    	PlayerEntity player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	//DataManager.preventnull(player);
    	if(player.isShiftKeyDown()&&DataManager.data.get(player).unlocked[1]&&DataManager.data.get(player).skill[1]>0) {
    		player.addEffect(new EffectInstance(Effects.LEVITATION,60,10));
    		DataManager.data.get(player).skill[1]--;
    		DataManager.sendzeldaplayerdatapack(player);
    	}
    	else if(DataManager.data.get(player).unlocked[3]&&DataManager.data.get(player).skill[3]>0) {
    		World world=Minecraft.getInstance().getSingleplayerServer().getLevel(player.level.dimension());
    		List<LivingEntity> targrtlist= world.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(20, 20, 20) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO 自动生成的方法存根
					if(t instanceof LivingEntity) {
						LivingEntity entity=(LivingEntity) t;
						if(entity.getClassification(false)==EntityClassification.MONSTER) {
							return true;
						}
						else
							return false;
					}
					else
						return false;
				}});
    		Iterator<LivingEntity> it=targrtlist.iterator();
    		while(it.hasNext()) {
    			LivingEntity target=(LivingEntity) it.next();
    			LightningBoltEntity lightning=new LightningBoltEntity(EntityType.LIGHTNING_BOLT,world);
    			lightning.setPos(target.getX(), target.getY(), target.getZ());
    			lightning.setVisualOnly(true);
    			world.addFreshEntity(lightning);
    			target.hurt(new EntityDamageSource("hero",player), 15);
    			target.setSecondsOnFire(10);
    		}
    		DataManager.data.get(player).skill[3]--;
    		DataManager.sendzeldaplayerdatapack(player);
    	}
    }
    private void placebomb(boolean round) {
    	PlayerEntity player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	if(!ZeldaConfig.BOMB.get()) {
			player.sendMessage(new TranslationTextComponent("msg.bombprohibited"), UUID.randomUUID());
			return;
		}
    	BombEntity bomb=new BombEntity(EntityLoader.BOMB.get(),player.level);
    	bomb.setPos(player.getX(), player.getY(), player.getZ());
    	bomb.setowner(player);
    	player.level.addFreshEntity(bomb);
    }
    private void detonate() {
    	PlayerEntity player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	if(!ZeldaConfig.BOMB.get()) {
			player.sendMessage(new TranslationTextComponent("msg.bombprohibited"), UUID.randomUUID());
			return;
		}
    	List<LivingEntity> bomblist= player.level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(32, 32, 32) ,new Predicate<Object>() {

			@Override
			public boolean test(Object t) {
				// TODO 自动生成的方法存根
				return t instanceof BombEntity&&((BombEntity)t).owner==player;
			}});
		Iterator<LivingEntity> it=bomblist.iterator();
		boolean iswindbomb=iswindbomb(player);
		if(iswindbomb) {
			player.removeEffect(Effects.SLOW_FALLING);
			player.removeEffect(Effects.MOVEMENT_SLOWDOWN);
			player.setNoGravity(false);
			player.hurt(DamageSource.explosion((LivingEntity)null), 2);
			player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,5,5));
			float yaw=player.yHeadRot;
			float f = 30F;
			double mz = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
			double mx = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
			player.setDeltaMovement(player.getDeltaMovement().add(mx,5, mz));
		}
		while(it.hasNext()) {
			BombEntity bomb=(BombEntity) it.next();
			if(bomb!=null&&bomb.isAlive()) {
				bomb.explode(iswindbomb);
			}
		}
    }
    private boolean iswindbomb(PlayerEntity player) {
    	if(!player.isNoGravity()||!ZeldaConfig.WINDBOMB.get())
    		return false;
    	List<LivingEntity> bomblist= player.level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(5, 5, 5) ,new Predicate<Object>() {
			@Override
			public boolean test(Object t) {
				// TODO 自动生成的方法存根
				return t instanceof BombEntity&&((BombEntity)t).owner==player;
			}});
    	if(bomblist.size()!=2)
    		return false;
		Iterator<LivingEntity> it=bomblist.iterator();
		boolean isoffground=false;
		while(it.hasNext()) {
			BombEntity bomb=(BombEntity) it.next();
			if(!bomb.isOnGround())
				isoffground=true;
		}
		return isoffground;
    }
}
