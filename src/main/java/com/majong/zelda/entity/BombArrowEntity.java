package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;

public class BombArrowEntity extends Arrow{

	public BombArrowEntity(EntityType<? extends Arrow> type, Level worldIn) {
		super(type, worldIn);
		// TODO �Զ����ɵĹ��캯�����
	}
	public BombArrowEntity(Level worldIn, double x, double y, double z) {
		super(worldIn,x,y,z);
	}
	public BombArrowEntity(Level worldIn, LivingEntity shooter) {
		super(worldIn,shooter);
	}
	@Override
	public void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		if(ZeldaConfig.BOMB_ARROW_DESTROY.get())
			this.level.explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 4, Level.ExplosionInteraction.TNT);
		else
			this.level.explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 4, Level.ExplosionInteraction.BLOCK);
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
			List<Player> playerlist= level.getEntitiesOfClass(Player.class,this.getBoundingBox().inflate(20, 20, 20));
    		Iterator<Player> it=playerlist.iterator();
    		while(it.hasNext()) {
    			Player player=(Player) it.next();
    			Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayer) player
	                    ),
	                    new ParticlePack(4,this.getX(),this.getY(),this.getZ(),0,0,0));
    		}
			if(this.inGround) {
				if(ZeldaConfig.BOMB_ARROW_DESTROY.get())
					this.level.explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 4, Level.ExplosionInteraction.TNT);
				else
					this.level.explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 4, Level.ExplosionInteraction.NONE);
				this.kill();
			}
			if(this.level.dimension().location().equals(Level.NETHER.location())) {
				if(ZeldaConfig.BOMB_ARROW_DESTROY.get())
					this.level.explode(null, this.getX(), this.getY(), this.getZ(), 4, Level.ExplosionInteraction.TNT);
				else
					this.level.explode(null, this.getX(), this.getY(), this.getZ(), 4, Level.ExplosionInteraction.NONE);
				this.kill();
			}
		}
	}
}
