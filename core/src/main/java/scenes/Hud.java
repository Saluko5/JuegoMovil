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

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    Label levelLabel;
    Label protaLabel;


    public Hud(SpriteBatch sb){
        viewport = new FitViewport(Main.V_WIDTH,Main.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        levelLabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //protaLabel = new Label("Prota1",new Label.LabelStyle(new BitmapFont(),Color.WHITE));

        //table.add(protaLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);

        stage.addActor(table);



    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}

