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

public class WinScreen implements Screen {

    private Viewport viewport;
    private Stage stage;

    private Texture backgroundTexture; // Añadido para la imagen de fondo
    private Image backgroundImage;

    // Para hacer boton menu
    private Texture TexturaBtnMenu;
    private TextButton BotonMenu;

    private BitmapFont font;

    // Creacion de boton siguiente nivel
    private Texture TexturaBtnNext;
    private TextButton BotonNext;

    private Music music;

    Main main;

    public WinScreen(Main main, int nivel) {
        this.main = main;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.color = Color.WHITE;
        parameter.size = (int) Math.ceil(28);
        parameter.magFilter = TextureFilter.Linear;
        parameter.minFilter = TextureFilter.MipMapLinearNearest;
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();

        viewport = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) main).batch);
        // ---
        backgroundTexture = new Texture(Gdx.files.internal("fondomenu.jpg")); // Ruta de la imagen de fondo
        backgroundImage = new Image(backgroundTexture); // Crear el objeto Image para el fondo
        backgroundImage.setSize(600, 800);
        backgroundImage.setPosition(0, 0);
        stage.addActor(backgroundImage); // Añadir la imagen al stage

        // Ahora hare el boton que me lleve al menu
        TexturaBtnMenu = new Texture(Gdx.files.internal("BtnDefault.png"));
        TextureRegionDrawable botonmenu = new TextureRegionDrawable(TexturaBtnMenu);

        TextButton.TextButtonStyle EstiloBtnMenu = new TextButton.TextButtonStyle();
        EstiloBtnMenu.font = font;
        EstiloBtnMenu.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        EstiloBtnMenu.up = botonmenu;

        BotonMenu = new TextButton("MENU", EstiloBtnMenu);

        // Posicionar el botón en la pantalla (en este caso en el centro)
        if (nivel == 1) {
            BotonMenu.setPosition(100, 200);
        } else {
            BotonMenu.setPosition(100, 300);
        }
        BotonMenu.setSize(200, 100);

        // Añadir un listener al botón para el evento de clic
        BotonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.dispose();
                main.setScreen(new MainMenu((Main) main));
                music.setVolume(main.volumen);
                dispose();
            }
        });

        stage.addActor(BotonMenu);

        // Creacion del boton para jugar al lvl2
        TexturaBtnNext = new Texture(Gdx.files.internal("BtnDefault.png"));
        TextureRegionDrawable botonnext = new TextureRegionDrawable(TexturaBtnNext);

        TextButton.TextButtonStyle EstiloBtnNext = new TextButton.TextButtonStyle();
        EstiloBtnNext.font = font;
        EstiloBtnNext.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        EstiloBtnNext.up = botonnext;

        BotonNext = new TextButton(LanguageManager.get("next"), EstiloBtnNext);

        // Posicionar el botón en la pantalla
        BotonNext.setPosition(100, 370);
        BotonNext.setSize(200, 100);
        if (nivel == 2) {
            BotonNext.setVisible(false);
            BotonNext.setDisabled(true);
        }

        // Añadir un listener al botón para el evento de clic
        BotonNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.dispose();
                main.setScreen(new PlayScreen(main, 2));
                dispose();
            }
        });

        stage.addActor(BotonNext);

        Gdx.input.setInputProcessor(stage);

        music = Main.manager.get("music/MusicaVictoria.mp3", Music.class);
        music.setLooping(false);
        music.setVolume(main.volumen);
        music.play();

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        //Liempieza de pantalla
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
