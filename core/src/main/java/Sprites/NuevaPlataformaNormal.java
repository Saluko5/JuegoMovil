package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import screens.PlayScreen;

public abstract class NuevaPlataformaNormal extends Sprite {

    protected World world;
    protected PlayScreen screen;
    public Body b2body;

    public NuevaPlataformaNormal(PlayScreen screen,float x,float y){
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x,y);
        definePlataforma();
    }

    protected abstract void definePlataforma();

}
