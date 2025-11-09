package com.raremoon.listeners;

import com.raremoon.client.multiplayer.ClientLevelDataExtension;
import com.raremoon.config.RareMoonClientConfig;
import com.raremoon.util.MoonType;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)

public final class ViewportEventListener {
	private ViewportEventListener() {
	}

	@SubscribeEvent
	public static void onViewportEvent$ComputeFogColor(final ViewportEvent.ComputeFogColor event) {
		Minecraft minecraft = Minecraft.getInstance();
		MoonType moonType = ClientLevelDataExtension.getMoon();

		long timeOfNight = minecraft.level.getDayTime() % 24000L - 12000;
		float nightTriangleWave = (timeOfNight < 6000 ? timeOfNight : -(timeOfNight - 12000)) / 4000F;

		float factor = RareMoonClientConfig.MOON_COLOR_CORRECTION.get() / 50.0F;
		float red = factor * Mth.clamp(nightTriangleWave, 0.0F, moonType.getRed());
		float green = factor * Mth.clamp(nightTriangleWave, 0.0F, moonType.getGreen());
		float blue = factor * Mth.clamp(nightTriangleWave, 0.0F, moonType.getBlue());

		event.setRed(event.getRed() + red);
		event.setGreen(event.getGreen() + green);
		event.setBlue(event.getBlue() + blue);
	}
}
