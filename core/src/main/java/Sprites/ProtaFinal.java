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

import screens.PlayScreen;

public class ProtaFinal extends Sprite {
    public enum estado {
        SALTANDO, DEPIE
    };

    public estado estadoactual;
    public estado estadoanterior;

    private Animation protasaltando;
    private float timerestado;

    public World world;
    public Body b2body;
    public static final float PPM = 100;
    private TextureRegion protadepie;

    public ProtaFinal(PlayScreen screen) {
        super(new Texture("Protasalto.png"), 500, 500);
        this.world = screen.getWorld();

        defineProta();

        // hace q la region de textura sea la textura del punto 0,0 con 16 pixeles de
        // alto y ancho, osea q en la textura llamada Prota_Salto coja los pixeles
        // del punto 0,0 con el tamaño anteriormente dicho
        protadepie = new TextureRegion(getTexture(), 0, 0, 150, 300);

        // Hacemos q aparezca en la posicion 0,0 con un tamaño 16 16 pixeles escalado al
        // tamaño del juego
        setBounds(0, 0, 50, 140);

        // Asociamos la region con la region de textura protadepie
        setRegion(protadepie);
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
        setPosition(posX - getWidth() / 2, posY - getHeight() / 2);
    }

    public void defineProta() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(200 / ProtaFinal.PPM, 95 / ProtaFinal.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20 / ProtaFinal.PPM); // esta a 20
        fdef.filter.categoryBits = Main.PROTA_BIT;
        fdef.filter.maskBits = Main.DEFAULT_BIT | Main.PLATAFORMA_BIT;


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

    }

}
