package com.raremoon.server.commands;

import com.mojang.brigadier.CommandDispatcher;

import com.raremoon.util.MoonType;

import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;


public final class MoonTypeCommand {
	private MoonTypeCommand() {
	}

	public static void register(CommandDispatcher<CommandSourceStack> command) {
		command.register(Commands.literal("raremoon")
				.requires((stack) -> stack.hasPermission(2))
				.then(Commands.literal("normal").executes((context) -> setMoonType(context.getSource(), MoonType.NORMAL)))
				.then(Commands.literal("blood").executes((context) -> setMoonType(context.getSource(), MoonType.BLOOD)))
				.then(Commands.literal("fortune").executes((context) -> setMoonType(context.getSource(), MoonType.FORTUNE)))
				.then(Commands.literal("harvest").executes((context) -> setMoonType(context.getSource(), MoonType.HARVEST)))
				.then(Commands.literal("blue").executes((context) -> setMoonType(context.getSource(), MoonType.BLUE))));
	}

	private static int setMoonType(CommandSourceStack stack, MoonType moonType) {
		RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(stack.getLevel().getServer());
		data.setMoonType(moonType);
		stack.sendSuccess(moonType.getCommandResponseMessage(), true);
		return -1;
	}
}
