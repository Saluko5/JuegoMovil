package screens;

import java.util.ArrayList;
import java.util.Random;

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

    // Definir un intervalo en segundos (200 ms)
    private float intervaloMovimiento = 0.03f; // 200 ms = 0.2 segundos
    private float tiempoAcumulado = 0f; // Acumula el tiempo transcurrido

    // Tiempo en el que se pasa el nivel
    private float tiempoTranscurrido; // Variable para el tiempo

    // Timer timer;
    // TimerTask tarea;

    // Constructor
    public PlayScreen(Main juego, int nivel) {

        atlas = new TextureAtlas("mario.atlas");

        this.juego = juego;
        this.nivel = nivel;

        // Crea la camara q sigue al marciano
        gamecam = new OrthographicCamera();

        gamePort = new StretchViewport(Main.V_WIDTH / ProtaFinal.PPM, Main.V_HEIGHT / ProtaFinal.PPM, gamecam);

        // Sirve para el hud del tiempo y el nivel
        this.tiempoTranscurrido = 0;

        // Creamos el HUD
        hud = new Hud(juego.batch);

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

        // Aqui creamos las plataformas dependiendo del nivel en el que esters
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
            // Creacion de plataformas para el nivel 2
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

        // Se pone musica a la hora de acceder a la partida
        music = Main.manager.get("music/MusicaLvl1.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(juego.volumen);
        music.play();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {

        // Movimiento
        if (!muerto) {
            //Si no esta muerto y hay contacto entra
            if (contacto.contacto) {
                //Le quita la velocidad de impulso y le da una nueva, esto se hace para que no se multiplique la velocidad de salto
                prota.b2body.setLinearVelocity(0, 0);
                prota.b2body.applyLinearImpulse(new Vector2(0, 5.5f), prota.b2body.getWorldCenter(), true);
                sonido = Main.manager.get("music/SonidoSalto.mp3", Music.class);
                sonido.setLooping(false);
                sonido.setVolume(Main.volumen);
                sonido.play();
            }
        }
    }

    public void update(float dt) {
        handleInput(dt);

        // Actualizamos el mundo con un paso de tiempo fijo
        world.step(1 / 60f, 6, 2); // Esto mantiene el juego a 60 FPS fijo

        gamecam.update();
        prota.update(dt);

        // Acumulamos el tiempo transcurrido
        tiempoAcumulado += dt;

        // Si el tiempo acumulado es mayor o igual al intervalo de movimiento (200ms),
        // movemos las plataformas
        if (tiempoAcumulado >= intervaloMovimiento) {
            // Mover las plataformas
            if (nivel == 1) {
                for (PlataformaNormal plataforma : plataformas) {
                    plataforma.Moviemiento(0.03f); // Aquí sigues utilizando tu lógica de movimiento
                }
            } else {
                for (PlataformaNube plataforma : plataformasnube) {
                    plataforma.Moviemiento(0.03f); // Lo mismo para las plataformas de nube
                }
            }

            // Resetear el tiempo acumulado
            tiempoAcumulado = 0f;
        }

        // Actualizar las plataformas (por si necesitas un movimiento continuo por
        // deltaTime)
        for (PlataformaNormal plataforma : plataformas) {
            plataforma.update(dt);
        }

        for (PlataformaNube plataforma : plataformasnube) {
            plataforma.update(dt);
        }

        hud.actualizarTiempo(dt);

        // Renders y actualización de la cámara
        renderer.setView(gamecam);

        // Control de la cámara y del HUD
        if (prota.b2body.getPosition().y > altidudMax / ProtaFinal.PPM && !muerto
                && prota.b2body.getPosition().y < 76.2f) {
            gamecam.position.y = prota.b2body.getPosition().y;
            altidudMax = prota.b2body.getPosition().y * ProtaFinal.PPM;
            altitudMin = (prota.b2body.getPosition().y * ProtaFinal.PPM) - 350;
        }

        if ((prota.b2body.getPosition().y > 80f) && !muerto) {
            ganar = true;
        }
        if (prota.b2body.getPosition().y < altitudMin / ProtaFinal.PPM && !muerto) {
            muerto = true;
        }

        // Manejo de entrada táctil
        if (Gdx.input.isTouched()) {
            float xTouchPixel = Gdx.input.getX();
            Vector3 touchPos = new Vector3(xTouchPixel, prota.b2body.getPosition().y, 0);
            gamecam.unproject(touchPos);
            prota.b2body.setTransform(touchPos.x, prota.b2body.getPosition().y, prota.b2body.getAngle());
        }

    }

    @Override
    public void render(float delta) {
        update(delta);

        // Liempieza de pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Para que rederice
        renderer.render();

        // Para renderizar las colisiones del mapa y del personaje
        // b2dr.render(world, gamecam.combined);

        // Renderizar el HUD

        juego.batch.setProjectionMatrix(gamecam.combined);
        juego.batch.begin();
        prota.draw(juego.batch);

        // Para pintar las plataformas
        for (int i = 0; i < plataformas.size(); i++) {
            plataformas.get(i).draw(juego.batch);
        }

        // ----
        for (int i = 0; i < plataformasnube.size(); i++) {
            plataformasnube.get(i).draw(juego.batch);
        }
        juego.batch.end();
        juego.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        // Dibujar el HUD
        hud.stage.draw();

        // Si esta muerto vibra y pasa a la pantalla de GameOver
        if (muerto) {
            Gdx.input.vibrate(1000, true);
            juego.setScreen(new GameOverScreen(juego, nivel));
            dispose();
            music.stop();
            music.dispose();
        }

        // Si gana pasa a la pantalla, se pasa los records y se para la musica
        if (ganar) {
            if (nivel == 1) {
                juego.meterRecordlvl1(hud.tiempoTranscurrido);
            } else {
                juego.meterRecordlvl2(hud.tiempoTranscurrido);
            }
            juego.setScreen(new WinScreen(juego, nivel));
            dispose();
            music.stop();
            music.dispose();
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
