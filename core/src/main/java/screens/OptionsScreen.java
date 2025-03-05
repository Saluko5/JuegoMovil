package screens;

import org.w3c.dom.Text;

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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

import Manager.LanguageManager;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class OptionsScreen implements Screen {
    private Main main;
    private Viewport viewport;
    private Stage stage;

    private Texture backgroundTexture; // Añadido para la imagen de fondo
    private Image backgroundImage;

    // Creacion de boton volver
    private Texture TexturaBtnVolver;
    private TextureRegion BtnVolverRegion;
    private ImageButton BotonVolver;

    // Para la fuente
    private BitmapFont font;
    private BitmapFont font2;

    // Para la musica
    private Music music;

    // Creacion del componente para hacer scroll de tutorial
    private ScrollPane scroll;

    // Para hacer el boton creditos
    private Texture TexturaBtnCreditos;
    private TextButton BotonCreditos;

    // Para hacer la label de los creditos
    private Label lblCreditos;

    // Para hacer la label de los records
    private Label lblRecords;

    // Para hacer boton ingles
    private Texture TexturaBtnEng;
    private TextButton BotonEng;

    private Texture TexturaBtnRecord;
    private TextButton BotonRecord;

    // Para hacer boton español
    private Texture TexturaBtnEsp;
    private TextButton BotonEsp;

    // Para hacer boton tutorial
    private Texture TexturaBtnTutorial;
    private TextButton BotonTutorial;

    // Label para texto de musica
    private Label lblMusica;

    // Para hacer boton cerrarTuto
    private Texture TexturaBtnCerrar;
    private TextureRegion BtnCerrarRegion;
    private ImageButton BotonCerrar;

    // Para hacer boton muteo
    private Texture TexturaBtnMute;
    private TextureRegion BtnMuteRegion;
    private ImageButton BotonMute;

    // Imagen Tutorial1
    private Texture TexturaTuto1;
    private TextureRegion Tutorial1Region;
    Image Tutorial1;

    // Imagen Tutorial2
    private Texture TexturaTuto2;
    private TextureRegion Tutorial2Region;
    Image Tutorial2;

    Label lblTutorial;

    Texture fondoslider = new Texture(Gdx.files.internal("fondoslider.png"));
    Texture botonslider = new Texture(Gdx.files.internal("botonslider2.png"));

    public OptionsScreen(Main main) {
        this.main = main;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.color = Color.WHITE;
        parameter.size = (int) Math.ceil(24); // para el tamaño
        parameter.magFilter = TextureFilter.Linear;
        parameter.minFilter = TextureFilter.MipMapLinearNearest;
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();

        // Para la fuente2
        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
        parameter2.genMipMaps = true;
        parameter2.color = Color.WHITE;
        parameter2.size = (int) Math.ceil(16); // para el tamaño
        parameter2.magFilter = TextureFilter.Linear;
        parameter2.minFilter = TextureFilter.MipMapLinearNearest;
        font2 = generator2.generateFont(parameter2); // font size 12 pixels

        viewport = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) main).batch);

        // Creo la imagen de fondo
        backgroundTexture = new Texture(Gdx.files.internal("fondomenu.jpg")); // Ruta de la imagen de fondo
        backgroundImage = new Image(backgroundTexture); // Crear el objeto Image para el fondo
        backgroundImage.setSize(600, 800);
        backgroundImage.setPosition(0, 0);

        stage.addActor(backgroundImage); // Añadir la imagen al stage

        // Redimensionar las imágenes si es necesario
        Image imagenfondoslider = new Image(fondoslider);
        imagenfondoslider.setSize(6, 5); // Ajustar el tamaño del fondo (slider)

        // Redimensionar el thumb
        Image imagenbotonslider = new Image(botonslider);
        imagenbotonslider.setSize(60, 30); // Ajustar el tamaño del thumb

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();

        // Usar las imágenes redimensionadas para el fondo y el thumb
        sliderStyle.background = imagenfondoslider.getDrawable(); // Fondo del slider
        sliderStyle.knob = imagenbotonslider.getDrawable(); // Thumb (botón)

        Slider slider = new Slider(0, 100, 1, false, sliderStyle); // Crear el slider
        slider.setValue(50); // Establecer un valor inicial
        slider.setSize(150, 50);
        slider.setPosition(125, 510);
        slider.setVisible(true);
        slider.setDisabled(false);

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!main.mute) {
                    main.volumen = (slider.getValue() / 100);
                    music.setVolume(main.volumen);
                } else {
                    main.volumenanterior = (slider.getValue() / 100);
                }
            }
        });

        stage.addActor(slider);

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

                music.stop();

                main.setScreen(new MainMenu(main));
                dispose();
            }
        });

        stage.addActor(BotonVolver);

        LabelStyle estilolabel = new LabelStyle();
        estilolabel.font = font;
        estilolabel.fontColor = com.badlogic.gdx.graphics.Color.BLACK;

        lblMusica = new Label(LanguageManager.get("music"), estilolabel);
        lblMusica.setPosition(40, 480);
        lblMusica.setSize(100, 100);

        stage.addActor(lblMusica);

        Gdx.input.setInputProcessor(stage);

        // Creacion del boton para tutorial
        TexturaBtnTutorial = new Texture(Gdx.files.internal("BtnDefault.png"));
        TextureRegionDrawable botontuto = new TextureRegionDrawable(TexturaBtnTutorial);

        TextButton.TextButtonStyle EstilBtnTuto = new TextButton.TextButtonStyle();
        EstilBtnTuto.font = font;
        EstilBtnTuto.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        EstilBtnTuto.up = botontuto;

        BotonTutorial = new TextButton("TUTORIAL", EstilBtnTuto);

        // Posicionar el botón en la pantalla
        BotonTutorial.setPosition(100, 170);
        BotonTutorial.setSize(200, 100);

        // Añadir un listener al botón para el evento de clic
        BotonTutorial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                BotonCerrar.setDisabled(false);
                BotonCerrar.setVisible(true);
                Tutorial1.setVisible(true);
                BotonEsp.setVisible(false);
                BotonEng.setVisible(false);
                lblMusica.setVisible(false);
                lblTutorial.setVisible(true);
                lblTutorial.toFront();

                BotonRecord.setVisible(false);
                BotonCreditos.setVisible(false);
                BotonVolver.setVisible(false);
                scroll.setVisible(true);
                scroll.toFront();

            }
        });

        stage.addActor(BotonTutorial);

        // Creacion de la label para el tutorial
        lblTutorial = new Label(LanguageManager.get("info"), estilolabel);
        lblTutorial.setPosition(50, 220);
        lblTutorial.setSize(500, 500);
        lblTutorial.setAlignment(Align.center);

        lblTutorial.setVisible(false);

        stage.addActor(lblTutorial);

        // Creacion del boton para volver al menu
        TexturaBtnCerrar = new Texture(Gdx.files.internal("BotonCerrar.png"));
        BtnCerrarRegion = new TextureRegion(TexturaBtnCerrar);

        ImageButton.ImageButtonStyle EstiloBtnCerrar = new ImageButton.ImageButtonStyle();
        EstiloBtnCerrar.up = new TextureRegionDrawable(BtnCerrarRegion);

        BotonCerrar = new ImageButton(EstiloBtnCerrar);

        // Posicionar el botón en la pantalla
        BotonCerrar.setPosition(350, 555);
        BotonCerrar.setSize(50, 50);

        BotonCerrar.setDisabled(true);
        BotonCerrar.setVisible(false);

        // Añadir un listener al botón para el evento de clic
        BotonCerrar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Tutorial1.setVisible(false);
                Tutorial2.setVisible(false);

                lblTutorial.setVisible(false);
                lblMusica.setVisible(true);
                BotonEsp.setVisible(true);
                BotonEng.setVisible(true);

                BotonCerrar.setDisabled(true);
                BotonCerrar.setVisible(false);

                BotonRecord.setVisible(true);
                BotonCreditos.setVisible(true);
                lblRecords.setVisible(false);
                lblCreditos.setVisible(false);

                BotonVolver.setVisible(true);
                scroll.setVisible(false);
            }
        });

        stage.addActor(BotonCerrar);

        // Creacion del boton mute
        if (main.mute) {
            TexturaBtnMute = new Texture(Gdx.files.internal("botonmuteon.png"));
        } else {
            TexturaBtnMute = new Texture(Gdx.files.internal("botonmuteoff.png"));
        }
        BtnMuteRegion = new TextureRegion(TexturaBtnMute);

        ImageButton.ImageButtonStyle EstiloBtnMute = new ImageButton.ImageButtonStyle();
        EstiloBtnMute.up = new TextureRegionDrawable(BtnMuteRegion);

        BotonMute = new ImageButton(EstiloBtnMute);

        // Posicionar el botón en la pantalla
        BotonMute.setPosition(280, 520);
        BotonMute.setSize(50, 50);

        // Añadir un listener al botón para el evento de clic
        BotonMute.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (main.mute) {
                    TexturaBtnMute = new Texture(Gdx.files.internal("botonmuteoff.png"));
                    BtnMuteRegion = new TextureRegion(TexturaBtnMute);
                    EstiloBtnMute.up = new TextureRegionDrawable(BtnMuteRegion);
                    main.mute = false;
                    main.volumen = main.volumenanterior;
                    music.setVolume(main.volumen);
                } else {
                    TexturaBtnMute = new Texture(Gdx.files.internal("botonmuteon.png"));
                    BtnMuteRegion = new TextureRegion(TexturaBtnMute);
                    EstiloBtnMute.up = new TextureRegionDrawable(BtnMuteRegion);
                    main.mute = true;
                    main.volumenanterior = main.volumen;
                    main.volumen = 0;
                    music.setVolume(0);
                }
            }
        });

        stage.addActor(BotonMute);

        // Creacion de la imagen del tutorial 1
        TexturaTuto1 = new Texture(Gdx.files.internal("fondotuto.png"));
        Tutorial1Region = new TextureRegion(TexturaTuto1);

        ImageButton.ImageButtonStyle EstiloTutorial = new ImageButton.ImageButtonStyle();
        EstiloTutorial.up = new TextureRegionDrawable(Tutorial1Region);
        Tutorial1 = new Image(TexturaTuto1);

        Tutorial1.setSize(320, 620);
        Tutorial1.setPosition(40, 20);

        Tutorial1.setVisible(false);

        stage.addActor(Tutorial1);

        // Creacion de la imagen del tutorial 2
        TexturaTuto2 = new Texture(Gdx.files.internal("tituloJuego.png"));
        Tutorial2Region = new TextureRegion(TexturaTuto2);

        ImageButton.ImageButtonStyle EstiloTutorial2 = new ImageButton.ImageButtonStyle();
        EstiloTutorial2.up = new TextureRegionDrawable(Tutorial2Region);
        Tutorial2 = new Image(TexturaTuto2);

        Tutorial2.setSize(260, 550);
        Tutorial2.setPosition(70, 75);

        Tutorial2.setVisible(false);

        stage.addActor(Tutorial2);

        // Creacion del boton para cambiar idioma a ingles
        TexturaBtnEng = new Texture(Gdx.files.internal("BtnDefault.png"));
        TextureRegionDrawable botoneng = new TextureRegionDrawable(TexturaBtnEng);

        TextButton.TextButtonStyle EstiloBtnEng = new TextButton.TextButtonStyle();
        EstiloBtnEng.font = font;
        EstiloBtnEng.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        EstiloBtnEng.up = botoneng;

        BotonEng = new TextButton(LanguageManager.get("english"), EstiloBtnEng);

        // Posicionar el botón en la pantalla
        BotonEng.setPosition(220, 70);
        BotonEng.setSize(140, 80);

        // Añadir un listener al botón para el evento de clic
        BotonEng.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LanguageManager.setLanguage("en");
                main.setScreen(new OptionsScreen(main));
                dispose();
            }
        });

        stage.addActor(BotonEng);

        TexturaBtnEsp = new Texture(Gdx.files.internal("BtnDefault.png"));
        TextureRegionDrawable botonesp = new TextureRegionDrawable(TexturaBtnEsp);

        TextButton.TextButtonStyle EstiloBtnEsp = new TextButton.TextButtonStyle();
        EstiloBtnEsp.font = font;
        EstiloBtnEsp.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        EstiloBtnEsp.up = botonesp;

        BotonEsp = new TextButton(LanguageManager.get("spanish"), EstiloBtnEsp);

        // Posicionar el botón en la pantalla
        BotonEsp.setPosition(40, 70);
        BotonEsp.setSize(140, 80);

        // Añadir un listener al botón para el evento de clic
        BotonEsp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LanguageManager.setLanguage("es");
                main.setScreen(new OptionsScreen(main));
                dispose();
            }
        });

        stage.addActor(BotonEsp);

        // Creacion de la label para el records

        lblRecords = new Label(main.cogerRecord(0), estilolabel);
        lblRecords.setPosition(50, 220);
        lblRecords.setSize(300, 300);
        lblRecords.setAlignment(Align.center);

        lblRecords.setVisible(false);

        stage.addActor(lblRecords);

        // Creacion del boton de los records
        TexturaBtnRecord = new Texture(Gdx.files.internal("BtnDefault.png"));
        TextureRegionDrawable botonrecord = new TextureRegionDrawable(TexturaBtnRecord);

        TextButton.TextButtonStyle EstiloBtnRecord = new TextButton.TextButtonStyle();
        EstiloBtnRecord.font = font;
        EstiloBtnRecord.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        EstiloBtnRecord.up = botonrecord;

        BotonRecord = new TextButton("RECORD", EstiloBtnRecord);

        // Posicionar el botón en la pantalla
        BotonRecord.setPosition(100, 410);
        BotonRecord.setSize(200, 100);

        // Añadir un listener al botón para el evento de clic
        BotonRecord.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Tutorial1.setVisible(true);

                BotonCerrar.setDisabled(false);
                BotonCerrar.setVisible(true);

                BotonRecord.setDisabled(true);
                BotonRecord.setVisible(false);
                BotonEng.setVisible(false);
                BotonEsp.setVisible(false);
                BotonCreditos.setVisible(false);

                lblRecords.setVisible(true);
                BotonVolver.setVisible(false);
            }
        });

        stage.addActor(BotonRecord);

        // Creacion de la label para el creditos

        LabelStyle estilolabelcred = new LabelStyle();
        estilolabelcred.font = font2;
        estilolabelcred.fontColor = com.badlogic.gdx.graphics.Color.BLACK;

        lblCreditos = new Label(LanguageManager.get("infocred"), estilolabelcred);
        lblCreditos.setPosition(50, 260);
        lblCreditos.setSize(300, 300);

        lblCreditos.setVisible(false);

        stage.addActor(lblCreditos);

        // Creaacion del boton para el creditos
        TexturaBtnCreditos = new Texture(Gdx.files.internal("BtnDefault.png"));

        TextureRegionDrawable botoncreditos = new TextureRegionDrawable(TexturaBtnCreditos);

        TextButton.TextButtonStyle EstiloBtnCreditos = new TextButton.TextButtonStyle();
        EstiloBtnCreditos.font = font;
        EstiloBtnCreditos.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        EstiloBtnCreditos.up = botoncreditos;

        BotonCreditos = new TextButton(LanguageManager.get("credits"), EstiloBtnCreditos);
        // Posicionar el botón en la pantalla (en este caso en el centro)
        BotonCreditos.setPosition(100, 290);
        BotonCreditos.setSize(200, 100);

        // Añadir un listener al botón para el evento de clic
        BotonCreditos.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Tutorial1.setVisible(true);

                BotonCerrar.setDisabled(false);
                BotonCerrar.setVisible(true);

                BotonRecord.setDisabled(true);
                BotonRecord.setVisible(false);
                BotonEng.setVisible(false);
                BotonEsp.setVisible(false);

                BotonCreditos.setDisabled(true);
                BotonCreditos.setVisible(false);

                lblCreditos.setVisible(true);

                BotonVolver.setVisible(false);
            }
        });
        stage.addActor(BotonCreditos);

        // Creacion del scrollPane
        scroll = new ScrollPane(lblTutorial);
        scroll.setPosition(50, 50);
        scroll.setSize(300, 550);
        scroll.setVisible(false);

        stage.addActor(scroll);

        music = Main.manager.get("music/CancionSettings.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(main.volumen);
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
