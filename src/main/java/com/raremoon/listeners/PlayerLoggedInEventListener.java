package com.raremoon.listeners;

import com.raremoon.RareMoon;
import com.raremoon.network.packets.ClientboundSyncSavedDataPacket;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

public class PlayerLoggedInEventListener {

	@SubscribeEvent
	public void onPlayerLoggedIn(final PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer) {
			RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.getEntity().getServer());
			RareMoon.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new ClientboundSyncSavedDataPacket(data.getMoon()));
		}
	}
}
