package entity.enemies;

import entity.ControlledPlayer;
import entity.GameObject;

import java.util.ArrayList;

public abstract class Enemy extends GameObject {
    protected boolean collision;
    int health;

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
            if(!enemies.get(i).equals(this)){
                if(this.intersect(enemies.get(i))) collision = true;
            }
        }
    }

    public abstract void fire(ControlledPlayer controlledPlayer);
    public abstract void checkAttack(ControlledPlayer controlledPlayer);

    public void faceTo(GameObject o) {
        angle = -Math.atan2(o.getCenterX() - x, o.getCenterY() - y);
    }
}


