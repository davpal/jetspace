package entity.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Animation;
import app.MainClass;
import app.ResourceLoader;

public class Ship extends Enemy {
	BufferedImage skin;
	Animation explosion;
	
	public Ship(int x, int y){
		super(x, y);
		
		collisionWidth = 60;
		width = 79;
		collisionHeight = 48;
		height = 68;
		speed = 1;
		
		skin = ResourceLoader.getImage("/enemy/ship.png");
		explosion = ResourceLoader.getAnimation("/enemy/explosion.png", 64, 64, 5, 50);
		
		right = true;
		down = true;
	}

	public void paint(Graphics g) {
		if(hit) explosion.paint(g);
		else g.drawImage(skin, x, y, width, height, null);
	}
	
	public void setHit(){	
		explosion.setPosition(x, y);
		hit = true;
	}

	@Override
	public void update(MainClass mc) {
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
