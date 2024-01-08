package com.raremoon.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.raremoon.client.multiplayer.ClientLevelDataExtension;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent.Stage;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderRareMoonTint {
	@SubscribeEvent
	public void onRenderLevelStageEvent(final RenderLevelStageEvent event) {
		if (event.getStage() == Stage.AFTER_SKY) {
			Minecraft minecraft = Minecraft.getInstance();
			long time = minecraft.level.getDayTime() - 12000;
			if (ClientLevelDataExtension.getMoon() == 1) {
				float green = Mth.clamp(time < 6000 ? -time / 1000F + 1 : (time - 12000) / 1000F + 1, 0.6F, 1.0F);
				float blue = Mth.clamp(time < 6000 ? -time / 1000F + 1 : (time - 12000) / 1000F + 1, 0.6F, 1.0F);
				RenderSystem.setShaderColor(1.0F, green, blue, 1.0F); // BLOOD MOON
			} else if (ClientLevelDataExtension.getMoon() == 2) {
				float blue = Mth.clamp(time < 6000 ? -time / 1000F + 1 : (time - 12000) / 1000F + 1, 0.5F, 1.0F);
				RenderSystem.setShaderColor(1.0F, 1.0F, blue, 1.0F); // FORTUNE MOON
			} else if (ClientLevelDataExtension.getMoon() == 3) {
				float red = Mth.clamp(time < 6000 ? -time / 1000F + 1 : (time - 12000) / 1000F + 1, 0.6F, 1.0F);
				float blue = Mth.clamp(time < 6000 ? -time / 1000F + 1 : (time - 12000) / 1000F + 1, 0.6F, 1.0F);
				RenderSystem.setShaderColor(red, 1.0F, blue, 1.0F); // HARVEST MOON
			} else if (ClientLevelDataExtension.getMoon() == 4) {
				float red = Mth.clamp(time < 6000 ? -time / 1000F + 1 : (time - 12000) / 1000F + 1, 0.5F, 1.0F);
				float green = Mth.clamp(time < 6000 ? -time / 1000F + 1 : (time - 12000) / 1000F + 1, 0.5F, 1.0F);
				RenderSystem.setShaderColor(red, green, 1.0F, 1.0F); // BLUE MOON
			} else {
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); // NORMAL MOON
			}
		}
	}
}
