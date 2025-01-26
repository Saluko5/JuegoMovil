package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import Sprites.ProtaFinal;

import scenes.Hud;
import tools.B2WorldCreator;

public class PlayScreen implements Screen {

    private Main juego;
    private Hud hud;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private ProtaFinal prota;
    private float altidudMax = 350;

    private TextureAtlas atlas;




    //Constructor
    public PlayScreen(Main juego){

        atlas = new TextureAtlas("mario.atlas");

        this.juego = juego;

        //Crea la camara q sigue al marciano
        gamecam = new OrthographicCamera();

        gamePort = new StretchViewport(Main.V_WIDTH / ProtaFinal.PPM,Main.V_HEIGHT/ ProtaFinal.PPM,gamecam); //esto se puede cambiar

        //Sirve para el hud del tiempo y el nivel
        hud = new Hud(juego.batch);

        //Creacion del mundo
        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();

        //Creacion del protagonista
        prota = new ProtaFinal(world,this);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/fondo.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ ProtaFinal.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2,gamePort.getWorldHeight() / 2,0);

        //Creacion del mundo
        new B2WorldCreator(world,map);

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){

        //Moviemiento
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            prota.b2body.applyLinearImpulse(new Vector2(0,4f),prota.b2body.getWorldCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && prota.b2body.getLinearVelocity().x <= 2){
            prota.b2body.applyLinearImpulse(new Vector2(0.1f,0),prota.b2body.getWorldCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && prota.b2body.getLinearVelocity().x >= -2){
            prota.b2body.applyLinearImpulse(new Vector2(-0.1f,0),prota.b2body.getWorldCenter(),true);
        }
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f,6,2);

        gamecam.update();

        //prota.update(dt);

        //rederizar el mapa del juego
        renderer.setView(gamecam);

        if (prota.b2body.getPosition().y > altidudMax/ ProtaFinal.PPM){
            gamecam.position.y = prota.b2body.getPosition().y;
            altidudMax = prota.b2body.getPosition().y * ProtaFinal.PPM;
        }
        //gamecam.position.x = prota.b2body.getPosition().x;

        //renderizar el BOX2DDebugLines
        b2dr.render(world,gamecam.combined);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        renderer.render();

        //Para renderizar las colisiones del mapa y del personaje
        b2dr.render(world,gamecam.combined);

        juego.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        juego.batch.begin();
        prota.draw(juego.batch);
        juego.batch.end();
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
