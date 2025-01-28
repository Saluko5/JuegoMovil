package Sprites;

import com.badlogic.gdx.graphics.Texture;
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

import screens.PlayScreen;

public class ProtaFinal extends Sprite {
    public World world;
    public Body b2body;
    public static final float PPM = 100;
    private TextureRegion protadepie;

    public ProtaFinal(PlayScreen screen){
        super(screen.getAtlas().findRegion("mario"));
        this.world = screen.getWorld();
        defineProta();

        //hace q la region de textura sea la textura del punto 0,0 con 16 pixeles de alto y ancho, osea q en la textura llamada Prota_Salto coja los pixeles
        //del punto 0,0 con el tamaño anteriormente dicho
        protadepie = new TextureRegion(new Texture("mario.png"),0,0,32,33);

        //Hacemos q aparezca en la posicion 0,0 con un tamaño 16 16 pixeles escalado al tamaño del juego
        setBounds(0,0,33,33);

        //Asociamos la region con la region de textura protadepie
        setRegion(protadepie);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x -getWidth() / 2, b2body.getPosition().y -getHeight() / 2);
    }

    public void defineProta(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(200/ ProtaFinal.PPM,95/ ProtaFinal.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20/ ProtaFinal.PPM); //esta a 20

        fdef.shape = shape;
        b2body.createFixture(fdef);

        //Ahora creare la linea q hara de sensor para saber si lo que esta esta tocando es el suelo, una plataforma normal, una plataforma movil etc
        EdgeShape pies = new EdgeShape();
        pies.set(new Vector2(-2 / ProtaFinal.PPM,-20 / ProtaFinal.PPM),new Vector2(2 / ProtaFinal.PPM,-20 /ProtaFinal.PPM));
        fdef.shape = pies;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("pies");

    }

}

