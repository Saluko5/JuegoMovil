package com.pruebas.mijuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.LevelsMenu;
import screens.MainMenu;
import screens.OptionsScreen;
import screens.PlayScreen;

public class Main extends Game {

    public SpriteBatch batch;

    public static final int V_WIDTH = 400; // 400
    public static final int V_HEIGHT = 700; // la altura que quieras
    public static final short TIERRA_BIT = 1;
    public static final short PROTA_BIT = 2;
    public static final short PLATAFORMA_BIT = 4;
    public static final short LINEADENUBE_BIT = 8;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainMenu(this));
    }

    @Override
    public void render() {

        super.render();
    }
}
