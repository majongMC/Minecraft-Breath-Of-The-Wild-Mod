package com.majong.zelda.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Nullable;

import com.majong.zelda.api.util.AttributeDamageApi;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.Lynel;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;
import com.majong.zelda.tag.EntityTypeTag;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.PacketDistributor;

public class AttributeDamage {
	//public static final Collection<Class<? extends LivingEntity>> FIRE_RESTRAINTED=new ArrayList<>();
	//public static final Collection<Class<? extends LivingEntity>> ICE_RESTRAINTED=new ArrayList<>();
	public static void firedamage(LivingEntity living,Entity attacker) {
		Iterator<Class<? extends LivingEntity>> it=AttributeDamageApi.FIRE_RESTRAINTED.iterator();
    	while(it.hasNext())
		{
    		Class<? extends LivingEntity> restrainted=it.next();
    		if(restrainted.isInstance(living)&&ZeldaConfig.ATTRIBUTE.get())
    			living.hurt(new EntityDamageSource("arrow",attacker),32767);
		}
    	if(living.getType().getTags().anyMatch((TagKey<EntityType<?>> t)->t.equals(EntityTypeTag.FIRE_RESTRAINTED)))
    		living.hurt(new EntityDamageSource("arrow",attacker),32767);
	}
    public static void icedamage(LivingEntity living,Entity attacker) {
    	Iterator<Class<? extends LivingEntity>> it=AttributeDamageApi.ICE_RESTRAINTED.iterator();
    	if(living instanceof Mob)
    		EntityFreezer.FreezeMob((Mob) living, 200);
    	else
    		living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200,9));
    	while(it.hasNext())
		{
    		Class<? extends LivingEntity> restrainted=it.next();
    		if(restrainted.isInstance(living)&&ZeldaConfig.ATTRIBUTE.get())
    			living.hurt(new EntityDamageSource("arrow",attacker),32767);
		}
    	if(living.getType().getTags().anyMatch((TagKey<EntityType<?>> t)->t.equals(EntityTypeTag.ICE_RESTRAINTED)))
    		living.hurt(new EntityDamageSource("arrow",attacker),32767);
	}
    public static void electricitydamage(LivingEntity living,Entity attacker) {
    	if(!living.level.isClientSide&&Math.random()<ZeldaConfig.ELECTRICITY.get()&&!living.getType().getTags().anyMatch((TagKey<EntityType<?>> t)->t.equals(EntityTypeTag.ELECTRICITY_INVULNERABLE))) {
    		if(living instanceof Player) {
    			Player player=(Player) living;
    			player.drop(player.getMainHandItem(), true, true);
    			player.drop(player.getOffhandItem(), true, true);
    			if(!player.getMainHandItem().isEmpty())
    				player.sendMessage(new TranslatableComponent(new TranslatableComponent(player.getMainHandItem().getItem().getDescriptionId()).getString()+"掉落"),UUID.randomUUID());
    			if(!player.getOffhandItem().isEmpty())
        			player.sendMessage(new TranslatableComponent(new TranslatableComponent(player.getOffhandItem().getItem().getDescriptionId()).getString()+"掉落"),UUID.randomUUID());
    			living.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
    			living.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
    		}
    		else {
			Entity itemdrops1=new ItemEntity(living.level,living.getX(),living.getY(),living.getZ(),living.getMainHandItem());
			living.level.addFreshEntity(itemdrops1);
			Entity itemdrops2=new ItemEntity(living.level,living.getX(),living.getY(),living.getZ(),living.getOffhandItem());
			living.level.addFreshEntity(itemdrops2);
			living.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
			living.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
    		}
		}
	}
    public static void ancientdamage(LivingEntity living,@Nullable Entity attacker) {
    	if(!living.level.isClientSide) {
			Iterator<Class<? extends LivingEntity>> it=AttributeDamageApi.ANCIENT_RESTRAINTED.iterator();
	    	while(it.hasNext())
			{
	    		Class<? extends LivingEntity> restrainted=it.next();
	    		if(restrainted.isInstance(living)&&ZeldaConfig.ATTRIBUTE.get()) {
	    			living.hurt(new DamageSource("ancient"),32767);
	    			return;
	    		}
			}
	    	if(living.getType().getTags().anyMatch((TagKey<EntityType<?>> t)->t.equals(EntityTypeTag.ANCIENT_RESTRAINTED)))
	    	{
	    		if(attacker!=null)
	    			living.hurt(new EntityDamageSource("arrow",attacker),32767);
	    		else
	    			living.hurt(new DamageSource("arrow"),32767);
	    		return;
	    	}
	    	if((living.getMaxHealth()<=20&&!(living instanceof Player))||living instanceof Lynel) {
	    		living.teleportTo(living.getX(), -80, living.getZ());
	    		if(attacker!=null&&attacker instanceof Player) {
	    			Networking.SOUND.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayer) attacker
		                    ),
		                    new SoundPack());
	    		}
	    		return;   
	    	}
			/*if(ischaoisland(living.level,living.blockPosition())&&living instanceof WitherEntity&&ZeldaConfig.KILLWITHER.get()) {
				living.spawnAtLocation(new ItemStack(Items.NETHER_STAR,1));
				living.kill();
				return;
			}*/
	    	if(living.canChangeDimensions()) {
				if(attacker!=null)
					living.hurt(new EntityDamageSource("ancient",attacker),20);
				else
					living.hurt(new DamageSource("ancient"),20);
			}
			else {
				if(attacker!=null)
					living.hurt(new EntityDamageSource("ancient",attacker),40);
				else
					living.hurt(new DamageSource("ancient"),40);
			}
		}
	}
    /*public static boolean ischaoisland(Level Level,BlockPos pos) {
    	if(!Level.dimension().location().equals(DimensionType.END_EFFECTS)||!Utils.DRACONIC_EVOLUTION_LOADED)
    		return false;
    	int x=Math.abs(pos.getX());
    	int y=Math.abs(pos.getY());
    	if(x<1000&&y<1000)
    		return false;
    	if(isnear10000(x)&&isnear10000(y)) 
    		return true;
    	return false;
    }
    private static boolean isnear10000(int x) {
    	return x%10000<128||x%10000>(10000-128);
    }*/
    public static void registerrestraint(Collection<Class<? extends LivingEntity>> restrainted_list,Class<? extends LivingEntity> entityclass) {
    	restrainted_list.add(entityclass);
    }
}
