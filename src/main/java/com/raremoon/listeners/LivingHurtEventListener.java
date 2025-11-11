package com.raremoon.listeners;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.raremoon.RareMoon;
import com.raremoon.config.RareMoonCommonConfig;
import com.raremoon.util.MoonType;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)
public class LivingHurtEventListener {
	private static final Marker LISTENER = MarkerManager.getMarker("LISTENER");

	private LivingHurtEventListener() {
	}

	@SubscribeEvent
	public static void onLivingHurtEvent(final LivingHurtEvent event) {
		boolean isOverworld = event.getEntity().level.dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD;
		boolean isBloodMoon = RareMoonOverworldExtension.getData(event.getEntity().level.getServer()).getMoonType() == MoonType.BLOOD;

		if (isOverworld && isBloodMoon) {
			boolean isBloodMoonIncreased = RareMoonCommonConfig.BLOOD_MOON_INCREASED.get().contains(event.getSource().msgId);
			if (isBloodMoonIncreased) {
				float amountOrig = event.getAmount();
				event.setAmount((float) (event.getAmount() * RareMoonCommonConfig.BLOOD_MOON_MULTIPLIER.get()));
				RareMoon.LOGGER.trace(LISTENER, "Damage " + event.getSource().getMsgId() + " changed from " + amountOrig + " to " + event.getAmount() + " due to Blood Moon");
			}
		}
	}
}
