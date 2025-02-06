package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

import Sprites.ProtaFinal;

public class GameOverScreen implements Screen {

    private Viewport viewport;
    private Stage stage;
    private Texture buttonTexture;
    private TextureRegion buttonRegion;
    private ImageButton BotonReinicio;

    private Texture TexturaBtnMenu;
    private TextureRegion BtnMenuRegion;
    private ImageButton BotonMenu;
    private Game game;
    public boolean inicio = false;
    PlayScreen screen;

    private Texture backgroundTexture; // Añadido para la imagen de fondo
    private Image backgroundImage;

    public GameOverScreen(Game game, PlayScreen screen) {
        this.game = game;

        this.screen = screen;
        viewport = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        // ---
        backgroundTexture = new Texture(Gdx.files.internal("plataformalvl1prueba.png")); // Ruta de la imagen de fondo
        backgroundImage = new Image(backgroundTexture); // Crear el objeto Image para el fondo
        backgroundImage.setSize(600, 800);
        backgroundImage.setPosition(0, 0);
        stage.addActor(backgroundImage); // Añadir la imagen al stage

        // Boton de reinicio
        buttonTexture = new Texture(Gdx.files.internal("plataformalvl1prueba.png"));
        buttonRegion = new TextureRegion(buttonTexture);

        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(buttonRegion);

        BotonReinicio = new ImageButton(buttonStyle);

        // Posicionar el botón en la pantalla (en este caso en el centro)
        BotonReinicio.setPosition(200 - buttonRegion.getRegionWidth() / 2, 500 - buttonRegion.getRegionHeight() / 2);

        // Añadir un listener al botón para el evento de clic
        BotonReinicio.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen((Main) game));
                dispose();
            }
        });

        stage.addActor(BotonReinicio);
        // -----------------------------------------------------------
        // Ahora hare el boton que me lleve al menu
        TexturaBtnMenu = new Texture(Gdx.files.internal("plataformalvl1prueba.png"));
        BtnMenuRegion = new TextureRegion(TexturaBtnMenu);

        ImageButton.ImageButtonStyle EstiloBtnMenu = new ImageButton.ImageButtonStyle();
        EstiloBtnMenu.up = new TextureRegionDrawable(BtnMenuRegion);

        BotonMenu = new ImageButton(EstiloBtnMenu);

        // Posicionar el botón en la pantalla (en este caso en el centro)
        BotonMenu.setPosition(200 - BtnMenuRegion.getRegionWidth() / 2, 250 - BtnMenuRegion.getRegionHeight() / 2);

        // Añadir un listener al botón para el evento de clic
        BotonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // game.setScreen(new PlayScreen((Main) game));
               // dispose();
               System.out.println("Menu");
            }
        });

        stage.addActor(BotonMenu);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
        stage.dispose();
    }

}
