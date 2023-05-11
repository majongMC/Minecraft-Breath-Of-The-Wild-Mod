package com.majong.zelda.util;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public class ChangeDimension {
	public static void toTemple(Player player) {
		MinecraftServer server=player.level.getServer();
		CommandSourceStack commandsourcestack=new CommandSourceStack(server, player.position(), player.getRotationVector(), (ServerLevel) player.level, 4, "@", Component.translatable("@"), server, player);
		server.getCommands().performPrefixedCommand(commandsourcestack, "/execute in zelda:temple run tp ~ ~ ~");
	}
	public static void toOverworld(Player player) {
		MinecraftServer server=player.level.getServer();
		CommandSourceStack commandsourcestack=new CommandSourceStack(server, player.position(), player.getRotationVector(), (ServerLevel) player.level, 4, "@", Component.translatable("@"), server, player);
		server.getCommands().performPrefixedCommand(commandsourcestack, "/execute in minecraft:overworld run tp ~ ~ ~");
	}
}
