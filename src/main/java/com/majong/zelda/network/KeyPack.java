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

public class KeyPack {
	private final UUID uuid;
	private final int type;
    public KeyPack(PacketBuffer buffer) {
    	type=buffer.readInt();
    	uuid=buffer.readUniqueId();
    }

    public KeyPack(int type) {
    	this.type=type;
    	this.uuid=Minecraft.getInstance().player.getUniqueID();
    }

    public void toBytes(PacketBuffer buf) {
    	buf.writeInt(type);
    	buf.writeUniqueId(uuid);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	switch(type) {
        	case 0:useskill();break;
        	case 1:placebomb(true);break;
        	case 2:placebomb(false);break;
        	case 3:detonate();break;
        	}
        });
        ctx.get().setPacketHandled(true);
    }
    private void useskill() {
    	PlayerEntity player=Minecraft.getInstance().getIntegratedServer().getPlayerList().getPlayerByUUID(uuid);
    	//DataManager.preventnull(player);
    	if(player.isSneaking()&&DataManager.data.get(player).skill[1]>0) {
    		player.addPotionEffect(new EffectInstance(Effects.LEVITATION,60,10));
    		DataManager.data.get(player).skill[1]--;
    		DataManager.sendzeldaplayerdatapack(player);
    	}
    	else if(DataManager.data.get(player).skill[3]>0) {
    		World world=Minecraft.getInstance().getIntegratedServer().getWorld(player.world.getDimensionKey());
    		List<LivingEntity> targrtlist= world.getEntitiesWithinAABB(LivingEntity.class,player.getBoundingBox().grow(20, 20, 20) ,new Predicate<Object>() {

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
    			lightning.setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
    			lightning.setEffectOnly(true);
    			world.addEntity(lightning);
    			target.attackEntityFrom(new EntityDamageSource("hero",player), 15);
    			target.setFire(10);
    		}
    		DataManager.data.get(player).skill[3]--;
    		DataManager.sendzeldaplayerdatapack(player);
    	}
    }
    private void placebomb(boolean round) {
    	PlayerEntity player=Minecraft.getInstance().getIntegratedServer().getPlayerList().getPlayerByUUID(uuid);
    	if(!ZeldaConfig.BOMB.get()) {
			player.sendMessage(new TranslationTextComponent("msg.bombprohibited"), UUID.randomUUID());
			return;
		}
    	BombEntity bomb=new BombEntity(EntityLoader.BOMB.get(),player.world);
    	bomb.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
    	bomb.setowner(player);
    	player.world.addEntity(bomb);
    }
    private void detonate() {
    	PlayerEntity player=Minecraft.getInstance().getIntegratedServer().getPlayerList().getPlayerByUUID(uuid);
    	if(!ZeldaConfig.BOMB.get()) {
			player.sendMessage(new TranslationTextComponent("msg.bombprohibited"), UUID.randomUUID());
			return;
		}
    	List<LivingEntity> bomblist= player.world.getEntitiesWithinAABB(LivingEntity.class,player.getBoundingBox().grow(32, 32, 32) ,new Predicate<Object>() {

			@Override
			public boolean test(Object t) {
				// TODO 自动生成的方法存根
				return t instanceof BombEntity&&((BombEntity)t).owner==player;
			}});
		Iterator<LivingEntity> it=bomblist.iterator();
		boolean iswindbomb=iswindbomb(player);
		if(iswindbomb) {
			player.removePotionEffect(Effects.SLOW_FALLING);
			player.removePotionEffect(Effects.SLOWNESS);
			player.setNoGravity(false);
			player.attackEntityFrom(DamageSource.causeExplosionDamage(player), 2);
			player.addPotionEffect(new EffectInstance(Effects.RESISTANCE,5,5));
			float yaw=player.rotationYawHead;
			float f = 30F;
			double mz = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
			double mx = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
			player.setMotion(player.getMotion().add(mx,5, mz));
		}
		while(it.hasNext()) {
			BombEntity bomb=(BombEntity) it.next();
			if(bomb!=null&&bomb.isAlive()) {
				bomb.explode(iswindbomb);
			}
		}
    }
    private boolean iswindbomb(PlayerEntity player) {
    	if(!player.hasNoGravity()||!ZeldaConfig.WINDBOMB.get())
    		return false;
    	List<LivingEntity> bomblist= player.world.getEntitiesWithinAABB(LivingEntity.class,player.getBoundingBox().grow(5, 5, 5) ,new Predicate<Object>() {
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
