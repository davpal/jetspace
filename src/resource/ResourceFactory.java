package resource;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class ResourceFactory {
    static Animation explosion;
    static Image ship = null;
    static Image playerLaser = null;
    static Image enemyLaser = null;
    static Image bomberShip = null;
	
    public static Animation getExplosion() {
        explosion = ResourceLoader.getAnimation("enemy/explosion.png", 64, 64, 5, 20);
        explosion.setLooping(false);

        return explosion;
    }

    public static Image getPlayerShip() {
	if(ship == null){
            ship = ResourceLoader.getImage("player/ship.png");
	}
        return ship;
    }

    public static Image getPlayerLaser() {
        if(playerLaser == null){
            playerLaser = ResourceLoader.getImage("player/laser.png");
        }
        return playerLaser;
    }
    
    public static Image getEnemyLaser() {
        if(enemyLaser == null){
            enemyLaser = ResourceLoader.getImage("enemy/laser.png");
        }
        return enemyLaser;
    }

    public static Image getBomberShip() {
        if(bomberShip == null){
            bomberShip = ResourceLoader.getImage("enemy/bomber.png");
        }
        return bomberShip;
    }
}
