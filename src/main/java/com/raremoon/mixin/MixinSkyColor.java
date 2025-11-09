package com.raremoon.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.raremoon.client.multiplayer.ClientLevelDataExtension;
import com.raremoon.config.RareMoonClientConfig;
import com.raremoon.util.MoonType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

@Mixin(ClientLevel.class)
public abstract class MixinSkyColor {
	@Inject(method = "getSkyColor(Lnet/minecraft/world/phys/Vec3;F)Lnet/minecraft/world/phys/Vec3;", at = @At(value = "RETURN"), cancellable = true)
	private void raremoon_getSkyColor(CallbackInfoReturnable<Vec3> cir) {
		Minecraft minecraft = Minecraft.getInstance();
		MoonType moonType = ClientLevelDataExtension.getMoon();

		long timeOfNight = minecraft.level.getDayTime() % 24000L - 12000;
		float nightTriangleWave = (timeOfNight < 6000 ? timeOfNight : -(timeOfNight - 12000)) / 4000F;

		float factor = RareMoonClientConfig.MOON_COLOR_CORRECTION.get() / 50.0F;
		float red = factor * Mth.clamp(nightTriangleWave, 0.0F, moonType.getRed());
		float green = factor * Mth.clamp(nightTriangleWave, 0.0F, moonType.getGreen());
		float blue = factor * Mth.clamp(nightTriangleWave, 0.0F, moonType.getBlue());

		Vec3 add = new Vec3(red, green, blue);
		cir.setReturnValue(cir.getReturnValue().add(add));
	}
}
