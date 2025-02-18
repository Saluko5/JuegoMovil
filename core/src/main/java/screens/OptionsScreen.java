package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

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

    // Para hacer boton volumen
    private Texture TexturaBtnVolumen;
    private TextureRegion BtnVolumenRegion;
    private ImageButton BotonVolumen;

    private Slider slider;
    // Para la musica
    private Music music;
    private Skin skin;

    // Para hacer boton tutorial
    private Texture TexturaBtnTutorial;
    private TextureRegion BtnTutorialRegion;
    private ImageButton BotonTutorial;

    // Para hacer boton anterior
    private Texture TexturaBtnAnterior;
    private TextureRegion BtnAnteriorRegion;
    private ImageButton BotonAnterior;

    // Para hacer boton siguiente
    private Texture TexturaBtnSiguiente;
    private TextureRegion BtnSiguienteRegion;
    private ImageButton BotonSiguiente;

    // Para hacer boton cerrarTuto
    private Texture TexturaBtnCerrar;
    private TextureRegion BtnCerrarRegion;
    private ImageButton BotonCerrar;

    // Imagen Tutorial1
    private Texture TexturaTuto1;
    private TextureRegion Tutorial1Region;
    Image Tutorial1;

    // Imagen Tutorial2
    private Texture TexturaTuto2;
    private TextureRegion Tutorial2Region;
    Image Tutorial2;

    public OptionsScreen(Main main) {
        this.main = main;

        viewport = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) main).batch);

        // Creo la imagen de fondo
        backgroundTexture = new Texture(Gdx.files.internal("fondomenu.jpg")); // Ruta de la imagen de fondo
        backgroundImage = new Image(backgroundTexture); // Crear el objeto Image para el fondo
        backgroundImage.setSize(600, 800);
        backgroundImage.setPosition(0, 0);

        stage.addActor(backgroundImage); // Añadir la imagen al stage

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

        // Ahora hare el boton que me lleve al volumen
        TexturaBtnVolumen = new Texture(Gdx.files.internal("BtnVolumen.png"));
        BtnVolumenRegion = new TextureRegion(TexturaBtnVolumen);

        ImageButton.ImageButtonStyle EstiloBtnVolumen = new ImageButton.ImageButtonStyle();
        EstiloBtnVolumen.up = new TextureRegionDrawable(BtnVolumenRegion);

        BotonVolumen = new ImageButton(EstiloBtnVolumen);

        // Posicionar el botón en la pantalla (en este caso en el centro)

        BotonVolumen.setPosition(100, 300);
        BotonVolumen.setSize(200, 100);

        // Añadir un listener al botón para el evento de clic
        BotonVolumen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        stage.addActor(BotonVolumen);

        // Para añadir el slider del volumen
        // skin = new Skin("BtnVolumen.png");
        // slider = new Slider(0f, 1f, 0.01f, false);

        Gdx.input.setInputProcessor(stage);

        // Creacion del boton para tutorial
        TexturaBtnTutorial = new Texture(Gdx.files.internal("BotonTutorial.png"));
        BtnTutorialRegion = new TextureRegion(TexturaBtnTutorial);

        ImageButton.ImageButtonStyle EstiloBtnTutorial = new ImageButton.ImageButtonStyle();
        EstiloBtnTutorial.up = new TextureRegionDrawable(BtnTutorialRegion);

        BotonTutorial = new ImageButton(EstiloBtnTutorial);

        // Posicionar el botón en la pantalla
        BotonTutorial.setPosition(100, 175);
        BotonTutorial.setSize(200, 100);

        // Añadir un listener al botón para el evento de clic
        BotonTutorial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Tutorial1.setVisible(true);

                BotonSiguiente.setDisabled(false);
                BotonSiguiente.setVisible(true);

            }
        });

        stage.addActor(BotonTutorial);

        // Creacion del boton para anterior
        TexturaBtnAnterior = new Texture(Gdx.files.internal("BotonBtn.png"));
        BtnAnteriorRegion = new TextureRegion(TexturaBtnAnterior);

        ImageButton.ImageButtonStyle EstiloBtnAnterior = new ImageButton.ImageButtonStyle();
        EstiloBtnAnterior.up = new TextureRegionDrawable(BtnAnteriorRegion);

        BotonAnterior = new ImageButton(EstiloBtnAnterior);

        // Posicionar el botón en la pantalla
        BotonAnterior.setPosition(25, 300);
        BotonAnterior.setSize(50, 50);

        BotonAnterior.setVisible(false);
        BotonAnterior.setDisabled(true);

        // Añadir un listener al botón para el evento de clic
        BotonAnterior.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                BotonSiguiente.setDisabled(false);
                BotonSiguiente.setVisible(true);

                BotonAnterior.setDisabled(true);
                BotonAnterior.setVisible(false);

                BotonCerrar.setDisabled(true);
                BotonCerrar.setVisible(false);

                Tutorial1.setVisible(true);
                Tutorial2.setVisible(false);

            }
        });

        stage.addActor(BotonAnterior);

        // Creacion del boton para anterior
        TexturaBtnSiguiente = new Texture(Gdx.files.internal("BotonSiguiente.png"));
        BtnSiguienteRegion = new TextureRegion(TexturaBtnSiguiente);

        ImageButton.ImageButtonStyle EstiloBtnSiguiente = new ImageButton.ImageButtonStyle();
        EstiloBtnSiguiente.up = new TextureRegionDrawable(BtnSiguienteRegion);

        BotonSiguiente = new ImageButton(EstiloBtnSiguiente);

        // Posicionar el botón en la pantalla
        BotonSiguiente.setPosition(325, 300);
        BotonSiguiente.setSize(50, 50);

        BotonSiguiente.setVisible(false);
        BotonSiguiente.setDisabled(true);

        // Añadir un listener al botón para el evento de clic
        BotonSiguiente.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                BotonAnterior.setDisabled(false);
                BotonAnterior.setVisible(true);

                BotonSiguiente.setDisabled(true);
                BotonSiguiente.setVisible(false);

                BotonCerrar.setDisabled(false);
                BotonCerrar.setVisible(true);

                Tutorial1.setVisible(false);
                Tutorial2.setVisible(true);

            }
        });

        stage.addActor(BotonSiguiente);

        // Creacion del boton para volver al menu
        TexturaBtnCerrar = new Texture(Gdx.files.internal("BotonCerrar.png"));
        BtnCerrarRegion = new TextureRegion(TexturaBtnCerrar);

        ImageButton.ImageButtonStyle EstiloBtnCerrar = new ImageButton.ImageButtonStyle();
        EstiloBtnCerrar.up = new TextureRegionDrawable(BtnCerrarRegion);

        BotonCerrar = new ImageButton(EstiloBtnCerrar);

        // Posicionar el botón en la pantalla
        BotonCerrar.setPosition(320, 555);
        BotonCerrar.setSize(50, 50);

        BotonCerrar.setDisabled(true);
        BotonCerrar.setVisible(false);

        // Añadir un listener al botón para el evento de clic
        BotonCerrar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Tutorial1.setVisible(false);
                Tutorial2.setVisible(false);

                BotonAnterior.setDisabled(true);
                BotonAnterior.setVisible(false);

                BotonSiguiente.setDisabled(true);
                BotonSiguiente.setVisible(false);

                BotonCerrar.setDisabled(true);
                BotonCerrar.setVisible(false);
            }
        });

        stage.addActor(BotonCerrar);

        // Creacion de la imagen del tutorial 1
        TexturaTuto1 = new Texture(Gdx.files.internal("imagentuto1.png"));
        Tutorial1Region = new TextureRegion(TexturaTuto1);

        ImageButton.ImageButtonStyle EstiloTutorial = new ImageButton.ImageButtonStyle();
        EstiloTutorial.up = new TextureRegionDrawable(Tutorial1Region);
        Tutorial1 = new Image(TexturaTuto1);

        Tutorial1.setSize(560, 750);
        Tutorial1.setPosition(-75, 0);

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

        music = Main.manager.get("music/CancionSettings.mp3", Music.class);
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
