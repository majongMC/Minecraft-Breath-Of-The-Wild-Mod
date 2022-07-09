package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.util.AttributeDamage;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;

public class ElectricityArrowEntity extends Arrow{
	public ElectricityArrowEntity(EntityType<? extends Arrow> type, Level worldIn) {
		super(type, worldIn);
		// TODO �Զ����ɵĹ��캯�����
	}
	public ElectricityArrowEntity(Level worldIn, double x, double y, double z) {
		super(worldIn,x,y,z);
	}
	public ElectricityArrowEntity(Level worldIn, LivingEntity shooter) {
		super(worldIn,shooter);
	}
	@Override
	public void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		AttributeDamage.electricitydamage(living, this.getOwner());
	}
	
	@Override
	public void tick() {
		if(!this.level.isClientSide) {
			List<Player> playerlist=level.getEntitiesOfClass(Player.class,this.getBoundingBox().inflate(20, 20, 20) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO �Զ����ɵķ������
					if(t instanceof Player) 
						return true;
					else
						return false;
				}});
    		Iterator<Player> it=playerlist.iterator();
    		while(it.hasNext()) {
    			Player player=(Player) it.next();
    			Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayer) player
	                    ),
	                    new ParticlePack(1,this.getX(),this.getY(),this.getZ(),0,0,0));
    		}
		}
		super.tick();
	}
}
