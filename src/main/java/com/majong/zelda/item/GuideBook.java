package com.majong.zelda.item;

import com.majong.zelda.util.ModCheck;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;

public class GuideBook extends Item{

	public GuideBook() {
		super(new Properties().stacksTo(1));
	}
	@Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if(!worldIn.isClientSide&&!(playerIn instanceof FakePlayer)) {
			if(!ModCheck.isModLoaded("patchouli"))
				playerIn.sendSystemMessage(Component.translatable("msg.zelda.no_patchouli"));
			else
				this.runcommand(worldIn, playerIn);
		}
		return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
	}
	private void runcommand(Level level, Player player) {
		MinecraftServer server=level.getServer();
		CommandSourceStack commandsourcestack=new CommandSourceStack(server, player.position(), player.getRotationVector(), (ServerLevel) level, 4, "@", Component.translatable("@"), server, player);
		server.getCommands().performPrefixedCommand(commandsourcestack, "/open-patchouli-book @s zelda:guide_book");
	}
}
