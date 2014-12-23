package entity;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import app.Game;
import app.ResourceLoader;
public class Laser extends GameObject  {
	BufferedImage beam;
	
	Laser(double d, double e) {
		super(d, e);
		collisionWidth = width = 8;
		collisionHeight = height = 30;
		speed = 6;
		
		beam = ResourceLoader.getImage("/player/laser.png");
	}

	public void paint (Graphics g){
		g.drawImage(beam, (int)x, (int)y, width, height, null);
	}

	public void update(Game mc){
		y -= speed;
		
		if(y < 0) dead = false;
	}
}
	
