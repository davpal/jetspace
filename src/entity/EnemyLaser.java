package entity;

import app.Game;
import app.ResourceLoader;

public class EnemyLaser extends Laser {
    public EnemyLaser(double d, double e) {
        super(d, e);
        beam = ResourceLoader.getImage("/enemy/laser.png");
        speed = 2.5;
    }
    
    public void update(Game g){
        y += speed;
    }
}
