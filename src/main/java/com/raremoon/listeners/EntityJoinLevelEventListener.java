package com.raremoon.listeners;

import com.raremoon.RareMoon;
import com.raremoon.network.packets.clientbound.SyncSavedDataPacket;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)

public final class EntityJoinLevelEventListener {
	private EntityJoinLevelEventListener() {
	}

	@SubscribeEvent
	public static void onEntityJoinLevel(final EntityJoinLevelEvent event) {
		boolean isOverworld = event.getLevel().dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD;
		boolean isServerPlayer = event.getEntity() instanceof ServerPlayer;

		if (isOverworld && isServerPlayer) {
			ServerPlayer serverPlayer = (ServerPlayer) event.getEntity();
			RareMoonOverworldExtension rareMoonOverworldExtension = RareMoonOverworldExtension.getData(event.getEntity().getServer());
			RareMoon.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new SyncSavedDataPacket(rareMoonOverworldExtension.getMoonType()));
		}
	}
}
