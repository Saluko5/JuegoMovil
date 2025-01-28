package tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;

import Sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener {

    public boolean contacto = true;

    //Esto sucede cuando hacen contacto las colisiones de dos objetos
    //Cuando hay un contacto hay dos accesorios, el a y el b, asi q los pillamos
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        //Como no sabemos cual de los dos accesorio es nuestro palo sensor pies
        //lo que hacemos es q si alguno de los lo es entre en el if
        if (fixA.getUserData() == "pies" || fixB.getUserData() == "pies"){
            //Si el accesorio a es cabeza entonces se guardar en el accesorio cabeza lo que
            // hay despues del ? si no osea el accesorio a si no, se guarda el accesorio b
            Fixture head = fixA.getUserData() == "pies" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            //si el accesorio object es diferente a null y si el objecto obtiene datos del usuario
            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).golpePies();
            }
        }
        contacto = true;
    }

    //Esto sucede cuando dejan de hacer contacto las colisiones de dos objetos
    @Override
    public void endContact(Contact contact) {
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
