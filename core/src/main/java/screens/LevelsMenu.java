package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

public class LevelsMenu implements Screen {

    private Viewport viewport;
    private Stage stage;

    private Texture backgroundTexture; // Añadido para la imagen de fondo
    private Image backgroundImage;

    // Creacion de boton lvl1
    private Texture TexturaBtnLvl1;
    private TextureRegion BtnLvl1Region;
    private ImageButton BotonLvl1;

    // Creacion de boton lvl2
    private Texture TexturaBtnLvl2;
    private TextureRegion BtnLvl2Region;
    private ImageButton BotonLvl2;

    // Creacion de boton volver
    private Texture TexturaBtnVolver;
    private TextureRegion BtnVolverRegion;
    private ImageButton BotonVolver;

    Game game;
    Main main;

    public LevelsMenu(Main game) {
        this.main = game;

        viewport = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        // ---
        backgroundTexture = new Texture(Gdx.files.internal("fondomenu.jpg")); // Ruta de la imagen de fondo
        backgroundImage = new Image(backgroundTexture); // Crear el objeto Image para el fondo
        backgroundImage.setSize(600, 800);
        backgroundImage.setPosition(0, 0);
        stage.addActor(backgroundImage); // Añadir la imagen al stage

        // Creacion del boton para jugar al lvl1
        TexturaBtnLvl1 = new Texture(Gdx.files.internal("lvl1.png"));
        BtnLvl1Region = new TextureRegion(TexturaBtnLvl1);

        ImageButton.ImageButtonStyle EstiloBtnLvl1 = new ImageButton.ImageButtonStyle();
        EstiloBtnLvl1.up = new TextureRegionDrawable(BtnLvl1Region);

        BotonLvl1 = new ImageButton(EstiloBtnLvl1);

        // Posicionar el botón en la pantalla
        BotonLvl1.setPosition(30, 250);
        BotonLvl1.setSize(150, 200);

        // Añadir un listener al botón para el evento de clic
        BotonLvl1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(main, 1));
                dispose();
            }
        });

        stage.addActor(BotonLvl1);

        // Creacion del boton para jugar al lvl2
        TexturaBtnLvl2 = new Texture(Gdx.files.internal("marcolvl2.png"));
        BtnLvl2Region = new TextureRegion(TexturaBtnLvl2);

        ImageButton.ImageButtonStyle EstiloBtnLvl2 = new ImageButton.ImageButtonStyle();
        EstiloBtnLvl2.up = new TextureRegionDrawable(BtnLvl2Region);

        BotonLvl2 = new ImageButton(EstiloBtnLvl2);

        // Posicionar el botón en la pantalla
        BotonLvl2.setPosition(200, 250);
        BotonLvl2.setSize(170, 200);

        // Añadir un listener al botón para el evento de clic
        BotonLvl2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(main, 2));
                dispose();
            }
        });

        stage.addActor(BotonLvl2);

        // Creacion del boton para volver al menu
        TexturaBtnVolver = new Texture(Gdx.files.internal("BotonVolver.png"));
        BtnVolverRegion = new TextureRegion(TexturaBtnVolver);

        ImageButton.ImageButtonStyle EstiloBtnVolver = new ImageButton.ImageButtonStyle();
        EstiloBtnVolver.up = new TextureRegionDrawable(BtnVolverRegion);

        BotonVolver = new ImageButton(EstiloBtnVolver);

        // Posicionar el botón en la pantalla
        BotonVolver.setPosition(0, 650);
        BotonVolver.setSize(50, 50);

        // Añadir un listener al botón para el evento de clic
        BotonVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });

        stage.addActor(BotonVolver);

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
    }
}
