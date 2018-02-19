package com.rcorp.airpokecross.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rcorp.airpokecross.AirPokeCrossGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "PokeCross";
		cfg.height = 1080;
		cfg.width = 1920;
		cfg.fullscreen = false;
		cfg.resizable = false;
		new LwjglApplication(new AirPokeCrossGame(), cfg);
	}
}
