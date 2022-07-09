package com.majong.zelda.network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.entity.BombEntity;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.world.dimension.TempleDimensionData;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.network.NetworkEvent;

public class PackWithUUID {
	private final UUID uuid;
	private final int type;
    public PackWithUUID(FriendlyByteBuf buffer) {
    	type=buffer.readInt();
    	uuid=buffer.readUUID();
    }

    public PackWithUUID(int type) {
    	this.type=type;
    	this.uuid=Minecraft.getInstance().player.getUUID();
    }

    public void toBytes(FriendlyByteBuf buf) {
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
        	case 5:usemagnet();break;
        	case 6:useStatic();break;
        	case 7:useice();break;
        	case 8:usecamera();break;
        	}
        });
        ctx.get().setPacketHandled(true);
    }
    private void teleporttooverworld() {
    	Player player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	TempleDimensionData.ExitTemple(player.level, player);
    }
    private void useskill() {
    	Player player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	//DataManager.preventnull(player);
    	if(player.isShiftKeyDown()&&DataManager.data.get(player).unlocked[1]&&DataManager.data.get(player).skill[1]>0) {
    		player.addEffect(new MobEffectInstance(MobEffects.LEVITATION,60,10));
    		DataManager.data.get(player).skill[1]--;
    		DataManager.sendzeldaplayerdatapack(player);
    	}
    	else if(DataManager.data.get(player).unlocked[3]&&DataManager.data.get(player).skill[3]>0) {
    		Level Level=Minecraft.getInstance().getSingleplayerServer().getLevel(player.level.dimension());
    		List<Entity> partentitylist=new ArrayList<>();
    		List<Entity> targrtlist= Level.getEntitiesOfClass(Entity.class,player.getBoundingBox().inflate(20, 20, 20) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO �Զ����ɵķ������
					if(t instanceof PartEntity) {
						Entity parent=((PartEntity)t).getParent();
						if(parent instanceof LivingEntity&&(parent.getClassification(false)==MobCategory.MONSTER||(!(parent instanceof Player)&&((LivingEntity) parent).getMaxHealth()>101))) {
							partentitylist.add(parent);
							return false;
						}
					}
					if(t instanceof LivingEntity) {
						LivingEntity entity=(LivingEntity) t;
						if(entity.getClassification(false)==MobCategory.MONSTER||(!(entity instanceof Player)&&entity.getMaxHealth()>101)) {
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
    			LightningBolt lightning=new LightningBolt(EntityType.LIGHTNING_BOLT,Level);
    			lightning.setPos(target.getX(), target.getY(), target.getZ());
    			lightning.setVisualOnly(true);
    			Level.addFreshEntity(lightning);
    			if(target.getMaxHealth()<=100)
    				target.hurt(new EntityDamageSource("hero",player).setThorns(), 15);
    			else
    				target.hurt(new EntityDamageSource("hero",player).setThorns(), 0.15F*target.getMaxHealth());
    			target.setSecondsOnFire(10);
    		}
    		for(int i=0;i<10;i++) {
    			LightningBolt lightning=new LightningBolt(EntityType.LIGHTNING_BOLT,Level);
    			lightning.setPos(player.getX()+Math.random()*20-10, player.getY()-3, player.getZ()+Math.random()*20-10);
    			lightning.setVisualOnly(true);
    			Level.addFreshEntity(lightning);
    		}
    		DataManager.data.get(player).skill[3]--;
    		DataManager.sendzeldaplayerdatapack(player);
    	}
    }
    private void placebomb(boolean round) {
    	Player player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	if(!ZeldaConfig.BOMB.get()) {
			player.sendSystemMessage(Component.translatable("msg.bombprohibited"));
			return;
		}
    	BombEntity bomb=new BombEntity(EntityLoader.BOMB.get(),player.level);
    	bomb.setPos(player.getX(), player.getY(), player.getZ());
    	bomb.setowner(player);
    	player.level.addFreshEntity(bomb);
    }
    private void detonate() {
    	Player player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	if(!ZeldaConfig.BOMB.get()) {
			player.sendSystemMessage(Component.translatable("msg.bombprohibited"));
			return;
		}
    	List<LivingEntity> bomblist= player.level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(32, 32, 32) ,new Predicate<Object>() {

			@Override
			public boolean test(Object t) {
				// TODO �Զ����ɵķ������
				return t instanceof BombEntity&&((BombEntity)t).owner==player;
			}});
		Iterator<LivingEntity> it=bomblist.iterator();
		boolean iswindbomb=iswindbomb(player);
		if(iswindbomb) {
			player.removeEffect(MobEffects.SLOW_FALLING);
			player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
			player.setNoGravity(false);
			player.hurt(DamageSource.explosion((LivingEntity)null), 2);
			player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,5,5));
			float yaw=player.yHeadRot;
			float f = 30F;
			double mz = Math.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
			double mx = -Math.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
			player.setDeltaMovement(player.getDeltaMovement().add(mx,5, mz));
		}
		while(it.hasNext()) {
			BombEntity bomb=(BombEntity) it.next();
			if(bomb!=null&&bomb.isAlive()) {
				bomb.explode(iswindbomb);
			}
		}
    }
    private boolean iswindbomb(Player player) {
    	if(!player.isNoGravity()||!ZeldaConfig.WINDBOMB.get())
    		return false;
    	List<LivingEntity> bomblist= player.level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(5, 5, 5) ,new Predicate<Object>() {
			@Override
			public boolean test(Object t) {
				// TODO �Զ����ɵķ������
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
    private void usemagnet() {
    	Player player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	double x=player.getX();
    	double y=player.getY();
    	double z=player.getZ();
    	List<ItemEntity> itemlist= player.level.getEntitiesOfClass(ItemEntity.class,player.getBoundingBox().inflate(16, 16, 16) ,new Predicate<Object>() {
			@Override
			public boolean test(Object t) {
				// TODO �Զ����ɵķ������
				return t instanceof ItemEntity;
			}});
    	Iterator<ItemEntity> it=itemlist.iterator();
		while(it.hasNext()) {
			ItemEntity item=it.next();
			item.teleportTo(x, y, z);
		}
    }
    private void useStatic() {
    	Player player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	ItemStack stack=player.getItemInHand(InteractionHand.MAIN_HAND);
    	if(stack.getItem()!=ItemLoader.SHIKA_STONE.get())
    		return;
    	CompoundTag nbt = stack.getOrCreateTagElement("static");
    	nbt.putBoolean("activated", true);
    	player.sendSystemMessage(Component.translatable("msg.zelda.staticactivated"));
    }
    private void useice() {
    	Player player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	if(player.isInWater())
    		return;
    	BlockPos playerpos=player.blockPosition();
    	Level Level=player.level;
    	for(int x=-8;x<9;x++)
    		for(int z=-8;z<9;z++) {
    			BlockPos pos=playerpos.offset(x,-1,z);
    			if(Level.getBlockState(pos).getBlock()==Blocks.WATER) {
    				Level.setBlockAndUpdate(pos, Blocks.ICE.defaultBlockState());
    			}
    		}
    }
    private void usecamera() {
    	Player player=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(uuid);
    	ItemStack stack=player.getItemInHand(InteractionHand.MAIN_HAND);
    	if(stack.getItem()!=ItemLoader.SHIKA_STONE.get())
    		return;
    	CompoundTag nbt = stack.getOrCreateTagElement("camera");
    	nbt.putBoolean("activated", true);
    	player.sendSystemMessage(Component.translatable("msg.zelda.cameraactivated"));
    }
}
