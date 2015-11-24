package rendering;

import entity.GameObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;
import resource.ResourceFactory;

public class Explosion extends GameObject {
    private Animation animation = ResourceFactory.getExplosion();
    private Audio sound = ResourceFactory.getExplosionSound();
    
    private GameObject o;
    
    public Explosion(GameObject o){
        super(o.getX(), o.getY(), o.getAngle());
        this.o = o;
        animation.start();
        sound.playAsSoundEffect(1f, 1f, false);
    }
    
    public GameObject getOwner(){
        return o;
    }
    
    public void update(GameContainer g) {
    }
    
    public boolean done(){
        return animation.isStopped();
    }

    public void render(Renderer r) {
        r.renderExplosion(this);
    }
    
    public Animation getAnimation(){
        return animation;
    }
}
