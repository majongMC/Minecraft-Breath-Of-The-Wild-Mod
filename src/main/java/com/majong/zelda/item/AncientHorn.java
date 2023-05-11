package com.majong.zelda.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.majong.zelda.advancement.TriggerRegistery;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class AncientHorn extends Item{

	public AncientHorn() {
		super(new Properties().durability(128));
		// TODO Auto-generated constructor stub
	}
	@Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if(!player.isUsingItem() &&!world.isClientSide) {
			world.playSound(null,player.blockPosition(), SoundEvents.WARDEN_SONIC_CHARGE, SoundSource.BLOCKS, 10f, 1f);
		}
		player.startUsingItem(hand);
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(hand));
	}
	@Override
	public int getUseDuration(ItemStack p_41454_) {
		return 40;
	}
	@Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        	Player player=(Player)entity;
        	performwardensonic(player);
        	if(!player.level.isClientSide) {
        		player.getCooldowns().addCooldown(this, 40);
        		stack.hurtAndBreak(1, (Player)entity,(Player playerIn) -> {
   		         playerIn.broadcastBreakEvent(EquipmentSlot.MAINHAND);
   		      });
        	}
        return stack;
    }
	@Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if(isSelected&&!world.isClientSide&&entity instanceof Player) {
			List<LivingEntity> targetlist= world.getEntitiesOfClass(LivingEntity.class,entity.getBoundingBox().inflate(20, 20, 20),(t)->t!=entity&&t.getDeltaMovement().x!=0&&t.getDeltaMovement().z!=0&&!t.hasEffect(MobEffects.GLOWING));
			Iterator<LivingEntity> it=targetlist.iterator();
    		while(it.hasNext()) {
    			LivingEntity target=it.next();
    			target.addEffect(new MobEffectInstance(MobEffects.GLOWING,260,0));
    		}
		}
	}
	private void performwardensonic(Player player) {
		float yaw=((Player)player).yHeadRot;
		float pitch=((Player)player).xRotO;
		double sinyaw=Math.sin((yaw+90)*Math.PI/180);
		double cosyaw=Math.cos((yaw+90)*Math.PI/180);
		double sinpitch=-Math.sin(pitch*Math.PI/180);
		double cospitch=Math.cos(pitch*Math.PI/180);
		BlockPos playerpos=player.blockPosition();
		if(!player.level.isClientSide) {
			player.level.playSound(null,player.blockPosition(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.BLOCKS, 10f, 1f);
			List<LivingEntity> targetlist=new ArrayList<LivingEntity>();
			for(double i=0;i<20;i=i+0.5) {
				double x,y,z,r;
				y=i*sinpitch;
				r=i*cospitch;
				x=r*cosyaw;
				z=r*sinyaw;
				List<LivingEntity> onesearch= player.level.getEntitiesOfClass(LivingEntity.class,new AABB(playerpos.getX()+x-0.7,playerpos.getY()+y+0.3,playerpos.getZ()+z-0.7,playerpos.getX()+x+0.7,playerpos.getY()+y+1.7,playerpos.getZ()+z+0.7) ,(t)->!(t instanceof Player));
				Iterator<LivingEntity> it=onesearch.iterator();
	    		while(it.hasNext()) {
	    			LivingEntity target=it.next();
	    			if(!targetlist.contains(target)) {
	    				targetlist.add(target);
	    			}
	    		}
			}
			double x,y,z,r,back=2;
			y=back*sinpitch;
			r=back*cospitch;
			x=r*cosyaw;
			z=r*sinyaw;
			Iterator<LivingEntity> it=targetlist.iterator();
			while(it.hasNext()) {
    			LivingEntity target=it.next();
    			target.hurt(player.level.damageSources().sonicBoom(player), 10);
    			TriggerRegistery.SONIC_BOOM.trigger((ServerPlayer) player);
    			if(target instanceof Warden)
    				TriggerRegistery.SONIC_BOOM_WARDEN.trigger((ServerPlayer) player);
    			target.setDeltaMovement(x, y, z);
			}
			player.setDeltaMovement(-x, -y, -z);
		}
		else {
			for(int i=0;i<20;i++) {
				double x,y,z,r;
				y=i*sinpitch;
				r=i*cospitch;
				x=r*cosyaw;
				z=r*sinyaw;
				//player.sendSystemMessage(Component.translatable("p:"+x+y+z));
				player.level.addAlwaysVisibleParticle(ParticleTypes.SONIC_BOOM,playerpos.getX()+x,playerpos.getY()+y+1,playerpos.getZ()+z,0,0,0);
			}
		}
	}
}
