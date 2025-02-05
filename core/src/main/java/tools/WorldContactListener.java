package tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.pruebas.mijuego.Main;

import Sprites.InteractiveTileObject;
import Sprites.PlataformaNube;

public class WorldContactListener implements ContactListener {

    public boolean contacto = true;

    // Esto sucede cuando hacen contacto las colisiones de dos objetos
    // Cuando hay un contacto hay dos accesorios, el a y el b, asi q los pillamos
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        // Como no sabemos cual de los dos accesorio es nuestro palo sensor pies
        // lo que hacemos es q si alguno de los lo es entre en el if
        if (fixA.getUserData() == "pies" || fixB.getUserData() == "pies") {
            // Si el accesorio a es cabeza entonces se guardar en el accesorio cabeza lo que
            // hay despues del ? si no osea el accesorio a si no, se guarda el accesorio b
            Fixture head = fixA.getUserData() == "pies" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            // si el accesorio object es diferente a null y si el objecto obtiene datos del
            // usuario
        contacto = true;
            if (object.getUserData() != null
                    && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).golpePies();
            }
        }

        switch (cDef) {
            case Main.PLATAFORMA_BIT | Main.PROTA_BIT:
                // Si el primero cuerpo de la colision es la plataforma
                if (fixA.getFilterData().categoryBits == Main.PLATAFORMA_BIT) {
                    ((PlataformaNube) fixA.getUserData()).pisada();
                } else if (fixB.getFilterData().categoryBits == Main.PLATAFORMA_BIT) {
                    ((PlataformaNube) fixB.getUserData()).pisada();
                }
                break;
        }
    }

    // Esto sucede cuando dejan de hacer contacto las colisiones de dos objetos
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
