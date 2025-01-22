package tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pruebas.mijuego.ProtaFinal;

public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Crear la colision de la "tierra"
        for (MapObject object : map.getLayers().get("Suelo").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / ProtaFinal.PPM, (rect.getY() + rect.getHeight() / 2) / ProtaFinal.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / ProtaFinal.PPM, rect.getHeight() / 2 / ProtaFinal.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
}
