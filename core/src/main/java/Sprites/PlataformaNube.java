package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pruebas.mijuego.Main;

import screens.PlayScreen;

public class PlataformaNube extends Sprite {

    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    private TextureRegion plataformacuerpo;
    private TextureRegion nada;
    private boolean setToDestroy;
    private boolean destroyed;
    public boolean contacto;
    float x;
    float y;

    public PlataformaNube(PlayScreen screen, float x, float y) {
        super(new Texture("plataformalvl1.png"), 250, 200);
        this.screen = screen;
        this.world = screen.getWorld();
        this.x = x;
        this.y = y;
        Gdx.app.log("coordenadas:" + x + "y: " + y, "");
        definePlataforma();

        plataformacuerpo = new TextureRegion(getTexture(), 15, 84, 225, 32);
        setBounds(0.1f, 0.1f, 120 / ProtaFinal.PPM, 30 / ProtaFinal.PPM);
        setRegion(plataformacuerpo);

        setToDestroy = false;
        destroyed = false;
    }

    protected void definePlataforma() {
        BodyDef bdef = new BodyDef();

        // Ponemos la posicion del "cuerpo" de lo plataforma
        bdef.position.set(x / ProtaFinal.PPM, y / ProtaFinal.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        // Con esto le damos la forma al "cuerpo"
        PolygonShape shape = new PolygonShape();
        // Y con esto le damos el tamaño
        shape.setAsBox(60 / ProtaFinal.PPM, 10 / ProtaFinal.PPM); // esta a 20
        fdef.filter.categoryBits = Main.LINEADENUBE_BIT;
        // fdef.filter.maskBits = Main.TIERRA_BIT | Main.PROTA_BIT |
        // Main.PLATAFORMA_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        // Creacion de la linea para la colision de desaparicion
        PolygonShape linea = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-58, 10).scl(1 / ProtaFinal.PPM);
        vertice[1] = new Vector2(58, 10).scl(1 / ProtaFinal.PPM);
        vertice[2] = new Vector2(-58, 0).scl(1 / ProtaFinal.PPM);
        vertice[3] = new Vector2(58, 0).scl(1 / ProtaFinal.PPM);
        linea.set(vertice);

        fdef.shape = linea;
        fdef.restitution = 0.0f; // Con esto hago que cuando salte encima se impulso con la fuerza que marco
        fdef.filter.categoryBits = Main.PLATAFORMA_BIT;
        b2body.createFixture(fdef).setUserData(this);

    }

    public void update(float dt) {
        float posX = b2body.getPosition().x * ProtaFinal.PPM;
        posX = posX - 10;
        float posY = b2body.getPosition().y * ProtaFinal.PPM;
        posY = posY - 10;

        setPosition((b2body.getPosition().x) - getWidth() / 2,
                (b2body.getPosition().y) - getHeight() / 2);
        if (setToDestroy && !destroyed) {
            // destruye el cuerpo plataforma nube y cambia la booleana destruido a true pq
            // ya esta destruido
            world.destroyBody(b2body);
            destroyed = true;
            nada = new TextureRegion(getTexture(), 0, 0, 0, 0);
            setBounds(0, 0.1f, 0 / ProtaFinal.PPM, 0 / ProtaFinal.PPM);
            setRegion(nada);
            contacto = false;
        } else {
            setRegion(plataformacuerpo);
        }
    }

    public void pisada() {
        setToDestroy = true;
        Gdx.app.log("plataforma nube", "");
        contacto = true;
    }
}
