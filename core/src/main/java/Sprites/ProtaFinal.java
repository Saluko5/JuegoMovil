package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import screens.PlayScreen;

public class ProtaFinal extends Sprite {
    public World world;
    public Body b2body;
    public static final float PPM = 100;
    private TextureRegion protadepie;

    public ProtaFinal(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("mario"));
        this.world = world;
        defineProta();

        //hace q la region de textura sea la textura del punto 0,0 con 16 pixeles de alto y ancho, osea q en la textura llamada Prota_Salto coja los pixeles
        //del punto 0,0 con el tamaño anteriormente dicho
        protadepie = new TextureRegion(getTexture(),0,0,16,16);

        //Hacemos q aparezca en la posicion 0,0 con un tamaño 16 16 pixeles escalado al tamaño del juego
        setBounds(0,0,100/ProtaFinal.PPM,100/ProtaFinal.PPM);

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

    }

}

