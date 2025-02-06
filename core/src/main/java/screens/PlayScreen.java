package screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pruebas.mijuego.Main;

import Sprites.PlataformaNormal;
import Sprites.PlataformaNube;
import Sprites.ProtaFinal;

import scenes.Hud;
import tools.B2WorldCreator;
import tools.WorldContactListener;

public class PlayScreen implements Screen {

    private Main juego;
    private Hud hud;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private boolean muerto = false;
    public boolean inicio = false;

    // private GameOverScreen gameover;

    // Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    public ProtaFinal prota;
    private float altidudMax = 350;
    public float altitudMin = 0;
    private Random rdm = new Random();

    private TextureAtlas atlas;

    private WorldContactListener contacto;
    private ArrayList<PlataformaNormal> plataformas = new ArrayList<>();
    private ArrayList<PlataformaNube> plataformasnube = new ArrayList<>();

    // Constructor
    public PlayScreen(Main juego) {

        atlas = new TextureAtlas("mario.atlas");

        this.juego = juego;

        // Crea la camara q sigue al marciano
        gamecam = new OrthographicCamera();

        gamePort = new StretchViewport(Main.V_WIDTH / ProtaFinal.PPM, Main.V_HEIGHT / ProtaFinal.PPM, gamecam);

        // Sirve para el hud del tiempo y el nivel
        hud = new Hud(juego.batch, altitudMin);

        // Creacion del mundo
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        // Creacion del protagonista
        prota = new ProtaFinal(this);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/fondo.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / ProtaFinal.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // Creacion del mundo
        new B2WorldCreator(this);

        contacto = new WorldContactListener();
        world.setContactListener(contacto);

        // int y = 200;
        // for (int i = 0; i < 25; i++) {
        // int x = rdm.nextInt(60, 340); // 60 minimo maximo 340 x
        // plataformas.add(new PlataformaNormal(this, x, y)); // primer y 200
        // y += 250;
        // }

        // -------------------------
        int y = 200;
        for (int i = 0; i < 25; i++) {
            int x = rdm.nextInt(60, 340); // 60 minimo maximo 340 x
            plataformasnube.add(new PlataformaNube(this, x, y)); // primer y 200
            y += 250;
        }
        // gameover = new GameOverScreen(juego, this);

    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {

        // Moviemiento
        if (!muerto) {
            if (contacto.contacto) {
                prota.b2body.applyLinearImpulse(new Vector2(0, 4f), prota.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && prota.b2body.getLinearVelocity().x <= 2) {
                prota.b2body.applyLinearImpulse(new Vector2(0.1f, 0), prota.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && prota.b2body.getLinearVelocity().x >= -2) {
                prota.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), prota.b2body.getWorldCenter(), true);
            }
        }
    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        gamecam.update();

        prota.update(dt);

        // for (int i = 0; i < plataformas.size(); i++) {
        // plataformas.get(i).update(dt);
        // }

        // ---------------
        for (int i = 0; i < plataformasnube.size(); i++) {
            plataformasnube.get(i).update(dt);
        }

        // rederizar el mapa del juego
        renderer.setView(gamecam);

        if (prota.b2body.getPosition().y > altidudMax / ProtaFinal.PPM && !muerto) {
            gamecam.position.y = prota.b2body.getPosition().y;
            altidudMax = prota.b2body.getPosition().y * ProtaFinal.PPM;
            altitudMin = (prota.b2body.getPosition().y * ProtaFinal.PPM) - 350;
            hud.ActualizarPuntuacion(altitudMin);
        }
        if (prota.b2body.getPosition().y < altitudMin / ProtaFinal.PPM && !muerto) {
            muerto = true;
        }
        /*
         * if (prota.b2body.getPosition().x < 0) {
         * prota.setPosition(3.7f, prota.b2body.getPosition().y);
         * } else if (prota.b2body.getPosition().x > 3.7f) {
         * prota.setPosition(0f, prota.b2body.getPosition().y);
         * }
         */
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        // Para renderizar las colisiones del mapa y del personaje
        b2dr.render(world, gamecam.combined);

        juego.batch.setProjectionMatrix(gamecam.combined);
        juego.batch.begin();
        prota.draw(juego.batch);

        for (int i = 0; i < plataformas.size(); i++) {
            plataformas.get(i).draw(juego.batch);
        }

        // ----
        for (int i = 0; i < plataformasnube.size(); i++) {
            plataformasnube.get(i).draw(juego.batch);
        }
        juego.batch.end();
        juego.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        hud.stage.draw();

        if (muerto) {
            juego.setScreen(new GameOverScreen(juego, this));
            dispose();
        }
        if (inicio) {
            juego.setScreen(new MainMenu(juego, this));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
