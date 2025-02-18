package screens;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
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

    // private GameOverScreen gameover;

    // Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    public ProtaFinal prota;
    private float altidudMax = 350;
    public float altitudMin = 0;
    public Random rdm = new Random();

    private TextureAtlas atlas;

    public int nivel = 1;
    private boolean ganar = false;

    private WorldContactListener contacto;
    private ArrayList<PlataformaNormal> plataformas = new ArrayList<>();
    private ArrayList<PlataformaNube> plataformasnube = new ArrayList<>();

    private Music music;
    private Music sonido;

    Timer timer;
    TimerTask tarea;

    // Constructor
    public PlayScreen(Main juego, int nivel) {

        atlas = new TextureAtlas("mario.atlas");

        this.juego = juego;
        this.nivel = nivel;

        // Crea la camara q sigue al marciano
        gamecam = new OrthographicCamera();

        gamePort = new StretchViewport(Main.V_WIDTH / ProtaFinal.PPM, Main.V_HEIGHT / ProtaFinal.PPM, gamecam);

        // Sirve para el hud del tiempo y el nivel
        hud = new Hud(juego.batch, altitudMin);

        // Creacion del mundo
        world = new World(new Vector2(0, -5), true);
        b2dr = new Box2DDebugRenderer();

        // Creacion del protagonista
        prota = new ProtaFinal(this);

        mapLoader = new TmxMapLoader();
        if (nivel == 1) {
            map = mapLoader.load("maps/fondolargofinal.tmx");
        } else {
            map = mapLoader.load("maps/fondoespacio.tmx");
        }
        renderer = new OrthogonalTiledMapRenderer(map, 1 / ProtaFinal.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // Creacion del mundo
        new B2WorldCreator(this);

        contacto = new WorldContactListener();
        world.setContactListener(contacto);

        if (nivel == 1) {

            int xtemp = 0;
            while (xtemp < 60) {
                xtemp = rdm.nextInt(140);
            }
            int xtemp2 = 0;
            while (xtemp2 < 240) {
                xtemp2 = rdm.nextInt(340);
            }
            int n = rdm.nextInt(2);
            if (n == 0) {
                plataformas.add(new PlataformaNormal(this, xtemp, 250, true));
            } else {
                plataformas.add(new PlataformaNormal(this, xtemp2, 250, false));
            }

            int y = 550;
            for (int i = 0; i < 30; i++) {
                int x = 0;
                while (x < 60) {
                    x = rdm.nextInt(340);
                }
                int n2 = rdm.nextInt(2);
                if (n2 == 0) {
                    plataformas.add(new PlataformaNormal(this, x, y, true)); // primer y 200
                } else {
                    plataformas.add(new PlataformaNormal(this, x, y, false)); // primer y 200
                }
                y += 250;
            }

        } else {
            // Para el nivel 2
            int xtemp = 0;
            while (xtemp < 60) {
                xtemp = rdm.nextInt(140);
            }
            int xtemp2 = 0;
            while (xtemp2 < 240) {
                xtemp2 = rdm.nextInt(340);
            }
            int n = rdm.nextInt(2);
            if (n == 1) {
                plataformasnube.add(new PlataformaNube(this, xtemp, 250, true));
            } else {
                plataformasnube.add(new PlataformaNube(this, xtemp2, 250, false));
            }

            int y = 550;
            for (int i = 0; i < 30; i++) {
                int x = 0;
                while (x < 60) {
                    x = rdm.nextInt(340);
                }
                int n2 = rdm.nextInt(2);
                if (n2 == 1) {
                    plataformasnube.add(new PlataformaNube(this, x, y, true)); // primer y 200
                } else {
                    plataformasnube.add(new PlataformaNube(this, x, y, false)); // primer y 200
                }

                y += 250;
            }
        }
        music = Main.manager.get("music/MusicaLvl1.mp3", Music.class);
        music.setLooping(true);
        music.play();

        timer = new Timer();

        tarea = new TimerTask() {
            @Override
            public void run() {
                if (nivel == 1) {
                    for (int i = 0; i < plataformas.size(); i++) {
                        plataformas.get(i).Moviemiento(0.03f);
                    }
                } else {
                    for (int i = 0; i < plataformasnube.size(); i++) {
                        plataformasnube.get(i).Moviemiento(0.03f);
                    } 
                }
            }
        };

        // Ejecuta la tarea cada 200 milisegundos, sin retraso inicial
        timer.scheduleAtFixedRate(tarea, 0, 50);
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
                prota.b2body.setLinearVelocity(0, 0);
                prota.b2body.applyLinearImpulse(new Vector2(0, 5.5f), prota.b2body.getWorldCenter(), true);
                sonido = Main.manager.get("music/SonidoSalto.mp3", Music.class);
                sonido.setLooping(false);
                sonido.play();
            }
        }
    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        gamecam.update();

        prota.update(dt);

        for (int i = 0; i < plataformas.size(); i++) {
            plataformas.get(i).update(dt);
        }

        // ---------------
        for (int i = 0; i < plataformasnube.size(); i++) {
            plataformasnube.get(i).update(dt);
        }

        // rederizar el mapa del juego
        renderer.setView(gamecam);

        if (prota.b2body.getPosition().y > altidudMax / ProtaFinal.PPM && !muerto
                && prota.b2body.getPosition().y < 76.2f) {
            gamecam.position.y = prota.b2body.getPosition().y;
            altidudMax = prota.b2body.getPosition().y * ProtaFinal.PPM;
            altitudMin = (prota.b2body.getPosition().y * ProtaFinal.PPM) - 350;
            hud.ActualizarPuntuacion(altitudMin);
        }
        if ((prota.b2body.getPosition().y > 80f) && !muerto) {
            ganar = true;
        }
        if (prota.b2body.getPosition().y < altitudMin / ProtaFinal.PPM && !muerto) {
            muerto = true;
        }

        if (Gdx.input.isTouched()) {
            // Conseguir la posición de toque en la pantalla
            float xTouchPixel = Gdx.input.getX();

            // Convertir las coordenadas de pantalla a coordenadas del mundo
            Vector3 touchPos = new Vector3(xTouchPixel, prota.b2body.getPosition().y, 0);
            gamecam.unproject(touchPos);

            // Actualizar la posición del prota según las coordenadas del mundo
            prota.b2body.setTransform(touchPos.x, prota.b2body.getPosition().y,
                    prota.b2body.getAngle());
        }

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
            timer.cancel();
            music.dispose();
            Gdx.input.vibrate(1000, false);
            juego.setScreen(new GameOverScreen(juego, nivel));
            dispose();
        }
        if (ganar) {
            timer.cancel();
            music.dispose();
            juego.setScreen(new WinScreen(juego, nivel));
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
        timer.cancel();
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
