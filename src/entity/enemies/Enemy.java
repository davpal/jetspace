package entity.enemies;

import entity.GameObject;
import entity.Player;

import java.util.ArrayList;

public abstract class Enemy extends GameObject {
    protected boolean collision;
    protected int health;

    Enemy(double x, double y, double a) {
        super(x, y, a);
    }

    public int getHealth(){
        return health;
    }
    
    public void setCollision() {
        collision = true;        
    }
    
    public void checkCollision(ArrayList<Enemy> enemies){
        for(int i = 0; i < enemies.size(); ++i){
            if(!enemies.get(i).equals(this) &&  this.intersect(enemies.get(i))) 
                collision = true;
        }
    }

    public abstract void fire(Player player);
    public abstract void checkAttack(Player player);

    public void faceTo(GameObject o) {
        angle = -Math.atan2(o.getCenterX() - x, o.getCenterY() - y);
    }
}


