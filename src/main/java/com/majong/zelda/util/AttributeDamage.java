package com.majong.zelda.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import com.majong.zelda.Utils;
import com.majong.zelda.api.util.AttributeDamageApi;
import com.majong.zelda.config.ZeldaConfig;

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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class AttributeDamage {
	public static final ResourceLocation FIRE_RESTRAINTED=new ResourceLocation(Utils.MOD_ID,"fire_restrainted");
	public static final ResourceLocation ICE_RESTRAINTED=new ResourceLocation(Utils.MOD_ID,"ice_restrainted");
	public static final ResourceLocation ANCIENT_RESTRAINTED=new ResourceLocation(Utils.MOD_ID,"ancient_restrainted");
	public static final ResourceLocation ELECTRICITY_INVULNERABLE=new ResourceLocation(Utils.MOD_ID,"electricity_invulnerable");
	public static void firedamage(LivingEntity living,Entity attacker) {
		Iterator<Class<? extends LivingEntity>> it=AttributeDamageApi.FIRE_RESTRAINTED.iterator();
    	while(it.hasNext())
		{
    		Class<? extends LivingEntity> restrainted=it.next();
    		if(restrainted.isInstance(living)&&ZeldaConfig.ATTRIBUTE.get())
    			living.hurt(new EntityDamageSource("arrow",attacker),32767);
		}
    	if(living.getType().getTags().contains(FIRE_RESTRAINTED))
    		living.hurt(new EntityDamageSource("arrow",attacker),32767);
	}
    public static void icedamage(LivingEntity living,Entity attacker) {
    	Iterator<Class<? extends LivingEntity>> it=AttributeDamageApi.ICE_RESTRAINTED.iterator();
    	living.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,200,9));
    	while(it.hasNext())
		{
    		Class<? extends LivingEntity> restrainted=it.next();
    		if(restrainted.isInstance(living)&&ZeldaConfig.ATTRIBUTE.get())
    			living.hurt(new EntityDamageSource("arrow",attacker),32767);
		}
    	if(living.getType().getTags().contains(ICE_RESTRAINTED))
    		living.hurt(new EntityDamageSource("arrow",attacker),32767);
	}
    public static void electricitydamage(LivingEntity living,Entity attacker) {
    	if(!living.level.isClientSide&&Math.random()<ZeldaConfig.ELECTRICITY.get()&&!living.getType().getTags().contains(ELECTRICITY_INVULNERABLE)) {
    		if(living instanceof PlayerEntity) {
    			PlayerEntity player=(PlayerEntity) living;
    			player.drop(player.getMainHandItem(), true, true);
    			player.drop(player.getOffhandItem(), true, true);
    			if(!player.getMainHandItem().isEmpty())
    				player.sendMessage(new TranslationTextComponent(new TranslationTextComponent(player.getMainHandItem().getItem().getDescriptionId()).getString()+"µôÂä"), UUID.randomUUID());
    			if(!player.getOffhandItem().isEmpty())
        			player.sendMessage(new TranslationTextComponent(new TranslationTextComponent(player.getOffhandItem().getItem().getDescriptionId()).getString()+"µôÂä"), UUID.randomUUID());
    			living.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
    			living.setItemInHand(Hand.OFF_HAND, ItemStack.EMPTY);
    		}
    		else {
			Entity itemdrops1=new ItemEntity(living.level,living.getX(),living.getY(),living.getZ(),living.getMainHandItem());
			living.level.addFreshEntity(itemdrops1);
			Entity itemdrops2=new ItemEntity(living.level,living.getX(),living.getY(),living.getZ(),living.getOffhandItem());
			living.level.addFreshEntity(itemdrops2);
			living.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
			living.setItemInHand(Hand.OFF_HAND, ItemStack.EMPTY);
    		}
		}
	}
    public static void ancientdamage(LivingEntity living,Entity attacker) {
    	if(!living.level.isClientSide) {
			Iterator<Class<? extends LivingEntity>> it=AttributeDamageApi.ANCIENT_RESTRAINTED.iterator();
	    	while(it.hasNext())
			{
	    		Class<? extends LivingEntity> restrainted=it.next();
	    		if(restrainted.isInstance(living)&&ZeldaConfig.ATTRIBUTE.get()) {
	    			living.hurt(new EntityDamageSource("ancient",attacker),32767);
	    			return;
	    		}
			}
	    	if(living.getType().getTags().contains(ANCIENT_RESTRAINTED))
	    		{living.hurt(new EntityDamageSource("arrow",attacker),32767);return;}
			if(ischaoisland(living.level,living.blockPosition())&&living instanceof WitherEntity&&ZeldaConfig.KILLWITHER.get()) {
				living.spawnAtLocation(new ItemStack(Items.NETHER_STAR,1));
				living.kill();
				return;
			}
			if(living.canChangeDimensions())
				living.hurt(new EntityDamageSource("ancient",attacker),20);
			else
				living.hurt(new EntityDamageSource("ancient",attacker),40);
		}
	}
    public static boolean ischaoisland(World world,BlockPos pos) {
    	if(!world.dimension().location().equals(DimensionType.END_EFFECTS)||!Utils.DRACONIC_EVOLUTION_LOADED)
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
