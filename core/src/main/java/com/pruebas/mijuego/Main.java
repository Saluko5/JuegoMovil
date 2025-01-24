package com.pruebas.mijuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.PlayScreen;


public class Main extends Game{

    public SpriteBatch batch;

    public static final int V_WIDTH = 400; //400
    public static final int V_HEIGHT = 700; //la altura que quieras

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
    }

    @Override
    public void render() {

        super.render();
    }
}
