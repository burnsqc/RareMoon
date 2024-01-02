package com.raremoon.network.packethandlers;

import java.util.function.Supplier;

import com.raremoon.client.renderer.RareMoonOverworldRenderer;
import com.raremoon.client.renderer.RenderRareMoonTint;
import com.raremoon.network.packets.ClientboundSetMoonTypePacket;
import com.raremoon.setup.config.RareMoonConfigClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

public class ClientboundPacketHandlers {
	public static void handleSetMoonType(ClientboundSetMoonTypePacket packet, final Supplier<NetworkEvent.Context> context) {
		RenderRareMoonTint.setMoonType(packet.getMoonType());
		RareMoonOverworldRenderer.setMoonType(packet.getMoonType());
		if (RareMoonConfigClient.RARE_MOON_CHAT_NOTIFICATION.get()) {
			Minecraft mc = Minecraft.getInstance();
			LocalPlayer player = mc.player;

			int moon = packet.getMoonType();
			String message = "";
			if (moon == 1) {
				message = "A blood moon rises...";
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 0.2F, 0.2F, false); // BLOOD
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLAZE_DEATH, SoundSource.BLOCKS, 0.2F, 0.1F, false); // BLOOD
			} else if (moon == 2) {
				message = "A fortune moon rises...";
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 10.0F, 0.2F, false); // FORTUNE
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.1F, 2.0F, false); // FORTUNE
			} else if (moon == 3) {
				message = "A harvest moon rises...";
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 10.0F, 0.1F, false); // HARVEST
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.CAT_PURR, SoundSource.BLOCKS, 10.0F, 0.1F, false); // HARVEST
			} else if (moon == 4) {
				message = "A blue moon rises...";
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 5.0F, 0.1F, false); // HARVEST
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 5.0F, 0.3F, false); // HARVEST
			}
			player.displayClientMessage(Component.literal(message), true);

		}
	}
}