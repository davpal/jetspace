package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnemyLaser extends Laser {
    public EnemyLaser(double x, double y, GameObject owner){
        super(x, y, owner);
        
        speed = 3; 
        angle -= Math.toRadians(180);
        dx = speed * Math.sin(angle);
        dy = - speed * Math.cos(angle);
        
        getBeam();
    }

    private void getBeam() {
        try {
            beam = new Image("enemy/laser.png");
        } catch (SlickException e1) {
            e1.printStackTrace();
        }
    }
}