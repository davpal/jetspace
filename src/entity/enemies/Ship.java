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
		
		collisionWidth = width = 79;
		collisionHeight = height = 68;
		speed = 1;
		
		skin = ResourceLoader.getImage("/enemy/ship.png");
		
		right = true;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(skin, x, y, width, height, null);
	}

	@Override
	public void update(MainClass mc) {
		if(right) x += speed;
		else if(left) x -= speed;
		
		if(x > mc.getWidth() - width){
			left = true;
			right = false;
		} else if(x < 0){
			right = true;
			left = false;
		}
	}
}
