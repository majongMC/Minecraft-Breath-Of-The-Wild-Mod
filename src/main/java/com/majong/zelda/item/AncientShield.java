package com.majong.zelda.item;

import javax.annotation.Nullable;

import com.majong.zelda.Utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class AncientShield extends ShieldItem{

	public AncientShield() {
		super(new Properties().group(Utils.ZELDA_CREATIVE_TAB).maxDamage(320));
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
		return true;
	}
}
