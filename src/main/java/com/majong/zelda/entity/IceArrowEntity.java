package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.util.AttributeDamage;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;

public class IceArrowEntity extends Arrow{

	public IceArrowEntity(EntityType<? extends Arrow> type, Level worldIn) {
		super(type, worldIn);
		// TODO �Զ����ɵĹ��캯�����
	}
	public IceArrowEntity(Level worldIn, double x, double y, double z) {
		super(worldIn,x,y,z);
	}
	public IceArrowEntity(Level worldIn, LivingEntity shooter) {
		super(worldIn,shooter);
	}
	@Override
	public void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		AttributeDamage.icedamage(living, this.getOwner());
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide&&(!this.inGround||this.random.nextInt(0, 10)==0)) {
			List<Player> playerlist= level.getEntitiesOfClass(Player.class,this.getBoundingBox().inflate(20, 20, 20));
    		Iterator<Player> it=playerlist.iterator();
    		while(it.hasNext()) {
    			Player player=(Player) it.next();
    			Networking.PARTICLE.send((ServerPlayer) player,new ParticlePack(3,this.getX(),this.getY(),this.getZ(),0,0,0));
    		}
		}
	}
}
