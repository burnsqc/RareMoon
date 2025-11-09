package com.raremoon.listeners;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.raremoon.RareMoon;
import com.raremoon.config.RareMoonCommonConfig;
import com.raremoon.util.MoonType;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)

public final class LevelTickEventListener {
	private static final Marker SERVER_TICK = MarkerManager.getMarker("SERVER_TICK");
	private static final long SUNSET = 12000L;
	private static boolean moonHasBeenSet = true;

	private LevelTickEventListener() {
	}

	@SubscribeEvent
	public static void onServerTickEvent(final LevelTickEvent event) {
		boolean isServerLevel = event.level instanceof ServerLevel;

		if (isServerLevel) {
			long timeOfDay = event.level.getDayTime() % 24000L;
			boolean isDayTime = timeOfDay < SUNSET;
			boolean isNightTime = timeOfDay >= SUNSET;

			if (isDayTime && moonHasBeenSet) {
				RareMoon.LOGGER.debug(SERVER_TICK, "Time of Day: " + timeOfDay + ", moonHasBeenSet=" + moonHasBeenSet);
				RareMoonOverworldExtension rareMoonOverworldExtension = RareMoonOverworldExtension.getData(event.level.getServer());
				rareMoonOverworldExtension.setMoonType(MoonType.NORMAL);
				moonHasBeenSet = false;
				RareMoon.LOGGER.debug(SERVER_TICK, "Moon type reset to normal");
			} else if (isNightTime && !moonHasBeenSet) {
				RareMoon.LOGGER.debug(SERVER_TICK, "Time of Day: " + timeOfDay + ", moonHasBeenSet=" + moonHasBeenSet);
				RareMoonOverworldExtension rareMoonOverworldExtension = RareMoonOverworldExtension.getData(event.level.getServer());
				boolean willSetRareMoonType = event.level.getRandom().nextInt(100) < RareMoonCommonConfig.RARE_MOON_RARITY.get();

				if (willSetRareMoonType) {
					int combinedWeight = RareMoonCommonConfig.BLOOD_MOON_WEIGHT.get() + RareMoonCommonConfig.FORTUNE_MOON_WEIGHT.get() + RareMoonCommonConfig.HARVEST_MOON_WEIGHT.get() + RareMoonCommonConfig.BLUE_MOON_WEIGHT.get();
					if (combinedWeight != 0) {
						int randomInt = event.level.getRandom().nextInt(combinedWeight);
						if (randomInt < RareMoonCommonConfig.BLOOD_MOON_WEIGHT.get()) {
							rareMoonOverworldExtension.setMoonType(MoonType.BLOOD);
							RareMoon.LOGGER.debug(SERVER_TICK, "Moon type set to blood moon");
						} else if (randomInt < (RareMoonCommonConfig.BLOOD_MOON_WEIGHT.get() + RareMoonCommonConfig.FORTUNE_MOON_WEIGHT.get())) {
							rareMoonOverworldExtension.setMoonType(MoonType.FORTUNE);
							RareMoon.LOGGER.debug(SERVER_TICK, "Moon type set to fortune moon");
						} else if (randomInt < (RareMoonCommonConfig.BLOOD_MOON_WEIGHT.get() + RareMoonCommonConfig.FORTUNE_MOON_WEIGHT.get() + RareMoonCommonConfig.HARVEST_MOON_WEIGHT.get())) {
							rareMoonOverworldExtension.setMoonType(MoonType.HARVEST);
							RareMoon.LOGGER.debug(SERVER_TICK, "Moon type set to harvest moon");
						} else {
							rareMoonOverworldExtension.setMoonType(MoonType.BLUE);
							RareMoon.LOGGER.debug(SERVER_TICK, "Moon type set to blue moon");
						}
					} else {
						rareMoonOverworldExtension.setMoonType(MoonType.NORMAL);
						RareMoon.LOGGER.warn(SERVER_TICK, "Please check raremoon-common.toml config file. Rarity value is greater than zero but combined weight values total zero. Moon type defaulted to normal.");
					}
				} else {
					rareMoonOverworldExtension.setMoonType(MoonType.NORMAL);
					RareMoon.LOGGER.debug(SERVER_TICK, "Moon type set to normal");
				}
				moonHasBeenSet = true;
			}
		}
	}
}
