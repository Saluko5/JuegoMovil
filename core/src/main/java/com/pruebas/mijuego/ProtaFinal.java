package com.pruebas.mijuego;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class ProtaFinal extends Sprite {
    public World world;
    public Body b2body;
    public static final float PPM = 100;

    public ProtaFinal(World world){
        this.world = world;
        defineProta();
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

