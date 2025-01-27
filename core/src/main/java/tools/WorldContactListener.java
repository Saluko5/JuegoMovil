package tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {

    public boolean contacto = true;

    //Esto sucede cuando hacen contacto las colisiones de dos objetos
    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Se hizo contacto","");
        contacto = true;
    }

    //Esto sucede cuando dejan de hacer contacto las colisiones de dos objetos
    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("Se dejo de hacer contacto","");
        contacto = false;
    }

    //
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
