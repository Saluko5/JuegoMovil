package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

import Manager.LanguageManager;
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

    // Para la fuente
    private BitmapFont font;
    private BitmapFont font2;

    // Creacion de boton jugar
    private Texture TexturaBtnJugar;
    private TextButton BotonJugar;

    // Creacion del boton options
    private Texture TexturaBtnOptions;
    private TextButton BotonOptions;

    // Creacion del boton Salir
    private Texture TexturaBtnExit;
    private TextButton BotonExit;

    private Texture TexturaTitulo;
    private TextureRegion TituloRegion;

    // Para la musica del menu
    private Music music;

    public MainMenu(Main juego) {
        this.juego = juego;

        //Se crea la fuente para las labels
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.color = Color.WHITE;
        parameter.size = (int) Math.ceil(40);
        parameter.magFilter = TextureFilter.Linear;
        parameter.minFilter = TextureFilter.MipMapLinearNearest;
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();

        viewport = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) juego).batch);

        // Creacion del fondo del menu
        backgroundTexture = new Texture(Gdx.files.internal("fondomenu.jpg")); // Ruta de la imagen de fondo
        backgroundImage = new Image(backgroundTexture); // Crear el objeto Image para el fondo
        backgroundImage.setSize(600, 800);
        backgroundImage.setPosition(0, 0);
        stage.addActor(backgroundImage); // Añadir la imagen al stage

        // Creacion del boton para jugar
        TexturaBtnJugar = new Texture(Gdx.files.internal("BtnDefault.png"));
        TextureRegionDrawable botonjugar = new TextureRegionDrawable(TexturaBtnJugar);

        TextButton.TextButtonStyle EstiloBtnJugar = new TextButton.TextButtonStyle();
        EstiloBtnJugar.font = font;
        EstiloBtnJugar.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        EstiloBtnJugar.up = botonjugar;

        BotonJugar = new TextButton(LanguageManager.get("play"), EstiloBtnJugar);

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
        // Creacion del boton para jugar
        TexturaBtnOptions = new Texture(Gdx.files.internal("BtnDefault.png"));
        TextureRegionDrawable botonoptions = new TextureRegionDrawable(TexturaBtnOptions);

        TextButton.TextButtonStyle EstiloBtnOptions = new TextButton.TextButtonStyle();
        EstiloBtnOptions.font = font;
        EstiloBtnOptions.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        EstiloBtnOptions.up = botonoptions;

        BotonOptions = new TextButton(LanguageManager.get("options"), EstiloBtnJugar);

        // Posicionar el botón en la pantalla
        BotonOptions.setPosition(100, 240);
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
        TexturaBtnExit = new Texture("BtnDefault.png");
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(TexturaBtnExit);

        // Crear un TextButton con el texto
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        // Asignar la imagen de fondo
        style.up = buttonDrawable; // Asignar el fondo del botón

        // Asignar el texto y la fuente
        style.font = font;
        style.fontColor = com.badlogic.gdx.graphics.Color.WHITE;

        // Crear el botón con texto
        BotonExit = new TextButton(LanguageManager.get("exit"), style);
        BotonExit.setSize(200, 110); // Tamaño del botón

        // Establecer la posición del botón
        BotonExit.setPosition(100, 100);

        // Agregar un escuchador para manejar clics
        BotonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Agregar el botón al stage
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

        //Se pone la musica
        music = Main.manager.get("music/CancionMenu.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(Main.volumen);
        music.play();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        //Limpieza de dibujo
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
