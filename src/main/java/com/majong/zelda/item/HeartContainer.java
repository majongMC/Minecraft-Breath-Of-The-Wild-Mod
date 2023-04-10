package com.majong.zelda.item;

import java.util.UUID;

import com.majong.zelda.advancement.TriggerRegistery;
import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HeartContainer extends Item{
	public static final UUID modifierID=UUID.fromString("05E57F1A-E106-351A-7573-4EBEE2C6B193");
	public HeartContainer() {
		super(new Properties());
	}
	@Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		if(level.isClientSide)
			level.playSound(playerIn, playerIn.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.AMBIENT, 10f, 1f);
		AttributeModifier old_modifier=playerIn.getAttribute(Attributes.MAX_HEALTH).getModifier(modifierID);
		double value;
		if(old_modifier==null)
			value=0;
		else {
			value=old_modifier.getAmount();
			playerIn.getAttribute(Attributes.MAX_HEALTH).removeModifier(old_modifier);
		}
		if(!level.isClientSide&&value+2>=2*ZeldaConfig.MAX_HEART.get())
			TriggerRegistery.HEART_FULL.trigger((ServerPlayer) playerIn);
		if(value<2*ZeldaConfig.MAX_HEART.get())
			playerIn.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(modifierID, "max_health_modifier",value+2, AttributeModifier.Operation.ADDITION));
		else {
			playerIn.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(modifierID, "max_health_modifier",value, AttributeModifier.Operation.ADDITION));
			return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
		}
		return InteractionResultHolder.pass(playerIn.getItemInHand(handIn).split(playerIn.getItemInHand(handIn).getCount()-1));
	}
}
