package com.raremoon.world.level.saveddata;

import com.raremoon.RareMoon;
import com.raremoon.network.packets.clientbound.SetMoonTypePacket;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.network.PacketDistributor;

public class RareMoonOverworldExtension extends SavedData {
	private int moonType = 0;

	public void setMoon(int moonType) {
		this.moonType = moonType;
		RareMoon.CHANNEL.send(PacketDistributor.DIMENSION.with(() -> Level.OVERWORLD), new SetMoonTypePacket(moonType));
		this.setDirty();
	}

	public int getMoon() {
		return moonType;
	}

	public static RareMoonOverworldExtension create() {
		return new RareMoonOverworldExtension();
	}

	public static RareMoonOverworldExtension load(CompoundTag tag) {
		RareMoonOverworldExtension data = create();
		int testInt = tag.getInt("moon");
		data.moonType = testInt;
		return data;
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		tag.putInt("moon", moonType);
		return tag;
	}

	public static RareMoonOverworldExtension getData(MinecraftServer server) {
		return server.overworld().getDataStorage().computeIfAbsent(RareMoonOverworldExtension::load, RareMoonOverworldExtension::create, RareMoon.MOD_ID + "_moontype");
	}
}
