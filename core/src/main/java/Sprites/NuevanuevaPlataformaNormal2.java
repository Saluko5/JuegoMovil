package Sprites;

import org.w3c.dom.Text;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.pruebas.mijuego.Main;

import screens.PlayScreen;

public class NuevanuevaPlataformaNormal2 extends NuevaPlataformaNormal {

    TextureRegion plataformasprite;
    private Animation moverse;

    public NuevanuevaPlataformaNormal2(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        //moverse = new Animation<>(0.4f, new TextureRegion("ProtaSalto.png"),0,0,100,100);
    }

    @Override
    protected void definePlataforma() {

        BodyDef bdef = new BodyDef();

        // Ponemos la posicion del "cuerpo" de lo plataforma
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        // Con esto le damos la forma al "cuerpo"
        PolygonShape shape = new PolygonShape();
        // Y con esto le damos el tamaño
        shape.setAsBox(100 / ProtaFinal.PPM, 100 / ProtaFinal.PPM); // esta a 20
        fdef.filter.categoryBits = Main.PLATAFORMA_BIT;
        fdef.filter.maskBits = Main.TIERRA_BIT | Main.PROTA_BIT | Main.PLATAFORMA_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }

    public void update(float dt) {

        //setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        //setRegion(plataformasprite);

    }
}
