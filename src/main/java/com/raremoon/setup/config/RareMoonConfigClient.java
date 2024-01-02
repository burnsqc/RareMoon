package com.raremoon.setup.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class RareMoonConfigClient {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec CLIENT_SPEC;

	public static final ForgeConfigSpec.ConfigValue<Boolean> RARE_MOON_CHAT_NOTIFICATION;

	static {
		BUILDER.comment("RARE MOON CLIENT CONFIG\n");

		BUILDER.comment("In single-player, these settings will affect only you.");
		BUILDER.comment("In multi-player, these settings will still affect only you.");
		BUILDER.comment("These settings have been set to defaults selected by the Rare Moon development team.\n");

		BUILDER.push("CHAT NOTIFICATIONS");
		RARE_MOON_CHAT_NOTIFICATION = BUILDER.comment("true - Display a chat message when a rare moon appears.\nfalse - Do not display a chat message when a rare moon appears.").define("Chat Notifications", true);
		BUILDER.pop();

		CLIENT_SPEC = BUILDER.build();
	}
}