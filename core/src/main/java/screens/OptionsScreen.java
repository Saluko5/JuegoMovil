package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

public class OptionsScreen implements Screen {
    private Game game;
    private Viewport viewport;
    private Stage stage;

    private Texture backgroundTexture; // Añadido para la imagen de fondo
    private Image backgroundImage;

    public OptionsScreen(Game game) {
        this.game = game;

        viewport = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);

        // Creo la imagen de fondo
        backgroundTexture = new Texture(Gdx.files.internal("plataformalvl1prueba.png")); // Ruta de la imagen de fondo
        backgroundImage = new Image(backgroundTexture); // Crear el objeto Image para el fondo
        backgroundImage.setSize(600, 800);
        backgroundImage.setPosition(0, 0);

        stage.addActor(backgroundImage); // Añadir la imagen al stage

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
