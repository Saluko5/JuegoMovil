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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

public class OptionsScreen implements Screen {
    private Main juego;
    private Viewport viewport;
    private Stage stage;

    private Texture backgroundTexture; // Añadido para la imagen de fondo
    private Image backgroundImage;

    // Creacion de boton volver
    private Texture TexturaBtnVolver;
    private TextureRegion BtnVolverRegion;
    private ImageButton BotonVolver;

    public OptionsScreen(Main juego) {
        this.juego = juego;

        viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) juego).batch);

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
                juego.setScreen(new MainMenu(juego));
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
        stage.dispose();
    }

}
