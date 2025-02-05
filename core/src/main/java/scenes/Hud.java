package scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

import screens.PlayScreen;

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    float puntuacion;

    Label levelLabel;
    Label protaLabel;

    public Hud(SpriteBatch sb, float puntuacion) {
        viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        this.puntuacion = puntuacion;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        levelLabel = new Label("A:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        // protaLabel = new Label("Prota1",new Label.LabelStyle(new
        // BitmapFont(),Color.WHITE));

        // table.add(protaLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);

        stage.addActor(table);

    }

    public void ActualizarPuntuacion(float puntuacion) {
        levelLabel = new Label("A:" + puntuacion, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
