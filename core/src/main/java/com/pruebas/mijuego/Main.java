package com.pruebas.mijuego;

import java.util.Arrays;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Manager.LanguageManager;
import screens.MainMenu;

public class Main extends Game {

    public SpriteBatch batch;

    public static final int V_WIDTH = 400; // 400
    public static final int V_HEIGHT = 700; // la altura que quieras

    //Diferentes bits de estado

    public static final short TIERRA_BIT = 1;
    public static final short PROTA_BIT = 2;
    public static final short PLATAFORMA_BIT = 4;
    public static final short LINEADENUBE_BIT = 8;

    //Volumen de la musica del juego
    public static float volumen = 50;
    //Volumen de la musica antes de mutearse
    public static float volumenanterior = 0;
    public static boolean mute = false;

    //Records de tiempo
    public static float[] recordslvl1 = new float[] { 99999f, 99999f, 99999f, 99999f, 99999f };
    public static float[] recordslvl2 = new float[] { 99999f, 99999f, 99999f, 99999f, 99999f };

    public static AssetManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("music/CancionMenu.mp3", Music.class);
        manager.load("music/CancionGameOver.mp3", Music.class);
        manager.load("music/CancionSettings.mp3", Music.class);
        manager.load("music/SonidoSalto.mp3", Music.class);
        manager.load("music/MusicaLvl1.mp3", Music.class);
        manager.load("music/MusicaVictoria.mp3", Music.class);
        manager.load("music/MusicLevels.mp3", Music.class);
        manager.finishLoading();
        LanguageManager.loadLanguage();
        setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    //Para meter los records de tiempo
    public void meterRecordlvl1(float score) {
        // Si el nuevo valor es menor que el máximo del array, reemplázalo
        float maxValue = Float.MIN_VALUE;
        int maxIndex = -1;

        // Buscar el valor más alto en el array
        for (int i = 0; i < recordslvl1.length; i++) {
            if (recordslvl1[i] > maxValue) {
                maxValue = recordslvl1[i];
                maxIndex = i;
            }
        }

        // Si el nuevo valor es menor que el máximo, reemplazamos
        if (score < maxValue) {
            recordslvl1[maxIndex] = score;
        }

        // Ordenamos el array para mantener los valores en orden ascendente
        Arrays.sort(recordslvl1);
    }

    public void meterRecordlvl2(float score) {
        // Si el nuevo valor es menor que el máximo del array, reemplázalo
        float maxValue = Float.MIN_VALUE;
        int maxIndex = -1;

        // Buscar el valor más alto en el array
        for (int i = 0; i < recordslvl2.length; i++) {
            if (recordslvl2[i] > maxValue) {
                maxValue = recordslvl2[i];
                maxIndex = i;
            }
        }

        // Si el nuevo valor es menor que el máximo, reemplazamos
        if (score < maxValue) {
            recordslvl2[maxIndex] = score;
        }

        // Ordenamos el array para mantener los valores en orden ascendente
        Arrays.sort(recordslvl2);
    }

    //Para obtener los records de tiempo en un formato para la label
    public String cogerRecord(int nivel) {
        String texto = "";

        texto = "NVL1:\n1:" + recordslvl1[0] + "\n2:" + recordslvl1[1] + "\n3:" + recordslvl1[2] + "\n4:"
                + recordslvl1[3]
                + "\n5:" + recordslvl1[4];

        texto += "\n\nNVL2:\n1:" + recordslvl2[0] + "\n2:" + recordslvl2[1] + "\n3:" + recordslvl2[2] + "\n4:"
                + recordslvl2[3]
                + "\n5:"
                + recordslvl2[4];
        texto = texto.replaceAll("99999\\.0", "--.--");
        return texto;
    }

}
