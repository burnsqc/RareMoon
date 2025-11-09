package com.raremoon.network.packets.clientbound;

import java.util.function.Supplier;

import com.raremoon.network.packethandlers.ClientboundPacketHandlers;
import com.raremoon.util.MoonType;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SyncSavedDataPacket {
	private MoonType moonType;

	public SyncSavedDataPacket(MoonType moonType) {
		this.moonType = moonType;
	}

	public static void encode(SyncSavedDataPacket packet, FriendlyByteBuf buffer) {
		buffer.writeEnum(packet.moonType);
	}

	public static SyncSavedDataPacket decode(FriendlyByteBuf buffer) {
		return new SyncSavedDataPacket(buffer.readEnum(MoonType.class));
	}

	public static void handle(SyncSavedDataPacket packet, final Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> ClientboundPacketHandlers.handleSyncSavedData(packet, context));
		context.get().setPacketHandled(true);
	}

	public MoonType getMoonType() {
		return moonType;
	}
}
