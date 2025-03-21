package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import screens.PlayScreen;

public class Suelo extends InteractiveTileObject {

    public Suelo(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
    }

    //Metodo que salta cada vez q se golpea con los pies
    @Override
    public void golpePies() {
    }

    //Metodo no usado
    @Override
    public void golpeCabeza() {
        throw new UnsupportedOperationException("Unimplemented method 'golpeCabeza'");
    }
}
