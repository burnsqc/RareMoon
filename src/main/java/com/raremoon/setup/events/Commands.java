package com.raremoon.setup.events;

import com.raremoon.server.commands.MoonTypeCommand;

import net.minecraftforge.event.RegisterCommandsEvent;

public class Commands {
	public static void register(final RegisterCommandsEvent event) {
		MoonTypeCommand.register(event.getDispatcher());
	}
}
