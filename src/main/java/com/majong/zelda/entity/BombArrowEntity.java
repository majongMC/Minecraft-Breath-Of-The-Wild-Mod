package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.DimensionType;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class BombArrowEntity extends ArrowEntity{

	public BombArrowEntity(EntityType<? extends ArrowEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO 自动生成的构造函数存根
	}
	public BombArrowEntity(World worldIn, double x, double y, double z) {
		super(worldIn,x,y,z);
	}
	public BombArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn,shooter);
	}
	@Override
	public void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		if(ZeldaConfig.BOMB_ARROW_DESTROY.get())
			this.level.explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 4, Mode.BREAK);
		else
			this.level.explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 4, Mode.NONE);
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
			List<PlayerEntity> playerlist= level.getEntitiesOfClass(PlayerEntity.class,this.getBoundingBox().inflate(20, 20, 20) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO 自动生成的方法存根
					if(t instanceof PlayerEntity) 
						return true;
					else
						return false;
				}});
    		Iterator<PlayerEntity> it=playerlist.iterator();
    		while(it.hasNext()) {
    			PlayerEntity player=(PlayerEntity) it.next();
    			Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new ParticlePack(4,this.getX(),this.getY(),this.getZ(),0,0,0));
    		}
			if(this.inGround) {
				if(ZeldaConfig.BOMB_ARROW_DESTROY.get())
					this.level.explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 4, Mode.BREAK);
				else
					this.level.explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 4, Mode.NONE);
				this.kill();
			}
			if(this.level.dimension().location().equals(DimensionType.NETHER_EFFECTS)) {
				if(ZeldaConfig.BOMB_ARROW_DESTROY.get())
					this.level.explode(null, this.getX(), this.getY(), this.getZ(), 4, Mode.BREAK);
				else
					this.level.explode(null, this.getX(), this.getY(), this.getZ(), 4, Mode.NONE);
				this.kill();
			}
		}
	}
}
