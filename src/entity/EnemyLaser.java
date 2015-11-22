package entity;

import rendering.Renderer;

public class EnemyLaser extends Laser {
    public EnemyLaser(double x, double y, GameObject owner){
        super(x, y, owner);
        
        speed = 3; 
        angle -= Math.toRadians(180);
        dx = speed * Math.sin(angle);
        dy = - speed * Math.cos(angle);
    }
    
    public void render(Renderer r){
        r.renderEnemyLaser(this);
    }
}