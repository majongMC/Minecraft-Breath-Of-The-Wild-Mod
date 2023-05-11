package com.majong.zelda.util;

import java.util.HashMap;
import java.util.Map;

import com.majong.zelda.tag.EntityTypeTag;

import majongmc.hllib.common.tickutils.Schedule;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;

public class EntityFreezer extends Schedule<Mob>{
	private double motionx=0,motionz=0;
	private static final Map<Mob,EntityFreezer> FREEZED_ENTITY=new HashMap<>();
	public EntityFreezer(int freezetime, Mob mob) {
		super(freezetime, mob);
	}

	@Override
	public boolean isclientside() {
		return false;
	}
	@Override
	public void init() {
		Mob mob=this.getParaments()[0];
		mob.setDeltaMovement(Vec3.ZERO);
		mob.setNoAi(true);
		FREEZED_ENTITY.put(mob, this);
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Mob mob=this.getParaments()[0];
		if(mob!=null)
			FREEZED_ENTITY.remove(mob);
		if(mob!=null&&mob.isAlive()) {
			mob.setNoAi(false);
			new Schedule<Object>(2,mob,this.motionx,this.motionz) {

				@Override
				public boolean isclientside() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void finish() {
					// TODO Auto-generated method stub
					Object[] o=this.getParaments();
					((Mob)o[0]).setDeltaMovement(new Vec3((double)o[1],0.2,(double)o[2]));
				}};
		}
	}
	@Override
	public void tick() {
		super.tick();
		Mob mob=this.getParaments()[0];
		Vec3 motion=mob.getDeltaMovement();
		this.motionx+=motion.x;
		this.motionz+=motion.z;
		mob.setDeltaMovement(Vec3.ZERO);
	}
	public static void FreezeMob(Mob mob,int freezetime) {
		if(canFreeze(mob)) {
			EntityFreezer freezer=getFreezer(mob);
			if(freezer==null)
				new EntityFreezer(freezetime,mob);
			else
				freezer.setProcess(0);
		}
	}
	public static EntityFreezer getFreezer(Mob mob) {
		return FREEZED_ENTITY.get(mob);
	}
	public static void unFreezeMob(Mob mob) {
		EntityFreezer freezer=getFreezer(mob);
		if(freezer!=null) {
			freezer.setProcess(Integer.MAX_VALUE-2);
		}
	}
	private static boolean canFreeze(Mob mob) {
		return !mob.getType().getTags().anyMatch((TagKey<EntityType<?>> t)->t.equals(EntityTypeTag.FREEZE_INVULNERABLE));
	}
}
