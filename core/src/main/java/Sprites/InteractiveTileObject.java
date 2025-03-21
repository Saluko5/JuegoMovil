package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import screens.PlayScreen;

public abstract class InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    // Constructor de la clase InteractiveTileObject
    public InteractiveTileObject(PlayScreen screen, Rectangle bounds) {

        // Asigna el mundo físico (Box2D) y el mapa desde el objeto PlayScreen
        this.world = screen.getWorld();
        this.map = screen.getMap();

        // Establece el rectángulo de colisión (bounds) de este objeto
        this.bounds = bounds;

        // Crea un BodyDef para definir las propiedades físicas del cuerpo
        BodyDef bdef = new BodyDef();

        // Crea una forma de polígono que representará la forma de colisión
        PolygonShape shape = new PolygonShape();

        // Crea un FixtureDef para definir las propiedades del fixture
        FixtureDef fdef = new FixtureDef();

        // Define que este objeto será un cuerpo estático (no se moverá)
        bdef.type = BodyDef.BodyType.StaticBody;

        // Establece la posición del cuerpo en el mundo, usando el centro del rectángulo
        // (bounds)
        // y convirtiendo las coordenadas de píxeles a metros usando ProtaFinal.PPM
        // (píxeles por metro)
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / ProtaFinal.PPM,
                (bounds.getY() + bounds.getHeight() / 2) / ProtaFinal.PPM);

        // Crea el cuerpo físico en el mundo con la configuración definida en bdef
        body = world.createBody(bdef);

        // Define la forma del objeto como un rectángulo, usando el tamaño de los bounds
        // (ancho y alto)
        shape.setAsBox(bounds.getWidth() / 2 / ProtaFinal.PPM, bounds.getHeight() / 2 / ProtaFinal.PPM);

        // Asocia la forma definida al FixtureDef
        fdef.shape = shape;

        // Crea el fixture en el cuerpo con la configuración definida en fdef
        fixture = body.createFixture(fdef);
    }

    public abstract void golpePies();

    public abstract void golpeCabeza();

    // Filtro de caterrgorias que pone el filtro
    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

}
