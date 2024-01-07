package com.raremoon.setup;

import com.raremoon.RareMoon;
import com.raremoon.client.renderer.RenderRareMoonTint;
import com.raremoon.setup.config.RareMoonConfigClient;
import com.raremoon.setup.events.DimensionSpecialEffects;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

public class ClientSetup {
	public static void init() {
		ModLoadingContext.get().registerConfig(Type.CLIENT, RareMoonConfigClient.CLIENT_SPEC, "raremoon-client.toml");
		RareMoon.MOD_EVENT_BUS.addListener(DimensionSpecialEffects::register);
		RareMoon.FORGE_EVENT_BUS.register(new RenderRareMoonTint());
	}
}
