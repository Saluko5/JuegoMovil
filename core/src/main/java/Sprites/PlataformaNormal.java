package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pruebas.mijuego.Main;

import screens.PlayScreen;

public class PlataformaNormal extends Sprite {

    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    private TextureRegion plataformacuerpo;
    float x;
    float y;
    boolean movilidad;
    private float desplazamiento = 0f;
    int contador = 0;
    boolean adelante;
    boolean atras;

    public PlataformaNormal(PlayScreen screen, float x, float y, boolean movilidad, boolean lado) {
        // Creación de la textura de la plataforma
        super(new Texture("plataformalvl1.png"), 250, 200);
        
        if (lado) {
            this.adelante = true;  // Se mueve hacia la derecha
            this.atras = false;
        } else {
            this.adelante = false;  // Se mueve hacia la izquierda
            this.atras = true;
            desplazamiento = 1f;
        }
        this.movilidad = movilidad;
        this.screen = screen;
        this.world = screen.getWorld();
        this.x = x;
        this.y = y;
        definePlataforma();
    
        plataformacuerpo = new TextureRegion(getTexture(), 15, 84, 225, 32);
        setBounds(0.1f, 0.1f, 90 / ProtaFinal.PPM, 30 / ProtaFinal.PPM);
        setRegion(plataformacuerpo);

    }

    protected void definePlataforma() {
        BodyDef bdef = new BodyDef();

        //Creacion de la colision de la plataforma

        // Ponemos la posicion del "cuerpo" de lo plataforma
        bdef.position.set(x / ProtaFinal.PPM, y / ProtaFinal.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        // Con esto le damos la forma al "cuerpo"
        PolygonShape shape = new PolygonShape();
        // Y con esto le damos el tamaño
        shape.setAsBox(45 / ProtaFinal.PPM, 10 / ProtaFinal.PPM); // esta a 20
        fdef.filter.categoryBits = Main.LINEADENUBE_BIT;
        // fdef.filter.maskBits = Main.TIERRA_BIT | Main.PROTA_BIT |
        // Main.PLATAFORMA_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt) {
        float posX = b2body.getPosition().x * ProtaFinal.PPM;
        posX = posX - 10;
        float posY = b2body.getPosition().y * ProtaFinal.PPM;
        posY = posY - 10;

        setPosition((b2body.getPosition().x) - getWidth() / 2,
                (b2body.getPosition().y) - getHeight() / 2);
        setRegion(plataformacuerpo);
    }

    //Metodo con el que hago que se muevan las plataformas 
    public void Moviemiento(float cantidad) {
        if (movilidad) {
            if (atras) {
                // Mover hacia la izquierda
                b2body.setTransform(new Vector2(b2body.getPosition().x - cantidad, b2body.getPosition().y), b2body.getAngle());
                desplazamiento = desplazamiento - cantidad;
                if (desplazamiento <= 0) {
                    atras = false;
                    adelante = true;  // Cambia de dirección hacia la derecha
                }
            }
            if (adelante) {
                // Mover hacia la derecha
                b2body.setTransform(new Vector2(b2body.getPosition().x + cantidad, b2body.getPosition().y), b2body.getAngle());
                desplazamiento = desplazamiento + cantidad;
                if (desplazamiento >= 1f) {
                    adelante = false;
                    atras = true;  // Cambia de dirección hacia la izquierda
                }
            }
        }
    }

    public void pisada() {
    }
}
