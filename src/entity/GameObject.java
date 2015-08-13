package entity;
import java.awt.Rectangle;

import org.newdawn.slick.*;

public abstract class GameObject {
    protected double x, y, speed, angle;
    protected int width, height, collisionWidth, collisionHeight;
    protected boolean left, right, up, down;
    protected boolean dead;
    protected Animation animation;
    
    protected GameObject(double d, double e, double a){
        this.x = d;
        this.y = e;
        angle = a;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getCenterX(){
        return x + width / 2;
    }
    
    public double getCenterY(){
        return y + height / 2;
    }
    
    public double getWidth(){
        return width;
    }
    
    public double getHeight(){
        return height;
    }
    
    public double getAngle(){
        return angle;
    }
    
    public boolean isDead(){
        return dead;
    }
    
    public void setDead(){
        dead = true;
    }
    
    public boolean intersect(GameObject o){
        Rectangle a = new Rectangle(
                            (int)x, 
                            (int)y, 
                            (int)collisionWidth, 
                            (int)collisionHeight);
        Rectangle b = new Rectangle(
                            (int)o.x, 
                            (int)o.y, 
                            (int)o.collisionWidth, 
                            (int)o.collisionHeight);
        return a.intersects(b);
    }
   
    public abstract void update(GameContainer g);
    public abstract void render(org.newdawn.slick.Graphics g);
}
