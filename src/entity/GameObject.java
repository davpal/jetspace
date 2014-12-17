package entity;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import app.MainClass;


public abstract class GameObject {
    protected int x, y, radius, speed;
    protected int width, height, collisionWidth, collisionHeight;
    protected boolean left, right, up, down;
	protected boolean dead;
    
    protected GameObject(int x, int y){
    	this.x = x;
    	this.y = y;
    }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void setDead(){
		dead = true;
	}
	
	public boolean intersect(GameObject o){
		Rectangle a = new Rectangle(
							x, 
							y, 
							collisionWidth, 
							collisionHeight);
		Rectangle b = new Rectangle(
							o.x, 
							o.y, 
							o.collisionWidth, 
							o.collisionHeight);
		return a.intersects(b);
	}
	
	public abstract void paint(Graphics g);

	public abstract void update(MainClass mc);
}
