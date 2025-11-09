package com.raremoon.setup.client;

import com.raremoon.config.RareMoonClientConfig;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

public final class ClientSetup {
	private ClientSetup() {
	}

	public static void init() {
		ModLoadingContext.get().registerConfig(Type.CLIENT, RareMoonClientConfig.SPEC, "raremoon-client.toml");
	}
}
