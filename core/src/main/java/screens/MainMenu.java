package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

import Sprites.ProtaFinal;

public class MainMenu implements Screen {

    private Viewport viewport;
    private Stage stage;
    Main juego;
    Texture botonJugar;
    TextureRegion fondo;
    private boolean jugar = false;

    private Texture backgroundTexture; // Añadido para la imagen de fondo
    private Image backgroundImage;

    // Creacion de boton jugar
    private Texture TexturaBtnJugar;
    private TextureRegion BtnJugarRegion;
    private ImageButton BotonJugar;

    // Creacion del boton options
    private Texture TexturaBtnOptions;
    private TextureRegion BtnOptionsRegion;
    private ImageButton BotonOptions;

    // Creacion del boton Salir
    private Texture TexturaBtnExit;
    private TextureRegion BtnExitRegion;
    private ImageButton BotonExit;

    private Texture TexturaTitulo;
    private TextureRegion TituloRegion;

    // Para la musica del menu
    private Music music;

    public MainMenu(Main juego) {
        this.juego = juego;

        viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) juego).batch);

        // Creacion del fondo del menu
        backgroundTexture = new Texture(Gdx.files.internal("fondomenu.jpg")); // Ruta de la imagen de fondo
        backgroundImage = new Image(backgroundTexture); // Crear el objeto Image para el fondo
        backgroundImage.setSize(600, 800);
        backgroundImage.setPosition(0, 0);
        stage.addActor(backgroundImage); // Añadir la imagen al stage

        // Creacion del boton para jugar
        TexturaBtnJugar = new Texture(Gdx.files.internal("BotonJugar.png"));
        BtnJugarRegion = new TextureRegion(TexturaBtnJugar);

        ImageButton.ImageButtonStyle EstiloBtnJugar = new ImageButton.ImageButtonStyle();
        EstiloBtnJugar.up = new TextureRegionDrawable(BtnJugarRegion);

        BotonJugar = new ImageButton(EstiloBtnJugar);

        // Posicionar el botón en la pantalla
        BotonJugar.setPosition(100, 370);
        BotonJugar.setSize(200, 100);

        // Añadir un listener al botón para el evento de clic
        BotonJugar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.stop();
                juego.setScreen(new LevelsMenu(juego));
                dispose();
            }
        });

        stage.addActor(BotonJugar);

        // Creacion del boton options
        TexturaBtnOptions = new Texture(Gdx.files.internal("BotonOptions.png"));
        BtnOptionsRegion = new TextureRegion(TexturaBtnOptions);

        ImageButton.ImageButtonStyle EstiloBtnOptions = new ImageButton.ImageButtonStyle();
        EstiloBtnOptions.up = new TextureRegionDrawable(BtnOptionsRegion);

        BotonOptions = new ImageButton(EstiloBtnOptions);

        // Posicionar el botón en la pantalla
        BotonOptions.setPosition(100, 250);
        BotonOptions.setSize(200, 100);

        // Añadir un listener al botón para el evento de clic
        BotonOptions.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.stop();
                juego.setScreen(new OptionsScreen(juego));
                dispose();
            }
        });

        stage.addActor(BotonOptions);

        // Creacion del boton salir
        TexturaBtnExit = new Texture(Gdx.files.internal("BotonExit.png"));
        BtnExitRegion = new TextureRegion(TexturaBtnExit);

        ImageButton.ImageButtonStyle EstiloBtnExit = new ImageButton.ImageButtonStyle();
        EstiloBtnExit.up = new TextureRegionDrawable(BtnExitRegion);

        BotonExit = new ImageButton(EstiloBtnExit);

        // Posicionar el botón en la pantalla
        BotonExit.setPosition(100, 120);
        BotonExit.setSize(200, 100);

        // Añadir un listener al botón para el evento de clic
        BotonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(BotonExit);

        // Creacion del boton para jugar
        TexturaTitulo = new Texture(Gdx.files.internal("tituloJuego.png"));
        TituloRegion = new TextureRegion(TexturaTitulo);

        ImageButton.ImageButtonStyle EstiloTitutlo = new ImageButton.ImageButtonStyle();
        EstiloTitutlo.up = new TextureRegionDrawable(TituloRegion);
        Image titulo = new Image(TexturaTitulo);

        titulo.setSize(350, 150);
        titulo.setPosition(25, 500);

        stage.addActor(titulo);

        // Textura del titulo del juego

        Gdx.input.setInputProcessor(stage);

        music = Main.manager.get("music/CancionMenu.mp3", Music.class);
        music.setLooping(true);
        music.play();
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
