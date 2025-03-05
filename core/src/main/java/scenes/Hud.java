package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;
    private BitmapFont font;

    public float tiempoTranscurrido; // Variable para almacenar el tiempo transcurrido
    private Label tiempoLabel; // Label para mostrar el tiempo

    // Constructor de la clase Hud
    public Hud(SpriteBatch sb) {
        viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        tiempoTranscurrido = 0; // Inicializamos el contador en 0

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.color = Color.WHITE;
        parameter.size = (int) Math.ceil(18); // para el tamaño
        parameter.magFilter = TextureFilter.Linear;
        parameter.minFilter = TextureFilter.MipMapLinearNearest;
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();

        LabelStyle estilo = new LabelStyle();
        estilo.font = font;

        // Label que muestra el tiempo
        tiempoLabel = new Label("T: 00:00:00", estilo);
        table.add(tiempoLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    // Método para actualizar el tiempo
    public void actualizarTiempo(float deltaTime) {
        tiempoTranscurrido += deltaTime; // Acumulamos el tiempo transcurrido

        // Convertimos el tiempo en minutos, segundos y milisegundos
        int minutos = (int) (tiempoTranscurrido / 60);
        int segundos = (int) (tiempoTranscurrido % 60);
        int milisegundos = (int) ((tiempoTranscurrido * 100) % 100); // Milisegundos

        // Formateamos el tiempo en formato mm:ss:SS
        String tiempoFormateado = String.format("%02d:%02d:%02d", minutos, segundos, milisegundos);

        // Actualizamos el label del tiempo
        tiempoLabel.setText("T: " + tiempoFormateado);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
