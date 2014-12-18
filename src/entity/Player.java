package entity;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.enemies.Enemy;
import app.MainClass;
import app.ResourceLoader;


public class Player extends GameObject implements KeyListener {
    private boolean collision;
	private int dx = 0;
	private int dy = 0;
	private int health, maxHealth;
	
	private BufferedImage ship;
	
	public boolean shooting = false;
	private ArrayList<Laser> lasers;

	public Player(int x, int y){
		super(x, y);
		
		width = 79;
		height = 68;
		
		collisionWidth = 58;
		collisionHeight = 70;
		
		speed = 3;
		maxHealth = health = 10;
		
		ship = ResourceLoader.getImage("/player/ship.png");
		
		lasers = new ArrayList<Laser>();
	}

	public void paint (Graphics g)
	{
		g.drawImage(ship, x, y, null);
		g.setColor(new Color((10 - health) * 25, health * 25, 0, 220));
		g.fillRect(x + (width - 50) / 2, y + height - 2, 5 * health, 5);
		
		for(Laser l:lasers)
			l.paint(g);
	}

	public void update(MainClass mc){
		if(collision){
			
		}
		
		x += dx;
		y += dy;	
		
		if(x < 0) x = 0;
		else if(x > 315 - width - 10) x = 315 - width - 10;
		
		if(y < 0) y = 0;
		else if(y > 600 - height) y = 600 - height;
		
		if(shooting)
		{
			lasers.add(new Laser(x + 4, y - 2));
			lasers.add(new Laser(x + 68, y - 2));
			
			shooting = false;
		}
		
		for(int i = 0; i < lasers.size(); ++i){
			lasers.get(i).update(mc);
			if(lasers.get(i).isDead()){
				lasers.remove(i--);
			}
		}
	}
	
	public void checkCollision(ArrayList<Enemy> enemies){
		for(int i = 0; i < enemies.size(); ++i){
			if(enemies.get(i).intersect(this)){
				collision = true;
				enemies.get(i).setHit();
			}
		}
	}
	
	public boolean checkAttack(ArrayList<Enemy> enemies){
		boolean hit = false;
		for(int i = 0; i < enemies.size(); ++i){
			for(int j = 0; j < lasers.size(); ++j){
				if(!lasers.get(j).isDead() && lasers.get(j).intersect(enemies.get(i))){
				   enemies.get(i).setHit();
				   lasers.get(j).setDead();
				   hit = true;
				}
			}
		}
		return hit;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:{
				dx = speed;
				break;
			}
			case KeyEvent.VK_LEFT:{
				dx = -speed;
				break;				
			}
			case KeyEvent.VK_UP:{
				dy = -speed;
				break;
			}					
			case KeyEvent.VK_DOWN:{
				dy= speed;
				break;
			}				
			case KeyEvent.VK_SPACE:{
				shooting = true;
				break;
			}		
		}
	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:{
				dx=0;
				break;		
			}
			case KeyEvent.VK_LEFT:{
				dx=0;
				break;
			}					
			case KeyEvent.VK_UP:{
				dy=0;
				break;
			}
			case KeyEvent.VK_DOWN:{
				dy=0;
				break;
			}
			
		}
			
	}
		
	public void keyTyped(KeyEvent arg0) {}

	public int getWidth() {
		return width;
	}
}
