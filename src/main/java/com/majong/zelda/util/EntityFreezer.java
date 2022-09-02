package com.majong.zelda.util;

import java.util.HashMap;
import java.util.Map;

import com.majong.zelda.Utils;
import com.majong.zelda.api.tickutils.Delayer;

import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

public class EntityFreezer extends Delayer<MobEntity>{
	public static final ResourceLocation FREEZE_INVULNERABLE=new ResourceLocation(Utils.MOD_ID,"freeze_invulnerable");
	private double motionx=0,motionz=0;
	private static final Map<MobEntity,EntityFreezer> FREEZED_ENTITY=new HashMap<>();
	public EntityFreezer(int freezetime, MobEntity MobEntity) {
		super(freezetime*2, MobEntity);
	}

	@Override
	public boolean isclientside() {
		return false;
	}
	@Override
	public void init() {
		MobEntity MobEntity=this.getParaments()[0];
		MobEntity.setDeltaMovement(Vector3d.ZERO);
		MobEntity.setNoAi(true);
		FREEZED_ENTITY.put(MobEntity, this);
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		MobEntity MobEntity=this.getParaments()[0];
		if(MobEntity!=null)
			FREEZED_ENTITY.remove(MobEntity);
		if(MobEntity!=null&&MobEntity.isAlive()) {
			MobEntity.setNoAi(false);
			new Delayer<Object>(2,MobEntity,this.motionx,this.motionz) {

				@Override
				public boolean isclientside() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void finish() {
					// TODO Auto-generated method stub
					Object[] o=this.getParaments();
					((MobEntity)o[0]).setDeltaMovement(new Vector3d((double)o[1],0.2,(double)o[2]));
				}};
		}
	}
	@Override
	public void tick() {
		super.tick();
		MobEntity MobEntity=this.getParaments()[0];
		Vector3d motion=MobEntity.getDeltaMovement();
		this.motionx+=motion.x;
		this.motionz+=motion.z;
		MobEntity.setDeltaMovement(Vector3d.ZERO);
	}
	public static void FreezeMobEntity(MobEntity MobEntity,int freezetime) {
		if(canFreeze(MobEntity)) {
			EntityFreezer freezer=getFreezer(MobEntity);
			if(freezer==null)
				new EntityFreezer(freezetime,MobEntity);
			else
				freezer.setProcess(0);
		}
	}
	public static EntityFreezer getFreezer(MobEntity MobEntity) {
		return FREEZED_ENTITY.get(MobEntity);
	}
	public static void unFreezeMobEntity(MobEntity MobEntity) {
		EntityFreezer freezer=getFreezer(MobEntity);
		if(freezer!=null) {
			freezer.setProcess(Integer.MAX_VALUE-2);
		}
	}
	private static boolean canFreeze(MobEntity MobEntity) {
		return !MobEntity.getType().getTags().contains(FREEZE_INVULNERABLE);
	}
}
