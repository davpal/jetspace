package resource;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ResourceFactory {
    static Animation explosion;
    static Image ship = null;
	
    public static Animation getExplosion() {
	if(explosion == null){
            explosion = ResourceLoader.getAnimation("enemy/explosion.png", 64, 64, 5, 20);
            explosion.setLooping(false);
	}
		
        return explosion;
    }

    public static Image getPlayerShip() {
	if(ship == null){
            ship = ResourceLoader.getImage("player/ship.png");
	}
        return ship;
    }
}
