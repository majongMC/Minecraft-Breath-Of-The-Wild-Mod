package com.majong.zelda.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import com.majong.zelda.Utils;
import com.majong.zelda.api.util.AttributeDamageApi;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.GuardianEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class AttributeDamage {
	//public static final Collection<Class<? extends LivingEntity>> FIRE_RESTRAINTED=new ArrayList<>();
	//public static final Collection<Class<? extends LivingEntity>> ICE_RESTRAINTED=new ArrayList<>();
	public static void firedamage(LivingEntity living,Entity attacker) {
		Iterator<Class<? extends LivingEntity>> it=AttributeDamageApi.FIRE_RESTRAINTED.iterator();
    	while(it.hasNext())
		{
    		Class<? extends LivingEntity> restrainted=it.next();
    		if(restrainted.isInstance(living)&&ZeldaConfig.ATTRIBUTE.get())
    			living.attackEntityFrom(new EntityDamageSource("arrow",attacker),32767);
		}
	}
    public static void icedamage(LivingEntity living,Entity attacker) {
    	Iterator<Class<? extends LivingEntity>> it=AttributeDamageApi.ICE_RESTRAINTED.iterator();
    	living.addPotionEffect(new EffectInstance(Effects.SLOWNESS,200,9));
    	while(it.hasNext())
		{
    		Class<? extends LivingEntity> restrainted=it.next();
    		if(restrainted.isInstance(living)&&ZeldaConfig.ATTRIBUTE.get())
    			living.attackEntityFrom(new EntityDamageSource("arrow",attacker),32767);
		}
	}
    public static void electricitydamage(LivingEntity living,Entity attacker) {
    	if(!living.world.isRemote&&Math.random()<ZeldaConfig.ELECTRICITY.get()) {
    		if(living instanceof PlayerEntity) {
    			PlayerEntity player=(PlayerEntity) living;
    			player.dropItem(player.getHeldItemMainhand(), true, true);
    			player.dropItem(player.getHeldItemOffhand(), true, true);
    			if(!player.getHeldItemMainhand().isEmpty())
    				player.sendMessage(new TranslationTextComponent(player.getHeldItemMainhand().getItem().getName().getString()+"µôÂä"), UUID.randomUUID());
    			if(!player.getHeldItemOffhand().isEmpty())
        			player.sendMessage(new TranslationTextComponent(player.getHeldItemOffhand().getItem().getName().getString()+"µôÂä"), UUID.randomUUID());
    			living.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
    			living.setHeldItem(Hand.OFF_HAND, ItemStack.EMPTY);
    		}
    		else {
			Entity itemdrops1=new ItemEntity(living.world,living.getPosX(),living.getPosY(),living.getPosZ(),living.getHeldItemMainhand());
			living.world.addEntity(itemdrops1);
			Entity itemdrops2=new ItemEntity(living.world,living.getPosX(),living.getPosY(),living.getPosZ(),living.getHeldItemOffhand());
			living.world.addEntity(itemdrops2);
			living.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
			living.setHeldItem(Hand.OFF_HAND, ItemStack.EMPTY);
    		}
		}
	}
    public static void ancientdamage(LivingEntity living,Entity attacker) {
    	if(!living.world.isRemote) {
			Iterator<Class<? extends LivingEntity>> it=AttributeDamageApi.ANCIENT_RESTRAINTED.iterator();
	    	while(it.hasNext())
			{
	    		Class<? extends LivingEntity> restrainted=it.next();
	    		if(restrainted.isInstance(living)&&ZeldaConfig.ATTRIBUTE.get()) {
	    			living.attackEntityFrom(new EntityDamageSource("ancient",attacker),32767);
	    			return;
	    		}
			}
			if(ischaoisland(living.world,living.getPosition())&&living instanceof WitherEntity&&ZeldaConfig.KILLWITHER.get()) {
				living.entityDropItem(new ItemStack(Items.NETHER_STAR,1));
				living.onKillCommand();
				return;
			}
			if(living.isNonBoss())
				living.attackEntityFrom(new EntityDamageSource("ancient",attacker),20);
			else
				living.attackEntityFrom(new EntityDamageSource("ancient",attacker),40);
		}
	}
    public static boolean ischaoisland(World world,BlockPos pos) {
    	if(!world.getDimensionKey().getLocation().equals(DimensionType.THE_END_ID)||!Utils.DRACONIC_EVOLUTION_LOADED)
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
    }
    public static void registerrestraint(Collection<Class<? extends LivingEntity>> restrainted_list,Class<? extends LivingEntity> entityclass) {
    	restrainted_list.add(entityclass);
    }
}
