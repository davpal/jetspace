package entity;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import app.MainClass;
import app.ResourceLoader;
public class Laser extends GameObject  {
	BufferedImage beam;
	int gunx, guny;
	
	Laser(int x, int y) {
		super(x, y);
		gunx = x;
		guny = y;
		collisionWidth = width = 8;
		collisionHeight = height = 30;
		speed = 6;
		
		beam = ResourceLoader.getImage("/player/laser.png");
	}

	public void paint (Graphics g){
		g.drawImage(beam, x, y, width, height, null);
	}

	public void update(MainClass mc){
		y -= speed;
		
		if(y < 0) dead = false;
	}
}
	
