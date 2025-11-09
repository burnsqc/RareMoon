package com.raremoon.world.level.saveddata;

import com.raremoon.RareMoon;
import com.raremoon.network.packets.clientbound.SetMoonTypePacket;
import com.raremoon.util.MoonType;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.network.PacketDistributor;

public class RareMoonOverworldExtension extends SavedData {
	private MoonType moonType;

	public void setMoonType(MoonType moonType) {
		this.moonType = moonType;
		RareMoon.CHANNEL.send(PacketDistributor.DIMENSION.with(() -> Level.OVERWORLD), new SetMoonTypePacket(moonType));
		this.setDirty();
	}

	public MoonType getMoonType() {
		return moonType;
	}

	public static RareMoonOverworldExtension create() {
		return new RareMoonOverworldExtension();
	}

	public static RareMoonOverworldExtension load(CompoundTag tag) {
		RareMoonOverworldExtension data = create();
		int id = tag.getInt("moonType");
		data.moonType = MoonType.getMoonType(id);
		return data;
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		tag.putInt("moonType", moonType.getID());
		return tag;
	}

	public static RareMoonOverworldExtension getData(MinecraftServer server) {
		return server.overworld().getDataStorage().computeIfAbsent(RareMoonOverworldExtension::load, RareMoonOverworldExtension::create, RareMoon.MOD_ID + "_moonType");
	}
}
