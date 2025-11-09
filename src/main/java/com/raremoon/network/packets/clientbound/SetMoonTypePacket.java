package com.raremoon.network.packets.clientbound;

import java.util.function.Supplier;

import com.raremoon.network.packethandlers.ClientboundPacketHandlers;
import com.raremoon.util.MoonType;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SetMoonTypePacket {
	private MoonType moonType;

	public SetMoonTypePacket(MoonType moonType) {
		this.moonType = moonType;
	}

	public static void encode(SetMoonTypePacket packet, FriendlyByteBuf buffer) {
		buffer.writeEnum(packet.moonType);
	}

	public static SetMoonTypePacket decode(FriendlyByteBuf buffer) {
		return new SetMoonTypePacket(buffer.readEnum(MoonType.class));
	}

	public static void handle(SetMoonTypePacket packet, final Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> ClientboundPacketHandlers.handleSetMoonType(packet, context));
		context.get().setPacketHandled(true);
	}

	public MoonType getMoonType() {
		return this.moonType;
	}
}
