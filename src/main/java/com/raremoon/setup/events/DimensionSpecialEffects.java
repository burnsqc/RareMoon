package com.raremoon.setup.events;

import com.raremoon.client.renderer.RareMoonOverworldRenderer;

import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;

public class DimensionSpecialEffects {
	public static void register(final RegisterDimensionSpecialEffectsEvent event) {
		event.register(BuiltinDimensionTypes.OVERWORLD_EFFECTS, new RareMoonOverworldRenderer());
	}
}
