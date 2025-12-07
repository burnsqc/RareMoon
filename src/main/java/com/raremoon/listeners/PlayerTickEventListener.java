package com.raremoon.listeners;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.raremoon.RareMoon;
import com.raremoon.config.RareMoonCommonConfig;
import com.raremoon.registration.dynamic.RareMoonMobEffectTags;
import com.raremoon.util.MoonType;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)

public final class PlayerTickEventListener {
	private static final Marker LISTENER = MarkerManager.getMarker("LISTENER");

	private PlayerTickEventListener() {
	}

	@SubscribeEvent
	public static void onPlayerTickEvent(final PlayerTickEvent event) {
		boolean isServerPlayer = event.player instanceof ServerPlayer;

		if (isServerPlayer) {
			boolean isOverworld = event.player.level.dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD;
			boolean isBlueMoon = RareMoonOverworldExtension.getData(event.player.level.getServer()).getMoonType() == MoonType.BLUE;

			if (isOverworld && isBlueMoon) {
				boolean isCreativeMode = event.player.getAbilities().instabuild;
				boolean isSpectatorMode = event.player.isSpectator();
				boolean hasNoEffects = event.player.getActiveEffects().isEmpty();
				boolean hasCooldownFinished = event.player.level.getGameTime() % (RareMoonCommonConfig.BLUE_MOON_DURATION.get() * 20 + RareMoonCommonConfig.BLUE_MOON_COOLDOWN.get() * 20) == 0;

				if (!isCreativeMode && !isSpectatorMode && hasNoEffects && hasCooldownFinished) {
					List<MobEffect> mobEffectsFiltered = ForgeRegistries.MOB_EFFECTS.getValues().stream().filter(effect -> ForgeRegistries.MOB_EFFECTS.tags().getTag(RareMoonMobEffectTags.BLUE_MOON_RANDOMIZED).contains(effect)).toList();
					int random = new Random().nextInt(mobEffectsFiltered.size());
					MobEffectInstance mobEffectInstance = new MobEffectInstance((MobEffect) mobEffectsFiltered.toArray()[random], RareMoonCommonConfig.BLUE_MOON_DURATION.get() * 20, 0);
					event.player.addEffect(mobEffectInstance);
					RareMoon.LOGGER.trace(LISTENER, "Applied effect " + mobEffectInstance.getDescriptionId() + " to " + event.player.getName().getString() + " due to Blue Moon.");
				}
			}
		}
	}
}
