package entity;

import org.newdawn.slick.*;

public class EnemyLaser extends Laser {
    public EnemyLaser(double x, double y, double sx, double sy, double tx, double ty, double angle) {
        super(x, y, sx, sy, tx, ty, angle);
        try {
            beam = new Image("enemy/laser.png");
        } catch (SlickException e1) {
            e1.printStackTrace();
        }
        speed = 2.5;
    }
    
    public void update(GameContainer g){
        y += speed;
    }
    
    @Override
    public void render(Graphics g){
        
    }
}
