package com.raremoon.listeners;

import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BloodMoonListener {

	@SubscribeEvent
	public void onLivingHurtEvent(final LivingHurtEvent event) {
		RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.getEntity().level().getServer());
		if (data.getMoon() == 1) {
			event.setAmount(event.getAmount() * 2);
		}
	}
}