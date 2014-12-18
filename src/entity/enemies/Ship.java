package entity.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.annotation.Resource;

import app.MainClass;
import app.ResourceLoader;

public class Ship extends Enemy {
	BufferedImage skin;
	
	public Ship(int x, int y){
		super(x, y);
		
		collisionWidth = 70;
		width = 79;
		collisionHeight = 58;
		height = 68;
		speed = 1;
		
		skin = ResourceLoader.getImage("/enemy/ship.png");
		
		right = true;
		down = true;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(skin, x, y, width, height, null);
	}

	@Override
	public void update(MainClass mc) {
		if(right) x += speed;
		else if(left) x -= speed;
		if(down) y += speed;
		else if(up) y -= speed;
		
		if(x > mc.getWidth() - width){
			left = true;
			right = false;
		} else if(x < 0){
			right = true;
			left = false;
		}
		
		if(y > mc.getHeight() - height){
			up = true;
			down = false;
		} else if(y < 0){
			up = false;
			down = true;
		}
	}
}
