package com.majong.zelda.item;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.majong.zelda.sound.SoundLoader;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class HornItem extends BasicItem{
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		player.setActiveHand(hand);
		if(!world.isRemote) {
			AwakeOthers(player,player);
			world.playSound(null,player.getPosition(), SoundLoader.HORN.get(), SoundCategory.BLOCKS, 10f, 1f);
		}
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity)
    {
		if(!entity.world.isRemote&&entity instanceof LivingEntity) {
			player.world.playSound(null,player.getPosition(), SoundLoader.HORN.get(), SoundCategory.BLOCKS, 10f, 1f);
		player.setHeldItem(Hand.MAIN_HAND, stack.split(stack.getCount()-1));
		AwakeOthers((LivingEntity) entity,player);
		}
		return false;
    }
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}
	public static void AwakeOthers(LivingEntity target,LivingEntity user) {
		World world=user.world;
		List<LivingEntity> targrtlist= world.getEntitiesWithinAABB(LivingEntity.class,user.getBoundingBox().grow(20, 20, 20) ,new Predicate<Object>() {

			@Override
			public boolean test(Object t) {
				// TODO 自动生成的方法存根
				return t instanceof LivingEntity&&!(t instanceof VillagerEntity);
			}});
		Iterator<LivingEntity> it=targrtlist.iterator();
		while(it.hasNext()) {
			LivingEntity entity=(LivingEntity) it.next();
			entity.setRevengeTarget(target);
		}
	}
}
