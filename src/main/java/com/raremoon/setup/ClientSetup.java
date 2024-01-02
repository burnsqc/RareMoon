package com.raremoon.setup;

import com.raremoon.RareMoon;
import com.raremoon.client.renderer.RareMoonOverworldRenderer;
import com.raremoon.client.renderer.RenderRareMoonTint;
import com.raremoon.setup.config.RareMoonConfigClient;

import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

public class ClientSetup {
	public static void init() {
		ModLoadingContext.get().registerConfig(Type.CLIENT, RareMoonConfigClient.CLIENT_SPEC, "raremoon-client.toml");
		RareMoon.MOD_EVENT_BUS.addListener(ClientSetup::onRegisterDimensionSpecialEffectsEvent);
		RareMoon.FORGE_EVENT_BUS.register(new RenderRareMoonTint());
	}

	public static void onRegisterDimensionSpecialEffectsEvent(final RegisterDimensionSpecialEffectsEvent event) {
		event.register(BuiltinDimensionTypes.OVERWORLD_EFFECTS, new RareMoonOverworldRenderer());
	}
}