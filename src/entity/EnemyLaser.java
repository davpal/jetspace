package entity;

import org.newdawn.slick.*;

import app.ResourceLoader;

public class EnemyLaser extends Laser {
    public EnemyLaser(double d, double e) {
        super(d, e);
        try {
            beam = new Image("enemy/laser.png");
        } catch (SlickException e1) {
            // TODO Auto-generated catch block
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
