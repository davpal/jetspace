package entity.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Animation;
import entity.EnemyLaser;
import entity.Laser;
import entity.Player;
import app.MainClass;
import app.ResourceLoader;

public class Ship extends Enemy {
	BufferedImage skin;
	Animation explosion;
	boolean crash;
	boolean shooting;
	int fireRate;
	
	ArrayList<EnemyLaser> lasers = new ArrayList<EnemyLaser>();
	private long shootTime;
	
	public Ship(int x, int y){
		super(x, y);
		
		collisionWidth = 60;
		width = 79;
		collisionHeight = 48;
		height = 68;
		speed = 1;
		health = 10;
		fireRate = 1000;
		
		skin = ResourceLoader.getImage("/enemy/ship.png");
		explosion = ResourceLoader.getAnimation("/enemy/explosion.png", 64, 64, 5, 50);
		
		right = true;
		down = true;
	}

	public void paint(Graphics g) {
		if(crash) explosion.paint(g);
		else {
			g.setColor(new Color((10 - health) * 25, health * 25, 0, 220));
			
			g.fillRect(x + (width - 50) / 2, y - 10, 5 * health, 5);
			g.drawImage(skin, x, y, width, height, null);
		}
		for(Laser l:lasers) l.paint(g);
	}
	
	public void setHit(){
        explosion.setPosition(x, y);
        hit = true;
	}

	public void fire(Player p){
		long elapsed = (System.nanoTime() - shootTime) / 1000000;
		if(elapsed > fireRate){
			if(x >= p.getX() && x <= p.getX() + p.getWidth() ||
				p.getX() >= x && p.getX() <= x + width)
				shooting = true;
			shootTime = System.nanoTime();
		}	
	}	
	
	@Override
	public void update(MainClass mc) {
		if(shooting){
			lasers.add(new EnemyLaser(x + 4, y + 30));
			lasers.add(new EnemyLaser(x + 68, y + 30));
			
			shooting = false;
		}
		
		for(int i = 0; i < lasers.size(); ++i){
			lasers.get(i).update(mc);
			if(lasers.get(i).isDead()){
				lasers.remove(i--);
			}
		}
		
		if(health <= 0){
			crash = true;
			explosion.update();
			if(explosion.hasPlayedOnce()){
				dead = true;
				hit = false;
			}
		}
		
		if(hit){
			health -= 1;
			hit = false;
		}
		
		if(collision){
			collision = false;
			
			if(right){
				left = true;
				right = false;
			} else if(left){
				left = false;
				right = true;
			}
			
			if(up){
				down = true;
				up = false;
			} else if(down){
				down = false;
				up = true;
			}
		}
		
		if(right) x += speed;
		else if(left) x -= speed;
		if(down) y += speed; 

		if(hit){
			explosion.update();
			if(explosion.hasPlayedOnce()){
				dead = true;
				hit = false;
			}
		}
		
		if(x > mc.getWidth() - width){
			left = true;
			right = false;
		} else if(x < 0){
			right = true;
			left = false;
		}
		
		if(y > 600) setDead();
	}

}
