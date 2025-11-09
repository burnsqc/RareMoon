package com.raremoon.client.multiplayer;

import com.raremoon.util.MoonType;

public class ClientLevelDataExtension {
	private static MoonType moonType;

	public static MoonType getMoon() {
		return moonType;
	}

	public void setMoon(MoonType moonTypeIn) {
		moonType = moonTypeIn;
	}
}
