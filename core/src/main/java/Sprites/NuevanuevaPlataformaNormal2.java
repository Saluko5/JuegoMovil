package Sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import screens.PlayScreen;

public class NuevanuevaPlataformaNormal2 extends NuevaPlataformaNormal{

    public NuevanuevaPlataformaNormal2(PlayScreen screen, float x, float y) {
        super(screen, x, y);
    }

    @Override
    protected void definePlataforma() {
        BodyDef bdef = new BodyDef();

        //Ponemos la posicion del "cuerpo" de lo plataforma
        bdef.position.set(100/ ProtaFinal.PPM,120/ ProtaFinal.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        //Con esto le damos la forma al "cuerpo"
        PolygonShape shape = new PolygonShape();
        //Y con esto le damos el tamaño
        shape.setAsBox(60/ProtaFinal.PPM,10/ ProtaFinal.PPM); //esta a 20

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
