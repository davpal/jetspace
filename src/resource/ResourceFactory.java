package resource;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;

public class ResourceFactory {
    static Animation explosion;
    static Audio explosionSound = explosionSound = ResourceLoader.getAudio("WAV", "audio/explosion.wav");
    static Image ship = ResourceLoader.getImage("player/ship.png");
    static Image playerLaser = ResourceLoader.getImage("player/laser.png");;
    static Image enemyLaser = ResourceLoader.getImage("enemy/laser.png");
    static Image bomberShip = ResourceLoader.getImage("enemy/bomber.png");
    static Audio laserSound = ResourceLoader.getAudio("WAV", "audio/laser.wav");
	
    public static Animation getExplosion() {
        explosion = ResourceLoader.getAnimation("enemy/explosion.png", 64, 64, 5, 20);
        explosion.setLooping(false);

        return explosion;
    }
    
    public static Audio getExplosionSound() {
        return explosionSound;
    }

    public static Image getPlayerShip() {
        return ship;
    }

    public static Image getPlayerLaser() {
        return playerLaser;
    }
    
    public static Image getEnemyLaser() {
        return enemyLaser;
    }

    public static Image getBomberShip() {
        return bomberShip;
    }

    public static Audio getLaserSound() {
        return laserSound;
    }
}