package com.majong.zelda.item;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.majong.zelda.advancement.TriggerRegistery;
import com.majong.zelda.sound.SoundLoader;

import majongmc.hllib.common.iforgeport.MiniIForgeItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class HornItem extends BasicItem implements MiniIForgeItem{
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		player.startUsingItem(hand);
		if(!world.isClientSide) {
			AwakeOthers(player,player);
			world.playSound(null,player.blockPosition(), SoundLoader.HORN.get(), SoundSource.BLOCKS, 10f, 1f);
		}
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(hand));
	}
	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity)
    {
		if(!entity.level.isClientSide&&entity instanceof LivingEntity) {
			player.level.playSound(null,player.blockPosition(), SoundLoader.HORN.get(), SoundSource.BLOCKS, 10f, 1f);
			TriggerRegistery.HORN.trigger((ServerPlayer) player);
			if(!((ServerPlayer)player).gameMode.isCreative())
				player.setItemInHand(InteractionHand.MAIN_HAND, stack.split(stack.getCount()-1));
		AwakeOthers((LivingEntity) entity,player);
		}
		return false;
    }
	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.TOOT_HORN;
	}
	public static void AwakeOthers(LivingEntity target,LivingEntity user) {
		Level world=user.level;
		List<LivingEntity> targrtlist= world.getEntitiesOfClass(LivingEntity.class,user.getBoundingBox().inflate(20, 20, 20) ,new Predicate<Object>() {

			@Override
			public boolean test(Object t) {
				return t instanceof LivingEntity&&!(t instanceof Villager);
			}});
		Iterator<LivingEntity> it=targrtlist.iterator();
		while(it.hasNext()) {
			LivingEntity entity=(LivingEntity) it.next();
			entity.setLastHurtByMob(target);
		}
	}
}
