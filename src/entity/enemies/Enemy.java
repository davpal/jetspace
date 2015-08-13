package entity.enemies;

import java.util.ArrayList;

import entity.GameObject;
import entity.Player;

public abstract class Enemy extends GameObject  {
    public boolean hit, collision;
    int health;
    protected boolean crash;
    
    Enemy(double x, double y, double a) {
        super(x, y, a);
    }
    
    public void setHit(){
        hit = true;
    }

    public boolean isHit() {
        return hit;
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

    public abstract void fire(Player player);
    public abstract void checkAttack(Player player);

    public void setCrash() {
        health = 0;
        crash = true;
    }

    public boolean isCrash() {
        return crash;
    }
    
    public void faceTo(GameObject o){
        angle = -Math.atan2(o.getCenterX() - x, o.getCenterY() - y);
    }
}


