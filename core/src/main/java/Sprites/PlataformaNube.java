package Sprites;

import java.util.Vector;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pruebas.mijuego.Main;

import screens.PlayScreen;

public class PlataformaNube extends NuevaPlataformaNormal {

    public PlataformaNube(PlayScreen screen, float x, float y) {
        super(screen, x, y);

    }

    @Override
    protected void definePlataforma() {
        BodyDef bdef = new BodyDef();

        // Ponemos la posicion del "cuerpo" de lo plataforma
        bdef.position.set(100 / ProtaFinal.PPM, 120 / ProtaFinal.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        // Con esto le damos la forma al "cuerpo"
        PolygonShape shape = new PolygonShape();
        // Y con esto le damos el tamaño
        shape.setAsBox(60 / ProtaFinal.PPM, 10 / ProtaFinal.PPM); // esta a 20
        fdef.filter.categoryBits = Main.PLATAFORMA_BIT;
        fdef.filter.maskBits = Main.TIERRA_BIT | Main.PROTA_BIT | Main.PLATAFORMA_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        // Creacion de la linea para la colision de desaparicion
        PolygonShape linea = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-10, 12).scl(1 / ProtaFinal.PPM);
        vertice[1] = new Vector2(10, 12).scl(1 / ProtaFinal.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / ProtaFinal.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / ProtaFinal.PPM);
        linea.set(vertice);

        fdef.shape = linea;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = Main.LINEADENUBE_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void update(float dt) {
        // setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y -
        // getHeight() / 2);
        // setRegion(plataformasprite);
    }

}
