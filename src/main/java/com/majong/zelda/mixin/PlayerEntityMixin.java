package com.majong.zelda.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.majong.zelda.data.DataManager;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin{
	@Inject(at=@At("HEAD"),method="readAdditionalSaveData")
	public void readAdditionalSaveData(CompoundNBT p_70037_1_,CallbackInfo callbackInfo) {
		DataManager.preventnull((PlayerEntity)(Object)this);
		if(p_70037_1_.contains("zpd")) {
			DataManager.writefromnbt(p_70037_1_.getCompound("zpd"), (PlayerEntity)(Object)this);
		}
	}
	@Inject(at=@At("HEAD"),method="addAdditionalSaveData")
	public void addAdditionalSaveData(CompoundNBT p_213281_1_,CallbackInfo callbackInfo) {
		CompoundNBT zeldaplayerdata=DataManager.readtonbt((PlayerEntity)(Object)this);
		p_213281_1_.put("zpd", zeldaplayerdata);
		//DataManager.removedata((PlayerEntity)(Object)this);
	}
}
