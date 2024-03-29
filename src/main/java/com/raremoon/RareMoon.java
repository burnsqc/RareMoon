package com.raremoon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raremoon.listeners.BloodMoonListener;
import com.raremoon.listeners.HarvestMoonListener;
import com.raremoon.listeners.PlayerLoggedInEventListener;
import com.raremoon.listeners.ServerTickEventListener;
import com.raremoon.network.packets.ClientboundSetMoonTypePacket;
import com.raremoon.network.packets.ClientboundSyncSavedDataPacket;
import com.raremoon.registration.deferred.GlobalLootModifierSerializers;
import com.raremoon.setup.ClientSetup;
import com.raremoon.setup.config.RareMoonConfigCommon;
import com.raremoon.setup.events.Commands;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(RareMoon.MOD_ID)
public class RareMoon {
	public static final String MOD_ID = "raremoon";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final IEventBus MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
	public static final IEventBus FORGE_EVENT_BUS = MinecraftForge.EVENT_BUS;
	public static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	public static int PACKET_ID = 0;

	public RareMoon() {
		LOGGER.info("RAREMOON NOW LOADING FOR DISTRIBUTION - " + FMLEnvironment.dist.toString());
		ModLoadingContext.get().registerConfig(Type.COMMON, RareMoonConfigCommon.COMMON_SPEC, "raremoon-common.toml");
		GlobalLootModifierSerializers.init();

		FORGE_EVENT_BUS.addListener(Commands::register);
		FORGE_EVENT_BUS.addListener(ServerTickEventListener::onServerTickEvent);
		FORGE_EVENT_BUS.register(new BloodMoonListener());
		FORGE_EVENT_BUS.register(new HarvestMoonListener());
		FORGE_EVENT_BUS.register(new PlayerLoggedInEventListener());

		CHANNEL.registerMessage(PACKET_ID++, ClientboundSetMoonTypePacket.class, ClientboundSetMoonTypePacket::encode, ClientboundSetMoonTypePacket::decode, ClientboundSetMoonTypePacket::handle);
		CHANNEL.registerMessage(PACKET_ID++, ClientboundSyncSavedDataPacket.class, ClientboundSyncSavedDataPacket::encode, ClientboundSyncSavedDataPacket::decode, ClientboundSyncSavedDataPacket::handle);

		if (FMLEnvironment.dist.isClient()) {
			ClientSetup.init();
		}
	}
}
