package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.util.AttributeDamage;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class AncientArrowEntity extends ArrowEntity{

	public AncientArrowEntity(EntityType<? extends ArrowEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO 自动生成的构造函数存根
	}
	public AncientArrowEntity(World worldIn, double x, double y, double z) {
		super(worldIn,x,y,z);
	}
	public AncientArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn,shooter);
	}
	@Override
	public void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		AttributeDamage.ancientdamage(living, this.getOwner());
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
			List<PlayerEntity> playerlist= level.getEntitiesOfClass(PlayerEntity.class,this.getBoundingBox().inflate(20, 20, 20));
    		Iterator<PlayerEntity> it=playerlist.iterator();
    		while(it.hasNext()) {
    			PlayerEntity player=(PlayerEntity) it.next();
    			Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new ParticlePack(5,this.getX(),this.getY(),this.getZ(),0,0,0));
    		}
		}
	}
}
