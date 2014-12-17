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
		
		width = 79;
		height = 68;
		
		skin = ResourceLoader.getImage("/enemy/ship.png");
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(skin, x, y, width, height, null);
		
	}

	@Override
	public void update(MainClass mc) {
	}
}
