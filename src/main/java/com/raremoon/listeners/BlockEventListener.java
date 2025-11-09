package com.raremoon.listeners;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.raremoon.RareMoon;
import com.raremoon.util.MoonType;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)

public final class BlockEventListener {
	private static final Marker LISTENER = MarkerManager.getMarker("LISTENER");

	private BlockEventListener() {
	}

	@SubscribeEvent
	public static void onCropGrowEventPre(final BlockEvent.CropGrowEvent.Pre event) {
		boolean isServerLevel = event.getLevel() instanceof ServerLevel;

		if (isServerLevel) {
			boolean isOverworld = ((ServerLevel) event.getLevel()).dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD;
			boolean isHarvestMoon = RareMoonOverworldExtension.getData(event.getLevel().getServer()).getMoonType() == MoonType.HARVEST;

			if (isOverworld && isHarvestMoon) {
				Result resultOrig = event.getResult();
				event.setResult(Result.ALLOW);
				RareMoon.LOGGER.trace(LISTENER, "CropGrowEvent.Pre result changed from " + resultOrig + " to " + event.getResult() + " due to Harvest Moon.");
			}
		}
	}

	@SubscribeEvent
	public static void onCropGrowEventPost(final BlockEvent.CropGrowEvent.Post event) {
		boolean isServerLevel = event.getLevel() instanceof ServerLevel;

		if (isServerLevel) {
			boolean isOverworld = ((ServerLevel) event.getLevel()).dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD;
			boolean isHarvestMoon = RareMoonOverworldExtension.getData(event.getLevel().getServer()).getMoonType() == MoonType.HARVEST;
			boolean isCrop = event.getState().getBlock() instanceof CropBlock;

			if (isOverworld && isHarvestMoon && isCrop) {
				CropBlock crop = (CropBlock) event.getState().getBlock();
				int age = crop.getAge(event.getState());
				boolean isLessThanMaxAge = age < crop.getMaxAge();

				if (isLessThanMaxAge) {
					event.getLevel().setBlock(event.getPos(), crop.getStateForAge(age + 1), 2);
					RareMoon.LOGGER.trace(LISTENER, "Crop " + crop.getDescriptionId() + " age changed from " + age + " to " + crop.getAge(crop.getStateForAge(age + 1)) + " due to Harvest Moon.");
				}
			}
		}
	}
}
