package com.game.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.game.Prueba;
import com.game.Undertale;

import java.awt.*;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired())   return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static void createApplication() {
            new Lwjgl3Application(new Undertale(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Undertale");
        configuration.useVsync(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        // Limits FPS to the refresh rate of the currently active monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        // If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        // useful for testing performance, but can also be very stressful to some hardware.
        // You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.

        // configuration.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        configuration.setWindowedMode(screenWidth, screenHeight);
        configuration.setWindowPosition(0, 25);

        // Establece el tamaño de la ventana para que coincida con el tamaño de la pantalla principal.
        configuration.setWindowedMode(screenWidth, screenHeight);
        // configuration.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        configuration.setWindowIcon("Title.jpg");
        configuration.setInitialBackgroundColor(Color.BLACK);
        configuration.setResizable(true);
        return configuration;
    }
}
