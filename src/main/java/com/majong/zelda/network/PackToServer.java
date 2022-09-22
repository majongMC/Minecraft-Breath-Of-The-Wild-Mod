package com.majong.zelda.network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.entity.BombEntity;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.MovingBlockCarrierEntity;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.util.EntityFreezer;
import com.majong.zelda.world.dimension.TempleDimensionData;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.fml.network.NetworkEvent;

public class PackToServer {
	private final int type;
    public PackToServer(PacketBuffer buffer) {
    	type=buffer.readInt();
    }

    public PackToServer(int type) {
    	this.type=type;
    }

    public void toBytes(PacketBuffer buf) {
    	buf.writeInt(type);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
    	PlayerEntity player=ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
        	switch(type) {
        	case 0:useskill(player);break;
        	case 1:placebomb(player,true);break;
        	case 2:placebomb(player,false);break;
        	case 3:detonate(player);break;
        	case 4:teleporttooverworld(player);break;
        	case 5:usemagnet(player);break;
        	case 6:useStatic(player);break;
        	case 7:useice(player);break;
        	case 8:usecamera(player);break;
        	}
        });
        ctx.get().setPacketHandled(true);
    }
    private void teleporttooverworld(PlayerEntity player) {
    	TempleDimensionData.ExitTemple(player.level, player);
    }
    private void useskill(PlayerEntity player) {
    	if(player.isShiftKeyDown()&&DataManager.data.get(player).unlocked[1]&&DataManager.data.get(player).skill[1]>0) {
    		player.addEffect(new EffectInstance(Effects.LEVITATION,60,10));
    		DataManager.data.get(player).skill[1]--;
    		DataManager.sendzeldaplayerdatapack(player);
    	}
    	else if(DataManager.data.get(player).unlocked[3]&&DataManager.data.get(player).skill[3]>0) {
    		World world=player.level;
    		List<Entity> partentitylist=new ArrayList<>();
    		List<Entity> targrtlist= world.getEntitiesOfClass(Entity.class,player.getBoundingBox().inflate(20, 20, 20) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO 自动生成的方法存根
					if(t instanceof PartEntity) {
						Entity parent=((PartEntity)t).getParent();
						if(parent instanceof LivingEntity&&(parent.getClassification(false)==EntityClassification.MONSTER||(!(parent instanceof PlayerEntity)&&((LivingEntity) parent).getMaxHealth()>101))) {
							partentitylist.add(parent);
							return false;
						}
					}
					if(t instanceof LivingEntity) {
						LivingEntity entity=(LivingEntity) t;
						if(entity.getClassification(false)==EntityClassification.MONSTER||(!(entity instanceof PlayerEntity)&&entity.getMaxHealth()>101)) {
							return true;
						}
						else
							return false;
					}
					else
						return false;
				}});
    		targrtlist.addAll(partentitylist);
    		Iterator<Entity> it=targrtlist.iterator();
    		while(it.hasNext()) {
    			LivingEntity target=(LivingEntity) it.next();
    			LightningBoltEntity lightning=new LightningBoltEntity(EntityType.LIGHTNING_BOLT,world);
    			lightning.setPos(target.getX(), target.getY(), target.getZ());
    			lightning.setVisualOnly(true);
    			world.addFreshEntity(lightning);
    			if(target.getMaxHealth()<=100)
    				target.hurt(new EntityDamageSource("hero",player).setThorns(), 15);
    			else
    				target.hurt(new EntityDamageSource("hero",player).setThorns(), 0.15F*target.getMaxHealth());
    			target.setSecondsOnFire(10);
    		}
    		for(int i=0;i<10;i++) {
    			LightningBoltEntity lightning=new LightningBoltEntity(EntityType.LIGHTNING_BOLT,world);
    			lightning.setPos(player.getX()+Math.random()*20-10, player.getY()-3, player.getZ()+Math.random()*20-10);
    			lightning.setVisualOnly(true);
    			world.addFreshEntity(lightning);
    		}
    		DataManager.data.get(player).skill[3]--;
    		DataManager.sendzeldaplayerdatapack(player);
    	}
    }
    private void placebomb(PlayerEntity player,boolean round) {
    	if(!ZeldaConfig.BOMB.get()) {
			player.sendMessage(new TranslationTextComponent("msg.bombprohibited"), UUID.randomUUID());
			return;
		}
    	if(!HasShikaStone(player)) {
    		player.sendMessage(new TranslationTextComponent("msg.anticheat.warn"),UUID.randomUUID());
    		LogManager.getLogger().warn("检测到疑似作弊行为，玩家名："+player.getScoreboardName()+" UUID:"+player.getUUID().toString());
    		return;
    	}
    	BombEntity bomb=new BombEntity(EntityLoader.BOMB.get(),player.level);
    	bomb.setPos(player.getX(), player.getY(), player.getZ());
    	bomb.setowner(player);
    	player.level.addFreshEntity(bomb);
    }
    private void detonate(PlayerEntity player) {
    	List<MobEntity> moblist= player.level.getEntitiesOfClass(MobEntity.class,player.getBoundingBox().inflate(32, 32, 32) ,(MobEntity t)->true);
    	Iterator<MobEntity> it1=moblist.iterator();
    	while(it1.hasNext()) {
			MobEntity mob=it1.next();
			EntityFreezer.unFreezeMobEntity(mob);
		}
    	List<MovingBlockCarrierEntity> list=player.level.getEntitiesOfClass(MovingBlockCarrierEntity.class,player.getBoundingBox().inflate(16));
		Iterator<MovingBlockCarrierEntity> it2=list.iterator();
		while(it2.hasNext()) {
			MovingBlockCarrierEntity entity=it2.next();
			entity.loose(player);
		}
    	if(!ZeldaConfig.BOMB.get()) {
			player.sendMessage(new TranslationTextComponent("msg.bombprohibited"), UUID.randomUUID());
			return;
		}
    	List<LivingEntity> bomblist= player.level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(32, 32, 32) ,(LivingEntity t)->t instanceof BombEntity&&((BombEntity)t).owner==player);
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
    	List<LivingEntity> bomblist= player.level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(5, 5, 5) ,(LivingEntity t)->t instanceof BombEntity&&((BombEntity)t).owner==player);
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
    private void usemagnet(PlayerEntity player) {
    	if(!HasShikaStone(player)) {
    		player.sendMessage(new TranslationTextComponent("msg.anticheat.warn"),UUID.randomUUID());
    		LogManager.getLogger().warn("检测到疑似作弊行为，玩家名："+player.getScoreboardName()+" UUID:"+player.getUUID().toString());
    		return;
    	}
    	if(!ZeldaConfig.USEFUL_MAGNET.get()) {
        	ItemStack stack=player.getItemInHand(Hand.MAIN_HAND);
        	if(stack.getItem()!=ItemLoader.SHIKA_STONE.get())
        		return;
        	CompoundNBT nbt = stack.getOrCreateTagElement("magnet");
        	nbt.putBoolean("activated", true);
        	player.sendMessage(new TranslationTextComponent("msg.zelda.magnetactivated"),UUID.randomUUID());
        	}else {
        	double x=player.getX();
        	double y=player.getY();
        	double z=player.getZ();
        	List<ItemEntity> itemlist= player.level.getEntitiesOfClass(ItemEntity.class,player.getBoundingBox().inflate(16, 16, 16) ,(ItemEntity t)->true);
        	Iterator<ItemEntity> it=itemlist.iterator();
    		while(it.hasNext()) {
    			ItemEntity item=it.next();
    			item.teleportTo(x, y, z);
    		}
        	}
    }
    private void useStatic(PlayerEntity player) {
    	if(!HasShikaStone(player)) {
    		player.sendMessage(new TranslationTextComponent("msg.anticheat.warn"),UUID.randomUUID());
    		LogManager.getLogger().warn("检测到疑似作弊行为，玩家名："+player.getScoreboardName()+" UUID:"+player.getUUID().toString());
    		return;
    	}
    	ItemStack stack=player.getItemInHand(Hand.MAIN_HAND);
    	if(stack.getItem()!=ItemLoader.SHIKA_STONE.get())
    		return;
    	CompoundNBT nbt = stack.getOrCreateTagElement("static");
    	nbt.putBoolean("activated", true);
    	player.sendMessage(new TranslationTextComponent("msg.zelda.staticactivated"), UUID.randomUUID());
    }
    private void useice(PlayerEntity player) {
    	if(!HasShikaStone(player)) {
    		player.sendMessage(new TranslationTextComponent("msg.anticheat.warn"),UUID.randomUUID());
    		LogManager.getLogger().warn("检测到疑似作弊行为，玩家名："+player.getScoreboardName()+" UUID:"+player.getUUID().toString());
    		return;
    	}
    	if(player.isInWater())
    		return;
    	BlockPos playerpos=player.blockPosition();
    	World world=player.level;
    	for(int x=-8;x<9;x++)
    		for(int z=-8;z<9;z++) {
    			BlockPos pos=playerpos.offset(x,-1,z);
    			if(world.getBlockState(pos).getBlock()==Blocks.WATER) {
    				world.setBlockAndUpdate(pos, Blocks.ICE.defaultBlockState());
    			}
    		}
    }
    private void usecamera(PlayerEntity player) {
    	if(!HasShikaStone(player)) {
    		player.sendMessage(new TranslationTextComponent("msg.anticheat.warn"),UUID.randomUUID());
    		LogManager.getLogger().warn("检测到疑似作弊行为，玩家名："+player.getScoreboardName()+" UUID:"+player.getUUID().toString());
    		return;
    	}
    	ItemStack stack=player.getItemInHand(Hand.MAIN_HAND);
    	if(stack.getItem()!=ItemLoader.SHIKA_STONE.get())
    		return;
    	CompoundNBT nbt = stack.getOrCreateTagElement("camera");
    	nbt.putBoolean("activated", true);
    	player.sendMessage(new TranslationTextComponent("msg.zelda.cameraactivated"), UUID.randomUUID());
    }
    private boolean HasShikaStone(PlayerEntity player) {
    	return player.getMainHandItem().getItem()==ItemLoader.SHIKA_STONE.get()||player.getOffhandItem().getItem()==ItemLoader.SHIKA_STONE.get();
    }
}
