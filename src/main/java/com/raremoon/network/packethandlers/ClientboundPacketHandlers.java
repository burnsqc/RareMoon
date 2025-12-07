package com.raremoon.network.packethandlers;

import java.util.function.Supplier;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.raremoon.RareMoon;
import com.raremoon.client.multiplayer.ClientLevelDataExtension;
import com.raremoon.config.RareMoonClientConfig;
import com.raremoon.network.packets.clientbound.SetMoonTypePacket;
import com.raremoon.network.packets.clientbound.SyncSavedDataPacket;
import com.raremoon.util.MoonType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

public final class ClientboundPacketHandlers {
	private static final Marker NETWORK = MarkerManager.getMarker("NETWORK");

	private ClientboundPacketHandlers() {
	}

	public static void handleSetMoonType(SetMoonTypePacket packet, final Supplier<NetworkEvent.Context> context) {
		RareMoon.LOGGER.debug(NETWORK, "Handling SetMoonTypePacket");
		MoonType moonType = packet.getMoonType();
		setMoonTexture(packet.getMoonType());
		Minecraft mc = Minecraft.getInstance();
		LocalPlayer player = mc.player;

		if (moonType != MoonType.NORMAL) {
			if (RareMoonClientConfig.RARE_MOON_TEXT_NOTIFICATION.get()) {
				RareMoon.LOGGER.trace(NETWORK, "Text notifications on, displaying message");
				player.displayClientMessage(moonType.getNotificationMessage(), true);
			}
			if (RareMoonClientConfig.RARE_MOON_SOUND_NOTIFICATION.get()) {
				RareMoon.LOGGER.trace(NETWORK, "Sound notifications on, playing sound");
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), moonType.getNotificationSound(), SoundSource.AMBIENT, 1.0F, 1.0F, false);
			}
		}
	}

	public static void handleSyncSavedData(SyncSavedDataPacket packet, final Supplier<NetworkEvent.Context> context) {
		RareMoon.LOGGER.debug(NETWORK, "Handling SyncSavedDataPacket");
		setMoonTexture(packet.getMoonType());
	}

	private static void setMoonTexture(MoonType moonType) {
		ClientLevelDataExtension data = new ClientLevelDataExtension();
		data.setMoon(moonType);
		LevelRenderer.MOON_LOCATION = moonType.getTexture();
	}
}
