package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.pruebas.mijuego.Main;

import Sprites.ProtaFinal;

public class MainMenu implements Screen {

    Main juego;
    Texture botonJugar;
    TextureRegion fondo;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    TextureRegion fondomenu;

    public MainMenu(Main juego) {
        this.juego = juego;
        botonJugar = new Texture("plataformalvl1prueba.png");
        fondomenu = new TextureRegion(botonJugar, 50, 40, 90, 140);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        juego.batch.begin();

        juego.batch.draw(botonJugar, 0, 0);

        juego.batch.end();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
