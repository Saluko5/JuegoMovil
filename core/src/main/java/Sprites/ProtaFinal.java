package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pruebas.mijuego.Main;

import java.util.ArrayList;

import screens.PlayScreen;

public class ProtaFinal extends Sprite {
    public enum estado {
        SALTANDO, DEPIE, MUERTO
    };

    public estado estadoactual;
    public estado estadoanterior;
    private float timerestado;
    private boolean ladoderecho;

    public World world;
    public Body b2body;
    public static final float PPM = 100;
    private TextureRegion protadepie;
    private TextureRegion protasaltando;

    public ProtaFinal(PlayScreen screen) {
        super(new Texture("ProtaSaltoMejorado.png"), 500, 500);
        this.world = screen.getWorld();

        estadoactual = estado.DEPIE;
        estadoanterior = estado.DEPIE;
        ladoderecho = true;
        timerestado = 0;

        defineProta();

        // hace q la region de textura sea la textura del punto 0,0 con 16 pixeles de
        // alto y ancho, osea q en la textura llamada Prota_Salto coja los pixeles
        // del punto 0,0 con el tamaño anteriormente dicho
        protadepie = new TextureRegion(getTexture(), 50, 40, 90, 140);

        // Hacemos q aparezca en la posicion 0,0 con un tamaño 40 40 pixeles escalado al
        // tamaño del juego
        setBounds(0, 0, 40 / PPM, 70 / PPM);

        // Ahora lo hago para el frame del protagonista saltando
        protasaltando = new TextureRegion(new Texture("ProtaSaltoMejorado.png"), 245, 40, 90, 140);
        setBounds(0, 0, 40 / PPM, 70 / PPM);

        // Asociamos la region con la region de textura protasaltando
    }

    public TextureRegion getFrame(float dt) {
        estadoactual = getEstado();
        TextureRegion region;

        switch (estadoactual) {

            case SALTANDO:
                region = protasaltando;
                break;
            default:
                region = protadepie;
                break;
        }

        // si la velocidad de x es menor de cero(osea q se mueve pero para no a la
        // derecha ocurre y si no esta girado el if
        if ((b2body.getLinearVelocity().x > 0 || !ladoderecho) && !region.isFlipX()) {
            // con esto lo voltea
            region.flip(true, false);
            ladoderecho = false;
            // ahora en para el otro lado
        } else if ((b2body.getLinearVelocity().x < 0 || ladoderecho) && region.isFlipX()) {
            // se le da la vuelta tmb
            region.flip(true, false);
            ladoderecho = true;
        }
        estadoanterior = estadoactual;
        return region;
    }

    public estado getEstado() {
        if (b2body.getLinearVelocity().y != 0) {
            return estado.DEPIE;
        } else {
            return estado.SALTANDO;
        }
    }

    public void update(float dt) {
        // Obtiene la posición del cuerpo en el mundo (en metros)
        float posX = b2body.getPosition().x * PPM;
        posX = posX - 10;
        float posY = b2body.getPosition().y * PPM;
        posY = posY - 10;

        // Aquí restamos la mitad de la altura del sprite para que esté alineado
        // La posición Y en Box2D está en el centro, por lo que restamos la mitad de la
        // altura del sprite
        setPosition((b2body.getPosition().x) - getWidth() / 2,
                (b2body.getPosition().y + 0.1f) - getHeight() / 2);
        // setRegion(protadepie);

        setRegion(getFrame(dt));
    }

    public void defineProta() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(200 / ProtaFinal.PPM, 100 / ProtaFinal.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20 / ProtaFinal.PPM); // esta a 20
        fdef.filter.categoryBits = Main.PROTA_BIT;
        fdef.filter.maskBits = Main.TIERRA_BIT | Main.PLATAFORMA_BIT | Main.LINEADENUBE_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        // Ahora creare la linea q hara de sensor para saber si lo que esta esta tocando
        // es el suelo, una plataforma normal, una plataforma movil etc
        EdgeShape pies = new EdgeShape();
        pies.set(new Vector2(-2 / ProtaFinal.PPM, -20 / ProtaFinal.PPM),
                new Vector2(2 / ProtaFinal.PPM, -20 / ProtaFinal.PPM));
        fdef.shape = pies;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("pies");

        // Ahora creare la linea q hara de sensor para saber si lo que esta esta es una
        // plataforma
        // y asi hacerlo sensor y por lo cual hacer que no tenga colision
        // EdgeShape cabeza = new EdgeShape();
        // cabeza.set(new Vector2(-2 / ProtaFinal.PPM, 100 / ProtaFinal.PPM),
        //         new Vector2(2 / ProtaFinal.PPM, 100 / ProtaFinal.PPM));
        // fdef.shape = cabeza;
        // fdef.isSensor = true;

        // b2body.createFixture(fdef).setUserData("cabeza");

    }

}
